/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Facultad;
import com.ec.uce.medicina.docente.modelo.Universidad;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de FacultadDaoImp que implemeta los metodos de la intefaz FacultadDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class FacultadDaoImp extends AbstractFacade<Facultad> implements FacultadDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacultadDaoImp() {
        super(Facultad.class);
    }
     /**Método que devuelve una lista de facultades por el id de una universidad. 
     * @param idUniversidad1
     * @return q.getResultList()
     */
        @Override
    public List<Facultad> listfacultadaPorUniversidad(int idUniversidad1) {

        Query q = em.createQuery("SELECT f FROM Facultad f WHERE f.idUniversidad.idUniversidad = :idUniversidad");
        q.setParameter("idUniversidad", idUniversidad1);
        return q.getResultList();

    }
   /**Método que busca una facuyltad por su id. 
     * @param id
     * @return facultadEncontrada
     */
    @Override
    public Facultad buscarFacultadID(int id) {
        Facultad facultadEncontrada = null;
        Query q = em.createQuery("SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad");
        q.setParameter("idFacultad", id);
        List<Facultad> lista = q.getResultList();
        if (!lista.isEmpty()) {
            facultadEncontrada = lista.get(0);
        }
        return facultadEncontrada;
    }
      /**Método que busca un registro de universidad en el registro facultad. 
     * @param idUniversidad
     * @return facultadEncontrada
     */
     @Override
        public boolean buscarRegistroPorUniversidad(Universidad idUniversidad) {
        boolean respuesta=false;
        Query q= em.createQuery("SELECT f FROM Facultad f WHERE f.idUniversidad = :idUniversidad");
        q.setParameter("idUniversidad", idUniversidad);
        List<Facultad> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
