/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface PaisOrigenServicio {
     void insertarPaisOrigen (PaisOrigen paisOrigen );
  void eliminarPaisOrigen (PaisOrigen paisOrigen );
  void actualizarPaisOrigen (PaisOrigen paisOrigen );
  List<PaisOrigen> listarPaisOrigen ();
  PaisOrigen encontrarPais(Object id);
   public  List<SelectItem> oneMenuPais(List<PaisOrigen> listaPais);
   List<PaisOrigen> ListarPaisporNombre();
}
