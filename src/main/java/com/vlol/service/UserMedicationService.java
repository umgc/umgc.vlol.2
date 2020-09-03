/**
 * UserMedication service class.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category  vlol
 * @package service
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.service;

import com.vlol.model.UserMedication;
import com.vlol.repository.UserMedicationRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserMedicationService {
    @Autowired
    private final UserMedicationRepository medicationRepository;

    @Autowired
    public UserMedicationService(UserMedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<UserMedication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public void saveMedication(UserMedication medication) {
        medicationRepository.save(medication);
    }
    public void truncateMedication() {
        medicationRepository.deleteAll();
    }

    public UserMedication getMedication(Long medicationID) {
        return medicationRepository.findById(medicationID).get();
    }

    public void deleteMedication(Long medicationID) {
        medicationRepository.deleteByPK(medicationID);
    }

    public List<UserMedication> findMedicationByKeyword(String keyword) {
        return medicationRepository.findMedicationByKeyword(keyword);
    }
    
    public List<UserMedication> findMedicationsByEmail(String email) {
        return medicationRepository.findMedicationByEmail(email);
    }
}
