/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Malla;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase MallaDaoImp que implemeta los métodos de la interfaz
 * MallaDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class MallaDaoImp extends AbstractFacade<Malla> implements MallaDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MallaDaoImp() {
        super(Malla.class);
    }

  /**
     * Método que devuelve una lista de mallas cuyo estado sea vigente.
     *
     * @return q.getResultList()
     */
    @Override
    public List<Malla> ListaMallaVigentes() {

        Query q = em.createQuery("SELECT m FROM Malla m WHERE m.estadoMalla=true ORDER BY m.nomMalla");
        return q.getResultList();

    }
/**
     * Método que busca un registro carrera en un registro  malla.
     *
     * @param idCarrera
     * @return respuesta
     */
    @Override
    public boolean buscarRegistroPorCarrera(Carrera idCarrera) {
       boolean respuesta=false;
        Query q= em.createQuery("SELECT m FROM Malla m WHERE m.idCarrera =:idCarrera");
        q.setParameter("idCarrera", idCarrera);
        List<Malla> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }

    @Override
    public List<Malla>  ListaMallaPorCarrera(int idCarrera) {
        Query q = em.createQuery("SELECT m FROM Malla m WHERE m.idCarrera.idCarrera =:idCarrera and m.estadoMalla=true ORDER BY m.nomMalla");
       q.setParameter("idCarrera",idCarrera);
        return q.getResultList(); 
    }
    
}
