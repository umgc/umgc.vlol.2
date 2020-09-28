/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author kimbe
 */
public class UserConditionTest {
    
    private Validator validator;
    private final UserCondition usercondition;
    
    public UserConditionTest() {
        //Instantiate the condition object 
        this.usercondition = new UserCondition();
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
@BeforeEach
    public void setUp() {
        //Populate the condition object before each test 
        usercondition.setConditionId(1l);
        usercondition.setConditionName("diabetes");
        // Setup validation of each method's validation annotations
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator(); 
    }
    /**
     * Test of getConditionId method, of class UserCondition.
     */
    
    @Test
    public void testGetConditionId() {
        System.out.println("getConditionId Test (Passing value)");
        Long expResult = 1l;
        Long result = usercondition.getConditionId();
        assertEquals(expResult, result);
       // Check for and print any violations of validation annotations
        Set<ConstraintViolation<UserCondition>> violations = validator.validate(usercondition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Test of setConditionId method, of class UserCondition.
     */
    @Test
    public void testSetConditionId() {
        System.out.println("setConditionId Test (Passing value)");
        Long conditionId = 1l;
        usercondition.setConditionId(conditionId);
        /// Check for and print any violations of validation annotations
        Set<ConstraintViolation<UserCondition>> violations = validator.validate(usercondition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }
    /**
    *Negative value test for the setConditionID method, of the class UserCondition.
    */
 @Test
    public void testSetConditionIdNegative() {
        System.out.println("setConditionId Test (Negative value)");
        Long conditionId = -1l;
        usercondition.setConditionId(conditionId);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<UserCondition>> violations = validator.validate(usercondition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
     /**
     * Out of Range value test for the setConditionId method, of class
     * UserCondition.
     */
    @Test
    public void testSetConditionIdOutOfRange() {
        System.out.println("setConditionId Test (Out of Range value)");
        Long conditionId = Long.MAX_VALUE + 1;
        usercondition.setConditionId(conditionId);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<UserCondition>> violations = validator.validate(usercondition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertFalse(violations.isEmpty());
    }
    /** 
     * Test GetConditionName method of class UserCondition
     */
     @Test
    public void testGetConditionName() {
        System.out.println("getConditionName Test (Passing value)");
        String expResult = "diabetes";
        String result = usercondition.getConditionName();
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<UserCondition>> violations = validator.validate(usercondition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertEquals(expResult, result);
    }

    /**
     * Test of setConditionName method, of class UserCondition.
     */
    @Test
    public void testSetConditionName() {
        System.out.println("setConditionName Test (Passing value)");
        String conditionName = "diabetes";
        usercondition.setConditionName(conditionName);
        // Check for and print any violations of validation annotations
        Set<ConstraintViolation<UserCondition>> violations = validator.validate(usercondition);
        String message = violations.iterator().hasNext() ? violations.iterator().next().getMessage() : "";
        if (!violations.isEmpty()) {
            System.out.println("Violation caught: " + message);
        }
        // Test method
        assertTrue(violations.isEmpty());
    }


}