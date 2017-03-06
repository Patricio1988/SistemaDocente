/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface DocenteDao {

    void create(Docente docente);

    void edit(Docente docente);

    void remove(Docente docente);

    Docente find(Object id);

    List<Docente> findAll();

    List<Docente> findRange(int[] range);

    int count();

    Docente buscarDocenteporCedula(String cedula);

    List<Docente> listaDocentesTodosporApellido();

    boolean buscarRegistroPorPais(PaisOrigen idpais);
    Docente buscarDocentdIDCarrera(int idDocente,int idCarrera);

}
