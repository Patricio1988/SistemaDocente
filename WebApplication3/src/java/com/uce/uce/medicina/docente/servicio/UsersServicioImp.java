/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.UsersDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Users;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Patricio
 */
@Stateless
public class UsersServicioImp implements UsersServicio{
    @EJB
    private UsersDao usersDao=null;

    public UsersServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.usersDao=factoria.getUsersDao();
    }

    @Override
    public void insertarUsers(Users users) {
        usersDao.create(users);
    }

    @Override
    public void eliminarUsers(Users users) {
     usersDao.remove(users);
    }

    @Override
    public void actualizarUsers(Users users) {
        usersDao.edit(users);
    }

    @Override
    public List<Users> listarUsers() {
        return usersDao.findAll();
    }

    @Override
    public Users encontrarUsers(Object id) {
       return usersDao.find(id);
    }

//    @Override
//    public Users bucarUsersContrasena(String nick, String pass) {
//       return usersDao.buscarUsersContrasena(nick, pass);
//    }

    @Override
    public Users bucarUsers(String user) {
     return usersDao.encontrarUsuarioporUser(user);
    }

    @Override
    public Users encontrarUsuarioporCI(String ci) {
        return usersDao.encontrarUsuarioporCI(ci);
    }
}
