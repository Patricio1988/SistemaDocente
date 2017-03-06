/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.fachada.AbstractFacade;
import com.ec.uce.medicina.docente.modelo.Users;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Declaración de la clase UsersDaoImp que implemeta los métodos de la interfaz
 * UsersDao
 *
 * @author Patricio
 * @version 2.0
 */
@Stateless
public class UsersDaoImp extends AbstractFacade<Users> implements UsersDao {

    @PersistenceContext(unitName = "SistemaADocente3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersDaoImp() {
        super(Users.class);
    }

    /**
     * Método que busca un usuario por su username
     *
     * @param username
     * @return usuario
     */
    @Override
    public Users encontrarUsuarioporUser(String username) {
        Users usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username");
            query.setParameter("username", username);
            List<Users> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception ex) {
            MensajesFaces.error("Error", ex.getMessage());

        }
        return usuario;
    }

    /**
     * Método que busca un usuario por su cédula
     *
     * @param ci
     * @return usuario
     */
    @Override
    public Users encontrarUsuarioporCI(String ci) {
        Users usuario = null;
        try {
            Query query = em.createQuery("SELECT u FROM Users u WHERE u.ci = :ci");
            query.setParameter("ci", ci);
            List<Users> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception ex) {
            MensajesFaces.error("Error", ex.getMessage());
        }

        return usuario;
    }

}
