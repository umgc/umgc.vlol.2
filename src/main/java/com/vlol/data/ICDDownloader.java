/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.data;

import com.vlol.model.Condition;
import com.vlol.service.ConditionService;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Calendar;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author marcuccm
 */
public class ICDDownloader {
    Integer limit = 1000;
    /**
     * Alternate query with id, preferred name, alternate name
     * PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0APREFIX%20meshv%3A%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%2Fvocab%23%3E%0ASELECT%20DISTINCT%20%3Fid%20%3Fpreferred%20%3Falt%0AFROM%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%3E%0AWHERE%20%7B%0A%3Fd%20a%20meshv%3ADescriptor%20.%0A%3Fd%20meshv%3Aidentifier%20%3Fid%20.%0A%3Fd%20meshv%3ApreferredTerm%20%3Fpt%20.%0A%3Fpt%20meshv%3AprefLabel%20%3Fpreferred%20.%0AOPTIONAL%7B%3Fpt%20meshv%3AaltLabel%20%3Falt%20.%7D%0A%3Fd%20meshv%3AtreeNumber%20%3Ftn%0AFILTER(REGEX(%3Ftn,%22C%22))%0A%7D%0AORDER%20BY%20%3Fpreferred
     */
    String query = "https://id.nlm.nih.gov/mesh/sparql?query=PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%0D%0APREFIX%20meshv%3A%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%2Fvocab%23%3E%0D%0ASELECT%20DISTINCT%20%3Fname%0D%0AFROM%20%3Chttp%3A%2F%2Fid.nlm.nih.gov%2Fmesh%3E%0D%0AWHERE%20%7B%0D%0A%3Fd%20a%20meshv%3ADescriptor%20.%0D%0A%3Fd%20rdfs%3Alabel%20%3Fname%20.%0D%0A%3Fd%20meshv%3AtreeNumber%20%3Ftn%0D%0AFILTER(REGEX(%3Ftn,%22C%22))%0D%0A%7D%0D%0AORDER%20BY%20%3Fname%0D%0A%20%0D%0A&format=JSON&inference=true&limit="+limit;
           
    private ConditionService conditionService;
    private EntityManager em;
    
    private final ScheduledExecutorService scheduler;
    public ICDDownloader(ConditionService conditionService, EntityManager em) {
        this.em = em;
        
        this.conditionService = conditionService;
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
                Boolean shouldUpdate = (Boolean)_em.createNativeQuery("SELECT last_updated <= curdate() - 7 FROM datasets WHERE name = 'icd'").getSingleResult();
                if(!shouldUpdate) return;
                _em.createNativeQuery("UPDATE datasets SET last_updated=curdate() WHERE name = 'icd'").executeUpdate();
            }catch(javax.persistence.NoResultException e){
                _em.createNativeQuery("INSERT INTO datasets (name, last_updated) VALUES('icd', curdate())").executeUpdate();
            }
            session.flush();
            session.clear();
            // Check next year and work back to find a valid file
            Integer nextYear = Calendar.getInstance().get(Calendar.YEAR)+1;
            for(Integer year = nextYear;year >= nextYear - 3; year--){
                try {
                    System.out.println("Start https://www.cms.gov/files/zip/"+year+
                            "-code-tables-and-index.zip download ~20mb"); 
                    HttpResponse zipResponse = download("https://www.cms.gov/files/zip/"+year+
                            "-code-tables-and-index.zip", HttpResponse.BodyHandlers.ofInputStream());

                    ZipInputStream zis = new ZipInputStream((InputStream)zipResponse.body());
                    ZipEntry zipEntry = zis.getNextEntry();
                    HashSet<String> conditionNameHasSeen = new HashSet<String>();

                    while (zipEntry != null) {
                        if(zipEntry.getName().endsWith("icd10cm_index_"+year+".xml")){
                            // TODO: Add back in to clear table on reload
                            conditionService.truncateConditions();
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                            Document doc = dBuilder.parse(zis);

                            NodeList nList = doc.getElementsByTagName("mainTerm");
                            for (int temp = 0; temp < nList.getLength(); temp++) {
                                Node nNode = nList.item(temp);
                                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eElement = (Element) nNode;
                                    NodeList title = eElement.getElementsByTagName("title");
                                    NodeList code = eElement.getElementsByTagName("code");
                                    if(title.getLength() > 0 && code.getLength() > 0){
                                        NodeList titleTextNode = title.item(0).getChildNodes();
                                        NodeList codeTextNode = code.item(0).getChildNodes();
                                        if(titleTextNode.getLength() > 0 && codeTextNode.getLength() > 0){
                                            Condition c = new Condition();
                                            // Should add the ID of the ICD code
                                            String titleText = titleTextNode.item(0).getTextContent().replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
                                            String codeText = codeTextNode.item(0).getTextContent();
                                            if(!conditionNameHasSeen.contains(titleText.toLowerCase())){
                                                conditionNameHasSeen.add(titleText.toLowerCase());
                                                c.setReferenceId(codeText);
                                                c.setConditionName(titleText);
                                                session.save(c);

                                                if (this.count++ % 100 == 0) { //100, same as the JDBC batch size
                                                    session.flush();
                                                    session.clear();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            // Stop if the file was found and extracted
                            break;
                        }
                        // Check for next file
                        zipEntry = zis.getNextEntry();
                    }
                    // Stop iterating through years if valid year was found
                    break;
                } catch (Exception ex) {
                    session.close();
                    ex.printStackTrace();
                }
            }


            session.flush();
            session.clear();
            tx.commit();
            session.close();

        }
    }
}
