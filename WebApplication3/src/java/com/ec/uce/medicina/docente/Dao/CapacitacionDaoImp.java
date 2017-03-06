/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Capacitacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de CapacitacionDaoImp que implemeta los metodos de la intefaz CapacitacionDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class CapacitacionDaoImp extends AbstractFacade<Capacitacion> implements CapacitacionDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CapacitacionDaoImp() {
        super(Capacitacion.class);
    }
    /**Método que duvuelve una lista de los registros de capacitación de un docente.
     * @param cedula
     * @return q.getResultList()
     */
    @Override
        public List<Capacitacion> listaCapacitacionporDocente(String cedula) {
         Query q= em.createQuery("SELECT c FROM Capacitacion c JOIN C.idDocente AS ca WHERE ca.identificacion =:identificacion");
        q.setParameter("identificacion", cedula);
      return  q.getResultList();
    }
 /**Método que busca una capacitación por su id.
     * @param id
     * @return capacitacionEncontrada
     */
    @Override
    public Capacitacion buscarCapacitacionID(int id) {
       Capacitacion capacitacionEncontrada=null;
        Query q= em.createQuery("SELECT c FROM Capacitacion c WHERE c.idCapacitacion = :idCapacitacion");
        q.setParameter("idCapacitacion", id);
        List<Capacitacion> lista=q.getResultList();
        if (!lista.isEmpty()) {
            capacitacionEncontrada=lista.get(0);
        } 
        return  capacitacionEncontrada;
    }

  
    
}
