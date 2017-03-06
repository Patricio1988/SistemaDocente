/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface TiempoDedicacionServicio {
      void insertarTiempoDedicacion (TiempoDedicacion tiempoDedicacion );
  void eliminarTiempoDedicacion (TiempoDedicacion TiempoDedicacion);
  void actualizarTiempoDedicacion (TiempoDedicacion TiempoDedicacion );
  TiempoDedicacion encontarTiempoDedicacionID(Object id);
  List<TiempoDedicacion> listarTiempoDedicacion ();
  List<SelectItem> oneMenuTiempoDedicacion(List<TiempoDedicacion> listaTiempoDedicacion);
   List<TiempoDedicacion> listarTiempoDediporNombre();
}
