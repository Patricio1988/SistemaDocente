/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Universidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface UniversidadDao {

    void create(Universidad universidad);

    void edit(Universidad universidad);

    void remove(Universidad universidad);

    Universidad find(Object id);

    List<Universidad> findAll();

    List<Universidad> findRange(int[] range);

    int count();

    Universidad buscarUniversidadID(int id);
}
