/**
 * Allergy Class.
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "allergy")
public class Allergy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Long allergyID;

    @Column(name = "allergy_name", length = 50, unique = true)
    @NotBlank(message = "Allergy name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String allergyName;

    @ManyToMany(mappedBy = "allergies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    public Long getAllergyID() {
        return allergyID;
    }

    public void setAllergyID(Long allergyID) {
        this.allergyID = allergyID;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
    Override the toString() method so allergy names will be shown in the form. And equals() and hashCode() must be overridden so Spring MVC and Thymeleaf will show the check marks correctly when the form is in edit mode. Thanks to https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
     */

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.allergyName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((allergyID == null) ? 0 : allergyID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Allergy other = (Allergy) obj;
        if (allergyID == null) {
            if (other.allergyID != null) {
                return false;
            }
        } else if (!allergyID.equals(other.allergyID)) {
            return false;
        }
        return true;
    }
}
