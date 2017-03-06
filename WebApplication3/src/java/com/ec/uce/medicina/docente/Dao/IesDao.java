/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface IesDao {

    void create(Ies ies);

    void edit(Ies ies);

    void remove(Ies ies);

    Ies find(Object id);

    List<Ies> findAll();

    List<Ies> findRange(int[] range);

    int count();

    List<Ies> listarIesporCodigo();

    Ies buscarporCodigo(int codigo);

}
