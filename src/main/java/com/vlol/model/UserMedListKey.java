/**
 * User Medication List Key.
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
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserMedListKey implements Serializable {

    @Column(name = "user_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    @NotNull(message = "Value cannot be null.")
    Long userID;

    @Column(name = "medication_id")
    @Min(value = 1, message = "Value must be greater than 1.")
    @NotNull(message = "Value cannot be null.")
    Long medicationID;
}
