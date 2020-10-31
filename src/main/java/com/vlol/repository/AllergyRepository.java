/**
 * Allergy Repository Interface.
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

import com.vlol.model.Allergy;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<Allergy, Long> {

  @Query(
      value =
          "SELECT a FROM Allergy a WHERE lower(a.allergyName) LIKE lower(concat(:keyword, '%'))"
              + " OR lower(a.allergyName) LIKE lower(concat('% ', :keyword, '%'))"
              + " ORDER BY CASE WHEN lower(a.allergyName) LIKE lower(concat(:keyword, '%')) THEN 1 ELSE 2 END")
  public List<Allergy> findAllergyByKeyword(@Param("keyword") String keyword);
}
