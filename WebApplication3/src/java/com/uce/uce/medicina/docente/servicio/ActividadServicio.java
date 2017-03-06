/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Actividad;
import java.util.List;
import javax.ejb.Local;
import  org.postgresql.util.PSQLException;

@Local
public interface ActividadServicio {

    void insertarActividad(Actividad capacitacion) throws PSQLException;

    void eliminarActividad(Actividad capacitacion);

    void actualizarActividad(Actividad capacitacion);

    Actividad buscarActividadporID(Object id);

    List<Actividad> listaActividadporNombre();

    Actividad buscarActividad(int id);

}
