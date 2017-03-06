/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Universidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase UniversidadDaoImp que implemeta los métodos de la interfaz
 * UniversidadDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class UniversidadDaoImp extends AbstractFacade<Universidad> implements UniversidadDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UniversidadDaoImp() {
        super(Universidad.class);
    }
    /**
     * Método que busca una universidad por su id
     *
     * @return q.getResultList()
     */
    @Override
    public Universidad buscarUniversidadID(int id) {
        Universidad universidadEncontrada = null;
        Query q = em.createQuery("SELECT u FROM Universidad u WHERE u.idUniversidad = :idUniversidad");
        q.setParameter("idUniversidad", id);
        List<Universidad> lista = q.getResultList();
        if (!lista.isEmpty()) {
            universidadEncontrada = lista.get(0);
        }
        return universidadEncontrada;
    }
}
