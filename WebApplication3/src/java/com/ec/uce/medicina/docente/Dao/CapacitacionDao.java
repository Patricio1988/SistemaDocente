/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Capacitacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface CapacitacionDao {

    void create(Capacitacion capacitacion);

    void edit(Capacitacion capacitacion);

    void remove(Capacitacion capacitacion);

    Capacitacion find(Object id);

    List<Capacitacion> findAll();

    List<Capacitacion> findRange(int[] range);

    int count();

    Capacitacion buscarCapacitacionID(int id);

    List<Capacitacion> listaCapacitacionporDocente(String cedula);

    

}
