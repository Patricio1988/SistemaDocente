/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Periodo;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface PeriodoServicio {
     void insertarPeriodo (Periodo periodo );
  void eliminarPeriodo (Periodo periodo );
  void actualizarPeriodo (Periodo periodo );
  Periodo encontrarPeriodoID(Object id);
  List<Periodo> listarPeriodo ();
   List<SelectItem> oneMenuPeriodo(List<Periodo> listaPeriodo);
     List<Periodo> listarPeriodoporFechaInicio();
      List<Periodo> listarPeriodoporActivos();

}
