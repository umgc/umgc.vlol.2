/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class PersistentLoginsTest {

  private Validator validator;
  private final PersistentLogins persistentlogins;

  public PersistentLoginsTest() {
    // Instantiate the allergy object
    this.persistentlogins = new PersistentLogins();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() throws ParseException {
    // Populate the allergy object before each test
    persistentlogins.setEmail("user@vlol.gov");
    persistentlogins.setLastUsed(
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00"));
    persistentlogins.setSeries("2");
    persistentlogins.setToken("1");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  /** Test of setSeries method, of class PersistentLogins. */
  @Test
  public void testSetSeries() {
    System.out.println("setSeries Test (Passing value)");
    String series = "2";
    persistentlogins.setSeries(series);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
  }
  /** Blank value of setSeries method, of class PersistentLogins. */
  @Test
  public void testSetSeriesBlank() {
    System.out.println("setSeries Test (Blank value)");
    String series = "";
    persistentlogins.setSeries(series);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
  /** Invalid value test for the setSeries method, of class PersistentLogins. */
  @Test
  public void testSetSeriesInvalid() {
    System.out.println("setSeries Test (Injection value)");
    String series = "<script>alert(\"This is an attack!\");</script>";
    persistentlogins.setSeries(series);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
  /** Test of setEmail method, of class PersistentLogins. */
  @Test
  public void testSetEmail() {
    System.out.println("setEmail Test (Passing value)");
    String email = "user@vlol.gov";
    persistentlogins.setEmail(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
  }
  /** Blank value of setEmail method, of class PersistentLogins. */
  @Test
  public void testSetEmailBlank() {
    System.out.println("setEmail Test (Blank value)");
    String email = "";
    persistentlogins.setEmail(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
  /** Invalid value test for the setEmail method, of class PersistentLogins. */
  @Test
  public void testSetEmailInvalid() {
    System.out.println("setEmail Test (Injection value)");
    String email = "<script>alert(\"This is an attack!\");</script>";
    persistentlogins.setEmail(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Test of setToken method, of class PersistentLogins. */
  @Test
  public void testSetToken() {
    System.out.println("setToken Test (Passing value)");
    String token = "1";
    persistentlogins.setToken(token);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);

      // Test method
      assertTrue(violations.isEmpty());
    }
  }

  /**
   * Test of setLastUsed method, of class PersistentLogins.
   *
   * @throws java.text.ParseException
   */
  @Test
  public void testLastUsed() throws ParseException {
    System.out.println("setLastUsed Test (Passing value)");
    Date lastUsed = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00");
    persistentlogins.setLastUsed(lastUsed);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Future value test for the setLastUsed method, of class PersistentLogins. */
  @Test
  public void testSetLastUsedFuture() {
    System.out.println("setLastUsed Test (Future value)");
    long millis = System.currentTimeMillis();
    Date date = new Date(millis + 60000);
    Date lastUsed = date;
    persistentlogins.setLastUsed(lastUsed);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Null value test for the setLastUsed method, of class PersistentLogins. */
  @Test
  public void testSetLastUsedNull() {
    System.out.println("setLastUsed Test (Null value)");
    Date lastUsed = null;
    persistentlogins.setLastUsed(lastUsed);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Test of getToken method, of class PersistentLogins. */
  @Test
  public void testGetToken() {
    System.out.println("getToken (Passing value)");
    String expResult = "1";
    String result = persistentlogins.getToken();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of getLastUsed method, of class PersistentLogins. */
  @Test
  public void testGetLastUsed() throws ParseException {
    System.out.println("getLastUsed Test (Passing value)");
    Date expResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00");
    Date result = persistentlogins.getLastUsed();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }
  /** Tests the GetEmail method of the class PersistentLogins */
  public void testGetEmail() {
    System.out.println("getEmail Test (Passing value)");
    String expResult = "user@vlol.gov";
    String result = persistentlogins.getEmail();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }
  /** Test of getSeries method, of class PersistentLogins. */
  @Test
  public void testGetSeries() {
    System.out.println("getSeries (Passing value)");
    String expResult = "2";
    String result = persistentlogins.getSeries();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }
  /** Overflow value test for the setEmail method, of class PersistentLogins. */
  @Test
  public void testSetEmailOverflow() {
    System.out.println("setEmail Test (Overflow value)");
    String email =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    persistentlogins.setEmail(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<PersistentLogins>> violations = validator.validate(persistentlogins);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
}
