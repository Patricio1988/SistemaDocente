/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.RelacionIes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface RelacionIesDao {

    void create(RelacionIes relacionIes);

    void edit(RelacionIes relacionIes);

    void remove(RelacionIes relacionIes);

    RelacionIes find(Object id);

    List<RelacionIes> findAll();

    List<RelacionIes> findRange(int[] range);

    int count();

    List<RelacionIes> listarRelacionIesporNombre();
}
