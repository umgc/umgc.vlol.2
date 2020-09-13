/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.data;

import com.google.gson.Gson;
import com.vlol.model.Allergy;
import com.vlol.service.AllergyService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author marcuccm
 */
public class AllergyDownloader {
    Integer limit = 1000;
    String query = "https://id.nlm.nih.gov/mesh/sparql?query=PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX%20meshv%3A%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%2Fvocab%23%3E%0D%0APREFIX%20mesh%3A%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%2F%3E%0D%0A%0D%0ASELECT%20DISTINCT%20%3Fid%20%3Flabel%0D%0AFROM%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%3E%0D%0AWHERE%20%7B%0D%0A%20%20mesh%3AD006967%20meshv%3AtreeNumber%20%3FtreeNum%20.%0D%0A%20%20%3FchildTreeNum%20meshv%3AparentTreeNumber%2B%20%3FtreeNum%20.%0D%0A%20%20%3Fdescriptor%20meshv%3AtreeNumber%20%3FchildTreeNum%20.%0D%0A%20%20%3Fdescriptor%20meshv%3Aidentifier%20%3Fid%20.%0D%0A%20%20%3Fdescriptor%20rdfs%3Alabel%20%3Flabel%20.%0D%0A%20%20FILTER%20(%3Fid%20NOT%20IN%20(%27D004342%27%2C%20%27D063926%27%2C%20%27D003875%27%2C%20%27D006968%27%2C%20%27D006969%27%2C%20%27D018876%27%2C%20%27D007105%27))%0D%0A%7D%0D%0AORDER%20BY%20%3Flabel%0D%0A%0D%0A&format=JSON&inference=false&limit="+limit;
           
    private AllergyService allergyService;
    private EntityManager em;
    
    private final ScheduledExecutorService scheduler;
    public AllergyDownloader(AllergyService allergyService, EntityManager em) {
        this.em = em;
        
        this.allergyService = allergyService;
        scheduler = Executors.newScheduledThreadPool(1);
        Runnable download = new Download();
        scheduler.schedule(download, 1, TimeUnit.DAYS);
        download.run();
    }
    private HttpResponse download(String url, HttpResponse.BodyHandler handler) throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        return client.send(request, handler);
    }

    private final class Download implements Runnable {
        Integer count = 0;

        @Override
        public void run() {
            EntityManager _em = em.getEntityManagerFactory().createEntityManager();
            Session session = _em.unwrap(Session.class);
            Transaction tx = session.beginTransaction();
            
            // Check if data was updated in the last 7 days
            try{                
                Boolean shouldUpdate = (Boolean)_em.createNativeQuery("SELECT last_updated <= curdate() - 7 FROM datasets WHERE name = 'mesh-al'").getSingleResult();
//                if(!shouldUpdate) return;
                _em.createNativeQuery("UPDATE datasets SET last_updated=curdate() WHERE name = 'mesh-al'").executeUpdate();
            }catch(javax.persistence.NoResultException e){
                _em.createNativeQuery("INSERT INTO datasets (name, last_updated) VALUES('mesh-al', curdate())").executeUpdate();
            }
            session.flush();
            session.clear();
            
            Integer offset = 0;
            try {
                HttpResponse response = download(query+"&offset="+offset, HttpResponse.BodyHandlers.ofString());
                List<Map> dataList = (List<Map>)((Map)new Gson().fromJson(response.body().toString(), Map.class).get("results")).get("bindings");
                // TODO: Add back in to clear table on reload
                if(dataList.size() > 0) allergyService.truncateAllergies();
                
                        System.out.println(dataList);
                while(dataList.size() > 0){
                    dataList.forEach((obj)->{
                        Allergy c = new Allergy();
                        System.out.println(obj);
                        String allergyName = (String)((Map)obj.get("label")).get("value");
                        String allergyId = (String)((Map)obj.get("id")).get("value");
                        if(allergyName != null) allergyName = allergyName.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
                        c.setAllergyName(allergyName);
                        c.setReferenceId(allergyId);
                        session.save(c);

                        if (this.count++ % 100 == 0) { //100, same as the JDBC batch size
                            session.flush();
                            session.clear();
                        }
                    });
                    if(dataList.size() < limit) break;
                    offset += limit;
                    response = download(query+"&offset="+offset, HttpResponse.BodyHandlers.ofString());
                    dataList = (List<Map>)((Map)new Gson().fromJson(response.body().toString(), Map.class).get("results")).get("bindings");
                }
                session.flush();
                session.clear();
                tx.commit();
                session.close();
            } catch (Exception ex) {
                session.close();
                ex.printStackTrace();
            }

        }
    }
}
