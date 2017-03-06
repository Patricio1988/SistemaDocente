/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.TipoPublicacionDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
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
public class TipoPublicacionServicioImp implements TipoPublicacionServicio{
@EJB
    private TipoPublicacionDao tipoPublicacionDao=null;

    public TipoPublicacionServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.tipoPublicacionDao=factoria.getTipoPublicacionDao();
    }
    @Override
    public void insertarTipoPublicacion(TipoPublicacion tipoPublicacion) {
        tipoPublicacionDao.create(tipoPublicacion);
    }

    @Override
    public void eliminarTipoPublicacion(TipoPublicacion tipoPublicacion) {
        tipoPublicacionDao.remove(tipoPublicacion);
    }

    @Override
    public void actualizarTipoPublicacion(TipoPublicacion tipoPublicacion) {
        tipoPublicacionDao.edit(tipoPublicacion);
    }

    @Override
    public List<TipoPublicacion> listarTipoPublicacion() {
      return tipoPublicacionDao.findAll();
    }

    @Override
    public TipoPublicacion encontrarTipoPublicacionID(Object id) {
        return tipoPublicacionDao.find(id);
    }

    @Override
    public List<TipoPublicacion> listarTipoPublicacionporNombre() {
        return tipoPublicacionDao.listarTipoPublicacionporNombre();
    }

    @Override
    public List<SelectItem> oneMenutipoPublicacion(List<TipoPublicacion> listaTipoPublicacion) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (TipoPublicacion u : listaTipoPublicacion) {
            SelectItem tmp = new SelectItem(u.getIdTipoPublicacion(), u.getNomTipoPublicacion());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }
     
    
}
