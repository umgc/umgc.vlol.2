/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

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
public class APIKeysTest {

  private Validator validator;
  private final APIKeys apikeys;

  public APIKeysTest() {
    // Instantiate the allergy object
    this.apikeys = new APIKeys();
  }
  @BeforeAll
  public static void setUpClass() {}
  
  @BeforeEach
  public void setUp() throws ParseException {
    // Populate the allergy object before each test
  apikeys.setApiKey("10");
  apikeys.setCreateDate(
        new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05"));
  apikeys.setUserRef("admin@vlol.gov");
  
   // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterAll
  public static void tearDownClass() {}

 /** Test of setAPIKeys method, of class APIKeys. */
  @Test
  public void testSetAPIKeys() {
    System.out.println("setAPIKeys Test (Passing value)");
    String apikey = "10";
    apikeys.setApiKey(apikey);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
  }
  /** Blank value of setAPIKeys method, of class APIKeys. */
  @Test
  public void testSetAPIKeysBlank() {
    System.out.println("setAPIKeys Test (Blank value)");
    String apikey = "";
    apikeys.setApiKey(apikey);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
  /** Invalid value test for the setAPIKeys method, of class APIKeys. */
  @Test
  public void testSetAPIKeysInvalid() {
    System.out.println("setAPIKeys Test (Injection value)");
    String apikey = "<script>alert(\"This is an attack!\");</script>";
    apikeys.setApiKey(apikey);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

 /** Test of getAPIKey method, of class APIKeys. */
  @Test
  public void testGetAPIKey() {
    System.out.println("getAPIKey (Passing value)");
    String expResult = "10";
    String result = apikeys.getApiKey();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }
    /** Test of setUserRef method, of class APIKeys. */
  @Test
  public void testSetUserRef() {
    System.out.println("setUserRef Test (Passing value)");
    String email = "admin@vlol.gov";
    apikeys.setUserRef(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
  }
  /** Blank value of setUserRef method, of class APIKeys. */
  @Test
  public void testSetUserRefBlank() {
    System.out.println("setUserRef Test (Blank value)");
    String email = "";
     apikeys.setUserRef(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }
  /** Invalid value test for the setUserRef method, of class APIKeys. */
  @Test
  public void testSetUserRefInvalid() {
    System.out.println("setUserRef Test (Injection value)");
    String email = "<script>alert(\"This is an attack!\");</script>";
    apikeys.setUserRef(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Test of getUserRef method, of class APIKeys. */
  @Test
  public void testGetUserRef() {
    System.out.println("getUserRef (Passing value)");
    String expResult = "admin@vlol.gov";
    String result = apikeys.getUserRef();
   // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

 

  /** Test of getCreateDate method, of class APIKeys.
   * @throws java.text.ParseException */
  @Test
  public void testGetCreateDate() throws ParseException {
    System.out.println("getCreateDate Test (Passing value)");
    Date expResult = new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05");
    Date result = apikeys.getCreateDate();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of setCreateDate method, of class APIKeys. 
   * @throws java.text.ParseException
   */
  @Test
  public void testCreateDate() throws ParseException {
    System.out.println("setCreateDate Test (Passing value)");
    Date createdate = new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05");
    apikeys.setCreateDate(createdate);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Future value test for the setCreateDate method, of class APIKeys. */
  @Test
  public void testSetCreateDateFuture() {
    System.out.println("setCreateDate Test (Future value)");
    long millis = System.currentTimeMillis();
    Date date = new Date(millis + 60000);
    Date createdate = date;
    apikeys.setCreateDate(createdate);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Null value test for the setCreateDate method, of class APIKeys. */
  @Test
  public void testSetCreateDateNull() {
    System.out.println("setCreateDate Test (Null value)");
    Date createDate = null;
    apikeys.setCreateDate(createDate);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of toString method, of class APIKeys. */
  @Test
  public void testToString() {
    System.out.println("toString");
    String expResult = "10";
    String result = apikeys.toString();
   // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Test of equals method, of class APIKeys. */
  @Test
  public void testEquals() {
    System.out.println("equals");
    Object obj = null;
    boolean expResult = false;
    boolean result = apikeys.equals(obj);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<APIKeys>> violations = validator.validate(apikeys);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
}
}
