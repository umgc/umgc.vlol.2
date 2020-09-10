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

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String email);

    @Query(value = "SELECT u FROM User u, UserInfo ui"
            +" WHERE lower(u.firstName) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(u.lastName) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.SSN) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.streetAddress) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.city) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.state) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.zipCode) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.phone) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.insCompany) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.insPolicyNo) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(ui.doctorName) LIKE lower(concat('%', :keyword, '%'))"
            + " OR lower(u.email) LIKE lower(concat('%', :keyword, '%'))"
    )
    public List<User> findUserByKeyword(@Param("keyword") String keyword);
    
    @Query(value = "SELECT u FROM User u WHERE u.role.roleId = 1")
    public List<User> findAllParticipants();
    
    @Query(value = "SELECT u.* FROM authorized_user au INNER JOIN appuser u USING(user_id) WHERE au.authorized_email = :email", nativeQuery=true) // Assume email is always lower case
    public List<User> findAuthorizingUsers(@Param("email") String email);
}
