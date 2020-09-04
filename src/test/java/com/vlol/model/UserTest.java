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
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
        user.setEmail("jdoe@vlol.com");
        user.setPassword("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");
        //user.setSecurityQuestion("Favorite numbers.");
        //user.setSecurityAnswer("$2a$10$EblZqNptyYvcLm/VwDCVAuBjzZOI7khzdyGPBr08PpIi0na624b8.");
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
     * Null value test for the setUserID method, of class User.
     */
    @Test
    public void testSetUserIDNull() {
        System.out.println("setUserID Test (Null value)");
        Long userID = null;
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
     * Passing test for the getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail Test (Passing value)");
        String expResult = "jdoe@vlol.com";
        String result = user.getEmail();
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
     * Passing test for the setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail Test (Passing value)");
        String email = "jdoe@vlol.com";
        user.setEmail(email);
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
     * Blank value test for the setEmail method, of class User.
     */
    @Test
    public void testSetEmailBlank() {
        System.out.println("setEmail Test (Blank value)");
        String username = "";
        user.setEmail(username);
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
     * Invalid value test for the setEmail method, of class User.
     */
    @Test
    public void testSetEmailInvalid() {
        System.out.println("setEmail Test (Injection value)");
        String username = "<script>alert(\"This is an attack!\");</script>";
        user.setEmail(username);
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
     * Overflow value test for the setEmail method, of class User.
     */
    @Test
    public void testSetEmailOverflow() {
        System.out.println("setEmail Test (Overflow value)");
        String username = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        user.setEmail(username);
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
     * Out of Range value test for the setDateCreated method, of class User.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetDateCreatedOutOfRange() throws ParseException {
        System.out.println("setDateCreated Test (Out of Range value)");
        Date dateCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("1955-13-05 06:00:00");
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
     * Blank value test for the setAdminComments method, of class User.
     */
    @Test
    public void testSetAdminCommentsBlank() {
        System.out.println("setAdminComments Test (Blank value)");
        String adminComments = "";
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
