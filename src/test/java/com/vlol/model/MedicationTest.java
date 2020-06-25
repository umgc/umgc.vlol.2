/**
 * Medication class tests.
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

public class MedicationTest {
    
    public MedicationTest() {
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
     * Test of getMedicationID method, of class Medication.
     */
    @Test
    public void testGetMedicationID() {
        System.out.println("getMedicationID");
        Medication instance = new Medication();
        Long expResult = null;
        Long result = instance.getMedicationID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMedicationID method, of class Medication.
     */
    @Test
    public void testSetMedicationID() {
        System.out.println("setMedicationID");
        Long medicationID = null;
        Medication instance = new Medication();
        instance.setMedicationID(medicationID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBrandName method, of class Medication.
     */
    @Test
    public void testGetBrandName() {
        System.out.println("getBrandName");
        Medication instance = new Medication();
        String expResult = "";
        String result = instance.getBrandName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBrandName method, of class Medication.
     */
    @Test
    public void testSetBrandName() {
        System.out.println("setBrandName");
        String brandName = "";
        Medication instance = new Medication();
        instance.setBrandName(brandName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenericName method, of class Medication.
     */
    @Test
    public void testGetGenericName() {
        System.out.println("getGenericName");
        Medication instance = new Medication();
        String expResult = "";
        String result = instance.getGenericName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenericName method, of class Medication.
     */
    @Test
    public void testSetGenericName() {
        System.out.println("setGenericName");
        String genericName = "";
        Medication instance = new Medication();
        instance.setGenericName(genericName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDrugAction method, of class Medication.
     */
    @Test
    public void testGetDrugAction() {
        System.out.println("getDrugAction");
        Medication instance = new Medication();
        String expResult = "";
        String result = instance.getDrugAction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDrugAction method, of class Medication.
     */
    @Test
    public void testSetDrugAction() {
        System.out.println("setDrugAction");
        String drugAction = "";
        Medication instance = new Medication();
        instance.setDrugAction(drugAction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getControlled method, of class Medication.
     */
    @Test
    public void testGetControlled() {
        System.out.println("getControlled");
        Medication instance = new Medication();
        Boolean expResult = null;
        Boolean result = instance.getControlled();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setControlled method, of class Medication.
     */
    @Test
    public void testSetControlled() {
        System.out.println("setControlled");
        Boolean controlled = null;
        Medication instance = new Medication();
        instance.setControlled(controlled);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBloodThinner method, of class Medication.
     */
    @Test
    public void testGetBloodThinner() {
        System.out.println("getBloodThinner");
        Medication instance = new Medication();
        Boolean expResult = null;
        Boolean result = instance.getBloodThinner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBloodThinner method, of class Medication.
     */
    @Test
    public void testSetBloodThinner() {
        System.out.println("setBloodThinner");
        Boolean bloodThinner = null;
        Medication instance = new Medication();
        instance.setBloodThinner(bloodThinner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Medication.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Medication instance = new Medication();
        Set<User> expResult = null;
        Set<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsers method, of class Medication.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        Set<User> users = null;
        Medication instance = new Medication();
        instance.setUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
