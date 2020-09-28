package com.vlol.repository;

import com.vlol.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userInfoRepository")
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM user_info m WHERE m.user_id = :id", nativeQuery = true)
  public void deleteByUserId(@Param("id") Long id);
}
