/**
 * Role Repository Interface.
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
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.repository;

import com.vlol.model.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByTitle(String title);

    @Query(value = "SELECT r FROM Role r WHERE lower(r.accessLevel) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(r.title) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(r.description) LIKE lower(concat('%', :keyword, '%'))")
    public List<Role> search(@Param("keyword") String keyword);
}
