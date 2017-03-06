/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Patricio
 */
@Stateless
public class CategoriaDaoImp extends AbstractFacade<Categoria> implements CategoriaDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaDaoImp() {
        super(Categoria.class);
    }

     @Override
    public List<Categoria> ListarCategoriapoNombre() {
         Query q= em.createQuery("SELECT c FROM Categoria c Order by c.nomCategoria");   
      return  q.getResultList();
    } 
    
}
