/**
 * AdvanceDirective Repository Interface.
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
 * @package repository
 */
package com.vlol.repository;

import com.vlol.model.AdvanceDirective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdvanceDirectiveRepository extends JpaRepository<AdvanceDirective, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM AdvanceDirective m WHERE m.id = :id")
    public void deleteByPK(@Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query(value = "DELETE FROM User_AdvanceDirective m WHERE m.user_id = :id", nativeQuery=true)
    public void deleteByUserId(@Param("id") Long id);
}
