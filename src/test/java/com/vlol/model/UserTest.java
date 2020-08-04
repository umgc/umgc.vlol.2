/**
 * Unit Test of the User Class.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category vlol
 * @package model
 * @author Rob Garcia <rgarcia92@student.umgc.edu> and Mohammed Allibalogun
 * <mohammed.allibalogun@gmail.com>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    private Validator validator;
    private final User user;

    public UserTest() {
        // Instantiate the User object
        this.user = new User();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws ParseException {
        // Populate the User object before each test
        user.setUserID(1l);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setDOB(new SimpleDateFormat("yyyy-MM-dd").parse("1955-11-05"));
        user.setSSN("123456789");
        user.setStreetAddress("1 Main St");
        user.setCity("Salisbury");
        user.setState("MD");
        user.setZipCode("21801");
        user.setPhone("4108675309");
        user.setInsCompany("TRICARE");
        user.setInsPolicyNo("A123456789");
        user.setAdvDirective(Boolean.TRUE);
        user.setAdvDirType("MOLST");
        user.setPocName("Emmett Brown");
        user.setPocPhone("4105555555");
        user.setUserAgentNo(1l);
        user.setDoctorName("Emmett Brown");
        user.setDoctorPhone("4105555555");
        user.setUserComments("I usually use 2 lpm O2.");
        user.setUsername("jdoe@vlol.com");
        user.setPassword("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");
        user.setSecurityQuestion("Favorite numbers.");
        user.setSecurityAnswer("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");
        user.setDateCreated(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00"));
        user.setLastLoginDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00"));
        user.setAdminComments("John is a program participant.");
        user.setIsActive(Boolean.TRUE);
        user.setIsLocked(Boolean.FALSE);
        // Setup validation of each method's validation annotations
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Passing test for the getUserID method, of class User.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID Test (Passing value)");
        Long expResult = 1l;
        Long result = user.getUserID();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setUserID method, of class User.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID Test (Passing value)");
        Long userID = 1l;
        user.setUserID(userID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setUserID method, of class User.
     */
    @Test
    public void testSetUserIDNegative() {
        System.out.println("setUserID Test (Negative value)");
        Long userID = -1l;
        user.setUserID(userID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setUserID method, of class User.
     */
    @Test
    public void testSetUserIDOutOfRange() {
        System.out.println("setUserID Test (Out of Range value)");
        Long userID = Long.MAX_VALUE + 1;
        user.setUserID(userID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName Test (Passing value)");
        String expResult = "John";
        String result = user.getFirstName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName Test (Passing value)");
        String firstName = "John";
        user.setFirstName(firstName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setFirstName method, of class User.
     */
    @Test
    public void testSetFirstNameBlank() {
        System.out.println("setFirstName Test (Blank value)");
        String firstName = "";
        user.setFirstName(firstName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setFirstName method, of class User.
     */
    @Test
    public void testSetFirstNameInvalid() {
        System.out.println("setFirstName Test (Injection value)");
        String firstName = "<script>alert(\"This is an attack!\");</script>";
        user.setFirstName(firstName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setFirstName method, of class User.
     */
    @Test
    public void testSetFirstNameOverflow() {
        System.out.println("setFirstName Test (Overflow value)");
        String firstName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setFirstName(firstName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName Test (Passing value)");
        String expResult = "Doe";
        String result = user.getLastName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setLastName method, of class User.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName Test (Passing value)");
        String lastName = "Doe";
        user.setLastName(lastName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setLastName method, of class User.
     */
    @Test
    public void testSetLastNameBlank() {
        System.out.println("setLastName Test (Blank value)");
        String lastName = "";
        user.setLastName(lastName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setLastName method, of class User.
     */
    @Test
    public void testSetLastNameInvalid() {
        System.out.println("setLastName Test (Injection value)");
        String lastName = "<script>alert(\"This is an attack!\");</script>";
        user.setLastName(lastName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setLastName method, of class User.
     */
    @Test
    public void testSetLastNameOverflow() {
        System.out.println("setLastName Test (Overflow value)");
        String lastName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setLastName(lastName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
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
        Date result = user.getDOB();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
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
        user.setDOB(dob);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Future value test for the setDOB method, of class User.
     */
    @Test
    public void testSetDOBFuture() throws ParseException {
        System.out.println("setDOB Test (Future value)");
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis + 60000);
        user.setDOB(new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-31"));
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setDOB method, of class User.
     */
    @Test
    public void testSetDOBNull() {
        System.out.println("setDOB Test (Null value)");
        Date dob = null;
        user.setDOB(dob);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getSSN method, of class User.
     */
    @Test
    public void testGetSSN() {
        System.out.println("getSSN Test (Passing value)");
        String expResult = "123456789";
        String result = user.getSSN();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setSSN method, of class User.
     */
    @Test
    public void testSetSSN() {
        System.out.println("setSSN Test (Passing value)");
        String ssn = "123456789";
        user.setSSN(ssn);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setSSN method, of class User.
     */
    @Test
    public void testSetSSNBlank() {
        System.out.println("setSSN Test (Blank value)");
        String ssn = "";
        user.setSSN(ssn);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setSSN method, of class User.
     */
    @Test
    public void testSetSSNInvalid() {
        System.out.println("setSSN Test (Injection value)");
        String ssn = "<script>alert(\"This is an attack!\");</script>";
        user.setSSN(ssn);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setSSN method, of class User.
     */
    @Test
    public void testSetSSNOverflow() {
        System.out.println("setSSN Test (Overflow value)");
        String ssn = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setSSN(ssn);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getStreetAddress method, of class User.
     */
    @Test
    public void testGetStreetAddress() {
        System.out.println("getStreetAddress Test (Passing value)");
        String expResult = "1 Main St";
        String result = user.getStreetAddress();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setStreetAddress method, of class User.
     */
    @Test
    public void testSetStreetAddress() {
        System.out.println("setStreetAddress Test (Passing value)");
        String streetAddress = "1 Main St";
        user.setStreetAddress(streetAddress);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setStreetAddress method, of class User.
     */
    @Test
    public void testSetStreetAddressBlank() {
        System.out.println("setStreetAddress Test (Blank value)");
        String streetAddress = "";
        user.setStreetAddress(streetAddress);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setStreetAddress method, of class User.
     */
    @Test
    public void testSetStreetAddressInvalid() {
        System.out.println("setStreetAddress Test (Injection value)");
        String streetAddress = "<script>alert(\"This is an attack!\");</script>";
        user.setStreetAddress(streetAddress);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setStreetAddress method, of class User.
     */
    @Test
    public void testSetStreetAddressOverflow() {
        System.out.println("setStreetAddress Test (Overflow value)");
        String streetAddress = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setStreetAddress(streetAddress);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getCity method, of class User.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity Test (Passing value)");
        String expResult = "Salisbury";
        String result = user.getCity();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setCity method, of class User.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity Test (Passing value)");
        String city = "Salisbury";
        user.setCity(city);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setCity method, of class User.
     */
    @Test
    public void testSetCityBlank() {
        System.out.println("setCity Test (Blank value)");
        String city = "";
        user.setCity(city);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setCity method, of class User.
     */
    @Test
    public void testSetCityInvalid() {
        System.out.println("setCity Test (Injection value)");
        String city = "<script>alert(\"This is an attack!\");</script>";
        user.setCity(city);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setCity method, of class User.
     */
    @Test
    public void testSetCityOverflow() {
        System.out.println("setCity Test (Overflow value)");
        String city = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setCity(city);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getState method, of class User.
     */
    @Test
    public void testGetState() {
        System.out.println("getState Test (Passing value)");
        String expResult = "MD";
        String result = user.getState();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setState method, of class User.
     */
    @Test
    public void testSetState() {
        System.out.println("setState Test (Passing value)");
        String state = "MD";
        user.setState(state);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setState method, of class User.
     */
    @Test
    public void testSetStateBlank() {
        System.out.println("setState Test (Blank value)");
        String state = "";
        user.setState(state);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setState method, of class User.
     */
    @Test
    public void testSetStateInvalid() {
        System.out.println("setState Test (Injection value)");
        String state = "<script>alert(\"This is an attack!\");</script>";
        user.setState(state);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setState method, of class User.
     */
    @Test
    public void testSetStateOverflow() {
        System.out.println("setState Test (Overflow value)");
        String state = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setState(state);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getZipCode method, of class User.
     */
    @Test
    public void testGetZipCode() {
        System.out.println("getZipCode Test (Passing value)");
        String expResult = "21801";
        String result = user.getZipCode();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setZipCode method, of class User.
     */
    @Test
    public void testSetZipCode() {
        System.out.println("setZipCode Test (Passing value)");
        String zipCode = "21801";
        user.setZipCode(zipCode);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setZipCode method, of class User.
     */
    @Test
    public void testSetZipCodeBlank() {
        System.out.println("setZipCode Test (Blank value)");
        String zipCode = "";
        user.setZipCode(zipCode);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setZipCode method, of class User.
     */
    @Test
    public void testSetZipCodeInvalid() {
        System.out.println("setZipCode Test (Injection value)");
        String zipCode = "<script>alert(\"This is an attack!\");</script>";
        user.setZipCode(zipCode);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setZipCode method, of class User.
     */
    @Test
    public void testSetZipCodeOverflow() {
        System.out.println("setZipCode Test (Overflow value)");
        String zipCode = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setZipCode(zipCode);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getPhone method, of class User.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone Test (Passing value)");
        String expResult = "4108675309";
        String result = user.getPhone();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setPhone method, of class User.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone Test (Passing value)");
        String phone = "4108675309";
        user.setPhone(phone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setPhone method, of class User.
     */
    @Test
    public void testSetPhoneBlank() {
        System.out.println("setPhone Test (Blank value)");
        String phone = "";
        user.setPhone(phone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setPhone method, of class User.
     */
    @Test
    public void testSetPhoneInvalid() {
        System.out.println("setPhone Test (Injection value)");
        String phone = "<script>alert(\"This is an attack!\");</script>";
        user.setPhone(phone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setPhone method, of class User.
     */
    @Test
    public void testSetPhoneOverflow() {
        System.out.println("setPhone Test (Overflow value)");
        String phone = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setPhone(phone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getInsCompany method, of class User.
     */
    @Test
    public void testGetInsCompany() {
        System.out.println("getInsCompany Test (Passing value)");
        String expResult = "TRICARE";
        String result = user.getInsCompany();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setInsCompany method, of class User.
     */
    @Test
    public void testSetInsCompany() {
        System.out.println("setInsCompany Test (Passing value)");
        String insCompany = "TRICARE";
        user.setInsCompany(insCompany);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setInsCompany method, of class User.
     */
    @Test
    public void testSetInsCompanyInvalid() {
        System.out.println("setInsCompany Test (Injection value)");
        String insCompany = "<script>alert(\"This is an attack!\");</script>";
        user.setInsCompany(insCompany);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setInsCompany method, of class User.
     */
    @Test
    public void testSetInsCompanyOverflow() {
        System.out.println("setInsCompany Test (Overflow value)");
        String insCompany = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setInsCompany(insCompany);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getInsPolicyNo method, of class User.
     */
    @Test
    public void testGetInsPolicyNo() {
        System.out.println("getInsPolicyNo Test (Passing value)");
        String expResult = "A123456789";
        String result = user.getInsPolicyNo();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setInsPolicyNo method, of class User.
     */
    @Test
    public void testSetInsPolicyNo() {
        System.out.println("setInsPolicyNo Test (Passing value)");
        String insPolicyNo = "A12345789";
        user.setInsPolicyNo(insPolicyNo);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setInsPolicyNo method, of class User.
     */
    @Test
    public void testSetInsPolicyNoInvalid() {
        System.out.println("setInsPolicyNo Test (Injection value)");
        String insPolicyNo = "<script>alert(\"This is an attack!\");</script>";
        user.setInsPolicyNo(insPolicyNo);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setInsPolicyNo method, of class User.
     */
    @Test
    public void testSetInsPolicyNoOverflow() {
        System.out.println("setInsPolicyNo Test (Overflow value)");
        String insPolicyNo = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setInsPolicyNo(insPolicyNo);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getAdvDirective method, of class User.
     */
    @Test
    public void testGetAdvDirective() {
        System.out.println("getAdvDirective Test (Passing value)");
        Boolean expResult = Boolean.TRUE;
        Boolean result = user.getAdvDirective();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAdvDirective method, of class User.
     */
    @Test
    public void testSetAdvDirective() {
        System.out.println("setAdvDirective Test (Passing value)");
        Boolean advDirective = Boolean.TRUE;
        user.setAdvDirective(advDirective);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Null value test for the setAdvDirective method, of class User.
     */
    @Test
    public void testSetAdvDirectiveNull() {
        System.out.println("setAdvDirective Test (Null value)");
        Boolean advDirective = null;
        user.setAdvDirective(advDirective);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getAdvDirType method, of class User.
     */
    @Test
    public void testGetAdvDirType() {
        System.out.println("getAdvDirType Test (Passing value)");
        String expResult = "MOLST";
        String result = user.getAdvDirType();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAdvDirType method, of class User.
     */
    @Test
    public void testSetAdvDirType() {
        System.out.println("setAdvDirType Test (Passing value)");
        String advDirType = "MOLST";
        user.setAdvDirType(advDirType);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setAdvDirType method, of class User.
     */
    @Test
    public void testSetAdvDirTypeInvalid() {
        System.out.println("setAdvDirType Test (Injection value)");
        String advDirType = "<script>alert(\"This is an attack!\");</script>";
        user.setAdvDirType(advDirType);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setAdvDirType method, of class User.
     */
    @Test
    public void testSetAdvDirTypeOverflow() {
        System.out.println("setAdvDirType Test (Overflow value)");
        String advDirType = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setAdvDirType(advDirType);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getPocName method, of class User.
     */
    @Test
    public void testGetPocName() {
        System.out.println("getPocName Test (Passing value)");
        String expResult = "Emmett Brown";
        String result = user.getPocName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setPocName method, of class User.
     */
    @Test
    public void testSetPocName() {
        System.out.println("setPocName Test (Passing value)");
        String pocName = "Emmett Brown";
        user.setPocName(pocName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setPocName method, of class User.
     */
    @Test
    public void testSetPocNameBlank() {
        System.out.println("setPocName Test (Blank value)");
        String pocName = "";
        user.setPocName(pocName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setPocName method, of class User.
     */
    @Test
    public void testSetPocNameInvalid() {
        System.out.println("setPocName Test (Injection value)");
        String pocName = "<script>alert(\"This is an attack!\");</script>";
        user.setPocName(pocName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setPocName method, of class User.
     */
    @Test
    public void testSetPocNameOverflow() {
        System.out.println("setPocName Test (Overflow value)");
        String pocName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setPocName(pocName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getPocPhone method, of class User.
     */
    @Test
    public void testGetPocPhone() {
        System.out.println("getPocPhone Test (Passing value)");
        String expResult = "4105555555";
        String result = user.getPocPhone();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setPocPhone method, of class User.
     */
    @Test
    public void testSetPocPhone() {
        System.out.println("setPocPhone Test (Passing value)");
        String pocPhone = "4105555555";
        user.setPocPhone(pocPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setPocPhone method, of class User.
     */
    @Test
    public void testSetPocPhoneBlank() {
        System.out.println("setPocPhone Test (Blank value)");
        String pocPhone = "";
        user.setPocPhone(pocPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setPocPhone method, of class User.
     */
    @Test
    public void testSetPocPhoneInvalid() {
        System.out.println("setPocPhone Test (Injection value)");
        String pocPhone = "<script>alert(\"This is an attack!\");</script>";
        user.setPocPhone(pocPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setPocPhone method, of class User.
     */
    @Test
    public void testSetPocPhoneOverflow() {
        System.out.println("setPocPhone Test (Overflow value)");
        String pocPhone = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setPocPhone(pocPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getUserAgentNo method, of class User.
     */
    @Test
    public void testGetUserAgentNo() {
        System.out.println("getUserAgentNo Test (Passing value)");
        Long expResult = 1l;
        Long result = user.getUserAgentNo();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setUserAgentNo method, of class User.
     */
    @Test
    public void testSetUserAgentNo() {
        System.out.println("setUserAgentNo Test (Passing value)");
        Long userAgentNo = 1l;
        user.setUserAgentNo(userAgentNo);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setUserAgentNo method, of class User.
     */
    @Test
    public void testSetUserAgentNoNegative() {
        System.out.println("setUserAgentNo Test (Negative value)");
        Long userAgentNo = -1l;
        user.setUserAgentNo(userAgentNo);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setUserAgentNo method, of class User.
     */
    @Test
    public void testSetUserAgentNoOutOfRange() {
        System.out.println("setUserAgentNo Test (Out of Range value)");
        Long userAgentNo = Long.MAX_VALUE + 1;
        user.setUserAgentNo(userAgentNo);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getDoctorName method, of class User.
     */
    @Test
    public void testGetDoctorName() {
        System.out.println("getDoctorName Test (Passing value)");
        String expResult = "Emmett Brown";
        String result = user.getDoctorName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setDoctorName method, of class User.
     */
    @Test
    public void testSetDoctorName() {
        System.out.println("setDoctorName Test (Passing value)");
        String doctorName = "Emmett Brown";
        user.setDoctorName(doctorName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setDoctorName method, of class User.
     */
    @Test
    public void testSetDoctorNameInvalid() {
        System.out.println("setDoctorName Test (Injection value)");
        String doctorName = "<script>alert(\"This is an attack!\");</script>";
        user.setDoctorName(doctorName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setDoctorName method, of class User.
     */
    @Test
    public void testSetDoctorNameOverflow() {
        System.out.println("setDoctorName Test (Overflow value)");
        String doctorName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setDoctorName(doctorName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getDoctorPhone method, of class User.
     */
    @Test
    public void testGetDoctorPhone() {
        System.out.println("getDoctorPhone Test (Passing value)");
        String expResult = "4105555555";
        String result = user.getDoctorPhone();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setDoctorPhone method, of class User.
     */
    @Test
    public void testSetDoctorPhone() {
        System.out.println("setDoctorPhone Test (Passing value)");
        String doctorPhone = "4105555555";
        user.setDoctorPhone(doctorPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setDoctorPhone method, of class User.
     */
    @Test
    public void testSetDoctorPhoneInvalid() {
        System.out.println("setDoctorPhone Test (Injection value)");
        String doctorPhone = "<script>alert(\"This is an attack!\");</script>";
        user.setDoctorPhone(doctorPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setDoctorPhone method, of class User.
     */
    @Test
    public void testSetDoctorPhoneOverflow() {
        System.out.println("setDoctorPhone Test (Overflow value)");
        String doctorPhone = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        user.setDoctorPhone(doctorPhone);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getUserComments method, of class User.
     */
    @Test
    public void testGetUserComments() {
        System.out.println("getUserComments Test (Passing value)");
        String expResult = "I usually use 2 lpm O2.";
        String result = user.getUserComments();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setUserComments method, of class User.
     */
    @Test
    public void testSetUserComments() {
        System.out.println("setUserComments Test (Passing value)");
        String userComments = "I usually use 2 lpm O2.";
        user.setUserComments(userComments);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setUserComments method, of class User.
     */
    @Test
    public void testSetUserCommentsInvalid() {
        System.out.println("setUserComments Test (Injection value)");
        String userComments = "<script>alert(\"This is an attack!\");</script>";
        user.setUserComments(userComments);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setUserComments method, of class User.
     */
    @Test
    public void testSetUserCommentsOverflow() {
        System.out.println("setUserComments Test (Overflow value)");
        String userComments = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setUserComments(userComments);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername Test (Passing value)");
        String expResult = "jdoe@vlol.com";
        String result = user.getUsername();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername Test (Passing value)");
        String username = "jdoe@vlol.com";
        user.setUsername(username);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setUsername method, of class User.
     */
    @Test
    public void testSetUsernameBlank() {
        System.out.println("setUsername Test (Blank value)");
        String username = "";
        user.setUsername(username);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setUsername method, of class User.
     */
    @Test
    public void testSetUsernameInvalid() {
        System.out.println("setUsername Test (Injection value)");
        String username = "<script>alert(\"This is an attack!\");</script>";
        user.setUsername(username);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setUsername method, of class User.
     */
    @Test
    public void testSetUsernameOverflow() {
        System.out.println("setUsername Test (Overflow value)");
        String username = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setUsername(username);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword Test (Passing value)");
        String expResult = "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.";
        String result = user.getPassword();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword Test (Passing value)");
        String password = "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.";
        user.setPassword(password);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setPassword method, of class User.
     */
    @Test
    public void testSetPasswordBlank() {
        System.out.println("setPassword Test (Blank value)");
        String password = "";
        user.setPassword(password);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setPassword method, of class User.
     */
    @Test
    public void testSetPasswordInvalid() {
        System.out.println("setPassword Test (Injection value)");
        String password = "<script>alert(\"This is an attack!\");</script>";
        user.setPassword(password);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setPassword method, of class User.
     */
    @Test
    public void testSetPasswordOverflow() {
        System.out.println("setPassword Test (Overflow value)");
        String password = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setPassword(password);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getSecurityQuestion method, of class User.
     */
    @Test
    public void testGetSecurityQuestion() {
        System.out.println("getSecurityQuestion Test (Passing value)");
        String expResult = "Favorite numbers.";
        String result = user.getSecurityQuestion();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setSecurityQuestion method, of class User.
     */
    @Test
    public void testSetSecurityQuestion() {
        System.out.println("setSecurityQuestion Test (Passing value)");
        String securityQuestion = "Favorite numbers.";
        user.setSecurityQuestion(securityQuestion);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setSecurityQuestion method, of class User.
     */
    @Test
    public void testSetSecurityQuestionBlank() {
        System.out.println("setSecurityQuestion Test (Blank value)");
        String securityQuestion = "";
        user.setSecurityQuestion(securityQuestion);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setSecurityQuestion method, of class User.
     */
    @Test
    public void testSetSecurityQuestionInvalid() {
        System.out.println("setSecurityQuestion Test (Injection value)");
        String securityQuestion = "<script>alert(\"This is an attack!\");</script>";
        user.setSecurityQuestion(securityQuestion);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setSecurityQuestion method, of class User.
     */
    @Test
    public void testSetSecurityQuestionOverflow() {
        System.out.println("setSecurityQuestion Test (Overflow value)");
        String securityQuestion = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setSecurityQuestion(securityQuestion);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getSecurityAnswer method, of class User.
     */
    @Test
    public void testGetSecurityAnswer() {
        System.out.println("getSecurityAnswer Test (Passing value)");
        String expResult = "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.";
        String result = user.getSecurityAnswer();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setSecurityAnswer method, of class User.
     */
    @Test
    public void testSetSecurityAnswer() {
        System.out.println("setSecurityAnswer Test (Passing value)");
        String securityAnswer = "$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.";
        user.setSecurityAnswer(securityAnswer);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setSecurityAnswer method, of class User.
     */
    @Test
    public void testSetSecurityAnswerBlank() {
        System.out.println("setSecurityAnswer Test (Blank value)");
        String securityAnswer = "";
        user.setSecurityAnswer(securityAnswer);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setSecurityAnswer method, of class User.
     */
    @Test
    public void testSetSecurityAnswerInvalid() {
        System.out.println("setSecurityAnswer Test (Injection value)");
        String securityAnswer = "<script>alert(\"This is an attack!\");</script>";
        user.setSecurityAnswer(securityAnswer);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setSecurityAnswer method, of class User.
     */
    @Test
    public void testSetSecurityAnswerOverflow() {
        System.out.println("setSecurityAnswer Test (Overflow value)");
        String securityAnswer = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setSecurityAnswer(securityAnswer);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getDateCreated method, of class User.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testGetDateCreated() throws ParseException {
        System.out.println("getDateCreated Test (Passing value)");
        Date expResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00");
        Date result = user.getDateCreated();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setDateCreated method, of class User.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetDateCreated() throws ParseException {
        System.out.println("setDateCreated Test (Passing value)");
        Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00");
        user.setDateCreated(dateCreated);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Future value test for the setDateCreated method, of class User.
     */
    @Test
    public void testSetDateCreatedFuture() {
        System.out.println("setDateCreated Test (Future value)");
        long millis = System.currentTimeMillis();
        Date date = new Date(millis + 60000);
        Date dateCreated = date;
        user.setDateCreated(dateCreated);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setDateCreated method, of class User.
     */
    @Test
    public void testSetDateCreatedNull() {
        System.out.println("setDateCreated Test (Null value)");
        Date dateCreated = null;
        user.setDateCreated(dateCreated);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getLastLoginDate method, of class User.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testGetLastLoginDate() throws ParseException {
        System.out.println("getLastLoginDate Test (Passing value)");
        Date expResult = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00");
        Date result = user.getLastLoginDate();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setLastLoginDate method, of class User.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetLastLoginDate() throws ParseException {
        System.out.println("setLastLoginDate Test (Passing value)");
        Date lastLoginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-11-05 06:00:00");
        user.setLastLoginDate(lastLoginDate);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Future value test for the setLastLoginDate method, of class User.
     */
    @Test
    public void testSetLastLoginDateFuture() {
        System.out.println("setLastLoginDate Test (Future value)");
        long millis = System.currentTimeMillis();
        Date date = new Date(millis + 60000);
        Date lastLoginDate = date;
        user.setLastLoginDate(lastLoginDate);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setLastLoginDate method, of class User.
     */
    @Test
    public void testSetLastLoginDateNull() {
        System.out.println("setLastLoginDate Test (Null value)");
        Date lastLoginDate = null;
        user.setLastLoginDate(lastLoginDate);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getAdminComments method, of class User.
     */
    @Test
    public void testGetAdminComments() {
        System.out.println("getAdminComments Test (Passing value)");
        String expResult = "John is a program participant.";
        String result = user.getAdminComments();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAdminComments method, of class User.
     */
    @Test
    public void testSetAdminComments() {
        System.out.println("setAdminComments Test (Passing value)");
        String adminComments = "participant";
        user.setAdminComments(adminComments);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setAdminComments method, of class User.
     */
    @Test
    public void testSetAdminCommentsInvalid() {
        System.out.println("setAdminComments Test (Injection value)");
        String adminComments = "<script>alert(\"This is an attack!\");</script>";
        user.setAdminComments(adminComments);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setAdminComments method, of class User.
     */
    @Test
    public void testSetAdminCommentsOverflow() {
        System.out.println("setAdminComments Test (Overflow value)");
        String adminComments = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setAdminComments(adminComments);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getIsActive method, of class User.
     */
    @Test
    public void testGetIsActive() {
        System.out.println("getIsActive Test (Passing value)");
        Boolean expResult = Boolean.TRUE;
        Boolean result = user.getIsActive();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setIsActive method, of class User.
     */
    @Test
    public void testSetIsActive() {
        System.out.println("setIsActive Test (Passing value)");
        Boolean isActive = Boolean.TRUE;
        user.setIsActive(isActive);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Null value test for the setIsActive method, of class User.
     */
    @Test
    public void testSetIsActiveNull() {
        System.out.println("setIsActive Test (Null value)");
        Boolean isActive = null;
        user.setIsActive(isActive);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getIsLocked method, of class User.
     */
    @Test
    public void testGetIsLocked() {
        System.out.println("getIsLocked Test (Passing value)");
        Boolean expResult = Boolean.FALSE;
        Boolean result = user.getIsLocked();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setIsLocked method, of class User.
     */
    @Test
    public void testSetIsLocked() {
        System.out.println("setIsLocked Test (Passing value)");
        Boolean isLocked = Boolean.FALSE;
        user.setIsLocked(isLocked);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Null value test for the setIsLocked method, of class User.
     */
    @Test
    public void testSetIsLockedNull() {
        System.out.println("setIsLocked Test (Null value)");
        Boolean isLocked = null;
        user.setIsLocked(isLocked);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
}
