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
public class ContactTest {

  private Validator validator;
  private final Contact contact;

  public ContactTest() {
    // Instantiate the vaccine object
    this.contact = new Contact();
  }

  @BeforeAll
  public static void setUpClass() {}

  @BeforeEach
  public void setUp() {
    // Populate the contact object before each test
    contact.setFirstName("John");
    contact.setLastName("Doe");
    contact.setDescription("participant");
    contact.setEmail("jdoe@vlol.com");
    contact.setReason("abcdefghijklmnopqrstuvwxyz0123456789");
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterAll
  public static void tearDownClass() {}

  /** Test of getFirstName method, of class Contact. */
  /** Passing test for the getFirstName method, of class Contact. */
  @Test
  public void testGetFirstName() {
    System.out.println("getFirstName Test (Passing value)");
    String expResult = "John";
    String result = contact.getFirstName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setFirstName method, of class Contact. */
  @Test
  public void testSetFirstName() {
    System.out.println("setFirstName Test (Passing value)");
    String firstName = "John";
    contact.setFirstName(firstName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setFirstName method, of class Contact. */
  @Test
  public void testSetFirstNameBlank() {
    System.out.println("setFirstName Test (Blank value)");
    String firstName = "";
    contact.setFirstName(firstName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Passing test for the getLastName method, of class Contact. */
  @Test
  public void testGetLastName() {
    System.out.println("getLastName Test (Passing value)");
    String expResult = "Doe";
    String result = contact.getLastName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setLastName method, of class Contact. */
  @Test
  public void testSetLastName() {
    System.out.println("setLastName Test (Passing value)");
    String lastName = "Doe";
    contact.setLastName(lastName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setLastName method, of class Contact. */
  @Test
  public void testSetLastNameBlank() {
    System.out.println("setLastName Test (Blank value)");
    String lastName = "";
    contact.setLastName(lastName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Passing test for the getEmail method, of class Contact. */
  @Test
  public void testGetEmail() {
    System.out.println("getEmail Test (Passing value)");
    String expResult = "jdoe@vlol.com";
    String result = contact.getEmail();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setEmail method, of class Contact. */
  @Test
  public void testSetEmail() {
    System.out.println("setEmail Test (Passing value)");
    String email = "jdoe@vlol.com";
    contact.setEmail(email);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setEmail method, of class Contact. */
  @Test
  public void testSetEmailBlank() {
    System.out.println("setEmail Test (Blank value)");
    String contactname = "";
    contact.setEmail(contactname);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /*Tests the setDescription method of the Contact class*/

  @Test
  public void testSetDescription() {
    System.out.println("setDescription Test (Passing value)");
    String description = "participant";
    contact.setDescription(description);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setDescription method, of class Contact. */
  @Test
  public void testSetDescriptionBlank() {
    System.out.println("setDescription Test (Blank value)");
    String description = "";
    contact.setDescription(description);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setReason method, of class Contact. */
  @Test
  public void testSetReasonBlank() {
    System.out.println("setReason Test (Blank value)");
    String reason = "";
    contact.setReason(reason);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  public void testGetDescription() {
    System.out.println("getDescription Test (Passing value)");
    String expResult = "participant";
    String result = contact.getDescription();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  public void testGetReason() {
    System.out.println("getReason Test (Passing value)");
    String expResult = "abcdefghijklmnopqrstuvwxyz0123456789";
    String result = contact.getDescription();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Contact>> violations = validator.validate(contact);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }
}
