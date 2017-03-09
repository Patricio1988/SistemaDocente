/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictor.interfac.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import predictor.entities.Car;
import predictor.entities.MoveRoad;

/**
 *
 * @author Patricio
 */
public class PredictorImplTest {
    
    public PredictorImplTest() {
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
     * Test of predictorPicoPlaca method, of class PredictorImpl.
     */
    @Test
    public void testPredictorPicoPlaca() {
 System.out.println("predictorPicoPlaca");
           Car car=new Car();
        car.setLicencePlate("TDH-39");
        MoveRoad road=new MoveRoad("9-03-2017","7:40", car);
        PredictorImpl instance = new PredictorImpl();
        boolean expResult = true;
        boolean result = instance.predictorPicoPlaca(road);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       if(result!=expResult){
        fail("The test case is a prototype.");
       }
    }

    /**
     * Test of predictorMsmPicoPlaca method, of class PredictorImpl.
     */
    @Test
    public void testPredictorMsmPicoPlaca() {
        System.out.println("predictorMsmPicoPlaca");
           Car car=new Car();
        car.setLicencePlate("TDH-38");
        MoveRoad road=new MoveRoad("9-03-2017","7:40", car);
        PredictorImpl instance = new PredictorImpl();
        String expResult = "The car can't be on the road";
        String result = instance.predictorMsmPicoPlaca(road);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
               if(result!=expResult){
        fail("The test case is a prototype.");
               }
    }
    
}
