/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.DocenteDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Patricio
 */
@Stateless
public class DocenteServicioImp implements DocenteServicio{
    @EJB
    private DocenteDao docenteDao=null;

    public DocenteServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.docenteDao=factoria.getDocenteDao();
    }

    @Override
    public void insertarDocente(Docente docente) {
        docenteDao.create(docente);
    }

    @Override
    public void eliminarDocente(Docente docente) {
        docenteDao.remove(docente);
    }

    @Override
    public void actualizarDocente(Docente docente) {
        docenteDao.edit(docente);
    }

    @Override
    public List<Docente> listarDocente() {
        return docenteDao.findAll();
    }

    @Override
    public Docente encontrarDocente(Object id) {
       return docenteDao.find(id);
    }

    @Override
    public Docente buscarDocenteporCedula(String cedula) {
        return docenteDao.buscarDocenteporCedula(cedula);
    }

  

    @Override
    public List<Docente> listaDocentesTodosporApellido() {
        return docenteDao.listaDocentesTodosporApellido();
    }

    @Override
    public boolean buscarRegistroPorPais(PaisOrigen idpais) {
      return docenteDao.buscarRegistroPorPais(idpais);
    }

    @Override
    public Docente buscarDocentdIDCarrera(int idDocente, int idCarrera) {
      return docenteDao.buscarDocentdIDCarrera(idDocente, idCarrera);
    }
    
}
