/**
 * Unit Test of the Condition Class.
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

public class ConditionTest {

  private Validator validator;
  private final Condition condition;

  public ConditionTest() {
    // Instantiate the Condition object
    this.condition = new Condition();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the Condition object before each test
    condition.setConditionId(1l);
    condition.setConditionName("Asthma");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterEach
  public void tearDown() {}

  /** Passing test for the getConditionId method, of class Condition. */
  @Test
  public void testGetConditionId() {
    System.out.println("getConditionId Test (Passing value)");
    Long expResult = 1l;
    Long result = condition.getConditionId();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setConditionId method, of class Condition. */
  @Test
  public void testSetConditionId() {
    System.out.println("setConditionId Test (Passing value)");
    Long conditionId = 1l;
    condition.setConditionId(conditionId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Negative value test for the setConditionId method, of class Condition. */
  @Test
  public void testSetConditionIdNegative() {
    System.out.println("setConditionId Test (Negative value)");
    Long conditionId = -1l;
    condition.setConditionId(conditionId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Out of Range value test for the setConditionId method, of class Condition. */
  @Test
  public void testSetConditionIdOutOfRange() {
    System.out.println("setConditionId Test (Out of Range value)");
    Long conditionId = Long.MAX_VALUE + 1;
    condition.setConditionId(conditionId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getConditionName method, of class Condition. */
  @Test
  public void testGetConditionName() {
    System.out.println("getConditionName Test (Passing value)");
    String expResult = "Asthma";
    String result = condition.getConditionName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setConditionName method, of class Condition. */
  @Test
  public void testSetConditionName() {
    System.out.println("setConditionName Test (Passing value)");
    String conditionName = "Asthma";
    condition.setConditionName(conditionName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setConditionName method, of class Condition. */
  @Test
  public void testSetConditionNameBlank() {
    System.out.println("setConditionName Test (Blank value)");
    String conditionName = "";
    condition.setConditionName(conditionName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Invalid value test for the setConditionName method, of class Condition. */
  @Test
  public void testSetConditionNameInvalid() {
    System.out.println("setConditionName Test (Injection value)");
    String conditionName = "<script>alert(\"This is an attack!\");</script>";
    condition.setConditionName(conditionName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setConditionName method, of class Condition. */
  @Test
  public void testSetConditionNameOverflow() {
    System.out.println("setConditionName Test (Overflow value)");
    String conditionName = ".".repeat(320);
    condition.setConditionName(conditionName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
}
