/**
 * Authorized User Class.
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
 * @package model
 * @author Rob Garcia <rgarcia92@student.umgc.edu>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.model;

import java.io.Serializable;
import java.util.HashSet;
import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "authorized_user")
public class AuthorizedUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authorized_user_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    private Long authorizedUserId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "authorized_email", length = 320)
    @NotBlank(message = "Email is required.")
    // Check if text is valid per RFC 3986.
    @Email(message = "Invalid email address.")
    // Check if length is valid per RFC 3986.
    @Size(min = 5, max = 320, message = "Input exceeds size limits.")
    private String authorizedEmail;
    
    @Column(name = "authorized_name", length = 128)
    // Check if text is valid per RFC 3986.
    @Size(max = 128, message = "Input exceeds size limits.")
    private String authorizedName;
    

    public Long getAuthorizedUserId() {
        return authorizedUserId;
    }

    public void setAuthorizedUserId(Long authorizedUserId) {
        this.authorizedUserId = authorizedUserId;
    }

    public String getEmail() {
        return authorizedEmail;
    }

    public void setEmail(String authorizedEmail) {
        this.authorizedEmail = authorizedEmail;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Override the toString() method so condition names will be shown in the
     * form. And equals() and hashCode() must be overridden so Spring MVC and
     * Thymeleaf will show the check marks correctly when the form is in edit
     * mode. Thanks to
     * https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
     */
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.authorizedEmail;
    }

}
