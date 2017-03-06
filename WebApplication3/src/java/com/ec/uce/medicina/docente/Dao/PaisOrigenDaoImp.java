/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase PaisOrigenDaoImp que implemeta los métodos de la interfaz
 PaisOrigenDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class PaisOrigenDaoImp extends AbstractFacade<PaisOrigen> implements PaisOrigenDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisOrigenDaoImp() {
        super(PaisOrigen.class);
    }
    /**Método que devuelve una lista de paises y los ordena por nombre
     * @return q.getResultList()
     */
        @Override
    public List<PaisOrigen> ListarPaisporNombre() {
        Query q = em.createQuery("SELECT p FROM PaisOrigen p Order by p.nomPais");
        return q.getResultList();
    }

}
