/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.FormacionDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Formacion;
import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Patricio
 */
@Stateless
public class FormacionServicioImp implements FormacionServicio{
    @EJB
    private FormacionDao formaciondDao=null;

    public FormacionServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.formaciondDao=factoria.getFormacionDao();
    }

    @Override
    public void insertarFormacion(Formacion formacion) {
        formaciondDao.create(formacion);
    }

    @Override
    public void eliminarFormacion(Formacion formacion) {
        formaciondDao.remove(formacion);
    }

    @Override
    public void actualizarFormacion(Formacion formacion) {
       formaciondDao.edit(formacion);
    }

    @Override
    public List<Formacion> listarFormacion() {
        return formaciondDao.findAll();
    }

    @Override
    public Formacion buscarFormacionporID(int idFormacion) {
       return formaciondDao.buscarFormacionID(idFormacion);
    }

    @Override
    public List<Formacion> listaFormacionporDocente(String cedula) {
        return formaciondDao.listaFormacionporDocente(cedula);
    }

    @Override
    public boolean buscarRegistroPoriES(Ies idIes) {
        return formaciondDao.buscarRegistroPoriES(idIes);
    }
    
}
