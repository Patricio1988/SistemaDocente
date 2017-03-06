/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase TipoPublicacionDaoImp que implemeta los métodos de la interfaz
 * TipoPublicacionDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class TipoPublicacionDaoImp extends AbstractFacade<TipoPublicacion> implements TipoPublicacionDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoPublicacionDaoImp() {
        super(TipoPublicacion.class);
    }
/** Método que devuelve una lista con registros tipo de publicación y los ordena por nombre
     * @return q.getResultList()
     */
    @Override
    public List<TipoPublicacion> listarTipoPublicacionporNombre() {
        Query q = em.createQuery("SELECT t FROM TipoPublicacion t Order by t.nomTipoPublicacion");
        return q.getResultList();
    }
}
