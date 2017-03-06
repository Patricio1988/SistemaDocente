/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Actividad;
import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author Patricio
 */
@Local
public interface ActividadDao {

    void create(Actividad actividad);

    void edit(Actividad actividad);

    void remove(Actividad actividad);

    Actividad find(Object id);

    List<Actividad> findAll();

    List<Actividad> findRange(int[] range);

    int count();

    Actividad buscarActividad(int id);

    List<Actividad> listaActividadporNombre();

}
