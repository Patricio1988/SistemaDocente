/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Periodo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface PeriodoDao {

    void create(Periodo periodo);

    void edit(Periodo periodo);

    void remove(Periodo periodo);

    Periodo find(Object id);

    List<Periodo> findAll();

    List<Periodo> findRange(int[] range);

    int count();

    List<Periodo> listarPeriodoporFechaInicio();

    List<Periodo> listarPeriodoporActivos();

}
