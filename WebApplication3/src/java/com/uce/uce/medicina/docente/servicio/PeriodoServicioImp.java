/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.PeriodoDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Periodo;
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
public class PeriodoServicioImp implements PeriodoServicio{
    @EJB
    private PeriodoDao periodoDao=null;

    public PeriodoServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.periodoDao=factoria.getPeriodoDao();
    }

    @Override
    public void insertarPeriodo(Periodo periodo) {
        
            periodoDao.create(periodo);
        
        
    }

    @Override
    public void eliminarPeriodo(Periodo periodo) {
        periodoDao.remove(periodo);
    }

    @Override
    public void actualizarPeriodo(Periodo periodo) {
        periodoDao.edit(periodo);
    }

    @Override
    public List<Periodo> listarPeriodo() {
        return periodoDao.findAll();
    }

    @Override
    public Periodo encontrarPeriodoID(Object id) {
        return periodoDao.find(id);
    }

    @Override
    public List<SelectItem> oneMenuPeriodo(List<Periodo> listaPeriodo) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Periodo u : listaPeriodo) {
            SelectItem tmp = new SelectItem(u.getIdPeriodo(), u.getNombrePeriodo());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
 
    }

    @Override
    public List<Periodo> listarPeriodoporFechaInicio() {
       return periodoDao.listarPeriodoporFechaInicio();
    }

    @Override
    public List<Periodo> listarPeriodoporActivos() {
       return periodoDao.listarPeriodoporActivos();
    }
    
}
