/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import static org.junit.jupiter.api.Assertions.*;

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
public class AdvanceDirectiveTest {

  private Validator validator;
  private final AdvanceDirective advanceDirective;
  private final User user = new User();
  private final byte[] bytes = new byte[] {1, 2};

  public AdvanceDirectiveTest() {
    // Instantiate the Allergy object
    this.advanceDirective = new AdvanceDirective();
    this.advanceDirective.setAdvanceDirectiveContentType("application/pdf");
    this.advanceDirective.setAdvanceDirectiveFile(bytes);
    this.advanceDirective.setAdvanceDirectiveFilename("test.pdf");
    this.advanceDirective.setAdvanceDirectiveId(1L);
    this.advanceDirective.setAdvanceDirectiveType("MOSLT");
    this.advanceDirective.setUser(user);
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() {
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /** Test of getAdvanceDirectiveId method, of class AdvanceDirective. */
  @Test
  public void testGetAdvanceDirectiveId() {
    System.out.println("getAdvanceDirectiveId");
    Long expResult = 1L;
    Long result = advanceDirective.getAdvanceDirectiveId();
    assertEquals(expResult, result);
  }

  /** Test of setAdvanceDirectiveId method, of class AdvanceDirective. */
  @Test
  public void testSetAdvanceDirectiveId() {
    System.out.println("setAdvanceDirectiveId");
    Long advanceDirectiveId = 2L;
    advanceDirective.setAdvanceDirectiveId(advanceDirectiveId);

    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<AdvanceDirective>> violations = validator.validate(advanceDirective);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getAdvanceDirectiveFile method, of class AdvanceDirective. */
  @Test
  public void testGetAdvanceDirectiveFile() {
    System.out.println("getAdvanceDirectiveFile");
    byte[] expResult = bytes;
    byte[] result = advanceDirective.getAdvanceDirectiveFile();
    assertArrayEquals(expResult, result);
  }

  /** Test of setAdvanceDirectiveFile method, of class AdvanceDirective. */
  @Test
  public void testSetAdvanceDirectiveFile() {
    System.out.println("setAdvanceDirectiveFile");
    byte[] advanceDirectiveFile = new byte[] {3, 4};
    advanceDirective.setAdvanceDirectiveFile(advanceDirectiveFile);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<AdvanceDirective>> violations = validator.validate(advanceDirective);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getAdvanceDirectiveType method, of class AdvanceDirective. */
  @Test
  public void testGetAdvanceDirectiveType() {
    System.out.println("getAdvanceDirectiveType");
    String expResult = "MOSLT";
    String result = advanceDirective.getAdvanceDirectiveType();
    assertEquals(expResult, result);
  }

  /** Test of setAdvanceDirectiveType method, of class AdvanceDirective. */
  @Test
  public void testSetAdvanceDirectiveType() {
    System.out.println("setAdvanceDirectiveType");
    String advanceDirectiveType = "TEST";
    advanceDirective.setAdvanceDirectiveType(advanceDirectiveType);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<AdvanceDirective>> violations = validator.validate(advanceDirective);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getAdvanceDirectiveContentType method, of class AdvanceDirective. */
  @Test
  public void testGetAdvanceDirectiveContentType() {
    System.out.println("getAdvanceDirectiveContentType");
    String expResult = "application/pdf";
    String result = advanceDirective.getAdvanceDirectiveContentType();
    assertEquals(expResult, result);
  }

  /** Test of setAdvanceDirectiveContentType method, of class AdvanceDirective. */
  @Test
  public void testSetAdvanceDirectiveContentType() {
    System.out.println("setAdvanceDirectiveContentType");
    String advanceDirectiveContentType = "application/doc";
    advanceDirective.setAdvanceDirectiveContentType(advanceDirectiveContentType);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<AdvanceDirective>> violations = validator.validate(advanceDirective);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getAdvanceDirectiveFilename method, of class AdvanceDirective. */
  @Test
  public void testGetAdvanceDirectiveFilename() {
    System.out.println("getAdvanceDirectiveFilename");
    String expResult = "test.pdf";
    String result = advanceDirective.getAdvanceDirectiveFilename();
    assertEquals(expResult, result);
  }

  /** Test of setAdvanceDirectiveFilename method, of class AdvanceDirective. */
  @Test
  public void testSetAdvanceDirectiveFilename() {
    System.out.println("setAdvanceDirectiveFilename");
    String advanceDirectiveFilename = "test2.pdf";
    advanceDirective.setAdvanceDirectiveFilename(advanceDirectiveFilename);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<AdvanceDirective>> violations = validator.validate(advanceDirective);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getUser method, of class AdvanceDirective. */
  @Test
  public void testGetUser() {
    System.out.println("getUser");
    User expResult = user;
    User result = advanceDirective.getUser();
    assertEquals(expResult, result);
  }

  /** Test of setUser method, of class AdvanceDirective. */
  @Test
  public void testSetUser() {
    System.out.println("setUser");
    User user = new User();
    advanceDirective.setUser(user);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<AdvanceDirective>> violations = validator.validate(advanceDirective);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
}
