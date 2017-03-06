/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Materia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase MateriaDaoImp que implemeta los métodos de la interfaz
 * MateriaDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class MateriaDaoImp extends AbstractFacade<Materia> implements MateriaDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MateriaDaoImp() {
        super(Materia.class);
    }
    /**Método que devuelve una lista de materias por el id de la malla.
     * 
     * @param id
     * @return q.getResultList()
     */
        @Override
    public List<Materia> listaMateriasporMalla(int id) {
        Query q = em.createQuery("SELECT m FROM Materia m JOIN M.idMalla AS ma WHERE ma.idMalla =:idMalla ORDER BY m.codMateria,m.semestre asc");
        q.setParameter("idMalla", id);
        return q.getResultList();
    }
 /**Método que busca un registro malla en un registro matería.
     * 
     * @param idMalla
     * @return qrespuesta
     */
    @Override
    public boolean buscarRegistroPorMalla(Malla idMalla) {
             boolean respuesta=false;
        Query q= em.createQuery("SELECT m FROM Materia m WHERE m.idMalla = :idMalla");
        q.setParameter("idMalla", idMalla);
        List<Materia> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
