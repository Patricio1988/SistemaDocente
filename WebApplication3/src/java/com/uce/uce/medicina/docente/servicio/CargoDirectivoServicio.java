/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.CargoDirectivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface CargoDirectivoServicio {
      void insertarCargoDirectivo(CargoDirectivo cargoDirectivo);
  void eliminarCargoDirectivo(CargoDirectivo cargoDirectivo);
  void actualizarCargoDirectivo(CargoDirectivo cargoDirectivo);
  List<CargoDirectivo> listarCargodirectivoDao();
  CargoDirectivo buscarCargoDirectivoporID(int idCargo);
    List<CargoDirectivo> listaCargoDirectivoporDocente(String cedula,int idPeriodo,int idCarrera);
   
}
