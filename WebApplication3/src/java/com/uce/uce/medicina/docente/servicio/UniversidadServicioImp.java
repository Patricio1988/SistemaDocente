/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.UniversidadDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Universidad;
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
public class UniversidadServicioImp implements UniversidadServicio{
    @EJB
    private UniversidadDao universidadDao=null;

    public UniversidadServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.universidadDao=factoria.getUniversidadDao();
    }

    @Override
    public void insertarUniversidad(Universidad universidad) {
       universidadDao.create(universidad);
    }

    @Override
    public void eliminarUniversidad(Universidad universidad) {
        universidadDao.remove(universidad);
    }

    @Override
    public void actualizarUniversidad(Universidad universidad) {
        universidadDao.edit(universidad);
    }

    @Override
    public List<Universidad> listarUniversidad() {
       return universidadDao.findAll();
    }

    @Override
    public Universidad encontrarUniversidadID(Object id) {
         return universidadDao.find(id);
    }

    @Override
    public Universidad buscarUniversidadID(int id) {
       return universidadDao.buscarUniversidadID(id);
    }

    @Override
    public List<SelectItem> oneMenuUniversidad(List<Universidad> listaUniversidad) {
         List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Universidad u : listaUniversidad) {
            SelectItem tmp = new SelectItem(u.getIdUniversidad(), u.getNomUniversidad());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }
 
}
