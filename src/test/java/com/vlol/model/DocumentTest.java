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

/**
 *
 * @author kimbe
 */
public class DocumentTest {
  private Validator validator;
  private final Document document;
  private final User user = new User();
  private final byte[] bytes = new byte[] {1, 2};
  
  public DocumentTest() {
    this.document = new Document();
    this.document.setDocumentContentType("application/pdf");
    this.document.setDocumentFile(bytes);
    this.document.setDocumentFilename("test.pdf");
    this.document.setDocumentId(1L);
    this.document.setDocumentType("MOSLT");
    this.document.setUser(user);
    
  }
  
  @BeforeAll
  public static void setUpClass() {
  }
  
  @AfterAll
  public static void tearDownClass() {
  }
 @BeforeEach
  public void setUp() {
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }
   /** Test of getDocumentId method, of class Document. */
  @Test
  public void testGetDocumentId() {
    System.out.println("getDocumentId");
    Long expResult = 1L;
    Long result = document.getDocumentId();
    assertEquals(expResult, result);
  }

  /** Test of setDocumentId method, of class Document. */
  @Test
  public void testSetDocumentId() {
    System.out.println("setDocumentId");
    Long documentId = 2L;
    document.setDocumentId(documentId);

    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Document>> violations = validator.validate(document);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getDocumentFile method, of class Document. */
  @Test
  public void testGetDocumentFile() {
    System.out.println("getDocumentFile");
    byte[] expResult = bytes;
    byte[] result = document.getDocumentFile();
    assertArrayEquals(expResult, result);
  }

  /** Test of setDocumentFile method, of class Document. */
  @Test
  public void testSetDocumentFile() {
    System.out.println("setDocumentFile");
    byte[] documentFile = new byte[] {3, 4};
    document.setDocumentFile(documentFile);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Document>> violations = validator.validate(document);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getDocumentType method, of class Document. */
  @Test
  public void testGetDocumentType() {
    System.out.println("getDocumentType");
    String expResult = "MOSLT";
    String result = document.getDocumentType();
    assertEquals(expResult, result);
  }

  /** Test of setDocumentType method, of class Document. */
  @Test
  public void testSetDocumentType() {
    System.out.println("setDocumentType");
    String documentType = "TEST";
    document.setDocumentType(documentType);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Document>> violations = validator.validate(document);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getDocumentContentType method, of class Document. */
  @Test
  public void testGetDocumentContentType() {
    System.out.println("getDocumentContentType");
    String expResult = "application/pdf";
    String result = document.getDocumentContentType();
    assertEquals(expResult, result);
  }

  /** Test of setDocumentContentType method, of class Document. */
  @Test
  public void testSetDocumentContentType() {
    System.out.println("setDocumentContentType");
    String documentContentType = "application/doc";
    document.setDocumentContentType(documentContentType);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Document>> violations = validator.validate(document);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getDocumentFilename method, of class Document. */
  @Test
  public void testGetDocumentFilename() {
    System.out.println("getDocumentFilename");
    String expResult = "test.pdf";
    String result = document.getDocumentFilename();
    assertEquals(expResult, result);
  }

  /** Test of setDocumentFilename method, of class Document. */
  @Test
  public void testSetDocumentFilename() {
    System.out.println("setDocumentFilename");
    String documentFilename = "test2.pdf";
    document.setDocumentFilename(documentFilename);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Document>> violations = validator.validate(document);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Test of getUser method, of class Document. */
  @Test
  public void testGetUser() {
    System.out.println("getUser");
    User expResult = user;
    User result = document.getUser();
    assertEquals(expResult, result);
  }

  /** Test of setUser method, of class Document. */
  @Test
  public void testSetUser() {
    System.out.println("setUser");
    User user = new User();
    document.setUser(user);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<Document>> violations = validator.validate(document);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
}
