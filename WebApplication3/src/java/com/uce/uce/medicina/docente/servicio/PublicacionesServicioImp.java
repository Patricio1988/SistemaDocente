/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.PublicacionesDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Publicaciones;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Patricio
 */
@Stateless
public class PublicacionesServicioImp implements PublicacionesServicio{
    @EJB
    private PublicacionesDao publicacionesDao=null;

    public PublicacionesServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.publicacionesDao=factoria.getPublicacionesDao();
    }

    @Override
    public void insertarPublicaciones(Publicaciones publicaciones) {
        publicacionesDao.create(publicaciones);
    }

    @Override
    public void eliminarPublicaciones(Publicaciones publicaciones) {
      publicacionesDao.remove(publicaciones);
    }

    @Override
    public void actualizarPublicaciones(Publicaciones publicaciones) {
        publicacionesDao.edit(publicaciones);
    }

    @Override
    public List<Publicaciones> listarPublicaciones() {
        return publicacionesDao.findAll();
    }

    @Override
    public Publicaciones buscarPublicacionesporNombre(String nombre) {
       return publicacionesDao.buscarPublicacionesporNombre(nombre);
    }

    @Override
    public List<Publicaciones> listaPublicacionesporDocente(String cedula) {
       return publicacionesDao.listaPublicacionesporDocente(cedula);
    }

    @Override
    public Publicaciones buscarPublicacionesid(Object id) {
       return publicacionesDao.find(id);
    }

    @Override
    public boolean buscarRegistroPorTpPublicacion(TipoPublicacion idTpPubli) {
       return publicacionesDao.buscarRegistroPorTpPublicacion(idTpPubli);
    }

    
}
