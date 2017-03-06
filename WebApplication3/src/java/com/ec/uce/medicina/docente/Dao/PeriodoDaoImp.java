/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Periodo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase PeriodoDaoImp que implemeta los métodos de la interfaz
 * PeriodoDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class PeriodoDaoImp extends AbstractFacade<Periodo> implements PeriodoDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PeriodoDaoImp() {
        super(Periodo.class);
    }
    /**Método que devuelve una lista con los períodos y los ordena por fecha de inicio
     * @return q.getResultList()
     */
    @Override
    public List<Periodo> listarPeriodoporFechaInicio() {
        Query q = em.createQuery("SELECT p FROM Periodo p Order by p.feInPer");
        return q.getResultList();
    }
 /**Método que devuelve una lista con los períodos con estado activo
     * @return q.getResultList()
     */
    @Override
    public List<Periodo> listarPeriodoporActivos() {
        Query q = em.createQuery("SELECT p FROM Periodo p where p.estadoPeriodo=true");
        return q.getResultList();
    }
    
}
