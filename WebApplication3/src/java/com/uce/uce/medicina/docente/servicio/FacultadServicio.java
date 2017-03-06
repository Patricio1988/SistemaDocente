/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Facultad;
import com.ec.uce.medicina.docente.modelo.Universidad;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface FacultadServicio {
      void insertarFacultad(Facultad  facultad);
  void eliminarFacultad(Facultad facultad);
  void actualizarFacultad(Facultad facultad);
  Facultad encontrarFacultad(Object id);
  List<Facultad> listfacultadaPorUniversidad(int idPais);
  List<Facultad> listarFacultad();
 List<SelectItem> oneMenuFacultad(List<Facultad> listaFacultad);
 Facultad buscarFacultadID(int id);
 boolean buscarRegistroPorUniversidad(Universidad idUniversidad);
    
}
