/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Actividad;
import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import com.ec.uce.medicina.docente.modelo.Materia;
import com.ec.uce.medicina.docente.modelo.Periodo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface DocentesMallaContratoMateriaDao {

    void create(DocentesMallaContratoMateria docentesMallaContratoMateria);

    void edit(DocentesMallaContratoMateria docentesMallaContratoMateria);

    void remove(DocentesMallaContratoMateria docentesMallaContratoMateria);

    DocentesMallaContratoMateria find(Object id);

    List<DocentesMallaContratoMateria> findAll();

    List<DocentesMallaContratoMateria> findRange(int[] range);

    int count();

    List<DocentesMallaContratoMateria> listarCargaporDocente(String cedula, int idPeriodo, int idCarrera,int idReLaboral);

    boolean buscarRegistroPorMateria(Materia idMateria);

    boolean buscarRegistroPorPeriodo(Periodo idPeriodo);

    boolean buscarRegistroPorActividad(Actividad idActividad);
}
