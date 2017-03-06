/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.IesDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Ies;
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
public class IesServicioImp implements IesServicio{
    @EJB
    private IesDao iesDao=null;

    public IesServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.iesDao=factoria.getIesDao();
    }

    @Override
    public void insertarIes(Ies ies) {
        iesDao.create(ies);
    }

    @Override
    public void eliminarIes(Ies ies) {
       iesDao.remove(ies);
    }

    @Override
    public void actualizarIes(Ies ies) {
        
        iesDao.edit(ies);
    }

    @Override
    public List<Ies> listarIes() {
        return iesDao.findAll();
    }

    @Override
    public Ies buscarIesID(Object id) {
       return iesDao.find(id);
    }
    
     

    @Override
    public List<SelectItem> oneMenuIes(List<Ies> listaIes) {
       List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Ies u : listaIes) {
            SelectItem tmp = new SelectItem(u.getIdIes(),u.getNomIes());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public List<Ies> listarIesporCodigo() {
        return iesDao.listarIesporCodigo();
    }

    @Override
    public Ies buscarporCodigo(int codIes) {
        return iesDao.buscarporCodigo(codIes);
    }

  

    
}
