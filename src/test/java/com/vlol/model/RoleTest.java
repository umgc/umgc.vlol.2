/**
 * Role class tests.
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

public class RoleTest {
    
    public RoleTest() {
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
     * Test of getRoleID method, of class Role.
     */
    @Test
    public void testGetRoleID() {
        System.out.println("getRoleID");
        Role instance = new Role();
        Long expResult = null;
        Long result = instance.getRoleID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoleID method, of class Role.
     */
    @Test
    public void testSetRoleID() {
        System.out.println("setRoleID");
        Long roleID = null;
        Role instance = new Role();
        instance.setRoleID(roleID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Role.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Role instance = new Role();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Role.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Role instance = new Role();
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccessLevel method, of class Role.
     */
    @Test
    public void testGetAccessLevel() {
        System.out.println("getAccessLevel");
        Role instance = new Role();
        int expResult = 0;
        int result = instance.getAccessLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccessLevel method, of class Role.
     */
    @Test
    public void testSetAccessLevel() {
        System.out.println("setAccessLevel");
        int accessLevel = 0;
        Role instance = new Role();
        instance.setAccessLevel(accessLevel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Role.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Role instance = new Role();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Role.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Role instance = new Role();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsers method, of class Role.
     */
    @Test
    public void testGetUsers() {
        System.out.println("getUsers");
        Role instance = new Role();
        Set<User> expResult = null;
        Set<User> result = instance.getUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsers method, of class Role.
     */
    @Test
    public void testSetUsers() {
        System.out.println("setUsers");
        Set<User> users = null;
        Role instance = new Role();
        instance.setUsers(users);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
