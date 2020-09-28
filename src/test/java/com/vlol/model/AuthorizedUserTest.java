/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author kimbe
 */
public class AuthorizedUserTest {
    
private Validator validator;
    private final AuthorizedUser authorizeduser;

    public AuthorizedUserTest() {
        // Instantiate the AuthorizedUser object
        this.authorizeduser = new AuthorizedUser();
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws ParseException {
        // Populate the AuthorizedUser object before each test
        authorizeduser.setAuthorizedEmail("admin@vlol.gov");
        authorizeduser.setAuthorizedName("John Doe");
        authorizeduser.setAuthorizedUserId(3L);
        // Setup validation of each method's validation annotations
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAuthorizedUserId method, of class AuthorizedUser.
     */
    
     @Test
    public void testGetAuthorizedUserId() {
        System.out.println("getAuthorizedUserId Test (Passing value)");
        Long expResult = 3L;
        Long result = authorizeduser.getAuthorizedUserId();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAuthorizedUserId method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedUserId() {
        System.out.println("setAuthorizedUserId Test (Passing value)");
        Long authorizedUserId = 3L;
        authorizeduser.setAuthorizedUserId(authorizedUserId);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setAuthorizedUserId method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedUserIdNegative() {
        System.out.println("setAuthorizedUserId Test (Negative value)");
        Long authorizedUserId = -3L;
        authorizeduser.setAuthorizedUserId(authorizedUserId);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setAuthorizedUserId method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedUserIdNull() {
        System.out.println("setAuthorizedUserId Test (Null value)");
        Long authorizeduserId = null;
        authorizeduser.setAuthorizedUserId(authorizeduserId);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setAuthorizedUserId method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedUserIdOutOfRange() {
        System.out.println("setAuthorizedUserId Test (Out of Range value)");
        Long authorizeduserId = Long.MAX_VALUE + 1;
        authorizeduser.setAuthorizedUserId(authorizeduserId);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
    /**
     * Test of getAuthorizedEmail method, of class AuthorizedUser.
     */
     @Test
    public void testGetAuthorizedEmail() {
        System.out.println("getAuthorizedEmail Test (Passing value)");
        String expResult = "admin@vlol.gov";
        String result = authorizeduser.getAuthorizedEmail();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAuthorizedEmail method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedEmail() {
        System.out.println("setAuthorizedEmail Test (Passing value)");
        String authorizedemail = "admin@vlol.com";
        authorizeduser.setAuthorizedEmail(authorizedemail);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setAuthorizedEmail method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedEmailBlank() {
        System.out.println("setAuthorizedEmail Test (Blank value)");
        String authorizedusername = "";
        authorizeduser.setAuthorizedEmail(authorizedusername);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setAuthorizedEmail method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedEmailInvalid() {
        System.out.println("setAuthorizedEmail Test (Injection value)");
        String authorizedusername = "<script>alert(\"This is an attack!\");</script>";
        authorizeduser.setAuthorizedEmail(authorizedusername);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setAuthorizedEmail method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedEmailOverflow() {
        System.out.println("setAuthorizedEmail Test (Overflow value)");
        String authorizedusername = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        authorizeduser.setAuthorizedEmail(authorizedusername);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    } 
    /**
     * Test of getAuthorizedName method, of class AuthorizedUser.
     */
     /**
     * Passing test for the getAuthorizedName method, of class AuthorizedUser.
     */
    @Test
    public void testGetAuthorizedName() {
        System.out.println("getAuthorizedName Test (Passing value)");
        String expResult = "John Doe";
        String result = authorizeduser.getAuthorizedName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAuthorizedName method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedName() {
        System.out.println("setAuthorizedName Test (Passing value)");
        String lastName = "Doe";
        authorizeduser.setAuthorizedName(lastName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setAuthorizedName method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedNameBlank() {
        System.out.println("setAuthorizedName Test (Blank value)");
        String AuthorizedName = "";
        authorizeduser.setAuthorizedName(AuthorizedName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations;
    violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setAuthorizedName method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedNameInvalid() {
        System.out.println("setAuthorizedName Test (Injection value)");
        String authorizedName = "<script>alert(\"This is an attack!\");</script>";
        authorizeduser.setAuthorizedName(authorizedName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setAuthorizedName method, of class AuthorizedUser.
     */
    @Test
    public void testSetAuthorizedNameOverflow() {
        System.out.println("setAuthorizedName Test (Overflow value)");
        String authorizedName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
                + "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        authorizeduser.setAuthorizedName(authorizedName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<AuthorizedUser>> violations = validator.validate(authorizeduser);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
}
