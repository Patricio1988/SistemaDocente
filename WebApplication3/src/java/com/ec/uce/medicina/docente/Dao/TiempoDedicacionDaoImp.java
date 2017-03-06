/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase TiempoDedicacionDaoImp que implemeta los métodos de la interfaz
 * TiempoDedicacionDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class TiempoDedicacionDaoImp extends AbstractFacade<TiempoDedicacion> implements TiempoDedicacionDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiempoDedicacionDaoImp() {
        super(TiempoDedicacion.class);
    }
  /** Método que devuelve una lista con registros tiempo de dedicación y los ordena por nombre
     * @return q.getResultList()
     */
    @Override
    public List<TiempoDedicacion> listarTiempoDediporNombre() {
        Query q = em.createQuery("SELECT t FROM TiempoDedicacion t Order by t.nomTdedi");
        return q.getResultList();
    }

}
