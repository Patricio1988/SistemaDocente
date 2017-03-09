/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictorpicoyplaca;

import predictor.entities.Car;
import predictor.entities.MoveRoad;
import predictor.interfac.impl.PredictorImpl;

/**
 *
 * @author Patricio
 */
public class PredictorPicoYPlaca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try {
          PredictorImpl pre=new PredictorImpl();  
        Car car=new Car();
        car.setLicencePlate("TDH-398");
        MoveRoad r=new MoveRoad("23-03-2017","16:30", car);
       System.out.print("The car can be on the road: "+ pre.predictorPicoPlaca(r));
        } catch (Exception e) {
            System.out.println("Error"+e.getMessage());
        }
        
        //r.horarioPicoyPlaca("16:30");
    }
    
}
