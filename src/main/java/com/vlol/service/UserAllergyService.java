/**
 * UserAllergy service class.
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

import com.vlol.model.UserAllergy;
import com.vlol.repository.UserAllergyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAllergyService {

    @Autowired
    private final UserAllergyRepository allergyRepository;

    @Autowired
    public UserAllergyService(UserAllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }

    public List<UserAllergy> getAllAllergies() {
        return allergyRepository.findAll();
    }

    public void saveAllergy(UserAllergy allergy) {
        allergyRepository.save(allergy);
    }

    public UserAllergy getAllergy(Long allergyId) {
        return allergyRepository.findById(allergyId).orElse(null);
    }

    public void deleteAllergy(Long allergyId) {
        allergyRepository.deleteById(allergyId);
    }
}
