/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.data;

import com.vlol.model.Vaccine;
import com.vlol.service.VaccineService;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

/** @author marcuccm */
public class VaccineDownloader {

  private VaccineService vaccineService;
  private EntityManager em;
  private List<Pattern> replacePatterns =
      new ArrayList<Pattern>(
          List.of(
              Pattern.compile(" (vaccination|vaccine) (given|done)", Pattern.CASE_INSENSITIVE),
              Pattern.compile("(vaccination|vaccine) for ", Pattern.CASE_INSENSITIVE),
              Pattern.compile(" vaccination")));
  private List<Pattern> badPatterns =
      new ArrayList<Pattern>(
          List.of(
              Pattern.compile("prophylactic", Pattern.CASE_INSENSITIVE),
              Pattern.compile("vaccination", Pattern.CASE_INSENSITIVE),
              Pattern.compile("\\bneeds\\b", Pattern.CASE_INSENSITIVE)));

  private final ScheduledExecutorService scheduler;

  public VaccineDownloader(VaccineService vaccineService, EntityManager em) {
    this.em = em;

    this.vaccineService = vaccineService;
    scheduler = Executors.newScheduledThreadPool(1);
    Runnable download = new Download();
    scheduler.schedule(download, 1, TimeUnit.DAYS);
    download.run();
  }

  private HttpResponse download(String url, HttpResponse.BodyHandler handler)
      throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
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
      try {
        Boolean shouldUpdate =
            (Boolean)
                _em.createNativeQuery(
                        "SELECT last_updated <= curdate() - 7 FROM datasets WHERE name = 'vac'")
                    .getSingleResult();
        if (!shouldUpdate) return;
        _em.createNativeQuery("UPDATE datasets SET last_updated=curdate() WHERE name = 'vac'")
            .executeUpdate();
      } catch (javax.persistence.NoResultException e) {
        _em.createNativeQuery("INSERT INTO datasets (name, last_updated) VALUES('vac', curdate())")
            .executeUpdate();
      }
      session.flush();
      session.clear();
      HashSet<String> hasSeen = new HashSet<String>();
      try {
        HttpResponse response =
            download(
                "https://www.icd10data.com/ICD10CM/Codes/Z00-Z99/Z20-Z29/Z23-/Z23",
                HttpResponse.BodyHandlers.ofString());
        Pattern listPatten =
            Pattern.compile(
                "synonyms\\s*</[a-z]+>\\s*<ul>(.*?)</ul>",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Pattern listElementPattern = Pattern.compile("<li>(.*?)</li>");
        String html = response.body().toString();
        Matcher m = listPatten.matcher(html);
        if (m.find()) {
          vaccineService.truncateVaccine();
          String synonymList = m.group(1);
          Matcher lm = listElementPattern.matcher(synonymList);
          while (lm.find()) {
            String name = lm.group(1);

            Boolean skip = false;
            for (Pattern regexp : replacePatterns) {
              name = regexp.matcher(name).replaceAll("");
            }
            ;
            for (Pattern regexp : badPatterns) {
              if (regexp.matcher(name.toLowerCase()).find()) skip = true;
            }
            ;
            if (skip) continue;
            if (!hasSeen.contains(name.toLowerCase())) {
              Vaccine v = new Vaccine();
              hasSeen.add(name.toLowerCase());
              name = convertToTitleCase(name);

              v.setVaccineName(name);
              session.save(v);

              if (this.count++ % 100 == 0) { // 100, same as the JDBC batch size
                session.flush();
                session.clear();
              }
            }
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
    return Arrays.stream(text.split(" "))
        .map(
            word ->
                word.isEmpty()
                    ? word
                    : Character.toTitleCase(word.charAt(0)) + word.substring(1).toLowerCase())
        .collect(Collectors.joining(" "));
  }
}
