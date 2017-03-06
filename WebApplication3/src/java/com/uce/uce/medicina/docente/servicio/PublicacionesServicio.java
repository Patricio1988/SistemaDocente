/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Publicaciones;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface PublicacionesServicio {
    void insertarPublicaciones (Publicaciones publicaciones );
  void eliminarPublicaciones (Publicaciones publicaciones );
  void actualizarPublicaciones (Publicaciones publicaciones );
  List<Publicaciones> listarPublicaciones ();
    Publicaciones buscarPublicacionesporNombre(String nombre);
    Publicaciones buscarPublicacionesid(Object id);
    List<Publicaciones> listaPublicacionesporDocente(String cedula);
     boolean buscarRegistroPorTpPublicacion(TipoPublicacion idTpPubli);
    
}
