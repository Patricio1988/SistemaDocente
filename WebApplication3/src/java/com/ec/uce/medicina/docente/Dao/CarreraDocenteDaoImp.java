/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de CarreraDocenteDaoImp que implemeta los metodos de la intefaz CarreraDocenteDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class CarreraDocenteDaoImp extends AbstractFacade<CarreraDocente> implements CarreraDocenteDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarreraDocenteDaoImp() {
        super(CarreraDocente.class);
    }
  /**Método que devuelve uba lista de los docentes de una carrera cuyo estado sea vigente .
     * @param idCarrera
     * @param idPeriodo
     * @return q.getResultList();
     */
    @Override
    public List<CarreraDocente> listarDocentesCarreraVigentes(int idCarrera,int idPeriodo) {
        Query q= em.createQuery("Select cd from CarreraDocente cd,Docente d,Carrera ca,Periodo p where d.idDocente=cd.idDocente.idDocente \n" +
"and ca.idCarrera=cd.idCarrera.idCarrera and p.idPeriodo=cd.idPeriodo.idPeriodo \n" +
"and p.idPeriodo =:idPeriodo and ca.idCarrera=:idCarrera  and cd.estadoDocente=true \n" +
"ORDER BY cd.idDocente.apellidoPaterno");
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("idPeriodo", idPeriodo);
        return q.getResultList();
    }
/**Método que devuelve uba lista de los docentes de una carrera y los ordena por apellido .
     * @param idPeriodo
     * @return q.getResultList();
     */
    @Override
    public List<CarreraDocente> listarDocentesCarreraporApellido(int idPeriodo) {
        Query q= em.createQuery("Select cd from CarreraDocente cd ,Periodo p,Docente d \n" +
"where p.idPeriodo=cd.idPeriodo.idPeriodo and d.idDocente=cd.idDocente.idDocente\n" +
"and p.idPeriodo =:idPeriodo and cd.estadoDocente=true ORDER BY cd.idDocente.apellidoPaterno");
        q.setParameter("idPeriodo", idPeriodo);
        return q.getResultList();
    }
    /**Método que devuelve una lista de los docentes pertencientes a una carrera cuyo estado se vigente y no vigente
     * @param idCarrera
     * @param idPeriodo
     * @return q.getResultList()
     */
     @Override
    public List<CarreraDocente> listarDocentesCarreraTodos(int idCarrera,int idPeriodo) {
        Query q= em.createQuery("Select cd from CarreraDocente cd,Docente d,Carrera ca,Periodo p where d.idDocente=cd.idDocente.idDocente \n" +
"and ca.idCarrera=cd.idCarrera.idCarrera and p.idPeriodo=cd.idPeriodo.idPeriodo \n" +
"and p.idPeriodo =:idPeriodo and ca.idCarrera=:idCarrera and cd.estadoDocente=true  \n" +
"ORDER BY cd.idDocente.apellidoPaterno ");
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("idPeriodo", idPeriodo);
        return q.getResultList();
    }
/**Método que permite encontrar un docente de una determinada Carrer
     * @param idDocente
     * @param idCarrera
     * @return 
     */
    @Override
    public CarreraDocente buscarDocenteCarreraporID(int idDocente, int idCarrera) {
        CarreraDocente docenteCarreraEncontrado=null;
        Query q= em.createQuery("Select cd from CarreraDocente cd,Docente d,Carrera ca where d.idDocente=cd.idDocente.idDocente and ca.idCarrera=cd.idCarrera.idCarrera and d.idDocente =:idDocente and ca.idCarrera=:idCarrera");
        q.setParameter("idDocente", idDocente);
        q.setParameter("idCarrera", idCarrera);
        List<CarreraDocente> lista=q.getResultList();
        if (!lista.isEmpty()) {
            docenteCarreraEncontrado=lista.get(0);
        } 
        return  docenteCarreraEncontrado;
     
    }

    @Override
    public CarreraDocente buscarDocenteCarreraporPeriodo(String identificacion, int idPeriodo, int idCarrera) {
               CarreraDocente docenteCarreraEncontrado=null;
        Query q= em.createQuery("select cd from CarreraDocente cd,Carrera c,Docente d, Periodo p\n" +
"where d.idDocente=cd.idDocente.idDocente and c.idCarrera=cd.idCarrera.idCarrera\n" +
"and p.idPeriodo=cd.idPeriodo.idPeriodo and cd.estadoDocente=false and d.identificacion=:identificacion \n" +
"and p.idPeriodo=:idPeriodo and c.idCarrera=:idCarrera");
        q.setParameter("identificacion", identificacion);
        q.setParameter("idPeriodo", idPeriodo);
        q.setParameter("idCarrera", idCarrera);
        List<CarreraDocente> lista=q.getResultList();
        if (!lista.isEmpty()) {
            docenteCarreraEncontrado=lista.get(0);
        } 
        return  docenteCarreraEncontrado;

    }
    
}
