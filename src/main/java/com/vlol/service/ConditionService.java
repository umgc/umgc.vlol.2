/**
 * Condition service class.
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

import com.vlol.data.ICDDownloader;
import com.vlol.model.Condition;
import com.vlol.repository.ConditionRepository;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ConditionService {

  @Autowired private final ConditionRepository conditionRepository;

  @Autowired
  public ConditionService(ConditionRepository conditionRepository, EntityManager em) {
    this.conditionRepository = conditionRepository;
    new ICDDownloader(this, em);
  }

  public List<Condition> getAllConditions() {
    return conditionRepository.findAll();
  }

  public void saveCondition(Condition condition) {
    conditionRepository.save(condition);
  }

  public Condition getCondition(Long conditionId) {
    return conditionRepository.findById(conditionId).orElse(null);
  }

  public void deleteCondition(Long conditionId) {
    conditionRepository.deleteById(conditionId);
  }

  public void truncateConditions() {
    conditionRepository.deleteAllInBatch();
  }

  public List<Condition> findConditionByKeyword(String keyword) {
    return conditionRepository.findConditionByKeyword(keyword);
  }
}
