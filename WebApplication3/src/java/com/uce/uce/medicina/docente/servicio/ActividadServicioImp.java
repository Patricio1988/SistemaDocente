/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.ActividadDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Actividad;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import org.postgresql.util.PSQLException;

/**
 * Declaración de la clase ActividadServicioImp que implemeta los metodos de la intefaz ActividadServicio
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class ActividadServicioImp implements ActividadServicio{
   @EJB
    private ActividadDao actividadDao=null;

    public ActividadServicioImp() throws PSQLException {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.actividadDao=factoria.getActividadDao();
    }
    @Override
    public void insertarActividad(Actividad capacitacion)  throws PSQLException  {
      
            
            actividadDao.create(capacitacion);
           
    }

    @Override
    public void eliminarActividad(Actividad capacitacion) {
       actividadDao.remove(capacitacion);
    }

    @Override
    public void actualizarActividad(Actividad capacitacion) {
       actividadDao.edit(capacitacion);
    }

    @Override
    public Actividad buscarActividadporID(Object id) {
        return actividadDao.find(id);
    }

    @Override
    public List<Actividad> listaActividadporNombre() {
        return actividadDao.listaActividadporNombre();
    }

    @Override
    public Actividad buscarActividad(int id) {
        return actividadDao.buscarActividad(id);
    }

    
}
