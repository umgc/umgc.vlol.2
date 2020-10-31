/**
 * Unit Test of the Medication Class.
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

public class MedicationTest {

  private Validator validator;
  private final Medication medication;

  public MedicationTest() {
    // Instantiate the Medication object
    this.medication = new Medication();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the Medication object before each test
    medication.setMedicationId(1l);
    medication.setBrandName("Abilify");
    medication.setGenericName("Aripiprazole");
    medication.setDrugAction("Antipsychotic");
    medication.setControlled(Boolean.FALSE);
    medication.setBloodThinner(Boolean.FALSE);
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterEach
  public void tearDown() {}

  /** Passing test for the getMedicationId method, of class Medication. */
  @Test
  public void testGetMedicationId() {
    System.out.println("getMedicationId Test (Passing value)");
    Long expResult = 1l;
    Long result = medication.getMedicationId();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setMedicationId method, of class Medication. */
  @Test
  public void testSetMedicationId() {
    System.out.println("setMedicationId Test (Passing value)");
    Long medicationId = 1l;
    medication.setMedicationId(medicationId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Negative value test for the setMedicationId method, of class Medication. */
  @Test
  public void testSetMedicationIdNegative() {
    System.out.println("setMedicationId Test (Negative value)");
    Long medicationId = -1l;
    medication.setMedicationId(medicationId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Out of Range value test for the setMedicationId method, of class Medication. */
  @Test
  public void testSetMedicationIdOutOfRange() {
    System.out.println("setMedicationId Test (Out of Range value)");
    Long medicationId = Long.MAX_VALUE + 1;
    medication.setMedicationId(medicationId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getBrandName method, of class Medication. */
  @Test
  public void testGetBrandName() {
    System.out.println("getBrandName Test (Passing value)");
    String expResult = "Abilify";
    String result = medication.getBrandName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setBrandName method, of class Medication. */
  @Test
  public void testSetBrandName() {
    System.out.println("setBrandName Test (Passing value)");
    String brandName = "Abilify";
    medication.setBrandName(brandName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setBrandName method, of class Medication. */
  @Test
  public void testSetBrandNameBlank() {
    System.out.println("setBrandName Test (Blank value)");
    String brandName = "";
    medication.setBrandName(brandName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setBrandName method, of class Medication. */
  @Test
  public void testSetBrandNameInvalid() {
    System.out.println("setBrandName Test (Injection value)");
    String brandName = "<script>alert(\"This is an attack!\");</script>";
    medication.setBrandName(brandName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setBrandName method, of class Medication. */
  @Test
  public void testSetBrandNameOverflow() {
    System.out.println("setBrandName Test (Overflow value)");
    String brandName = "A".repeat(2048);
    medication.setBrandName(brandName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getGenericName method, of class Medication. */
  @Test
  public void testGetGenericName() {
    System.out.println("getGenericName Test (Passing value)");
    String expResult = "Aripiprazole";
    String result = medication.getGenericName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setGenericName method, of class Medication. */
  @Test
  public void testSetGenericName() {
    System.out.println("setGenericName Test (Passing value)");
    String genericName = "Aripiprazole";
    medication.setGenericName(genericName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setGenericName method, of class Medication. */
  @Test
  public void testSetGenericNameBlank() {
    System.out.println("setGenericName Test (Blank value)");
    String genericName = "";
    medication.setGenericName(genericName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setGenericName method, of class Medication. */
  @Test
  public void testSetGenericNameInvalid() {
    System.out.println("setGenericName Test (Injection value)");
    String genericName = "<script>alert(\"This is an attack!\");</script>";
    medication.setGenericName(genericName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setGenericName method, of class Medication. */
  @Test
  public void testSetGenericNameOverflow() {
    System.out.println("setGenericName Test (Overflow value)");
    String genericName = "A".repeat(2048);
    medication.setGenericName(genericName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getDrugAction method, of class Medication. */
  @Test
  public void testGetDrugAction() {
    System.out.println("getDrugAction Test (Passing value)");
    String expResult = "Antipsychotic";
    String result = medication.getDrugAction();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setDrugAction method, of class Medication. */
  @Test
  public void testSetDrugAction() {
    System.out.println("setDrugAction Test (Passing value)");
    String drugAction = "Antipsychotic";
    medication.setDrugAction(drugAction);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setDrugAction method, of class Medication. */
  @Test
  public void testSetDrugActionBlank() {
    System.out.println("setDrugAction Test (Blank value)");
    String drugAction = "";
    medication.setDrugAction(drugAction);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setDrugAction method, of class Medication. */
  @Test
  public void testSetDrugActionInvalid() {
    System.out.println("setDrugAction Test (Injection value)");
    String drugAction = "<script>alert(\"This is an attack!\");</script>";
    medication.setDrugAction(drugAction);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setDrugAction method, of class Medication. */
  @Test
  public void testSetDrugActionOverflow() {
    System.out.println("setDrugAction Test (Overflow value)");
    String drugAction = "A".repeat(2048);
    medication.setDrugAction(drugAction);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getControlled method, of class Medication. */
  @Test
  public void testGetControlled() {
    System.out.println("getControlled Test (Passing value)");
    Boolean expResult = Boolean.FALSE;
    Boolean result = medication.getControlled();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setControlled method, of class Medication. */
  @Test
  public void testSetControlled() {
    System.out.println("setControlled Test (Passing value)");
    Boolean controlled = Boolean.TRUE;
    medication.setControlled(controlled);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Null value test for the setControlled method, of class Medication. */
  @Test
  public void testSetControlledNull() {
    System.out.println("setControlled Test (Null value)");
    Boolean controlled = null;
    medication.setControlled(controlled);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getBloodThinner method, of class Medication. */
  @Test
  public void testGetBloodThinner() {
    System.out.println("getBloodThinner Test (Passing value)");
    Boolean expResult = Boolean.FALSE;
    Boolean result = medication.getBloodThinner();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setBloodThinner method, of class Medication. */
  @Test
  public void testSetBloodThinner() {
    System.out.println("setBloodThinner Test (Passing value)");
    Boolean bloodThinner = Boolean.TRUE;
    medication.setBloodThinner(bloodThinner);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Null value test for the setBloodThinner method, of class Medication. */
  @Test
  public void testSetBloodThinnerNull() {
    System.out.println("setBloodThinner Test (Null value)");
    Boolean bloodThinner = null;
    medication.setBloodThinner(bloodThinner);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Medication>> violations = validator.validate(medication);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
}
