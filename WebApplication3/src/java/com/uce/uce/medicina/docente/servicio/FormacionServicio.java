/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Formacion;
import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface FormacionServicio {
     void insertarFormacion (Formacion  formacion );
  void eliminarFormacion (Formacion  formacion );
  void actualizarFormacion (Formacion  formacion );
  List<Formacion > listarFormacion ();
   Formacion buscarFormacionporID(int idFormacion);
    List<Formacion> listaFormacionporDocente(String cedula);
    boolean buscarRegistroPoriES(Ies idIes);
    
}
