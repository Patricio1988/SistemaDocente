/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictor.entities;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patricio
 */
public class MoveRoadTest {
    
   
    /**
     * Test of horarioPicoyPlaca method, of class MoveRoad.
     */
    @Test
    public void testHorarioPicoyPlaca() throws Exception {
        System.out.println("horarioPicoyPlaca");
        String t = "19:29";
        MoveRoad instance = new MoveRoad();
        boolean expResult = false;
        boolean result = instance.horarioPicoyPlaca(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(expResult!=result)
        {
        fail("The test case is a prototype.");
        }
    }
    
}
