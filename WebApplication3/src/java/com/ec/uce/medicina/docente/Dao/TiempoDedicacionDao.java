/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface TiempoDedicacionDao {

    void create(TiempoDedicacion tiempoDedicacion);

    void edit(TiempoDedicacion tiempoDedicacion);

    void remove(TiempoDedicacion tiempoDedicacion);

    TiempoDedicacion find(Object id);

    List<TiempoDedicacion> findAll();

    List<TiempoDedicacion> findRange(int[] range);

    int count();

    List<TiempoDedicacion> listarTiempoDediporNombre();
}
