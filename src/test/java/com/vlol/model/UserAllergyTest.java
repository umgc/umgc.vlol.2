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
public class UserAllergyTest {

  private Validator validator;
  private final UserAllergy userallergy;

  public UserAllergyTest() {
    // Instantiate the allergy object
    this.userallergy = new UserAllergy();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the allergy object before each test
    userallergy.setAllergyId(1l);
    userallergy.setAllergyName("eggs");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
  /** Test of getAllergyId method, of class UserAllergy. */
  @Test
  public void testGetAllergyId() {
    System.out.println("getAllergyId Test (Passing value)");
    Long expResult = 1l;
    Long result = userallergy.getAllergyId();
    assertEquals(expResult, result);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserAllergy>> violations = validator.validate(userallergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of setAllergyId method, of class UserAllergy. */
  @Test
  public void testSetAllergyId() {
    System.out.println("setAllergyId Test (Passing value)");
    Long allergyId = 1l;
    userallergy.setAllergyId(allergyId);
    /// Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserAllergy>> violations = validator.validate(userallergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
  /** Negative value test for the setAllergyID method, of the class UserAllergy. */
  @Test
  public void testSetAllergyIdNegative() {
    System.out.println("setAllergyId Test (Negative value)");
    Long allergyId = -1l;
    userallergy.setAllergyId(allergyId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserAllergy>> violations = validator.validate(userallergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
  /** Out of Range value test for the setAllergyId method, of class UserAllergy. */
  @Test
  public void testSetAllergyIdOutOfRange() {
    System.out.println("setAllergyId Test (Out of Range value)");
    Long allergyId = Long.MAX_VALUE + 1;
    userallergy.setAllergyId(allergyId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserAllergy>> violations = validator.validate(userallergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
  /** Test GetAllergyName method of class UserAllergy */
  @Test
  public void testGetAllergyName() {
    System.out.println("getAllergyName Test (Passing value)");
    String expResult = "eggs";
    String result = userallergy.getAllergyName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserAllergy>> violations = validator.validate(userallergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of setAllergyName method, of class UserAllergy. */
  @Test
  public void testSetAllergyName() {
    System.out.println("setAllergyName Test (Passing value)");
    String allergyName = "eggs";
    userallergy.setAllergyName(allergyName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserAllergy>> violations = validator.validate(userallergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
}
