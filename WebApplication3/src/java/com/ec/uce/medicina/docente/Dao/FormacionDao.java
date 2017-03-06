/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Formacion;
import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface FormacionDao {

    void create(Formacion formacion);

    void edit(Formacion formacion);

    void remove(Formacion formacion);

    Formacion find(Object id);

    List<Formacion> findAll();

    List<Formacion> findRange(int[] range);

    int count();

    Formacion buscarFormacionID(int idFormacion);

    List<Formacion> listaFormacionporDocente(String cedula);

    boolean buscarRegistroPoriES(Ies idIes);
}
