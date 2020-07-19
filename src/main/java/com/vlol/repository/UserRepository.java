/**
 * User Repository Interface.
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

import com.vlol.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);

    public User findUserByUsername(String username);

    @Query(value = "SELECT u FROM User u WHERE lower(u.firstName) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.lastName) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.SSN) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.streetAddress) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.city) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.state) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.zipCode) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.phone) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.email) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.insCompany) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.insPolicyNo) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.advDirType) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.doctorName) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.doctorPhone) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.userComments) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.username) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.securityQuestion) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.adminComments) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.DOB) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.dateCreated) LIKE lower(concat('%', :keyword, '%'))"
            + "OR lower(u.lastLoginDate) LIKE lower(concat('%', :keyword, '%'))"
    )
    public List<User> findUserByKeyword(@Param("keyword") String keyword);
}
