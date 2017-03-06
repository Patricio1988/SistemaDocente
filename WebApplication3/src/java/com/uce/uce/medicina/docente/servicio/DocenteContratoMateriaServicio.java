/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

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
public interface DocenteContratoMateriaServicio {
     void insertarDocente_contrato_materia(DocentesMallaContratoMateria docentesMallaContratoMateria);

    void actualizarDocente_contrato_materia(DocentesMallaContratoMateria docentesMallaContratoMateria);

    void eliminarDocente_contrato_materia(DocentesMallaContratoMateria docentesMallaContratoMateria);

    DocentesMallaContratoMateria encontarDocente_contrato_materiaId(Object id);

    List<DocentesMallaContratoMateria> ListarDocente_contrato_materia();
     List<DocentesMallaContratoMateria> listarCargaporDocente(String cedula,int idPeriodo,int idCarrera,int idReLaboral);
      boolean buscarRegistroPorMateria(Materia idMateria);
      boolean buscarRegistroPorPeriodo(Periodo idPeriodo);
      boolean buscarRegistroPorActividad(Actividad idActividad);

    
}
