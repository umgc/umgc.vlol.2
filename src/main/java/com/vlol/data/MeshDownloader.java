///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vlol.data;
//
//import com.google.gson.Gson;
//import com.vlol.model.Condition;
//import com.vlol.model.Medication;
//import com.vlol.service.ConditionService;
//import com.vlol.service.MedicationService;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//import java.util.concurrent.TimeUnit;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import javax.persistence.EntityManager;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;
//import org.hibernate.service.ServiceRegistry;
//import org.springframework.data.jpa.provider.HibernateUtils;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// *
// * @author marcuccm
// */
//public class MeshDownloader {
//    Integer limit = 1000;
//    /**
//     * Alternate query with id, preferred name, alternate name
//     * PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20meshv%3A%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%2Fvocab%23%3E%0ASELECT%20DISTINCT%20%3Fid%20%3Fpreferred%20%3Falt%0AFROM%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%3E%0AWHERE%20%7B%0A%3Fd%20a%20meshv%3ADescriptor%20.%0A%3Fd%20meshv%3Aidentifier%20%3Fid%20.%0A%3Fd%20meshv%3ApreferredTerm%20%3Fpt%20.%0A%3Fpt%20meshv%3AprefLabel%20%3Fpreferred%20.%0AOPTIONAL%7B%3Fpt%20meshv%3AaltLabel%20%3Falt%20.%7D%0A%3Fd%20meshv%3AtreeNumber%20%3Ftn%0AFILTER(REGEX(%3Ftn,%22C%22))%0A%7D%0AORDER%20BY%20%3Fpreferred
//     */
//    String query = "https://id.nlm.nih.gov/mesh/sparql?query=PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX%20meshv%3A%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%2Fvocab%23%3E%0D%0ASELECT%20DISTINCT%20%3Fname%0D%0AFROM%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%3E%0D%0AWHERE%20%7B%0D%0A%3Fd%20a%20meshv%3ADescriptor%20.%0D%0A%3Fd%20rdfs%3Alabel%20%3Fname%20.%0D%0A%3Fd%20meshv%3AtreeNumber%20%3Ftn%0D%0AFILTER(REGEX(%3Ftn,%22C%22))%0D%0A%7D%0D%0AORDER%20BY%20%3Fname%0D%0A%20%0D%0A&format=JSON&inference=true&limit="+limit;
//           
//    private ConditionService conditionService;
//    private EntityManager em;
//    
//    private final ScheduledExecutorService scheduler;
//    public MeshDownloader(ConditionService conditionService, EntityManager em) {
//        this.em = em;
//        
//        this.conditionService = conditionService;
//        scheduler = Executors.newScheduledThreadPool(1);
//        Runnable download = new Download();
//        scheduler.schedule(download, 1, TimeUnit.DAYS);
//        download.run();
//    }
//    private HttpResponse download(String url, HttpResponse.BodyHandler handler) throws IOException, InterruptedException{
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(url))
//                .build();
//        return client.send(request, handler);
//    }
//
//    private final class Download implements Runnable {
//        Integer count = 0;
//
//        @Override
//        public void run() {
//            EntityManager _em = em.getEntityManagerFactory().createEntityManager();
//            Session session = _em.unwrap(Session.class);
//            Transaction tx = session.beginTransaction();
//            
//            // Check if data was updated in the last 7 days
//            try{                
//                Boolean shouldUpdate = (Boolean)_em.createNativeQuery("SELECT last_updated <= curdate() - 7 FROM datasets WHERE name = 'mesh'").getSingleResult();
//                if(!shouldUpdate) return;
//                _em.createNativeQuery("UPDATE datasets SET last_updated=curdate() WHERE name = 'mesh'").executeUpdate();
//            }catch(javax.persistence.NoResultException e){
//                _em.createNativeQuery("INSERT INTO datasets (name, last_updated) VALUES('mesh', curdate())").executeUpdate();
//            }
//            session.flush();
//            session.clear();
//            
//            Integer offset = 0;
//            try {
//                HttpResponse response = download(query+"&offset="+offset, HttpResponse.BodyHandlers.ofString());
//                List<Map> dataList = (List<Map>)((Map)new Gson().fromJson(response.body().toString(), Map.class).get("results")).get("bindings");
//                if(dataList.size() > 0) conditionService.truncateConditions();
//                
//                while(dataList.size() > 0){
//                    dataList.forEach((obj)->{
//                        Condition c = new Condition();
//                        String conditionName = (String)((Map)obj.get("name")).get("value");
//                        if(conditionName != null) conditionName = conditionName.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
//                        c.setConditionName(conditionName);
//                        session.save(c);
//
//                        if (this.count++ % 100 == 0) { //100, same as the JDBC batch size
//                            session.flush();
//                            session.clear();
//                        }
//                    });
//                    if(dataList.size() < limit) break;
//                    offset += limit;
//                    response = download(query+"&offset="+offset, HttpResponse.BodyHandlers.ofString());
//                    dataList = (List<Map>)((Map)new Gson().fromJson(response.body().toString(), Map.class).get("results")).get("bindings");
//                }
//                session.flush();
//                session.clear();
//                tx.commit();
//                session.close();
//            } catch (Exception ex) {
//                session.close();
//                ex.printStackTrace();
//            }
//
//        }
//    }
//}
