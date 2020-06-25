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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_med_list")
public class UserMedList implements Serializable {

    @EmbeddedId
    UserMedListKey UserMedListID;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("medication_id")
    @JoinColumn(name = "medication_id")
    Medication medication;

    @Column(name = "dosage")
    @Digits(integer = 6, fraction = 2, message = "Input is not in the form of a decimal.")
    @NotNull
    private float dosage;

    @Column(name = "frequency")
    @Digits(integer = 2, fraction = 1, message = "Input is not in the form of a decimal.")
    @NotNull
    private int frequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "freq_interval", length = 10)
    private FrequencyInterval freqInterval;

    public float getDosage() {
        return dosage;
    }

    public void setDosage(float dosage) {
        this.dosage = dosage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public FrequencyInterval getFrequencyInterval() {
        return freqInterval;
    }

    public void setFrequencyInterval(FrequencyInterval freqInterval) {
        this.freqInterval = freqInterval;
    }
}
