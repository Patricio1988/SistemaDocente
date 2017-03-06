/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.CarreraDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Facultad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;



/**
 *
 * @author Patricio
 */
@Stateless
public class CarreraServicioImp implements CarreraServicio{
 @EJB
    private CarreraDao carreraDao=null;

    public CarreraServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.carreraDao=factoria.getCarreraDao();
    }

    @Override
    public void insertarCarrera(Carrera carrera) {
        carreraDao.create(carrera);
    }

    @Override
    public void eliminarCarrera(Carrera carrera) {
        carreraDao.remove(carrera);
    }

    @Override
    public void actualizarCarrera(Carrera carrera) {
        carreraDao.edit(carrera);
    }

    @Override
    public List<Carrera> listarCarrera() {
        return  carreraDao.findAll();
    }

    @Override
    public List<Carrera> buscarCarreraPorFacultad(int facultad) {
        return carreraDao.buscarCarreraPorFacultad(facultad);
    }

    @Override
    public Carrera encontrarCarrera(Object id) {
        return  carreraDao.find(id);
    }
    @Override
    public  List<SelectItem> oneMenuCarrera(List<Carrera> listaCarrera) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Carrera u : listaCarrera) {
            SelectItem tmp = new SelectItem(u.getIdCarrera(), u.getNomCarrera());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public boolean buscarRegistroPorFacultad(Facultad facultad) {
       return carreraDao.buscarRegistroPorFacultad(facultad);
    }
    
}
