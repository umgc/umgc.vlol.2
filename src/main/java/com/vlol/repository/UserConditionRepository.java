package com.vlol.repository;

import com.vlol.model.UserCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserConditionRepository extends JpaRepository<UserCondition, Long> {
  @Transactional
  @Modifying
  @Query(value = "DELETE FROM UserCondition m WHERE m.id = :id")
  public void deleteByPK(@Param("id") Long id);

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM User_Condition m WHERE m.user_id = :id", nativeQuery = true)
  public void deleteByUserId(@Param("id") Long id);
}
