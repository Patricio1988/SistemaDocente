/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


 /** Declaración de DocenteDaoImp que implemeta los metodos de la intefaz DocenteDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class DocenteDaoImp extends AbstractFacade<Docente> implements DocenteDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocenteDaoImp() {
        super(Docente.class);
    }
     /**Método busca un docente por su número de cédula. 
     * @param cedula
     * @return docenteEncontrada
     */
     @Override
    public Docente buscarDocenteporCedula(String cedula) {
       Docente docenteEncontrada=null;
        Query q= em.createQuery("SELECT d FROM Docente d WHERE d.identificacion = :identificacion");
        q.setParameter("identificacion", cedula);
        List<Docente> lista=q.getResultList();
        if (!lista.isEmpty()) {
            docenteEncontrada=lista.get(0);
        } 
        return  docenteEncontrada;
    }


   /**Método que devuelve una lista de los docentes y los ardena por apellido. 
     * @return  q.getResultList();
     */

    @Override
    public List<Docente> listaDocentesTodosporApellido() {
         Query q= em.createQuery("SELECT d FROM Docente d ORDER BY d.apellidoPaterno");
       
        return q.getResultList();
        
    }
/**Método que busca un registro del docente por pais. 
     * @return  q.getResultList();
     */

    /**
     * Método que busca un registro del docente por pais.
     * @param idpais
     * @return respuesta
     */
    @Override
    public boolean buscarRegistroPorPais(PaisOrigen idpais) {
       boolean respuesta=false;
        Query q= em.createQuery("SELECT d FROM Docente d WHERE d.idPais =:idpais");
        q.setParameter("idpais", idpais);
        List<Docente> lista=q.getResultList();
        if (!lista.isEmpty()) {
            respuesta=true;
        } 
        return  respuesta;
    }

    @Override
    public Docente buscarDocentdIDCarrera(int idDocente, int idCarrera) {
        Docente docenteEncontrada=null;
        Query q= em.createQuery("Select d from Docente d,Carreradocente cd,Carrera ca where d.idDocente=cd.idDocente.idDocente and ca.idCarrera=cd.idCarrera.idCarrera and d.idDocente =:idDocente and ca.idCarrera=:idCarrera");
        q.setParameter("idDocente", idDocente);
        q.setParameter("idCarrera", idCarrera);
        List<Docente> lista=q.getResultList();
        if (!lista.isEmpty()) {
            docenteEncontrada=lista.get(0);
        } 
        return  docenteEncontrada;
    }
}
