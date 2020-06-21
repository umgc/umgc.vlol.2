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
import java.util.Set;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "medication")
public class Medication implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medication_id")
    private Long medicationID;

    @Column(name = "brand_name", unique = true)
    @NotBlank(message = "Brand name is required.")
    private String brandName;

    @Column(name = "generic_name")
    @NotBlank(message = "Generic name is required.")
    private String genericName;

    @Column(name = "drug_action")
    @NotBlank(message = "Drug action is required.")
    private String drugAction;

    @Column(name = "controlled")
    private Boolean controlled;

    @Column(name = "blood_thinner")
    private Boolean bloodThinner;

    @ManyToMany(mappedBy = "medications", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users;
    
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
}
