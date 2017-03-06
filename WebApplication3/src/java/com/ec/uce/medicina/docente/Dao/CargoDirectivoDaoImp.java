/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.CargoDirectivo;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

 /** Declaración de CargoDirectivoDaoImp que implemeta los metodos de la intefaz CargoDirectivoDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class CargoDirectivoDaoImp extends AbstractFacade<CargoDirectivo> implements CargoDirectivoDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CargoDirectivoDaoImp() {
        super(CargoDirectivo.class);
    }
     /**Método que busca un cargo directivo por su id.
     * @param idCargo
     * @return cargoDirectivoEncontrada
     */
        @Override
    public CargoDirectivo buscarCargoporID(int idCargo) {
        CargoDirectivo cargoDirectivoEncontrada=null;
        Query q= em.createQuery("SELECT c FROM CargoDirectivo c WHERE c.idCargo = :idCargo");
        q.setParameter("idCargo", idCargo);
        List<CargoDirectivo> lista=q.getResultList();
        if (!lista.isEmpty()) {
            cargoDirectivoEncontrada=lista.get(0);
        } 
        return  cargoDirectivoEncontrada;
        
    }
  /**Método que devuelve una lista de todos los cargos directivos de un docente en un determinado período.
     * @param cedula
     * @param idPeriodo
     * @param idCarrera
     * @return q.getResultList()
     */
    @Override
    public List<CargoDirectivo> listaCargoporDocente(String cedula,int idPeriodo,int idCarrera) {
        Query q= em.createQuery("SELECT c FROM CargoDirectivo c,CarreraDocente cd,Periodo p,Carrera ca,Docente d\n" +
"where cd.idCarreraDocente=c.idCarreraDocente.idCarreraDocente and d.idDocente=cd.idDocente.idDocente and\n" +
"p.idPeriodo=cd.idPeriodo.idPeriodo and ca.idCarrera=cd.idCarrera.idCarrera\n" +
"and d.identificacion=:identificacion  and p.idPeriodo=:idPeriodo and ca.idCarrera=:idCarrera");
           q.setParameter("identificacion", cedula); 
         q.setParameter("idPeriodo", idPeriodo);
         q.setParameter("idCarrera", idCarrera);
      return  q.getResultList();
    }


    
}
