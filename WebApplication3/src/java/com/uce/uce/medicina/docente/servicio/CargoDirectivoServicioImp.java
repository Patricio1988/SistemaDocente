/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.CargoDirectivo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.ec.uce.medicina.docente.Dao.CargoDirectivoDao;

/**
 *
 * @author Patricio
 */
@Stateless
public class CargoDirectivoServicioImp implements CargoDirectivoServicio{
    @EJB
    private CargoDirectivoDao cargodirectivoDao=null;

    public CargoDirectivoServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.cargodirectivoDao=factoria.getCargoDirectivoDao();
    }

    @Override
    public void insertarCargoDirectivo(CargoDirectivo cargoDirectivo) {
        cargodirectivoDao.create(cargoDirectivo);
    }

    @Override
    public void eliminarCargoDirectivo(CargoDirectivo cargoDirectivo) {
        cargodirectivoDao.remove(cargoDirectivo);
    }

    @Override
    public void actualizarCargoDirectivo(CargoDirectivo cargoDirectivo) {
        cargodirectivoDao.edit(cargoDirectivo);

    }

    @Override
    public List<CargoDirectivo> listarCargodirectivoDao() {
       return  cargodirectivoDao.findAll();
    }

    @Override
    public CargoDirectivo buscarCargoDirectivoporID(int idCargo) {
     return cargodirectivoDao.buscarCargoporID(idCargo);
    }

    @Override
    public List<CargoDirectivo> listaCargoDirectivoporDocente(String cedula,int idPeriodo, int idCarrera) {
        return cargodirectivoDao.listaCargoporDocente(cedula, idPeriodo,idCarrera);
    }

    
    
}
