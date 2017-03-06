/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase RelacionIesDaoImp que implemeta los métodos de la interfaz
 * RelacionIesDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class RelacionIesDaoImp extends AbstractFacade<RelacionIes> implements RelacionIesDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionIesDaoImp() {
        super(RelacionIes.class);
    }
         /**Método que devuelve una lista de relación con la IES y las ordena por nombre
     * @return q.getResultList()
     */
       @Override
    public List<RelacionIes> listarRelacionIesporNombre() {
        Query q = em.createQuery("SELECT r FROM RelacionIes r Order by r.nomRelacionIes");
        return q.getResultList();
    }
    
}
