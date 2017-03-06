/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.RelacionIesDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
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
public class RelacionIesServicioImp implements RelacionIesServicio{
    @EJB
    private RelacionIesDao relacionIesDao=null;

    public RelacionIesServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.relacionIesDao=factoria.getRelacionIesDao();
    }

    @Override
    public void insertarRelacionIes(RelacionIes relacionIes) {
       relacionIesDao.create(relacionIes);
    }

    @Override
    public void eliminarRelacionIes(RelacionIes relacionIes) {
        relacionIesDao.remove(relacionIes);
    }

    @Override
    public void actualizarRelacionIes(RelacionIes relacionIes) {
        relacionIesDao.edit(relacionIes);
    }

    @Override
    public List<RelacionIes> listarRelacionIes() {
        return relacionIesDao.findAll();
    }

    @Override
    public RelacionIes encontrarRelacion(Object id) {
        return relacionIesDao.find(id);
    }
    @Override
      public  List<SelectItem> oneMenuRelacionIes(List<RelacionIes> listaRelacionIes) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (RelacionIes re : listaRelacionIes) {
            SelectItem tmp = new SelectItem(re.getIdRelacionIes(), re.getNomRelacionIes());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public List<RelacionIes> listarRelacionIesporNombre() {
       return  relacionIesDao.listarRelacionIesporNombre();
    }
}
