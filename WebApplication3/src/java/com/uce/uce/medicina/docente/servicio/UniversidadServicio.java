/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Universidad;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface UniversidadServicio {
     void insertarUniversidad (Universidad universidad );
  void eliminarUniversidad (Universidad universidad);
  void actualizarUniversidad (Universidad universidad );
  Universidad encontrarUniversidadID(Object id);
  List<Universidad> listarUniversidad ();
 Universidad buscarUniversidadID(int id);
 List<SelectItem> oneMenuUniversidad(List<Universidad> listaUniversidad); 
    
}
