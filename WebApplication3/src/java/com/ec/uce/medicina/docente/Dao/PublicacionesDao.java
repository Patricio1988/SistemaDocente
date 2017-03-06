/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Publicaciones;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface PublicacionesDao {

    void create(Publicaciones publicaciones);

    void edit(Publicaciones publicaciones);

    void remove(Publicaciones publicaciones);

    Publicaciones find(Object id);

    List<Publicaciones> findAll();

    List<Publicaciones> findRange(int[] range);

    int count();

    Publicaciones buscarPublicacionesporNombre(String nombre);

    List<Publicaciones> listaPublicacionesporDocente(String cedula);

    boolean buscarRegistroPorTpPublicacion(TipoPublicacion idTpPubli);

}
