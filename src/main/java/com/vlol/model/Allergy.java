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
import javax.persistence.*;
import java.util.Set;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "allergy")
public class Allergy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "allergy_id")
    private Long allergyID;

    @Column(name = "allergy_name", unique=true)
    @NotBlank(message = "Allergy name is required.")
    private String allergyName;
    
    @ManyToMany(mappedBy = "allergies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set <User> users;

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
}
