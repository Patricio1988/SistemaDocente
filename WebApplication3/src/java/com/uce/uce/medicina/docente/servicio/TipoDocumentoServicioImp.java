/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.TipoDocumentoDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
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
public class TipoDocumentoServicioImp implements TipoDocumentoServicio{
    @EJB
    private TipoDocumentoDao tipoDocumentoDao=null;

    public TipoDocumentoServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.tipoDocumentoDao=factoria.getTipoDocumentoDao();
    }

    @Override
    public void insertarTipoDocumento(TipoDocumento tipoDocumento) {
       tipoDocumentoDao.create(tipoDocumento);
    }

    @Override
    public void eliminarTipoDocumento(TipoDocumento tipoDocumento) {
        tipoDocumentoDao.remove(tipoDocumento);
    }

    @Override
    public void actualizarTipoDocumento(TipoDocumento tipoDocumento) {
        tipoDocumentoDao.edit(tipoDocumento);
    }

    @Override
    public List<TipoDocumento> listarTipoDocumento() {
        return tipoDocumentoDao.findAll();
    }

    @Override
    public TipoDocumento encontrarTipoDocumento(Object id) {
        return tipoDocumentoDao.find(id);
    }
     @Override
    public List<SelectItem> oneMenuTipoDocumento(List<TipoDocumento> listaTipoDocumenrto) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (TipoDocumento tipo : listaTipoDocumenrto) {
            SelectItem tmp = new SelectItem(tipo.getIdTipoDocumento(), tipo.getNomTipoDocumento());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public List<TipoDocumento> listarTipoDocuporNombre() {
        return tipoDocumentoDao.listarTipoDocuporNombre();
    }
    
}
