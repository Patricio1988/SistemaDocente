/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Formacion;
import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de  la clase FormacionDaoImp que implemeta los métodos de la interfaz FormacionDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class FormacionDaoImp extends AbstractFacade<Formacion> implements FormacionDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FormacionDaoImp() {
        super(Formacion.class);
    }
      /**Método que busca un registro formación por su id. 
     * @param idFormacion
     * @return formacionEncontrada
     */
        @Override
    public Formacion buscarFormacionID(int idFormacion) {
        Formacion formacionEncontrada = null;
        Query q = em.createQuery("SELECT f FROM Formacion f WHERE f.idFormacion = :idFormacion");
        q.setParameter("idFormacion", idFormacion);
        List<Formacion> lista = q.getResultList();
        if (!lista.isEmpty()) {
            formacionEncontrada = lista.get(0);
        }
        return formacionEncontrada;
    }
    /**Método que devuelve una lista de registros de formación pertenecientes a un docente. 
     * @param cedula
     * @return q.getResultList()
     */
    @Override
    public List<Formacion> listaFormacionporDocente(String cedula) {
        Query q = em.createQuery("SELECT f FROM Formacion f JOIN f.idDocente AS ca WHERE ca.identificacion =:identificacion");
        q.setParameter("identificacion", cedula);
        return q.getResultList();
    }
    /**Método que busca un registro Ies en un registro Formación. 
     * @param idIes
     *    * @return <ul>
     * <li>true:si exsite un registro Ies en el registro formación</li>
     * <li>false:no exsite un registro Ies en el registro  formación</li>
     * </ul>
     */
    @Override
    public boolean buscarRegistroPoriES(Ies idIes) {
         boolean respuesta=false;
        Query q= em.createQuery("SELECT f FROM Formacion f WHERE f.idIes =:idIes");
        q.setParameter("idIes", idIes);
        List<Formacion> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
