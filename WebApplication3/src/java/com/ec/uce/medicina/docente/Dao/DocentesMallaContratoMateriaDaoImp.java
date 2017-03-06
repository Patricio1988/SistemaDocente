/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Actividad;
import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import com.ec.uce.medicina.docente.modelo.Materia;
import com.ec.uce.medicina.docente.modelo.Periodo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de la clase DocentesMallaContratoMateriaDaoImp que implemeta los métodos de la interfaz DocentesMallaContratoMateriaDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class DocentesMallaContratoMateriaDaoImp extends AbstractFacade<DocentesMallaContratoMateria> implements DocentesMallaContratoMateriaDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocentesMallaContratoMateriaDaoImp() {
        super(DocentesMallaContratoMateria.class);
    }
     /**Método que devuelve una lista de carga horaría de docente de una determinada carrera y en un determinado periodo. 
     * @param cedula
     * @param idPeriodo
     * @param idCarrera
     * @param idReLaboral
     * @return q.getResultList()
     */
        @Override
    public List<DocentesMallaContratoMateria> listarCargaporDocente(String cedula,int idPeriodo,int idCarrera,int idReLaboral) {
        Query q = em.createQuery("SELECT c FROM DocentesMallaContratoMateria c,RelacionLaboral r,Docente d,CarreraDocente cd,Periodo p,Carrera ca \n" +
"where r.idReLaboral= c.idReLaboral.idReLaboral  and cd.idCarreraDocente=r.idCarreraDocente.idCarreraDocente\n" +
"and d.idDocente=cd.idDocente.idDocente and p.idPeriodo=cd.idPeriodo.idPeriodo and ca.idCarrera=cd.idCarrera.idCarrera\n" +
"and ca.idCarrera=:idCarrera and d.identificacion=:identificacion and p.idPeriodo=:idPeriodo and r.idReLaboral=:idReLaboral");
        q.setParameter("identificacion", cedula);
        q.setParameter("idPeriodo", idPeriodo);
         q.setParameter("idCarrera", idCarrera);
         q.setParameter("idReLaboral", idReLaboral);
        return q.getResultList();
    }
   /**Método que busca un registro de una materia en un un registro carga horaría. 
     *
     * @param idMateria
     * @return <ul>
     * <li>true:si exsite un registro de materia en el registro carga horaría</li>
     * <li>false:no exsite un registro de materia en el registro carga horaría</li>
     * </ul>
     */
    @Override
    public boolean buscarRegistroPorMateria(Materia idMateria) {
             boolean respuesta=false;
        Query q= em.createQuery("SELECT d FROM DocentesMallaContratoMateria d WHERE d.idMateria =:idMateria");
        q.setParameter("idMateria", idMateria);
        List<Materia> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
      /**Método que busca un registro de un periodo en  el registro carga horaría. 
     *
     * @param idPeriodo
     * @return <ul>
     * <li>true:si exsite un registro de un período en el registro carga horaría</li>
     * <li>false:no exsite un registro de un período en el registro carga horaría</li>
     * </ul>
     */

    @Override
    public boolean buscarRegistroPorPeriodo(Periodo idPeriodo) {
           boolean respuesta=false;
        Query q= em.createQuery("SELECT d FROM DocentesMallaContratoMateria d WHERE d.idPeriodo =:idPeriodo");
        q.setParameter("idPeriodo", idPeriodo);
        List<Materia> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    /**Método que busca un registro de una actividad en  el registro carga horaría. 
     *
     * @param idActividad
     * @return <ul>
     * <li>true:si exsite un registro de una actividad en el registro carga horaría</li>
     * <li>false:no exsite un registro de una actividad en el registro carga horaría</li>
     * </ul>
     */
    @Override
    public boolean buscarRegistroPorActividad(Actividad idActividad) {
         boolean respuesta=false;
        Query q= em.createQuery("SELECT d FROM DocentesMallaContratoMateria d WHERE d.idActivida =:idActividad");
        q.setParameter("idActividad", idActividad);
        List<Materia> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
