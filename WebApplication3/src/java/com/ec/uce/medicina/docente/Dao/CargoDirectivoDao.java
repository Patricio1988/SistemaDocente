/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.CargoDirectivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface CargoDirectivoDao {

    void create(CargoDirectivo cargoDirectivo);

    void edit(CargoDirectivo cargoDirectivo);

    void remove(CargoDirectivo cargoDirectivo);

    CargoDirectivo find(Object id);

    List<CargoDirectivo> findAll();

    List<CargoDirectivo> findRange(int[] range);

    int count();

    CargoDirectivo buscarCargoporID(int idCargo);

    List<CargoDirectivo> listaCargoporDocente(String cedula,int idPeriodo,int idCarrera);

}
