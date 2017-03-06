/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase IesDaoImp que implemeta los métodos de la interfaz
 * IesDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class IesDaoImp extends AbstractFacade<Ies> implements IesDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IesDaoImp() {
        super(Ies.class);
    }

    /**
     * Método que devuelve una lista de IES y los ordena por su código.
     *
     * @return q.getResultList()
     */
    @Override
    public List<Ies> listarIesporCodigo() {
        Query q = em.createQuery("SELECT i FROM Ies i Order by i.codIes");
        return q.getResultList();
    }
 /**
     * Método que busca un registro Ies por su código.
     *
     * @return iesEncontrada
     */
    @Override
    public Ies buscarporCodigo(int codIes) {
        Ies iesEncontrada = null;
        Query q = em.createQuery("SELECT i FROM Ies i WHERE i.codIes = :codIes");
        q.setParameter("codIes", codIes);

        List<Ies> lista = q.getResultList();
        if (!lista.isEmpty()) {
            iesEncontrada = lista.get(0);
        }
        return iesEncontrada;
    }

}
