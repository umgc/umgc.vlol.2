/**
 * Condition service class.
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

import com.vlol.model.Condition;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vlol.repository.ConditionRepository;

@Service
@Transactional
public class ConditionService {

    @Autowired
    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public void saveCondition(Condition condition) {
        conditionRepository.save(condition);
    }

    public Condition getCondition(long conditionID) {
        return conditionRepository.findById(conditionID).get();
    }

    public void deleteCondition(long conditionID) {
        conditionRepository.deleteById(conditionID);
    }

    public List<Condition> findConditionByKeyword(String keyword) {
        return conditionRepository.findConditionByKeyword(keyword);
    }
}
