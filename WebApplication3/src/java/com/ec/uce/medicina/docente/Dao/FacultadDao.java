/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Facultad;
import com.ec.uce.medicina.docente.modelo.Universidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface FacultadDao {

    void create(Facultad facultad);

    void edit(Facultad facultad);

    void remove(Facultad facultad);

    Facultad find(Object id);

    List<Facultad> findAll();

    List<Facultad> findRange(int[] range);

    int count();

    List<Facultad> listfacultadaPorUniversidad(int idPais);

    Facultad buscarFacultadID(int id);

    boolean buscarRegistroPorUniversidad(Universidad idUniversidad);

}
