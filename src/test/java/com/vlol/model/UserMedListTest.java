/**
 * User-to-Medication Associative Class Unit Tests.
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserMedListTest {
    
    public UserMedListTest() {
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
     * Test of getDosage method, of class UserMedList.
     */
    @Test
    public void testGetDosage() {
        System.out.println("getDosage");
        UserMedList instance = new UserMedList();
        float expResult = 0.0F;
        float result = instance.getDosage();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDosage method, of class UserMedList.
     */
    @Test
    public void testSetDosage() {
        System.out.println("setDosage");
        float dosage = 0.0F;
        UserMedList instance = new UserMedList();
        instance.setDosage(dosage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrequency method, of class UserMedList.
     */
    @Test
    public void testGetFrequency() {
        System.out.println("getFrequency");
        UserMedList instance = new UserMedList();
        int expResult = 0;
        int result = instance.getFrequency();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrequency method, of class UserMedList.
     */
    @Test
    public void testSetFrequency() {
        System.out.println("setFrequency");
        int frequency = 0;
        UserMedList instance = new UserMedList();
        instance.setFrequency(frequency);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrequencyInterval method, of class UserMedList.
     */
    @Test
    public void testGetFrequencyInterval() {
        System.out.println("getFrequencyInterval");
        UserMedList instance = new UserMedList();
        FrequencyInterval expResult = null;
        FrequencyInterval result = instance.getFrequencyInterval();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrequencyInterval method, of class UserMedList.
     */
    @Test
    public void testSetFrequencyInterval() {
        System.out.println("setFrequencyInterval");
        FrequencyInterval freqInterval = null;
        UserMedList instance = new UserMedList();
        instance.setFrequencyInterval(freqInterval);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
