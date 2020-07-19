/**
 * User Medication List Class.
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user_med_list")
public class UserMedList implements Serializable {

    @EmbeddedId
    UserMedListKey UserMedListID;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @NotNull(message = "Value cannot be null.")
    User user;

    @ManyToOne
    @MapsId("medication_id")
    @JoinColumn(name = "medication_id")
    @NotNull(message = "Value cannot be null.")
    Medication medication;

    @Column(name = "dosage")
    @DecimalMin(value = "0.0", inclusive = false, message = "Value must be greater than 1.")
    @Digits(integer = 6, fraction = 2, message = "Input is not in the form of a decimal.")
    @NotNull(message = "Value cannot be null.")
    private float dosage;

    @Column(name = "frequency", length = 32)
    @NotBlank(message = "Frequency of dosage is required.")
    // Check if text is valid per RFC 3986.
    @Pattern(regexp = "^[A-Za-z0-9\\s\\-._~:\\/?#\\[\\]@!$&'()*+,;=]*$", message = "Input contains illegal characters.")
    @Size(max = 100, message = "Input exceeds size limits.")
    private String frequency;

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
