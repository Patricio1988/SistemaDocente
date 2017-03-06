/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Actividad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * Declaración de la clase ActividadDaoImp que implemeta los metodos de la intefaz ActividadDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class ActividadDaoImp extends AbstractFacade<Actividad> implements ActividadDao {

@PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadDaoImp() {
        super(Actividad.class);
    }
         @Override
    public List<Actividad> listaActividadporNombre() {
      Query q= em.createQuery("SELECT a FROM Actividad a Order by a.nomActividad");   
      return  q.getResultList();
    }
/**Método que permite buscar una Actividad por su id
     * @param id
     * @return actividadEncontrada
 */
    @Override
    public Actividad buscarActividad(int id) {
       Actividad actividadEncontrada=null;
        Query q= em.createQuery("SELECT a FROM Actividad a WHERE a.idActivida = :idActivida");
        q.setParameter("idActivida", id);
        List<Actividad> lista=q.getResultList();
        if (!lista.isEmpty()) {
           actividadEncontrada=lista.get(0);
        } 
        return  actividadEncontrada;
    }
}
