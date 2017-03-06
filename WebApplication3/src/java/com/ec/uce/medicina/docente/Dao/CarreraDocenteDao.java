/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface CarreraDocenteDao {

    void create(CarreraDocente carreraDocente);

    void edit(CarreraDocente carreraDocente);

    void remove(CarreraDocente carreraDocente);

    CarreraDocente find(Object id);

    List<CarreraDocente> findAll();

    List<CarreraDocente> findRange(int[] range);

    int count();
    List<CarreraDocente> listarDocentesCarreraVigentes(int idCarrera, int idPeriodo);
    List<CarreraDocente> listarDocentesCarreraporApellido(int idPeriodo);
    List<CarreraDocente> listarDocentesCarreraTodos(int idCarrera,int idPeriodo);
    CarreraDocente buscarDocenteCarreraporID(int idDocente,int idCarrera);
    CarreraDocente buscarDocenteCarreraporPeriodo(String identificacion,int idPeriodo,int idCarrera);
}
