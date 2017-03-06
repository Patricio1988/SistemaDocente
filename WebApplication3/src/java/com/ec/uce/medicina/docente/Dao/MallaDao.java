/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Malla;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface MallaDao {

    void create(Malla malla);

    void edit(Malla malla);

    void remove(Malla malla);

    Malla find(Object id);

    List<Malla> findAll();

    List<Malla> findRange(int[] range);

    int count();

    List<Malla> ListaMallaVigentes();
     boolean buscarRegistroPorCarrera(Carrera idCarrera);
       List<Malla>  ListaMallaPorCarrera(int idCarrera);
    
}
