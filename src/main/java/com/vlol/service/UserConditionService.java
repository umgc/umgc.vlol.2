/**
 * UserCondition service class.
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

import com.vlol.model.UserCondition;
import com.vlol.repository.UserConditionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserConditionService {

  @Autowired private final UserConditionRepository userConditionRepository;

  @Autowired
  public UserConditionService(UserConditionRepository conditionRepository) {
    this.userConditionRepository = conditionRepository;
  }

  public List<UserCondition> getAllConditions() {
    return userConditionRepository.findAll();
  }

  public void saveCondition(UserCondition condition) {
    userConditionRepository.save(condition);
  }

  public UserCondition getCondition(Long conditionId) {
    return userConditionRepository.findById(conditionId).orElse(null);
  }

  public void deleteCondition(Long conditionId) {
    userConditionRepository.deleteByPK(conditionId);
  }
}
