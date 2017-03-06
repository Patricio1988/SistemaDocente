/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface PaisOrigenDao {

    void create(PaisOrigen paisOrigen);

    void edit(PaisOrigen paisOrigen);

    void remove(PaisOrigen paisOrigen);

    PaisOrigen find(Object id);

    List<PaisOrigen> findAll();

    List<PaisOrigen> findRange(int[] range);

    int count();
    List<PaisOrigen> ListarPaisporNombre();
    
}
