/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.FacultadDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Facultad;
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
public class FacultadServicioImp implements FacultadServicio{
 @EJB
    private FacultadDao facultadDao=null;

    public FacultadServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.facultadDao=factoria.getFacultadDao();
    }
    @Override
    public void insertarFacultad(Facultad facultad) {
        facultadDao.create(facultad);
    }

    @Override
    public void eliminarFacultad(Facultad facultad) {
        facultadDao.remove(facultad);
    }

    @Override
    public void actualizarFacultad(Facultad facultad) {
       facultadDao.edit(facultad);
    }

    @Override
    public List<Facultad> listarFacultad() {
        return  facultadDao.findAll();
    }

    @Override
    public Facultad encontrarFacultad(Object id) {
         return  facultadDao.find(id);
        
    }

    @Override
    public List<Facultad> listfacultadaPorUniversidad(int idPais) {
        return facultadDao.listfacultadaPorUniversidad(idPais);
    }
    @Override
     public List<SelectItem> oneMenuFacultad(List<Facultad> listaFacultad) {
        List<SelectItem> itemsOneMenu = new ArrayList<>();
        for (Facultad u : listaFacultad) {
            SelectItem tmp = new SelectItem(u.getIdFacultad(), u.getNomFacultad());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }

    @Override
    public Facultad buscarFacultadID(int id) {
       return facultadDao.buscarFacultadID(id);
    }

    @Override
    public boolean buscarRegistroPorUniversidad(Universidad idUniversidad) {
       return facultadDao.buscarRegistroPorUniversidad(idUniversidad);
    }
    
}
