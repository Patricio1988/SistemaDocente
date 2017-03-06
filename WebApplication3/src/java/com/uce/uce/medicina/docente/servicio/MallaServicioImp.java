/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.MallaDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Malla;
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
public class MallaServicioImp implements MallaServicio{
     @EJB
    private MallaDao mallaDao=null;

    public MallaServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.mallaDao=factoria.getMallaDao();
    }

    @Override
    public void insertarMalla(Malla malla) {
        mallaDao.create(malla);
    }

    @Override
    public void eliminarMalla(Malla malla) {
        mallaDao.remove(malla);
    }

    @Override
    public void actualizarMalla(Malla malla) {
        mallaDao.edit(malla);
    }

    @Override
    public List<Malla> listarMalla() {
        return mallaDao.findAll();
    }

    @Override
    public List<SelectItem> oneMenuMalla(List<Malla> listaMalla) {
             List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Malla ma : listaMalla) {
            SelectItem tmp = new SelectItem(ma.getIdMalla(), ma.getNomMalla());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public Malla encontrarMallaID(Object id) {
       return mallaDao.find(id);
    }


    @Override
    public List<Malla> ListaMallaVigentes() {
       return mallaDao.ListaMallaVigentes();
    }

    @Override
    public boolean buscarRegistroPorCarrera(Carrera idCarrera) {
       return mallaDao.buscarRegistroPorCarrera(idCarrera);
    }

    @Override
    public List<Malla> ListaMallaPorCarreraVigentes(int idCarrera) {
       return mallaDao.ListaMallaPorCarrera(idCarrera);
    }
    
}
