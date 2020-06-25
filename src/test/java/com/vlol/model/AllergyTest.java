/**
 * Allergy class tests.
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

public class AllergyTest {
    
    public AllergyTest() {
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
     * Test of getAllergyID method, of class Allergy.
     */
    @Test
    public void testGetAllergyID() {
        System.out.println("getAllergyID");
        Allergy instance = new Allergy();
        Long expResult = null;
        Long result = instance.getAllergyID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAllergyID method, of class Allergy.
     */
    @Test
    public void testSetAllergyID() {
        System.out.println("setAllergyID");
        Long allergyID = null;
        Allergy instance = new Allergy();
        instance.setAllergyID(allergyID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllergyName method, of class Allergy.
     */
    @Test
    public void testGetAllergyName() {
        System.out.println("getAllergyName");
        Allergy instance = new Allergy();
        String expResult = "";
        String result = instance.getAllergyName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAllergyName method, of class Allergy.
     */
    @Test
    public void testSetAllergyName() {
        System.out.println("setAllergyName");
        String allergyName = "";
        Allergy instance = new Allergy();
        instance.setAllergyName(allergyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Allergy.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Allergy instance = new Allergy();
        Set<User> expResult = null;
        Set<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsers method, of class Allergy.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        Set<User> users = null;
        Allergy instance = new Allergy();
        instance.setUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
