/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Publicaciones;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase PublicacionesDaoImp que implemeta los métodos de la interfaz
 * PublicacionesDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class PublicacionesDaoImp extends AbstractFacade<Publicaciones> implements PublicacionesDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PublicacionesDaoImp() {
        super(Publicaciones.class);
    }
       /**Método que busca una publicación por su nombre
     * @param nombre
     * @return publicacionesEncontrada
     */
        @Override
    public Publicaciones buscarPublicacionesporNombre(String nombre) {
       Publicaciones publicacionesEncontrada=null;
        Query q= em.createQuery("SELECT p FROM Publicaciones p WHERE p.nomPublicacion = :nomPublicacion");
        q.setParameter("nomPublicacion", nombre);
        List<Publicaciones> lista=q.getResultList();
        if (!lista.isEmpty()) {
            publicacionesEncontrada=lista.get(0);
        } 
        return  publicacionesEncontrada;
    }
     /**Método que devuelve una lista con las publicacioes de un docente
     * @param cedula
     * @return  q.getResultList();
     */
    @Override
    public List<Publicaciones> listaPublicacionesporDocente(String cedula) {
          Query q= em.createQuery("SELECT p FROM Publicaciones p JOIN P.idDocente AS publi WHERE publi.identificacion =:identificacion");
        q.setParameter("identificacion", cedula);
      return  q.getResultList();
    }
  /**Método busca un registro tipo de publicación en un registro publicación
     * @param idTpPubli
     * @return  respuesta
     */
    @Override
    public boolean buscarRegistroPorTpPublicacion(TipoPublicacion idTpPubli) {
        boolean respuesta=false;
        Query q= em.createQuery("SELECT p FROM Publicaciones p WHERE p.idTipoPublicacion =:idTpPubli");
        q.setParameter("idTpPubli", idTpPubli);
        List<Publicaciones> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
