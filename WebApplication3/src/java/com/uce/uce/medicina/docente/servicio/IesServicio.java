/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface IesServicio {
    void insertarIes (Ies ies );
  void eliminarIes (Ies ies );
  void actualizarIes (Ies ies );
  List<Ies > listarIes ();
   Ies buscarIesID(Object id);
  // Ies buscarIes(int id);
   public  List<SelectItem> oneMenuIes(List<Ies> listaIes);
    List<Ies> listarIesporCodigo();
    Ies buscarporCodigo(int codIes);
   
}
