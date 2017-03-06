/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface CarreraDocenteServicio {

    void insertarCarreraDocente(CarreraDocente carreradocente);

    void actulizarCarreraDocente(CarreraDocente carreradocente);

    void eliminarCarreraDocente(CarreraDocente carreradocente);

    CarreraDocente buscarCarreraDocenterporId(Object id);

    List<CarreraDocente> ListarCarreraDocente();

    List<CarreraDocente> findRange(int[] range);

    int count();

    List<CarreraDocente> listarDocenteCarreraVigente(int idCarrera,int idPeriodo);

    List<CarreraDocente> listarDocenteCarreraporApellido(int idPeriodo);

    List<CarreraDocente> listarDocentesCarreraTodos(int idCarrera,int idPeriodo);

    CarreraDocente buscarDocenteCarreraporID(int idDocente, int idCarrera);
    CarreraDocente buscarDocenteCarreraporPeriodo(String identificacion,int idPeriodo, int idCarrera);

}
