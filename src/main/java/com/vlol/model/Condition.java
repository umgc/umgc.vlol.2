/**
 * Medical Condition Class.
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
@Table(name = "illness")
public class Condition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "illness_id")
    private Long conditionID;

    @Column(name = "illness_name", length = 50, unique = true)
    @NotBlank(message = "Condition name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String conditionName;

    @ManyToMany(mappedBy = "conditions", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    public Long getConditionID() {
        return conditionID;
    }

    public void setConditionID(Long conditionID) {
        this.conditionID = conditionID;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
    Override the toString() method so condition names will be shown in the form. And equals() and hashCode() must be overridden so Spring MVC and Thymeleaf will show the check marks correctly when the form is in edit mode. Thanks to https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
     */

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.conditionName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((conditionID == null) ? 0 : conditionID.hashCode());
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
        Condition other = (Condition) obj;
        if (conditionID == null) {
            if (other.conditionID != null) {
                return false;
            }
        } else if (!conditionID.equals(other.conditionID)) {
            return false;
        }
        return true;
    }
    
    public String getIdAsString() {
        return conditionID.toString();
    }
}
