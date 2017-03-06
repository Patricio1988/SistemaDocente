/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.RelacionIes;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface RelacionIesServicio {
     void insertarRelacionIes (RelacionIes relacionIes );
  void eliminarRelacionIes (RelacionIes relacionIes);
  void actualizarRelacionIes (RelacionIes relacionIes );
  RelacionIes encontrarRelacion(Object id);
  List<RelacionIes> listarRelacionIes ();
  List<SelectItem> oneMenuRelacionIes(List<RelacionIes> listaRelacionIes);
   List<RelacionIes> listarRelacionIesporNombre();
    
}
