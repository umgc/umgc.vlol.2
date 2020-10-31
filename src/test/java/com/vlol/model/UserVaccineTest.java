/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** @author kimbe */
public class UserVaccineTest {

  private Validator validator;
  private final UserVaccine uservaccine;

  public UserVaccineTest() {
    // Instantiate the vaccine object
    this.uservaccine = new UserVaccine();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the vaccine object before each test
    uservaccine.setVaccineId(1l);
    uservaccine.setVaccineName("herpes");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
  /** Test of getVaccineId method, of class UserVaccine. */
  @Test
  public void testGetVaccineId() {
    System.out.println("getVaccineId Test (Passing value)");
    Long expResult = 1l;
    Long result = uservaccine.getVaccineId();
    assertEquals(expResult, result);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserVaccine>> violations = validator.validate(uservaccine);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of setVaccineId method, of class UserVaccine. */
  @Test
  public void testSetVaccineId() {
    System.out.println("setVaccineId Test (Passing value)");
    Long vaccineId = 1l;
    uservaccine.setVaccineId(vaccineId);
    /// Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserVaccine>> violations = validator.validate(uservaccine);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
  /** Negative value test for the setVaccineID method, of the class UserVaccine. */
  @Test
  public void testSetVaccineIdNegative() {
    System.out.println("setVaccineId Test (Negative value)");
    Long vaccineId = -1l;
    uservaccine.setVaccineId(vaccineId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserVaccine>> violations = validator.validate(uservaccine);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
  /** Out of Range value test for the setVaccineId method, of class UserVaccine. */
  @Test
  public void testSetVaccineIdOutOfRange() {
    System.out.println("setVaccineId Test (Out of Range value)");
    Long vaccineId = Long.MAX_VALUE + 1;
    uservaccine.setVaccineId(vaccineId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserVaccine>> violations = validator.validate(uservaccine);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
  /** Test GetVaccineName method of class User Vaccine */
  @Test
  public void testGetVaccineName() {
    System.out.println("getVaccineName Test (Passing value)");
    String expResult = "herpes";
    String result = uservaccine.getVaccineName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserVaccine>> violations = validator.validate(uservaccine);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of setVaccineName method, of class UserVaccine. */
  @Test
  public void testSetVaccineName() {
    System.out.println("setVaccineName Test (Passing value)");
    String vaccineName = "herpes";
    uservaccine.setVaccineName(vaccineName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserVaccine>> violations = validator.validate(uservaccine);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
}
