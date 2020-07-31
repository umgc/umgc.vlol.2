/**
 * Medication service class.
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

import com.vlol.model.Medication;
import com.vlol.repository.MedicationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicationService {

    @Autowired
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public void saveMedication(Medication medication) {
        medicationRepository.save(medication);
    }

    public Medication getMedication(Long medicationID) {
        return medicationRepository.findById(medicationID).orElse(null);
    }

    public void deleteMedication(Long medicationID) {
        medicationRepository.deleteById(medicationID);
    }

    public List<Medication> findMedicationByKeyword(String keyword) {
        return medicationRepository.findMedicationByKeyword(keyword);
    }
}
