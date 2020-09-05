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
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_medication")
public class UserMedication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    private Long medicationID;

    @Column(name = "brand_name", length = 256)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 256, message = "Input exceeds size limits.")
    private String brandName;

    @Column(name = "generic_name", length = 256)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 256, message = "Input exceeds size limits.")
    private String genericName;

    @Column(name = "drug_action", length = 1024)
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 1024, message = "Input exceeds size limits.")
    private String drugAction;

    @Column(name = "controlled")
    @NotNull(message = "Value cannot be null.")
    private Boolean controlled = false;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "blood_thinner")
    @NotNull(message = "Value cannot be null.")
    private Boolean bloodThinner = false;

    @Column(name = "dosage", length = 32)
    @NotNull(message = "Value cannot be null.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 32, message = "Input exceeds size limits.")
    private String dosage;

    @Column(name = "frequency", length = 32)
    @NotBlank(message = "Frequency of dosage is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 32, message = "Input exceeds size limits.")
    private String frequency;

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
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
        System.out.println("equals"+medicationID);
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        UserMedication other = (UserMedication) obj;
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
        System.out.println("getIdAsString"+medicationID);
        return medicationID.toString();
    }
}
