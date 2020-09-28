/**
 * UserVaccine service class.
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

import com.vlol.model.UserVaccine;
import com.vlol.repository.UserVaccineRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserVaccineService {

  @Autowired private final UserVaccineRepository vaccineRepository;

  @Autowired
  public UserVaccineService(UserVaccineRepository vaccineRepository) {
    this.vaccineRepository = vaccineRepository;
  }

  public List<UserVaccine> getAllVaccines() {
    return vaccineRepository.findAll();
  }

  public void saveVaccine(UserVaccine vaccine) {
    vaccineRepository.save(vaccine);
  }

  public UserVaccine getVaccine(Long vaccineId) {
    return vaccineRepository.findById(vaccineId).orElse(null);
  }

  public void deleteVaccine(Long vaccineId) {
    vaccineRepository.deleteByPK(vaccineId);
  }
}
