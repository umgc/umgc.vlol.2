/**
 * Unit Test of the Allergy Class.
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

public class AllergyTest {

    private Validator validator;
    private final Allergy allergy;

    public AllergyTest() {
        // Instantiate the Allergy object
        this.allergy = new Allergy();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // Populate the Allergy object before each test
        allergy.setAllergyID(1l);
        allergy.setAllergyName("Latex");
        // Setup validation of each method's validation annotations
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void tearDown() {
    }

    /**
     * Passing test for the getAllergyID method, of class Allergy.
     */
    @Test
    public void testGetAllergyID() {
        System.out.println("getAllergyID Test (Passing value)");
        Long expResult = 1l;
        Long result = allergy.getAllergyID();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAllergyID method, of class Allergy.
     */
    @Test
    public void testSetAllergyID() {
        System.out.println("setAllergyID Test (Passing value)");
        Long allergyID = 1l;
        allergy.setAllergyID(allergyID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Negative value test for the setAllergyID method, of class Allergy.
     */
    @Test
    public void testSetAllergyIDNegative() {
        System.out.println("setAllergyID Test (Negative value)");
        Long allergyID = -1l;
        allergy.setAllergyID(allergyID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Null value test for the setAllergyID method, of class Allergy.
     */
    @Test
    public void testSetAllergyIDNull() {
        System.out.println("setAllergyID Test (Null value)");
        Long allergyID = null;
        allergy.setAllergyID(allergyID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Out of Range value test for the setAllergyID method, of class Allergy.
     */
    @Test
    public void testSetAllergyIDOutOfRange() {
        System.out.println("setAllergyID Test (Out of Range value)");
        Long allergyID = Long.MAX_VALUE + 1;
        allergy.setAllergyID(allergyID);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Passing test for the getAllergyName method, of class Allergy.
     */
    @Test
    public void testGetAllergyName() {
        System.out.println("getAllergyName Test (Passing value)");
        String expResult = "Latex";
        String result = allergy.getAllergyName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Passing test for the setAllergyName method, of class Allergy.
     */
    @Test
    public void testSetAllergyName() {
        System.out.println("setAllergyName Test (Passing value)");
        String allergyName = "Latex";
        allergy.setAllergyName(allergyName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }

    /**
     * Blank value test for the setAllergyName method, of class Allergy.
     */
    @Test
    public void testSetAllergyNameBlank() {
        System.out.println("setAllergyName Test (Blank value)");
        String allergyName = "";
        allergy.setAllergyName(allergyName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Invalid value test for the setAllergyName method, of class Allergy.
     */
    @Test
    public void testSetAllergyNameInvalid() {
        System.out.println("setAllergyName Test (Injection value)");
        String allergyName = "<script>alert(\"This is an attack!\");</script>";
        allergy.setAllergyName(allergyName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }

    /**
     * Overflow value test for the setAllergyName method, of class Allergy.
     */
    @Test
    public void testSetAllergyNameOverflow() {
        System.out.println("setAllergyName Test (Overflow value)");
        String allergyName = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        allergy.setAllergyName(allergyName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<Allergy>> violations = validator.validate(allergy);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
}
