/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.ec.uce.medicina.docente.Dao.CarreraDocenteDao;

/**
 *
 * @author Patricio
 */
@Stateless
public class CarreraDocenteServicioImp implements CarreraDocenteServicio{
    @EJB
    private CarreraDocenteDao carreraDocenteDao=null;

    public CarreraDocenteServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.carreraDocenteDao=factoria.getCarreraDocenteDao();
    }

    @Override
    public void insertarCarreraDocente(CarreraDocente carreradocente) {
       carreraDocenteDao.create(carreradocente);
    }

    @Override
    public void actulizarCarreraDocente(CarreraDocente carreradocente) {
       carreraDocenteDao.edit(carreradocente);
    }

    @Override
    public void eliminarCarreraDocente(CarreraDocente carreradocente) {
      carreraDocenteDao.remove(carreradocente);
    }

    @Override
    public CarreraDocente buscarCarreraDocenterporId(Object id) {
       return  carreraDocenteDao.find(id);
    }

    @Override
    public List<CarreraDocente> ListarCarreraDocente() {
        return carreraDocenteDao.findAll();
       
    }

    @Override
    public List<CarreraDocente> findRange(int[] range) {
       return  carreraDocenteDao.findRange(range);
    }

    @Override
    public int count() {
       return carreraDocenteDao.count();
    }

    @Override
    public List<CarreraDocente> listarDocenteCarreraVigente(int idCarrera,int idPeriodo) {
       return carreraDocenteDao.listarDocentesCarreraVigentes(idCarrera,idPeriodo);
    }

    @Override
    public List<CarreraDocente> listarDocenteCarreraporApellido(int idPeriodo) {
    return carreraDocenteDao.listarDocentesCarreraporApellido(idPeriodo);
    }

    @Override
    public List<CarreraDocente> listarDocentesCarreraTodos(int idCarrera,int idPeriodo) {
       return carreraDocenteDao.listarDocentesCarreraTodos(idCarrera,idPeriodo);
    }

    @Override
    public CarreraDocente buscarDocenteCarreraporID(int idDocente, int idCarrera) {
        return carreraDocenteDao.buscarDocenteCarreraporID(idDocente, idCarrera);
    }

    @Override
    public CarreraDocente buscarDocenteCarreraporPeriodo(String identificacion, int idPeriodo, int idCarrera) {
         return carreraDocenteDao.buscarDocenteCarreraporPeriodo(identificacion,idPeriodo, idCarrera);
    }

   
    
}
