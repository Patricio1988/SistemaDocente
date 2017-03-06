/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Facultad;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface CarreraServicio {
     void insertarCarrera(Carrera carrera);
  void eliminarCarrera(Carrera carrera);
  void actualizarCarrera(Carrera carrera);
  List<Carrera> listarCarrera();
  List<Carrera> buscarCarreraPorFacultad(int facultad);
  Carrera encontrarCarrera(Object id);
  List<SelectItem> oneMenuCarrera(List<Carrera> listaCarrera);
 boolean buscarRegistroPorFacultad(Facultad facultad);

    
}
