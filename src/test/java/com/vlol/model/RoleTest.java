/**
 * Unit Test of the Role Class.
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

public class RoleTest {

  private Validator validator;
  private final Role role;

  public RoleTest() {
    // Instantiate the Role object
    this.role = new Role();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the Role object before each test
    role.setRoleId(1l);
    role.setTitle("participant");
    role.setDescription("Program Participant (Profile Access Only)");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterEach
  public void tearDown() {}

  /** Passing test for the getRoleId method, of class Role. */
  @Test
  public void testGetRoleId() {
    System.out.println("getRoleId Test (Passing value)");
    Long expResult = 1l;
    Long result = role.getRoleId();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setRoleId method, of class Role. */
  @Test
  public void testSetRoleId() {
    System.out.println("setRoleId Test (Passing value)");
    Long roleId = 1l;
    role.setRoleId(roleId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Negative value test for the setRoleId method, of class Role. */
  @Test
  public void testSetRoleIdNegative() {
    System.out.println("setRoleId Test (Negative value)");
    Long roleId = -1l;
    role.setRoleId(roleId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Out of Range value test for the setRoleId method, of class Role. */
  @Test
  public void testSetRoleIdOutOfRange() {
    System.out.println("setRoleId Test (Out of Range value)");
    Long roleId = Long.MAX_VALUE + 1;
    role.setRoleId(roleId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getTitle method, of class Role. */
  @Test
  public void testGetTitle() {
    System.out.println("getTitle Test (Passing value)");
    String expResult = "participant";
    String result = role.getTitle();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setTitle method, of class Role. */
  @Test
  public void testSetTitle() {
    System.out.println("setTitle Test (Passing value)");
    String title = "participant";
    role.setTitle(title);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setTitle method, of class Role. */
  @Test
  public void testSetTitleBlank() {
    System.out.println("setTitle Test (Blank value)");
    String title = "";
    role.setTitle(title);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Invalid value test for the setTitle method, of class Role. */
  @Test
  public void testSetTitleInvalid() {
    System.out.println("setTitle Test (Injection value)");
    String title = "<script>alert(\"This is an attack!\");</script>";
    role.setTitle(title);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setTitle method, of class Role. */
  @Test
  public void testSetTitleOverflow() {
    System.out.println("setTitle Test (Overflow value)");
    String title = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    role.setTitle(title);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getDescription method, of class Role. */
  @Test
  public void testGetDescription() {
    System.out.println("getDescription Test (Passing value)");
    String expResult = "Program Participant (Profile Access Only)";
    String result = role.getDescription();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setDescription method, of class Role. */
  @Test
  public void testSetDescription() {
    System.out.println("setDescription Test (Passing value)");
    String description = "Program Participant (Profile Access Only)";
    role.setDescription(description);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setDescription method, of class Role. */
  @Test
  public void testSetDescriptionInvalid() {
    System.out.println("setDescription Test (Injection value)");
    String description = "<script>alert(\"This is an attack!\");</script>";
    role.setDescription(description);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setDescription method, of class Role. */
  @Test
  public void testSetDescriptionOverflow() {
    System.out.println("setDescription Test (Overflow value)");
    String description =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkl\n"
            + "mnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMN\n"
            + "OPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\n"
            + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZab\n"
            + "cdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCD\n"
            + "EFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnop";
    role.setDescription(description);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Role>> violations = validator.validate(role);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
}
