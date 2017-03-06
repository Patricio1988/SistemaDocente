/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.AuthoritiesDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Authorities;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Stateless
public class AuthoritiesServicioImp implements AuthoritiesServicio{
    @EJB
    private AuthoritiesDao authoritiesDao=null;

    public AuthoritiesServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.authoritiesDao=factoria.getAuthoritiesDao();
    }

    @Override
    public void insertarAuthorities(Authorities perfil) {
        authoritiesDao.create(perfil);
    }

    @Override
    public void eliminarAuthorities(Authorities perfil) {
        authoritiesDao.remove(perfil);
    }

    @Override
    public void actualizarAuthorities(Authorities perfil) {
       authoritiesDao.edit(perfil);
    }

    @Override
    public List<Authorities> listarAuthorities() {
       return authoritiesDao.findAll();
    }

    @Override
    public Authorities encontrarAuthorities(Object id) {
        return authoritiesDao.find(id);
    }

    @Override
    public List<SelectItem> oneMenuAuthorities(List<Authorities> listaAuthorities) {
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Authorities u : listaAuthorities) {
            SelectItem tmp = new SelectItem(u.getIdRol(), u.getAuthority());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    }
    
}
