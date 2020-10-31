/**
 * UserMedication Repository Interface.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package repository
 * @license https://opensource.org/licenses/MIT The MIT License
 */
package com.vlol.repository;

import com.vlol.model.AuthorizedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AuthorizedUserRepository extends JpaRepository<AuthorizedUser, Long> {

  @Transactional
  @Modifying
  @Query(value = "DELETE FROM AuthorizedUser au WHERE au.id = :id")
  public void deleteByPK(@Param("id") Long id);
}
