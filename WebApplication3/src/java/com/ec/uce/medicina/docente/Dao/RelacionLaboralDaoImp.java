/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Categoria;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase RelacionLaboralDaoImp que implemeta los métodos de la interfaz
 RelacionLaboralDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class RelacionLaboralDaoImp extends AbstractFacade<RelacionLaboral> implements RelacionLaboralDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RelacionLaboralDaoImp() {
        super(RelacionLaboral.class);
    }

    
    /**
     * Método que devuelve una lista con las relaciónes laborales de un docente
     * @param cedula
     * @param idCarrera
     * @param idPeriodo
     * @return q.getResultList()
     */
    @Override
    public List<RelacionLaboral> listaRelacionporDocente(String cedula,int idCarrera,int idPeriodo) {
        Query q = em.createQuery("SELECT r FROM RelacionLaboral r,CarreraDocente cd,Docente d,Periodo p,Carrera ca \n" +
"where cd.idCarreraDocente=r.idCarreraDocente.idCarreraDocente \n" +
"and d.idDocente=cd.idDocente.idDocente\n" +
"and ca.idCarrera=cd.idCarrera.idCarrera and p.idPeriodo=cd.idPeriodo.idPeriodo\n" +
"and d.identificacion=:identificacion and ca.idCarrera=:idCarrera and p.idPeriodo=:idPeriodo\n" +
"ORDER BY r.fechaInicioContrato asc");
        q.setParameter("identificacion", cedula);
        q.setParameter("idCarrera", idCarrera);
        q.setParameter("idPeriodo", idPeriodo);
        return q.getResultList();
    }
    /**
     * Método que buscar un registro categoría en un registro RelacionLaboral
     * @param idCategoria
     * @return respuesta
     */
    @Override
    public boolean buscarRegistroPorCategoria(Categoria idCategoria) {
              boolean respuesta=false;
        Query q= em.createQuery("SELECT r FROM RelacionLaboral r WHERE r.idCategoria = :idCategoria");
        q.setParameter("idCategoria", idCategoria);
        List<RelacionLaboral> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
       /**
     * Método que buscar un registro Tipo de documento en un registro RelacionLaboral
     * @param idTpDocumento
     * @return respuesta       
     * 
     */
    @Override
    public boolean buscarRegistroPorTpDocumento(TipoDocumento idTpDocumento) {
             boolean respuesta=false;
        Query q= em.createQuery("SELECT r FROM RelacionLaboral r WHERE r.idTipoDocumento = :idTpDocumento");
        q.setParameter("idTpDocumento", idTpDocumento);
        List<RelacionLaboral> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
   /** Método que buscar un registro Tipo relación IES en un registro RelacionLaboral
     * @param idRelacionIes
     * @return respuesta
     */
    @Override
    public boolean buscarRegistroPorTpRelacioIes(RelacionIes idRelacionIes) {
             boolean respuesta=false;
        Query q= em.createQuery("SELECT r FROM RelacionLaboral r WHERE r.idRelacionIes = :idRelacionIes");
        q.setParameter("idRelacionIes", idRelacionIes);
        List<RelacionLaboral> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
  /** Método que buscar un registro Tiempo de dedicación IES en un registro RelacionLaboral
     * @param idTpDedicacion
     * @return respuesta
     */
    @Override
    public boolean buscarRegistroPorTpDedicacion(TiempoDedicacion idTpDedicacion) {
             boolean respuesta=false;
        Query q= em.createQuery("SELECT r FROM RelacionLaboral r WHERE r.idTdedi = :idTpDedicacion");
        q.setParameter("idTpDedicacion", idTpDedicacion);
        List<RelacionLaboral> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }
    
}
