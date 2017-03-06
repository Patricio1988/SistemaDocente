/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Facultad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface CarreraDao {

    void create(Carrera carrera);

    void edit(Carrera carrera);

    void remove(Carrera carrera);

    Carrera find(Object id);

    List<Carrera> findAll();

    List<Carrera> findRange(int[] range);

    int count();

    List<Carrera> buscarCarreraPorFacultad(int Facultad);

    boolean buscarRegistroPorFacultad(Facultad facultad);

}
