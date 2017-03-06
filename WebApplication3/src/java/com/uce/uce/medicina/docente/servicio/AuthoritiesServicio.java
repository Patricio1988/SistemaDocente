/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Authorities;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface AuthoritiesServicio {
     void insertarAuthorities (Authorities perfil );
  void eliminarAuthorities (Authorities perfil );
  void actualizarAuthorities (Authorities perfil );
  List<Authorities> listarAuthorities ();
  Authorities encontrarAuthorities(Object id);
  List<SelectItem> oneMenuAuthorities(List<Authorities> listaAuthorities);
    
}
