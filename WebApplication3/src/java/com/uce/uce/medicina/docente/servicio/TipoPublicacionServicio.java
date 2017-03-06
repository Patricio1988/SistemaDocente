/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface TipoPublicacionServicio {
      void insertarTipoPublicacion (TipoPublicacion tipoPublicacion );
  void eliminarTipoPublicacion (TipoPublicacion tipoPublicacion);
  void actualizarTipoPublicacion (TipoPublicacion tipoPublicacion );
  TipoPublicacion encontrarTipoPublicacionID(Object id);
  List<TipoPublicacion> listarTipoPublicacion ();
   List<TipoPublicacion> listarTipoPublicacionporNombre();
  List<SelectItem> oneMenutipoPublicacion(List<TipoPublicacion> listaTipoPublicacion);
}
