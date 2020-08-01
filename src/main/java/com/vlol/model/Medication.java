/**
 * Medication Class.
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    private Long medicationID;

    @Column(name = "brand_name", length = 50, unique = true)
    @NotBlank(message = "Brand name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String brandName;

    @Column(name = "generic_name", length = 50)
    @NotBlank(message = "Generic name is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String genericName;

    @Column(name = "drug_action", length = 50)
    @NotBlank(message = "Drug action is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 50, message = "Input exceeds size limits.")
    private String drugAction;

    @Column(name = "controlled")
    @NotNull(message = "Value cannot be null.")
    private Boolean controlled = false;

    @Column(name = "blood_thinner")
    @NotNull(message = "Value cannot be null.")
    private Boolean bloodThinner = false;

    @ManyToMany(mappedBy = "medications", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "medication")
    Set<UserMedList> userMedList;

    public Long getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(Long medicationID) {
        this.medicationID = medicationID;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getDrugAction() {
        return drugAction;
    }

    public void setDrugAction(String drugAction) {
        this.drugAction = drugAction;
    }

    public Boolean getControlled() {
        return controlled;
    }

    public void setControlled(Boolean controlled) {
        this.controlled = controlled;
    }

    public Boolean getBloodThinner() {
        return bloodThinner;
    }

    public void setBloodThinner(Boolean bloodThinner) {
        this.bloodThinner = bloodThinner;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    /**
    Override the toString() method so medication names will be shown in the form. And equals() and hashCode() must be overridden so Spring MVC and Thymeleaf will show the check marks correctly when the form is in edit mode. Thanks to https://www.codejava.net/frameworks/spring-boot/spring-thymeleaf-form-multi-checkboxes-mapping-with-collection-example
     */

    /**
     * 
     * @return 
     */
    @Override
    public String toString() {
        return this.genericName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((medicationID == null) ? 0 : medicationID.hashCode());
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
        Medication other = (Medication) obj;
        if (medicationID == null) {
            if (other.medicationID != null) {
                return false;
            }
        } else if (!medicationID.equals(other.medicationID)) {
            return false;
        }
        return true;
    }
    
    public String getIdAsString() {
        return medicationID.toString();
    }
}

