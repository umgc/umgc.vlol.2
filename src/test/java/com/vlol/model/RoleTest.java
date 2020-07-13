/**
 * Unit Test of the Role Class.
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

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoleTest {

    private Validator validator;
    private final Role role;

    public RoleTest() {
        // Instantiate the Role object
        this.role = new Role();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Populate the Role object before each test
        role.setRoleID(1l);
        role.setTitle("participant");
        role.setDescription("Program Participant (Profile Access Only)");
        role.setAccessLevel(1);
        // Setup validation of each method's validation annotations
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void tearDown() {
    }

    /**
     * Passing test for the getRoleID method, of class Role.
     */
    @Test
    public void testGetRoleID() {
        System.out.println("getRoleID Test (Passing value)");
        Long expResult = 1l;
        Long result = role.getRoleID();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setRoleID method, of class Role.
     */
    @Test
    public void testSetRoleID() {
        System.out.println("setRoleID Test (Passing value)");
        Long roleID = 1l;
        role.setRoleID(roleID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setRoleID method, of class Role.
     */
    @Test
    public void testSetRoleIDNegative() {
        System.out.println("setRoleID Test (Negative value)");
        Long roleID = -1l;
        role.setRoleID(roleID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setRoleID method, of class Role.
     */
    @Test
    public void testSetRoleIDNull() {
        System.out.println("setRoleID Test (Null value)");
        Long roleID = null;
        role.setRoleID(roleID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setRoleID method, of class Role.
     */
    @Test
    public void testSetRoleIDOutOfRange() {
        System.out.println("setRoleID Test (Out of Range value)");
        Long roleID = Long.MAX_VALUE + 1;
        role.setRoleID(roleID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getTitle method, of class Role.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle Test (Passing value)");
        String expResult = "participant";
        String result = role.getTitle();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setTitle method, of class Role.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle Test (Passing value)");
        String title = "participant";
        role.setTitle(title);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setTitle method, of class Role.
     */
    @Test
    public void testSetTitleBlank() {
        System.out.println("setTitle Test (Blank value)");
        String title = "";
        role.setTitle(title);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setTitle method, of class Role.
     */
    @Test
    public void testSetTitleInvalid() {
        System.out.println("setTitle Test (Injection value)");
        String title = "<script>alert(\"This is an attack!\");</script>";
        role.setTitle(title);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setTitle method, of class Role.
     */
    @Test
    public void testSetTitleOverflow() {
        System.out.println("setTitle Test (Overflow value)");
        String title = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        role.setTitle(title);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getDescription method, of class Role.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription Test (Passing value)");
        String expResult = "Program Participant (Profile Access Only)";
        String result = role.getDescription();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setDescription method, of class Role.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription Test (Passing value)");
        String description = "Program Participant (Profile Access Only)";
        role.setDescription(description);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Invalid value test for the setDescription method, of class Role.
     */
    @Test
    public void testSetDescriptionInvalid() {
        System.out.println("setDescription Test (Injection value)");
        String description = "<script>alert(\"This is an attack!\");</script>";
        role.setDescription(description);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setDescription method, of class Role.
     */
    @Test
    public void testSetDescriptionOverflow() {
        System.out.println("setDescription Test (Overflow value)");
        String description = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkl\n"
                + "mnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMN\n"
                + "OPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz\n"
                + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZab\n"
                + "cdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCD\n"
                + "EFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnop";
        role.setDescription(description);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getAccessLevel method, of class Role.
     */
    @Test
    public void testGetAccessLevel() {
        System.out.println("getAccessLevel Test (Passing value)");
        Integer expResult = 1;
        Integer result = role.getAccessLevel();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAccessLevel method, of class Role.
     */
    @Test
    public void testSetAccessLevel() {
        System.out.println("setAccessLevel Test (Passing value)");
        Integer accessLevel = 1;
        role.setAccessLevel(accessLevel);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setAccessLevel method, of class Role.
     */
    @Test
    public void testSetAccessLevelNegative() {
        System.out.println("setAccessLevel Test (Negative value)");
        Integer accessLevel = -1;
        role.setAccessLevel(accessLevel);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setAccessLevel method, of class Role.
     */
    @Test
    public void testSetAccessLevelNull() {
        System.out.println("setAccessLevel Test (Null value)");
        Integer accessLevel = null;
        role.setAccessLevel(accessLevel);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setAccessLevel method, of class Role.
     */
    @Test
    public void testSetAccessLevelOutOfRange() {
        System.out.println("setAccessLevel Test (Out of Range value)");
        Integer accessLevel = Integer.MAX_VALUE + 1;
        role.setAccessLevel(accessLevel);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Role>> violations = validator.validate(role);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
}
