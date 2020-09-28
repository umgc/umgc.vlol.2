/**
 * Unit Test of the Allergy Class.
 *
 * <p>Java Runtime Environment (JRE) version used: 11.0.7 Java Development Kit (JDK) version used:
 * 11.0.7
 *
 * <p>Styling guide: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)
 * and Code Conventions for the Java Programming Language (Oracle: Deprecated)
 * (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package model
 * @author Rob Garcia <rgarcia92@student.umgc.edu> and Mohammed Allibalogun
 *     <mohammed.allibalogun@gmail.com>
 * @license https://opensource.org/licenses/MIT The MIT License
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllergyTest {

  private Validator validator;
  private final Allergy allergy;

  public AllergyTest() {
    // Instantiate the Allergy object
    this.allergy = new Allergy();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the Allergy object before each test
    allergy.setAllergyId(1l);
    allergy.setAllergyName("Latex");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterEach
  public void tearDown() {}

  /** Passing test for the getAllergyId method, of class Allergy. */
  @Test
  public void testGetAllergyId() {
    System.out.println("getAllergyId Test (Passing value)");
    Long expResult = 1l;
    Long result = allergy.getAllergyId();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setAllergyId method, of class Allergy. */
  @Test
  public void testSetAllergyId() {
    System.out.println("setAllergyId Test (Passing value)");
    Long allergyId = 1l;
    allergy.setAllergyId(allergyId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Negative value test for the setAllergyId method, of class Allergy. */
  @Test
  public void testSetAllergyIdNegative() {
    System.out.println("setAllergyId Test (Negative value)");
    Long allergyId = -1l;
    allergy.setAllergyId(allergyId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Out of Range value test for the setAllergyId method, of class Allergy. */
  @Test
  public void testSetAllergyIdOutOfRange() {
    System.out.println("setAllergyId Test (Out of Range value)");
    Long allergyId = Long.MAX_VALUE + 1;
    allergy.setAllergyId(allergyId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getAllergyName method, of class Allergy. */
  @Test
  public void testGetAllergyName() {
    System.out.println("getAllergyName Test (Passing value)");
    String expResult = "Latex";
    String result = allergy.getAllergyName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setAllergyName method, of class Allergy. */
  @Test
  public void testSetAllergyName() {
    System.out.println("setAllergyName Test (Passing value)");
    String allergyName = "Latex";
    allergy.setAllergyName(allergyName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setAllergyName method, of class Allergy. */
  @Test
  public void testSetAllergyNameBlank() {
    System.out.println("setAllergyName Test (Blank value)");
    String allergyName = "";
    allergy.setAllergyName(allergyName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Invalid value test for the setAllergyName method, of class Allergy. */
  @Test
  public void testSetAllergyNameInvalid() {
    System.out.println("setAllergyName Test (Injection value)");
    String allergyName = "<script>alert(\"This is an attack!\");</script>";
    allergy.setAllergyName(allergyName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setAllergyName method, of class Allergy. */
  @Test
  public void testSetAllergyNameOverflow() {
    System.out.println("setAllergyName Test (Overflow value)");
    String allergyName = ".".repeat(257);
    allergy.setAllergyName(allergyName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
}
