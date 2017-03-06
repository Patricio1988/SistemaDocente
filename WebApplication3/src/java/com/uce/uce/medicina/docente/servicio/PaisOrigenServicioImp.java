/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import com.ec.uce.medicina.docente.Dao.PaisOrigenDao;

/**
 *
 * @author Patricio
 */
@Stateless
public class PaisOrigenServicioImp implements PaisOrigenServicio{
    @EJB
    private PaisOrigenDao paisOrigenDao=null;

    public PaisOrigenServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.paisOrigenDao=factoria.getPaisOrigenDao();
    }

    @Override
    public void insertarPaisOrigen(PaisOrigen paisOrigen) {
        paisOrigenDao.create(paisOrigen);
    }

    @Override
    public void eliminarPaisOrigen(PaisOrigen paisOrigen) {
        paisOrigenDao.remove(paisOrigen);
    }

    @Override
    public void actualizarPaisOrigen(PaisOrigen paisOrigen) {
        paisOrigenDao.edit(paisOrigen);
    }

    @Override
    public List<PaisOrigen> listarPaisOrigen() {
        return paisOrigenDao.findAll();
    }

    @Override
    public PaisOrigen encontrarPais(Object id) {
        return paisOrigenDao.find(id);
    }

    @Override
    public List<SelectItem> oneMenuPais(List<PaisOrigen> listaPais) {      
        List<SelectItem> itemsOneMenu = new ArrayList<>();
        for (PaisOrigen p : listaPais) {
            SelectItem tmp = new SelectItem(p.getIdPais(),p.getNomPais());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    
    }

    @Override
    public List<PaisOrigen> ListarPaisporNombre() {
        return paisOrigenDao.ListarPaisporNombre();
    }
    
}
