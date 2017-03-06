/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.TiempoDedicacionDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
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
public class TiempoDedicacionServicioImp implements TiempoDedicacionServicio{
    @EJB
    private TiempoDedicacionDao tiempoDedicacionDao=null;

    public TiempoDedicacionServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.tiempoDedicacionDao=factoria.getTiempoDedicacionDao();
    } 

    @Override
    public void insertarTiempoDedicacion(TiempoDedicacion tiempoDedicacion) {
        tiempoDedicacionDao.create(tiempoDedicacion);
    }

    @Override
    public void eliminarTiempoDedicacion(TiempoDedicacion TiempoDedicacion) {
        tiempoDedicacionDao.remove(TiempoDedicacion);
    }

    @Override
    public void actualizarTiempoDedicacion(TiempoDedicacion TiempoDedicacion) {
       tiempoDedicacionDao.edit(TiempoDedicacion);
    }

    @Override
    public List<TiempoDedicacion> listarTiempoDedicacion() {
        return tiempoDedicacionDao.findAll();
    }

    @Override
    public TiempoDedicacion encontarTiempoDedicacionID(Object id) {
      return tiempoDedicacionDao.find(id);
    }
       @Override
    public List<SelectItem> oneMenuTiempoDedicacion(List<TiempoDedicacion> listaTiempoDedicacion) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (TiempoDedicacion tiempo : listaTiempoDedicacion) {
            SelectItem tmp = new SelectItem(tiempo.getIdTdedi(), tiempo.getNomTdedi());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public List<TiempoDedicacion> listarTiempoDediporNombre() {
        return tiempoDedicacionDao.listarTiempoDediporNombre();
    }
}
