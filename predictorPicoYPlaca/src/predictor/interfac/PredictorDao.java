/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predictor.interfac;

import predictor.entities.MoveRoad;

/**
 *
 * @author Patricio
 */
public interface PredictorDao {
    public boolean predictorPicoPlaca(MoveRoad road);
    public String predictorMsmPicoPlaca(MoveRoad road);
    
}
