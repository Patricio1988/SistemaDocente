/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface UsersDao {

    void create(Users users);

    void edit(Users users);

    void remove(Users users);

    Users find(Object id);

    List<Users> findAll();

    List<Users> findRange(int[] range);

    int count();
    Users encontrarUsuarioporUser(String user);
    Users encontrarUsuarioporCI(String ci);
   

    //Users buscarUsuarioContrasena(String nick, String Pass);
}
