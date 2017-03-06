/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface TipoDocumentoServicio {
      void insertarTipoDocumento (TipoDocumento tipoDocumento );
  void eliminarTipoDocumento (TipoDocumento tipoDocumento);
  void actualizarTipoDocumento (TipoDocumento tipoDocumento );
  TipoDocumento encontrarTipoDocumento(Object id);
  List<TipoDocumento> listarTipoDocumento ();
    List<SelectItem> oneMenuTipoDocumento(List<TipoDocumento> listaTipoDocumenrto);
    List<TipoDocumento> listarTipoDocuporNombre();
}
