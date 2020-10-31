/**
 * Unit Test of the User Class.
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.*;

public class UserInfoTest {

  private Validator validator;
  private final UserInfo userInfo;
  private final User user;

  public UserInfoTest() {
    // Instantiate the User object
    this.userInfo = new UserInfo();
    this.user = new User();
  }

  @BeforeAll
  public static void setUpClass() {}

  @AfterAll
  public static void tearDownClass() {}

  @BeforeEach
  public void setUp() throws ParseException {
    // Populate the User object before each test
    user.setUserId(1l);
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setEmail("jdoe@vlol.com");
    user.setPassword("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");

    userInfo.setUser(user);
    userInfo.setInfoId(1l);
    userInfo.setDOB(new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05"));
    userInfo.setSSN("123456789");
    userInfo.setStreetAddress("1 Main St");
    userInfo.setCity("Salisbury");
    userInfo.setState("MD");
    userInfo.setZipCode("21801");
    userInfo.setPhone("4108675309");
    userInfo.setInsCompany("TRICARE");
    userInfo.setInsPolicyNo("A123456789");
    userInfo.setPocName("Emmett Brown");
    userInfo.setPocPhone("4105555555");
    userInfo.setDoctorName("Emmett Brown");
    userInfo.setDoctorPhone("4105555555");
    userInfo.setUserComments("I usually use 2 lpm O2.");
    userInfo.setIsDeceased(Boolean.FALSE);
    // Setup validation of each method's validation annotations
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @AfterEach
  public void tearDown() {}

  /** Passing test for the getUserId method, of class User. */
  @Test
  public void testGetUserId() {
    System.out.println("getUserId Test (Passing value)");
    Long expResult = 1l;
    Long result = userInfo.getInfoId();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setInfoId method, of class User. */
  @Test
  public void testSetUserId() {
    System.out.println("setInfoId Test (Passing value)");
    Long userId = 1l;
    userInfo.setInfoId(userId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Negative value test for the setInfoId method, of class User. */
  @Test
  public void testSetUserIdNegative() {
    System.out.println("setInfoId Test (Negative value)");
    Long userId = -1l;
    userInfo.setInfoId(userId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Out of Range value test for the setInfoId method, of class User. */
  @Test
  public void testSetUserIdOutOfRange() {
    System.out.println("setInfoId Test (Out of Range value)");
    Long userId = Long.MAX_VALUE + 1;
    userInfo.setInfoId(userId);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /**
   * Passing test for the getDOB method, of class User.
   *
   * @throws java.text.ParseException
   */
  @Test
  public void testGetDOB() throws ParseException {
    System.out.println("getDOB Test (Passing value)");
    Date expResult = new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05");
    Date result = userInfo.getDOB();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /**
   * Passing test for the setDOB method, of class User.
   *
   * @throws java.text.ParseException
   */
  @Test
  public void testSetDOB() throws ParseException {
    System.out.println("setDOB Test (Passing value)");
    Date dob = new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05");
    userInfo.setDOB(dob);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  //    /**
  //     * Future value test for the setDOB method, of class User.
  //     */
  //    @Test
  //    public void testSetDOBFuture() {
  //        System.out.println("setDOB Test (Future value)");
  //        long millis = System.currentTimeMillis();
  //        java.sql.Date date = new java.sql.Date(millis + 7*24*60*60*1000);
  //        Date dob = date;
  //        userInfo.setDOB(dob);
  //        // Check for and print any violations of validation annotations
  //        Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
  //        String message = violations.iterator().hasNext() ?
  // violations.iterator().next().getMessage() : "";
  //        if (!violations.isEmpty()) {
  //            System.out.println("Violation caught: " + message);
  //        }
  //        // Test method
  //        assertFalse(violations.isEmpty());
  //    }

  /** Null value test for the setDOB method, of class User. */
  @Test
  public void testSetDOBNull() {
    System.out.println("setDOB Test (Null value)");
    Date dob = null;
    userInfo.setDOB(dob);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Passing test for the getSSN method, of class User. */
  @Test
  public void testGetSSN() {
    System.out.println("getSSN Test (Passing value)");
    String expResult = "123456789";
    String result = userInfo.getSSN();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setSSN method, of class User. */
  @Test
  public void testSetSSN() {
    System.out.println("setSSN Test (Passing value)");
    String ssn = "123456789";
    userInfo.setSSN(ssn);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setSSN method, of class User. */
  @Test
  public void testSetSSNBlank() {
    System.out.println("setSSN Test (Blank value)");
    String ssn = "";
    userInfo.setSSN(ssn);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setSSN method, of class User. */
  @Test
  public void testSetSSNInvalid() {
    System.out.println("setSSN Test (Injection value)");
    String ssn = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setSSN(ssn);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setSSN method, of class User. */
  @Test
  public void testSetSSNOverflow() {
    System.out.println("setSSN Test (Overflow value)");
    String ssn = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setSSN(ssn);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getStreetAddress method, of class User. */
  @Test
  public void testGetStreetAddress() {
    System.out.println("getStreetAddress Test (Passing value)");
    String expResult = "1 Main St";
    String result = userInfo.getStreetAddress();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setStreetAddress method, of class User. */
  @Test
  public void testSetStreetAddress() {
    System.out.println("setStreetAddress Test (Passing value)");
    String streetAddress = "1 Main St";
    userInfo.setStreetAddress(streetAddress);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setStreetAddress method, of class User. */
  @Test
  public void testSetStreetAddressBlank() {
    System.out.println("setStreetAddress Test (Blank value)");
    String streetAddress = "";
    userInfo.setStreetAddress(streetAddress);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setStreetAddress method, of class User. */
  @Test
  public void testSetStreetAddressInvalid() {
    System.out.println("setStreetAddress Test (Injection value)");
    String streetAddress = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setStreetAddress(streetAddress);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setStreetAddress method, of class User. */
  @Test
  public void testSetStreetAddressOverflow() {
    System.out.println("setStreetAddress Test (Overflow value)");
    String streetAddress =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    userInfo.setStreetAddress(streetAddress);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getCity method, of class User. */
  @Test
  public void testGetCity() {
    System.out.println("getCity Test (Passing value)");
    String expResult = "Salisbury";
    String result = userInfo.getCity();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setCity method, of class User. */
  @Test
  public void testSetCity() {
    System.out.println("setCity Test (Passing value)");
    String city = "Salisbury";
    userInfo.setCity(city);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setCity method, of class User. */
  @Test
  public void testSetCityBlank() {
    System.out.println("setCity Test (Blank value)");
    String city = "";
    userInfo.setCity(city);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setCity method, of class User. */
  @Test
  public void testSetCityInvalid() {
    System.out.println("setCity Test (Injection value)");
    String city = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setCity(city);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setCity method, of class User. */
  @Test
  public void testSetCityOverflow() {
    System.out.println("setCity Test (Overflow value)");
    String city = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setCity(city);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getState method, of class User. */
  @Test
  public void testGetState() {
    System.out.println("getState Test (Passing value)");
    String expResult = "MD";
    String result = userInfo.getState();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setState method, of class User. */
  @Test
  public void testSetState() {
    System.out.println("setState Test (Passing value)");
    String state = "MD";
    userInfo.setState(state);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setState method, of class User. */
  @Test
  public void testSetStateBlank() {
    System.out.println("setState Test (Blank value)");
    String state = "";
    userInfo.setState(state);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setState method, of class User. */
  @Test
  public void testSetStateInvalid() {
    System.out.println("setState Test (Injection value)");
    String state = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setState(state);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setState method, of class User. */
  @Test
  public void testSetStateOverflow() {
    System.out.println("setState Test (Overflow value)");
    String state = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setState(state);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getZipCode method, of class User. */
  @Test
  public void testGetZipCode() {
    System.out.println("getZipCode Test (Passing value)");
    String expResult = "21801";
    String result = userInfo.getZipCode();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setZipCode method, of class User. */
  @Test
  public void testSetZipCode() {
    System.out.println("setZipCode Test (Passing value)");
    String zipCode = "21801";
    userInfo.setZipCode(zipCode);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setZipCode method, of class User. */
  @Test
  public void testSetZipCodeBlank() {
    System.out.println("setZipCode Test (Blank value)");
    String zipCode = "";
    userInfo.setZipCode(zipCode);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setZipCode method, of class User. */
  @Test
  public void testSetZipCodeInvalid() {
    System.out.println("setZipCode Test (Injection value)");
    String zipCode = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setZipCode(zipCode);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setZipCode method, of class User. */
  @Test
  public void testSetZipCodeOverflow() {
    System.out.println("setZipCode Test (Overflow value)");
    String zipCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setZipCode(zipCode);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getPhone method, of class User. */
  @Test
  public void testGetPhone() {
    System.out.println("getPhone Test (Passing value)");
    String expResult = "4108675309";
    String result = userInfo.getPhone();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setPhone method, of class User. */
  @Test
  public void testSetPhone() {
    System.out.println("setPhone Test (Passing value)");
    String phone = "4108675309";
    userInfo.setPhone(phone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setPhone method, of class User. */
  @Test
  public void testSetPhoneBlank() {
    System.out.println("setPhone Test (Blank value)");
    String phone = "";
    userInfo.setPhone(phone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setPhone method, of class User. */
  @Test
  public void testSetPhoneInvalid() {
    System.out.println("setPhone Test (Injection value)");
    String phone = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setPhone(phone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setPhone method, of class User. */
  @Test
  public void testSetPhoneOverflow() {
    System.out.println("setPhone Test (Overflow value)");
    String phone = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setPhone(phone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getInsCompany method, of class User. */
  @Test
  public void testGetInsCompany() {
    System.out.println("getInsCompany Test (Passing value)");
    String expResult = "TRICARE";
    String result = userInfo.getInsCompany();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setInsCompany method, of class User. */
  @Test
  public void testSetInsCompany() {
    System.out.println("setInsCompany Test (Passing value)");
    String insCompany = "TRICARE";
    userInfo.setInsCompany(insCompany);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setInsCompany method, of class User. */
  @Test
  public void testSetInsCompanyBlank() {
    System.out.println("setInsCompany Test (Blank value)");
    String insCompany = "";
    userInfo.setInsCompany(insCompany);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setInsCompany method, of class User. */
  @Test
  public void testSetInsCompanyInvalid() {
    System.out.println("setInsCompany Test (Injection value)");
    String insCompany = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setInsCompany(insCompany);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setInsCompany method, of class User. */
  @Test
  public void testSetInsCompanyOverflow() {
    System.out.println("setInsCompany Test (Overflow value)");
    String insCompany = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setInsCompany(insCompany);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getInsPolicyNo method, of class User. */
  @Test
  public void testGetInsPolicyNo() {
    System.out.println("getInsPolicyNo Test (Passing value)");
    String expResult = "A123456789";
    String result = userInfo.getInsPolicyNo();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setInsPolicyNo method, of class User. */
  @Test
  public void testSetInsPolicyNo() {
    System.out.println("setInsPolicyNo Test (Passing value)");
    String insPolicyNo = "A12345789";
    userInfo.setInsPolicyNo(insPolicyNo);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setInsPolicyNo method, of class User. */
  @Test
  public void testSetInsPolicyNoBlank() {
    System.out.println("setInsPolicyNo Test (Blank value)");
    String insPolicyNo = "";
    userInfo.setInsPolicyNo(insPolicyNo);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setInsPolicyNo method, of class User. */
  @Test
  public void testSetInsPolicyNoInvalid() {
    System.out.println("setInsPolicyNo Test (Injection value)");
    String insPolicyNo = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setInsPolicyNo(insPolicyNo);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setInsPolicyNo method, of class User. */
  @Test
  public void testSetInsPolicyNoOverflow() {
    System.out.println("setInsPolicyNo Test (Overflow value)");
    String insPolicyNo = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setInsPolicyNo(insPolicyNo);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getPocName method, of class User. */
  @Test
  public void testGetPocName() {
    System.out.println("getPocName Test (Passing value)");
    String expResult = "Emmett Brown";
    String result = userInfo.getPocName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setPocName method, of class User. */
  @Test
  public void testSetPocName() {
    System.out.println("setPocName Test (Passing value)");
    String pocName = "Emmett Brown";
    userInfo.setPocName(pocName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setPocName method, of class User. */
  @Test
  public void testSetPocNameBlank() {
    System.out.println("setPocName Test (Blank value)");
    String pocName = "";
    userInfo.setPocName(pocName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setPocName method, of class User. */
  @Test
  public void testSetPocNameInvalid() {
    System.out.println("setPocName Test (Injection value)");
    String pocName = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setPocName(pocName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setPocName method, of class User. */
  @Test
  public void testSetPocNameOverflow() {
    System.out.println("setPocName Test (Overflow value)");
    String pocName =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    userInfo.setPocName(pocName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getPocPhone method, of class User. */
  @Test
  public void testGetPocPhone() {
    System.out.println("getPocPhone Test (Passing value)");
    String expResult = "4105555555";
    String result = userInfo.getPocPhone();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setPocPhone method, of class User. */
  @Test
  public void testSetPocPhone() {
    System.out.println("setPocPhone Test (Passing value)");
    String pocPhone = "4105555555";
    userInfo.setPocPhone(pocPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setPocPhone method, of class User. */
  @Test
  public void testSetPocPhoneBlank() {
    System.out.println("setPocPhone Test (Blank value)");
    String pocPhone = "";
    userInfo.setPocPhone(pocPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setPocPhone method, of class User. */
  @Test
  public void testSetPocPhoneInvalid() {
    System.out.println("setPocPhone Test (Injection value)");
    String pocPhone = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setPocPhone(pocPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setPocPhone method, of class User. */
  @Test
  public void testSetPocPhoneOverflow() {
    System.out.println("setPocPhone Test (Overflow value)");
    String pocPhone = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setPocPhone(pocPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getDoctorName method, of class User. */
  @Test
  public void testGetDoctorName() {
    System.out.println("getDoctorName Test (Passing value)");
    String expResult = "Emmett Brown";
    String result = userInfo.getDoctorName();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setDoctorName method, of class User. */
  @Test
  public void testSetDoctorName() {
    System.out.println("setDoctorName Test (Passing value)");
    String doctorName = "Emmett Brown";
    userInfo.setDoctorName(doctorName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setDoctorName method, of class User. */
  @Test
  public void testSetDoctorNameInvalid() {
    System.out.println("setDoctorName Test (Injection value)");
    String doctorName = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setDoctorName(doctorName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setDoctorName method, of class User. */
  @Test
  public void testSetDoctorNameOverflow() {
    System.out.println("setDoctorName Test (Overflow value)");
    String doctorName =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    userInfo.setDoctorName(doctorName);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getDoctorPhone method, of class User. */
  @Test
  public void testGetDoctorPhone() {
    System.out.println("getDoctorPhone Test (Passing value)");
    String expResult = "4105555555";
    String result = userInfo.getDoctorPhone();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setDoctorPhone method, of class User. */
  @Test
  public void testSetDoctorPhone() {
    System.out.println("setDoctorPhone Test (Passing value)");
    String doctorPhone = "4105555555";
    userInfo.setDoctorPhone(doctorPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setDoctorPhone method, of class User. */
  @Test
  public void testSetDoctorPhoneBlank() {
    System.out.println("setDoctorPhone Test (Blank value)");
    String doctorPhone = "";
    userInfo.setDoctorPhone(doctorPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setDoctorPhone method, of class User. */
  @Test
  public void testSetDoctorPhoneInvalid() {
    System.out.println("setDoctorPhone Test (Injection value)");
    String doctorPhone = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setDoctorPhone(doctorPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setDoctorPhone method, of class User. */
  @Test
  public void testSetDoctorPhoneOverflow() {
    System.out.println("setDoctorPhone Test (Overflow value)");
    String doctorPhone = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    userInfo.setDoctorPhone(doctorPhone);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getUserComments method, of class User. */
  @Test
  public void testGetUserComments() {
    System.out.println("getUserComments Test (Passing value)");
    String expResult = "I usually use 2 lpm O2.";
    String result = userInfo.getUserComments();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setUserComments method, of class User. */
  @Test
  public void testSetUserComments() {
    System.out.println("setUserComments Test (Passing value)");
    String userComments = "I usually use 2 lpm O2.";
    userInfo.setUserComments(userComments);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Blank value test for the setUserComments method, of class User. */
  @Test
  public void testSetUserCommentsBlank() {
    System.out.println("setUserComments Test (Blank value)");
    String userComments = "";
    userInfo.setUserComments(userComments);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Invalid value test for the setUserComments method, of class User. */
  @Test
  public void testSetUserCommentsInvalid() {
    System.out.println("setUserComments Test (Injection value)");
    String userComments = "<script>alert(\"This is an attack!\");</script>";
    userInfo.setUserComments(userComments);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Overflow value test for the setUserComments method, of class User. */
  @Test
  public void testSetUserCommentsOverflow() {
    System.out.println("setUserComments Test (Overflow value)");
    String userComments = ".".repeat(65536);
    userInfo.setUserComments(userComments);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertFalse(violations.isEmpty());
  }

  /** Passing test for the getIsDeceased method, of class User. */
  @Test
  public void testGetIsDeceased() {
    System.out.println("getIsDeceased Test (Passing value)");
    Boolean expResult = Boolean.FALSE;
    Boolean result = userInfo.getIsDeceased();
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertEquals(expResult, result);
  }

  /** Passing test for the setIsDeceased method, of class User. */
  @Test
  public void testSetIsDeceased() {
    System.out.println("setIsDeceased Test (Passing value)");
    Boolean isDeceased = Boolean.TRUE;
    userInfo.setIsDeceased(isDeceased);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }

  /** Null value test for the setIsDeceased method, of class User. */
  @Test
  public void testSetIsDeceasedNull() {
    System.out.println("setIsDeceased Test (Null value)");
    Boolean isDeceased = null;
    userInfo.setIsDeceased(isDeceased);
    // Check for and print any violations of validation annotations
    Set<ConstraintViolation<UserInfo>> violations = validator.validate(userInfo);
    String message =
        violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
    if (!violations.isEmpty()) {
      System.out.println("Violation caught: " + message);
    }
    // Test method
    assertTrue(violations.isEmpty());
  }
}
