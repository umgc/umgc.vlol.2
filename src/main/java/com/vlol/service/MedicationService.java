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
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.service;

import com.vlol.data.FDADownloader;
import com.vlol.model.Medication;
import com.vlol.repository.MedicationRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicationService {

    @Autowired
    private final MedicationRepository medicationRepository;

    @Autowired
    public MedicationService(MedicationRepository medicationRepository, EntityManager em) {
        this.medicationRepository = medicationRepository;
        new FDADownloader(this, em);
    }

    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public void saveMedication(Medication medication) {
        medicationRepository.save(medication);
    }

    public Medication getMedication(Long medicationId) {
        return medicationRepository.findById(medicationId).orElse(null);
    }

    public void deleteMedication(Long medicationId) {
        medicationRepository.deleteById(medicationId);
    }
    
    public void truncateMedication() {
        medicationRepository.deleteAllInBatch();
    }

    public List<Medication> findMedicationByKeyword(String keyword) {
        return medicationRepository.findMedicationByKeyword(keyword);
    }
}
