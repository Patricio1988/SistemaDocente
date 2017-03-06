/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Materia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface MateriaDao {

    void create(Materia materia);

    void edit(Materia materia);

    void remove(Materia materia);

    Materia find(Object id);

    List<Materia> findAll();

    List<Materia> findRange(int[] range);

    int count();

    List<Materia> listaMateriasporMalla(int id);

    boolean buscarRegistroPorMalla(Malla idMalla);

}
