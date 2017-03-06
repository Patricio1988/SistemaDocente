/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface TipoPublicacionDao {

    void create(TipoPublicacion tipoPublicacion);

    void edit(TipoPublicacion tipoPublicacion);

    void remove(TipoPublicacion tipoPublicacion);

    TipoPublicacion find(Object id);

    List<TipoPublicacion> findAll();

    List<TipoPublicacion> findRange(int[] range);

    int count();

    List<TipoPublicacion> listarTipoPublicacionporNombre();

}
