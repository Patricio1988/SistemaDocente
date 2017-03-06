/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.CapacitacionDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Capacitacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Patricio
 */
@Stateless
public class CapacitacionServicioImp implements CapacitacionServicio{
    @EJB
    private CapacitacionDao capacitacionDao=null;

    public CapacitacionServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.capacitacionDao=factoria.getCapacitacionDao();
    }
    

    @Override
    public void insertarCapcitacion(Capacitacion capacitacion) {
       capacitacionDao.create(capacitacion);
    }

    @Override
    public void eliminarCacitacion(Capacitacion capacitacion) {
        capacitacionDao.remove(capacitacion);
    }

    @Override
    public void actualizarCapacitacion(Capacitacion capacitacion) {
       capacitacionDao.edit(capacitacion);
    }

    @Override
    public List<Capacitacion> listarCapcitacion() {
       return capacitacionDao.findAll();
    }

    @Override
    public List<Capacitacion> listaActualizacionporDocente(String cedula) {
        return capacitacionDao.listaCapacitacionporDocente(cedula);
    }

    @Override
    public Capacitacion EncontrarCapacitacion(Object id) {
        return capacitacionDao.find(id);
    }

    @Override
    public Capacitacion buscarCapacitacionporID(int id) {
      return capacitacionDao.buscarCapacitacionID(id);
    }

    
    
}
