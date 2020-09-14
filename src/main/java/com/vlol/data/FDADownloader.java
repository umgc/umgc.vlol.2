/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.data;

import com.google.gson.Gson;
import com.vlol.model.Medication;
import com.vlol.service.MedicationService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author marcuccm
 */
public class FDADownloader {

    private MedicationService medicationService;
    private EntityManager em;
    private List<Pattern> badWords = new ArrayList<Pattern>(List.of(
            Pattern.compile("sunscreen"), 
            Pattern.compile("hand (sanitizer|gel|cleaner|wash)"),
            Pattern.compile("(disinfect|wet|alcohol|sanitiz).+(wipe|tissue|hand|swab)"),
            Pattern.compile("antiperspirant"),
            Pattern.compile("lip.+balm|balm.+lip"),
            Pattern.compile("^([0-9]+% )?alcohol$"),
            Pattern.compile("^([0-9]+% )?ethyl alcohol$"),
            Pattern.compile("^([0-9]+% )?salicylic acid$"),
            Pattern.compile("^([0-9]+% )?isopropyl alcohol$"),
            Pattern.compile("toothpaste")));
    
    private final ScheduledExecutorService scheduler;
    public FDADownloader(MedicationService medicationService, EntityManager em) {
        this.em = em;
        
        this.medicationService = medicationService;
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
                Boolean shouldUpdate = (Boolean)_em.createNativeQuery("SELECT last_updated <= curdate() - 7 FROM datasets WHERE name = 'fda'").getSingleResult();
                if(!shouldUpdate) return;
                _em.createNativeQuery("UPDATE datasets SET last_updated=curdate() WHERE name = 'fda'").executeUpdate();
            }catch(javax.persistence.NoResultException e){
                _em.createNativeQuery("INSERT INTO datasets (name, last_updated) VALUES('fda', curdate())").executeUpdate();
            }
            session.flush();
            session.clear();
//            return;
            try {
                HttpResponse response = download("https://api.fda.gov/download.json", HttpResponse.BodyHandlers.ofString());
    
                Map obj = (Map)new Gson().fromJson(response.body().toString(), Map.class).get("results");
                List <Map<String, String>> partitions = (List <Map<String, String>>)((Map)((Map)obj.get("drug")).get("ndc")).get("partitions");
                String exportDate = (String)((Map)((Map)obj.get("drug")).get("ndc")).get("export_date");
                HashSet<String> brandNameSeen = new HashSet<String>();
                medicationService.truncateMedication();
                for (Map<String, String> part : partitions){
                    HttpResponse zipResponse = download(part.get("file"), HttpResponse.BodyHandlers.ofInputStream());
                    
                    System.out.println("Start "+part.get("file")+" download ~25mb"); 
                    ZipInputStream zis = new ZipInputStream((InputStream)zipResponse.body());
                    ZipEntry zipEntry = zis.getNextEntry();
                    while (zipEntry != null) {
                        List<Map> res = (List)new Gson().fromJson(new BufferedReader(new InputStreamReader(zis)), Map.class).get("results");
                        res.forEach((drug)->{
                            String type = (String)drug.get("product_type");
                            String brandName = (String)drug.get("brand_name");
                            String genericName = (String)drug.get("generic_name");
                            String ndc = (String)drug.get("product_ndc");
                            
                            String dosageForm = (String)drug.get("dosage_form");
                            HashSet activeIngredients = new HashSet();
                            if(drug.get("active_ingredients") != null)
                                ((List<Map>)drug.get("active_ingredients")).forEach((ing)->{
                                    activeIngredients.add(((String)ing.get("name")).toLowerCase());
                                });
                            String drugAction = "";
                            HashSet pharmaClasses = new HashSet();
                            if(drug.get("pharm_class") != null){
                                pharmaClasses = new HashSet((List)drug.get("pharm_class"));
                                Object[] array = pharmaClasses.toArray();
                                drugAction = String.join(", ", Arrays.copyOf(array, array.length,  String[].class));
                            }
                            if(brandName == null)
                                brandName = genericName;
                            
                            if(ndc != null) ndc = ndc.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
                            if(genericName != null) genericName = genericName.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
                            if(brandName != null) brandName = brandName.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
                            if(drugAction != null) drugAction = drugAction.replaceAll("[^A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]", "");
                            
                            Boolean skip = false;
                            for(Pattern regexp : badWords){
                                if(regexp.matcher(brandName.toLowerCase()).find())skip = true;
                                if(genericName != null && regexp.matcher(genericName.toLowerCase()).find())skip = true;
                            };
                            if(!skip && brandName != null
                                    && (type.equals("HUMAN PRESCRIPTION DRUG") || type.equals("HUMAN OTC DRUG")) 
                                    && !brandNameSeen.contains(brandName.toLowerCase()) 
                                    && (brandName.length() < 256)
                                    && (genericName == null || genericName.length() < 256)
                                    && (dosageForm == null || dosageForm.length() < 256)){
                                Medication m = new Medication();
                                if(brandName != null && brandName.toUpperCase().equals(brandName)){
                                    brandName = convertToTitleCase(brandName);
                                }
                                if(genericName != null && genericName.toUpperCase().equals(genericName)){
                                    genericName = convertToTitleCase(genericName);
                                }
                                brandNameSeen.add(brandName.toLowerCase());
                                m.setControlled(type.equals("HUMAN PRESCRIPTION DRUG"));
                                m.setGenericName(genericName);
                                m.setBrandName(brandName);
                                m.setDrugAction(drugAction);
                                m.setReferenceId(ndc);
                                m.setBloodThinner(
                                        pharmaClasses.contains("Vitamin K Inhibitors [MoA]") || 
                                        pharmaClasses.contains("Vitamin K Antagonist [EPC]") || 
                                        pharmaClasses.contains("Platelet Aggregation Inhibitor [EPC]") || 
                                        pharmaClasses.contains("Decreased Platelet Aggregation [PE]") ||
                                        activeIngredients.contains("aspirin"));
                                
                                session.save(m);
                                
                                if (this.count++ % 100 == 0) { //100, same as the JDBC batch size
                                    session.flush();
                                    session.clear();
                                }
                            }
                        });
                        zipEntry = zis.getNextEntry();
                    }
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
    public static String convertToTitleCase(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        return Arrays
          .stream(text.split(" "))
          .map(word -> word.isEmpty()
            ? word
            : Character.toTitleCase(word.charAt(0)) + word
              .substring(1)
              .toLowerCase())
          .collect(Collectors.joining(" "));
    }
}
