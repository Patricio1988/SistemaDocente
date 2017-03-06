/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Facultad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de CarreraDaoImp que implemeta los metodos de la intefaz CarreraDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class CarreraDaoImp extends AbstractFacade<Carrera> implements CarreraDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraDaoImp() {
        super(Carrera.class);
    }
     /**Método que busca una lista de carreras por el id de la facultad.
     * @param idFacultad1
     * @return q.getResultList();
     */
          @Override
    public List<Carrera> buscarCarreraPorFacultad(int idFacultad1) {
        
        Query q= em.createQuery("SELECT c FROM Carrera c WHERE c.idFacultad.idFacultad = :idFacultad");
        q.setParameter("idFacultad",idFacultad1);
        return q.getResultList();
    }
   /**Método que busca un registro de una facultad en una carrera
    * @param idFacultad
     * @return respuesta
     */
@Override
    public boolean buscarRegistroPorFacultad(Facultad idFacultad) {
         boolean respuesta=false;
        Query q= em.createQuery("SELECT c FROM Carrera c WHERE c.idFacultad =:idFacultad");
        q.setParameter("idFacultad", idFacultad);
        List<Facultad> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
