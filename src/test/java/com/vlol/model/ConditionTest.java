/**
 * Condition class tests.
 *
 * Java Runtime Environment (JRE) version used: 11.0.7
 * Java Development Kit (JDK) version used: 11.0.7
 *
 * Styling guide: Google Java Style Guide
 *     (https://google.github.io/styleguide/javaguide.html) and
 *     Code Conventions for the Java Programming Language (Oracle: Deprecated)
 *     (https://www.oracle.com/technetwork/java/javase/documentation/codeconvtoc-136057.html)
 *
 * @category  com
 * @package vlol
 * @author Mohammed Allibalogun <mohammed.allibalogun@gmail.com>
 * @license https://opensource.org/licenses/MIT The MIT License
 * @link      https://github.com/garciart/SWEN670
 * @copyright 2020 EMS Plus
 */
package com.vlol.model;

import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConditionTest {
    
    public ConditionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getConditionID method, of class Condition.
     */
    @Test
    public void testGetConditionID() {
        System.out.println("getConditionID");
        Condition instance = new Condition();
        Long expResult = null;
        Long result = instance.getConditionID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConditionID method, of class Condition.
     */
    @Test
    public void testSetConditionID() {
        System.out.println("setConditionID");
        Long conditionID = null;
        Condition instance = new Condition();
        instance.setConditionID(conditionID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditionName method, of class Condition.
     */
    @Test
    public void testGetConditionName() {
        System.out.println("getConditionName");
        Condition instance = new Condition();
        String expResult = "";
        String result = instance.getConditionName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConditionName method, of class Condition.
     */
    @Test
    public void testSetConditionName() {
        System.out.println("setConditionName");
        String conditionName = "";
        Condition instance = new Condition();
        instance.setConditionName(conditionName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Condition.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Condition instance = new Condition();
        Set<User> expResult = null;
        Set<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsers method, of class Condition.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        Set<User> users = null;
        Condition instance = new Condition();
        instance.setUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
