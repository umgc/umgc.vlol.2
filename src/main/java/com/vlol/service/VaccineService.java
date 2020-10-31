/**
 * Vaccine service class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package service
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.service;

import com.vlol.data.VaccineDownloader;
import com.vlol.model.Vaccine;
import com.vlol.repository.VaccineRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VaccineService {

  @Autowired private final VaccineRepository vaccineRepository;

  @Autowired
  public VaccineService(VaccineRepository vaccineRepository, EntityManager em) {
    this.vaccineRepository = vaccineRepository;
    new VaccineDownloader(this, em);
  }

  public List<Vaccine> getAllAllergies() {
    return vaccineRepository.findAll();
  }

  public void saveVaccine(Vaccine allergy) {
    vaccineRepository.save(allergy);
  }

  public Vaccine getVaccine(Long allergyId) {
    return vaccineRepository.findById(allergyId).orElse(null);
  }

  public void deleteVaccine(Long allergyId) {
    vaccineRepository.deleteById(allergyId);
  }

  public void truncateVaccine() {
    vaccineRepository.deleteAllInBatch();
  }

  public List<Vaccine> findVaccineByKeyword(String keyword) {
    return vaccineRepository.findVaccineByKeyword(keyword);
  }
}
