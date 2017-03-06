/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import java.util.List;
import com.ec.uce.medicina.docente.modelo.Capacitacion;
import javax.ejb.Local;
/**
 *
 * @author Patricio
 */
@Local
public interface CapacitacionServicio {
     void insertarCapcitacion(Capacitacion capacitacion);
  void eliminarCacitacion(Capacitacion capacitacion);
  void actualizarCapacitacion(Capacitacion capacitacion);
  Capacitacion EncontrarCapacitacion(Object id);
  Capacitacion buscarCapacitacionporID(int id);
    List<Capacitacion> listaActualizacionporDocente(String cedula);
  List<Capacitacion> listarCapcitacion();

  
  
    
}
