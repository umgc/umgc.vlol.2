/**
 * Allergy service class.
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

import com.vlol.data.AllergyDownloader;
import com.vlol.model.Allergy;
import com.vlol.repository.AllergyRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllergyService {

  @Autowired private final AllergyRepository allergyRepository;

  @Autowired
  public AllergyService(AllergyRepository allergyRepository, EntityManager em) {
    this.allergyRepository = allergyRepository;
    new AllergyDownloader(this, em);
  }

  public List<Allergy> getAllAllergies() {
    return allergyRepository.findAll();
  }

  public void saveAllergy(Allergy allergy) {
    allergyRepository.save(allergy);
  }

  public Allergy getAllergy(Long allergyId) {
    return allergyRepository.findById(allergyId).orElse(null);
  }

  public void deleteAllergy(Long allergyId) {
    allergyRepository.deleteById(allergyId);
  }

  public void truncateAllergies() {
    allergyRepository.deleteAllInBatch();
  }

  public List<Allergy> findAllergyByKeyword(String keyword) {
    return allergyRepository.findAllergyByKeyword(keyword);
  }
}
