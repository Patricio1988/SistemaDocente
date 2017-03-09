/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictor.interfac.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import predictor.entities.MoveRoad;
import predictor.interfac.PredictorDao;

/**
 *
 * @author Patricio
 */
public class PredictorImpl implements PredictorDao {

    @Override
    public boolean predictorPicoPlaca(MoveRoad road) {
        boolean roadCar = true;
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        int lastDigit = 0;
        int day = 0;
        char[] caracteres = null;
        Date date = null;
        try {
            caracteres = road.getCar().getLicencePlate().toCharArray();
            lastDigit = Integer.parseInt(String.valueOf(caracteres[caracteres.length - 1]));
            date = formato.parse(road.getDate());
            cal.setTime(date);
            day = cal.get(cal.DAY_OF_WEEK);
            if (road.horarioPicoyPlaca(road.getTime()) == false) {
                switch (day) {
                    case 1:
                    case 7:
                        roadCar = true;
                        System.out.println(" The car can be on the road");
                        break;
                    case 2:
                        if ((lastDigit == 1 || lastDigit == 2)) {
                            roadCar = false;
                            System.out.println("The car can't be on the road");
                        }
                        break;
                    case 3:
                        if (lastDigit == 3 || lastDigit == 4) {
                            roadCar = false;
                            System.out.println("The car can't be on the road");
                        }
                        break;
                    case 4:
                        if (lastDigit == 5 || lastDigit == 6) {
                            roadCar = false;
                            System.out.println("The car can't be on the road");
                        }
                        break;
                    case 5:
                        if (lastDigit == 7 || lastDigit == 8) {
                            roadCar = false;
                            System.out.println("The car can't be on the road");
                        }
                        break;
                    case 6:
                        if (lastDigit == 9 || lastDigit == 0) {
                            roadCar = false;
                            System.out.println("The car can't be on the road");
                        }
                        break;
                      

                }
            } else {
                roadCar = true;
                System.out.println("the car can be on the road");
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
        // for (int x=0;x<caracteres.length;x++){
        //System.out.println("ultimo digito " + caracteres[x]);
        // lastDigit=caracteres[x];

        return roadCar;

    }

    @Override
    public String predictorMsmPicoPlaca(MoveRoad road) {
        String msm=null;
        if (predictorPicoPlaca(road)==true) {
            msm="The car can be on the road";
            
        }
        else
        {
            msm="The car can't be on the road";
        }
        return msm;
    }

}
