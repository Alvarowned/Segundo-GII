/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingensoftware;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvar
 */
public class CircularArrayWeighTest {
    
    public CircularArrayWeighTest() {
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
     * Test of writeFile method, of class CircularArrayWeigh.
     */
    @Test
    public void testWriteFile() {
        System.out.println("writeFile");
        String filePath = "";
        double[] module = null;
        double[] phase = null;
        CircularArrayWeigh.writeFile(filePath, module, phase);
    }

    /**
     * Test of Window_circleEON method, of class CircularArrayWeigh.
     */
    @Test
    public void testWindow_circleEON() {
        System.out.println("Window_circleEON");
        double dx = 11.0;
        double dy = 12.17;
        double radius = 70.0;
        double A = 0.0;
        double B = 1.0;
        String expResult = "OK";
        String result = CircularArrayWeigh.Window_circleEON(dx, dy, radius, A, B);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class CircularArrayWeigh.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CircularArrayWeigh.main(args);
    }
    
}
