/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.idrinth.endlessspace2.modvalidator;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Björn
 */
public class ExternalReferenceTest {
    
    public ExternalReferenceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of file method, of class ExternalReference.
     */
    @Test
    public void testFile() {
        System.out.println("file");
        ExternalReference instance = null;
        File expResult = null;
        File result = instance.file();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tag method, of class ExternalReference.
     */
    @Test
    public void testTag() {
        System.out.println("tag");
        ExternalReference instance = null;
        String expResult = "";
        String result = instance.tag();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of target method, of class ExternalReference.
     */
    @Test
    public void testTarget() {
        System.out.println("target");
        ExternalReference instance = null;
        String expResult = "";
        String result = instance.target();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class ExternalReference.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        ExternalReference instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ExternalReference.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        ExternalReference instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
