/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Authorities;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Declaración de AuthoritiesDaoImp que implemeta los metodos de la intefaz AuthoritiesDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class AuthoritiesDaoImp extends AbstractFacade<Authorities> implements AuthoritiesDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthoritiesDaoImp() {
        super(Authorities.class);
    }
    
}
