/**
 * Unit Test of the Condition Class.
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
 * @author Rob Garcia <rgarcia92@student.umgc.edu> and Mohammed Allibalogun <mohammed.allibalogun@gmail.com>
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

public class ConditionTest {

    private Validator validator;
    private final Condition condition;

    public ConditionTest() {
        // Instantiate the Condition object
        this.condition = new Condition();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Populate the Condition object before each test
        condition.setConditionID(1l);
        condition.setConditionName("Asthma");
        // Setup validation of each method's validation annotations
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void tearDown() {
    }

    /**
     * Passing test for the getConditionID method, of class Condition.
     */
    @Test
    public void testGetConditionID() {
        System.out.println("getConditionID Test (Passing value)");
        Long expResult = 1l;
        Long result = condition.getConditionID();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setConditionID method, of class Condition.
     */
    @Test
    public void testSetConditionID() {
        System.out.println("setConditionID Test (Passing value)");
        Long conditionID = 1l;
        condition.setConditionID(conditionID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setConditionID method, of class Condition.
     */
    @Test
    public void testSetConditionIDNegative() {
        System.out.println("setConditionID Test (Negative value)");
        Long conditionID = -1l;
        condition.setConditionID(conditionID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setConditionID method, of class Condition.
     */
    @Test
    public void testSetConditionIDNull() {
        System.out.println("setConditionID Test (Null value)");
        Long conditionID = null;
        condition.setConditionID(conditionID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setConditionID method, of class Condition.
     */
    @Test
    public void testSetConditionIDOutOfRange() {
        System.out.println("setConditionID Test (Out of Range value)");
        Long conditionID = Long.MAX_VALUE + 1;
        condition.setConditionID(conditionID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getConditionName method, of class Condition.
     */
    @Test
    public void testGetConditionName() {
        System.out.println("getConditionName Test (Passing value)");
        String expResult = "Asthma";
        String result = condition.getConditionName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setConditionName method, of class Condition.
     */
    @Test
    public void testSetConditionName() {
        System.out.println("setConditionName Test (Passing value)");
        String conditionName = "Asthma";
        condition.setConditionName(conditionName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setConditionName method, of class Condition.
     */
    @Test
    public void testSetConditionNameBlank() {
        System.out.println("setConditionName Test (Blank value)");
        String conditionName = "";
        condition.setConditionName(conditionName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setConditionName method, of class Condition.
     */
    @Test
    public void testSetConditionNameInvalid() {
        System.out.println("setConditionName Test (Injection value)");
        String conditionName = "<script>alert(\"This is an attack!\");</script>";
        condition.setConditionName(conditionName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setConditionName method, of class Condition.
     */
    @Test
    public void testSetConditionNameOverflow() {
        System.out.println("setConditionName Test (Overflow value)");
        String conditionName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        condition.setConditionName(conditionName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Condition>> violations = validator.validate(condition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
}
