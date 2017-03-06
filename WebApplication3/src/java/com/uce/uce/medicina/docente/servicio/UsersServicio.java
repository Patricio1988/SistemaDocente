/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface UsersServicio {
     void insertarUsers (Users usuario );
  void eliminarUsers (Users usuario);
  void actualizarUsers (Users usuario );
  Users encontrarUsers(Object id);
  List<Users> listarUsers ();
  Users bucarUsers(String user);
  public Users encontrarUsuarioporCI(String ci);
  
}
