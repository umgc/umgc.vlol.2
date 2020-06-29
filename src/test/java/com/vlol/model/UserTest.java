/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vlol.model;

import java.util.Date;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Rob
 */
public class UserTest {
    
    public UserTest() {
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
     * Test of getUserID method, of class User.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID");
        User instance = new User();
        Long expResult = null;
        Long result = instance.getUserID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserID method, of class User.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID");
        Long userID = null;
        User instance = new User();
        instance.setUserID(userID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRole method, of class User.
     */
    @Test
    public void testGetRole() {
        System.out.println("getRole");
        User instance = new User();
        Role expResult = null;
        Role result = instance.getRole();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRole method, of class User.
     */
    @Test
    public void testSetRole() {
        System.out.println("setRole");
        Role role = null;
        User instance = new User();
        instance.setRole(role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        User instance = new User();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class User.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "";
        User instance = new User();
        instance.setFirstName(firstName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        User instance = new User();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class User.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "";
        User instance = new User();
        instance.setLastName(lastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDOB method, of class User.
     */
    @Test
    public void testGetDOB() {
        System.out.println("getDOB");
        User instance = new User();
        Date expResult = null;
        Date result = instance.getDOB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDOB method, of class User.
     */
    @Test
    public void testSetDOB() {
        System.out.println("setDOB");
        Date DOB = null;
        User instance = new User();
        instance.setDOB(DOB);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSSN method, of class User.
     */
    @Test
    public void testGetSSN() {
        System.out.println("getSSN");
        User instance = new User();
        String expResult = "";
        String result = instance.getSSN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSSN method, of class User.
     */
    @Test
    public void testSetSSN() {
        System.out.println("setSSN");
        String SSN = "";
        User instance = new User();
        instance.setSSN(SSN);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStreetAddress method, of class User.
     */
    @Test
    public void testGetStreetAddress() {
        System.out.println("getStreetAddress");
        User instance = new User();
        String expResult = "";
        String result = instance.getStreetAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStreetAddress method, of class User.
     */
    @Test
    public void testSetStreetAddress() {
        System.out.println("setStreetAddress");
        String streetAddress = "";
        User instance = new User();
        instance.setStreetAddress(streetAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCity method, of class User.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        User instance = new User();
        String expResult = "";
        String result = instance.getCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCity method, of class User.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "";
        User instance = new User();
        instance.setCity(city);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class User.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        User instance = new User();
        String expResult = "";
        String result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class User.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        String state = "";
        User instance = new User();
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZipCode method, of class User.
     */
    @Test
    public void testGetZipCode() {
        System.out.println("getZipCode");
        User instance = new User();
        String expResult = "";
        String result = instance.getZipCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setZipCode method, of class User.
     */
    @Test
    public void testSetZipCode() {
        System.out.println("setZipCode");
        String zipCode = "";
        User instance = new User();
        instance.setZipCode(zipCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone method, of class User.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        User instance = new User();
        String expResult = "";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhone method, of class User.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "";
        User instance = new User();
        instance.setPhone(phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInsCompany method, of class User.
     */
    @Test
    public void testGetInsCompany() {
        System.out.println("getInsCompany");
        User instance = new User();
        String expResult = "";
        String result = instance.getInsCompany();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInsCompany method, of class User.
     */
    @Test
    public void testSetInsCompany() {
        System.out.println("setInsCompany");
        String insCompany = "";
        User instance = new User();
        instance.setInsCompany(insCompany);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInsPolicyNo method, of class User.
     */
    @Test
    public void testGetInsPolicyNo() {
        System.out.println("getInsPolicyNo");
        User instance = new User();
        String expResult = "";
        String result = instance.getInsPolicyNo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInsPolicyNo method, of class User.
     */
    @Test
    public void testSetInsPolicyNo() {
        System.out.println("setInsPolicyNo");
        String insPolicyNo = "";
        User instance = new User();
        instance.setInsPolicyNo(insPolicyNo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdvDirective method, of class User.
     */
    @Test
    public void testGetAdvDirective() {
        System.out.println("getAdvDirective");
        User instance = new User();
        Boolean expResult = null;
        Boolean result = instance.getAdvDirective();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAdvDirective method, of class User.
     */
    @Test
    public void testSetAdvDirective() {
        System.out.println("setAdvDirective");
        Boolean advDirective = null;
        User instance = new User();
        instance.setAdvDirective(advDirective);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdvDirType method, of class User.
     */
    @Test
    public void testGetAdvDirType() {
        System.out.println("getAdvDirType");
        User instance = new User();
        String expResult = "";
        String result = instance.getAdvDirType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAdvDirType method, of class User.
     */
    @Test
    public void testSetAdvDirType() {
        System.out.println("setAdvDirType");
        String advDirType = "";
        User instance = new User();
        instance.setAdvDirType(advDirType);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPocName method, of class User.
     */
    @Test
    public void testGetPocName() {
        System.out.println("getPocName");
        User instance = new User();
        String expResult = "";
        String result = instance.getPocName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPocName method, of class User.
     */
    @Test
    public void testSetPocName() {
        System.out.println("setPocName");
        String pocName = "";
        User instance = new User();
        instance.setPocName(pocName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPocPhone method, of class User.
     */
    @Test
    public void testGetPocPhone() {
        System.out.println("getPocPhone");
        User instance = new User();
        String expResult = "";
        String result = instance.getPocPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserAgentID method, of class User.
     */
    @Test
    public void testSetUserAgentID() {
        System.out.println("setUserAgentID");
        Long userAgentID = null;
        User instance = new User();
        instance.setUserAgentID(userAgentID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserAgentID method, of class User.
     */
    @Test
    public void testGetUserAgentID() {
        System.out.println("getUserAgentID");
        User instance = new User();
        Long expResult = null;
        Long result = instance.getUserAgentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPocPhone method, of class User.
     */
    @Test
    public void testSetPocPhone() {
        System.out.println("setPocPhone");
        String pocPhone = "";
        User instance = new User();
        instance.setPocPhone(pocPhone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoctorName method, of class User.
     */
    @Test
    public void testGetDoctorName() {
        System.out.println("getDoctorName");
        User instance = new User();
        String expResult = "";
        String result = instance.getDoctorName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDoctorName method, of class User.
     */
    @Test
    public void testSetDoctorName() {
        System.out.println("setDoctorName");
        String doctorName = "";
        User instance = new User();
        instance.setDoctorName(doctorName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoctorPhone method, of class User.
     */
    @Test
    public void testGetDoctorPhone() {
        System.out.println("getDoctorPhone");
        User instance = new User();
        String expResult = "";
        String result = instance.getDoctorPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDoctorPhone method, of class User.
     */
    @Test
    public void testSetDoctorPhone() {
        System.out.println("setDoctorPhone");
        String doctorPhone = "";
        User instance = new User();
        instance.setDoctorPhone(doctorPhone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllergies method, of class User.
     */
    @Test
    public void testGetAllergies() {
        System.out.println("getAllergies");
        User instance = new User();
        Set<Allergy> expResult = null;
        Set<Allergy> result = instance.getAllergies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAllergies method, of class User.
     */
    @Test
    public void testSetAllergies() {
        System.out.println("setAllergies");
        Set<Allergy> allergies = null;
        User instance = new User();
        instance.setAllergies(allergies);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConditions method, of class User.
     */
    @Test
    public void testGetConditions() {
        System.out.println("getConditions");
        User instance = new User();
        Set<Condition> expResult = null;
        Set<Condition> result = instance.getConditions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConditions method, of class User.
     */
    @Test
    public void testSetConditions() {
        System.out.println("setConditions");
        Set<Condition> conditions = null;
        User instance = new User();
        instance.setConditions(conditions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMedications method, of class User.
     */
    @Test
    public void testGetMedications() {
        System.out.println("getMedications");
        User instance = new User();
        Set<Medication> expResult = null;
        Set<Medication> result = instance.getMedications();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMedications method, of class User.
     */
    @Test
    public void testSetMedications() {
        System.out.println("setMedications");
        Set<Medication> medications = null;
        User instance = new User();
        instance.setMedications(medications);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserComments method, of class User.
     */
    @Test
    public void testGetUserComments() {
        System.out.println("getUserComments");
        User instance = new User();
        String expResult = "";
        String result = instance.getUserComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUserComments method, of class User.
     */
    @Test
    public void testSetUserComments() {
        System.out.println("setUserComments");
        String userComments = "";
        User instance = new User();
        instance.setUserComments(userComments);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class User.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        User instance = new User();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class User.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        User instance = new User();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        User instance = new User();
        String expResult = "";
        String result = instance.getUsername();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsername method, of class User.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "";
        User instance = new User();
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswordConfirm method, of class User.
     */
    @Test
    public void testGetPasswordConfirm() {
        System.out.println("getPasswordConfirm");
        User instance = new User();
        String expResult = "";
        String result = instance.getPasswordConfirm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswordConfirm method, of class User.
     */
    @Test
    public void testSetPasswordConfirm() {
        System.out.println("setPasswordConfirm");
        String passwordConfirm = "";
        User instance = new User();
        instance.setPasswordConfirm(passwordConfirm);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecurityQuestion method, of class User.
     */
    @Test
    public void testGetSecurityQuestion() {
        System.out.println("getSecurityQuestion");
        User instance = new User();
        String expResult = "";
        String result = instance.getSecurityQuestion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSecurityQuestion method, of class User.
     */
    @Test
    public void testSetSecurityQuestion() {
        System.out.println("setSecurityQuestion");
        String securityQuestion = "";
        User instance = new User();
        instance.setSecurityQuestion(securityQuestion);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecurityAnswer method, of class User.
     */
    @Test
    public void testGetSecurityAnswer() {
        System.out.println("getSecurityAnswer");
        User instance = new User();
        String expResult = "";
        String result = instance.getSecurityAnswer();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSecurityAnswer method, of class User.
     */
    @Test
    public void testSetSecurityAnswer() {
        System.out.println("setSecurityAnswer");
        String securityAnswer = "";
        User instance = new User();
        instance.setSecurityAnswer(securityAnswer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateCreated method, of class User.
     */
    @Test
    public void testGetDateCreated() {
        System.out.println("getDateCreated");
        User instance = new User();
        Date expResult = null;
        Date result = instance.getDateCreated();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDateCreated method, of class User.
     */
    @Test
    public void testSetDateCreated() {
        System.out.println("setDateCreated");
        Date dateCreated = null;
        User instance = new User();
        instance.setDateCreated(dateCreated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastLoginDate method, of class User.
     */
    @Test
    public void testGetLastLoginDate() {
        System.out.println("getLastLoginDate");
        User instance = new User();
        Date expResult = null;
        Date result = instance.getLastLoginDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastLoginDate method, of class User.
     */
    @Test
    public void testSetLastLoginDate() {
        System.out.println("setLastLoginDate");
        Date lastLoginDate = null;
        User instance = new User();
        instance.setLastLoginDate(lastLoginDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAdminComments method, of class User.
     */
    @Test
    public void testGetAdminComments() {
        System.out.println("getAdminComments");
        User instance = new User();
        String expResult = "";
        String result = instance.getAdminComments();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAdminComments method, of class User.
     */
    @Test
    public void testSetAdminComments() {
        System.out.println("setAdminComments");
        String adminComments = "";
        User instance = new User();
        instance.setAdminComments(adminComments);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsActive method, of class User.
     */
    @Test
    public void testGetIsActive() {
        System.out.println("getIsActive");
        User instance = new User();
        Boolean expResult = null;
        Boolean result = instance.getIsActive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsActive method, of class User.
     */
    @Test
    public void testSetIsActive() {
        System.out.println("setIsActive");
        Boolean isActive = null;
        User instance = new User();
        instance.setIsActive(isActive);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIsLocked method, of class User.
     */
    @Test
    public void testGetIsLocked() {
        System.out.println("getIsLocked");
        User instance = new User();
        Boolean expResult = null;
        Boolean result = instance.getIsLocked();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsLocked method, of class User.
     */
    @Test
    public void testSetIsLocked() {
        System.out.println("setIsLocked");
        Boolean isLocked = null;
        User instance = new User();
        instance.setIsLocked(isLocked);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
