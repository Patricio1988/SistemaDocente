/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase TipoDocumentoDaoImp que implemeta los métodos de la interfaz
 * TipoDocumentoDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class TipoDocumentoDaoImp extends AbstractFacade<TipoDocumento> implements TipoDocumentoDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoDocumentoDaoImp() {
        super(TipoDocumento.class);
    }
    /** Método que devuelve una lista con registros tipo de documento y los ordena por nombre
     * @return q.getResultList()
     */
      @Override
    public List<TipoDocumento> listarTipoDocuporNombre() {
        Query q = em.createQuery("SELECT t FROM TipoDocumento t Order by t.nomTipoDocumento");
        return q.getResultList();
    }
    
}
