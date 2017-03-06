/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.DocentesMallaContratoMateriaDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Actividad;
import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import com.ec.uce.medicina.docente.modelo.Materia;
import com.ec.uce.medicina.docente.modelo.Periodo;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Patricio
 */
@Stateless
public class DocenteContratoMateriaServivioImp implements DocenteContratoMateriaServicio{
  @EJB
    private DocentesMallaContratoMateriaDao docentesMallaContratoMateriaDao=null;

    public DocenteContratoMateriaServivioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.docentesMallaContratoMateriaDao=factoria.getDocentesMallaContratoMateriaDao();
    }

    @Override
    public void insertarDocente_contrato_materia(DocentesMallaContratoMateria docentesMallaContratoMateria) {
      docentesMallaContratoMateriaDao.create(docentesMallaContratoMateria);
    }

    @Override
    public void actualizarDocente_contrato_materia(DocentesMallaContratoMateria docentesMallaContratoMateria) {
       docentesMallaContratoMateriaDao.edit(docentesMallaContratoMateria);
    }

    @Override
    public void eliminarDocente_contrato_materia(DocentesMallaContratoMateria docentesMallaContratoMateria) {
        docentesMallaContratoMateriaDao.remove(docentesMallaContratoMateria);
    }

    @Override
    public DocentesMallaContratoMateria encontarDocente_contrato_materiaId(Object id) {
       return docentesMallaContratoMateriaDao.find(id);
    }

    @Override
    public List<DocentesMallaContratoMateria> ListarDocente_contrato_materia() {
      return docentesMallaContratoMateriaDao.findAll();
    }

    @Override
    public List<DocentesMallaContratoMateria> listarCargaporDocente(String cedula,int idPeriodo,int idCarrera,int idReLaboral) {
        return  docentesMallaContratoMateriaDao.listarCargaporDocente(cedula,idPeriodo,idCarrera,idReLaboral);
    }

    @Override
    public boolean buscarRegistroPorMateria(Materia idMateria) {
       return docentesMallaContratoMateriaDao.buscarRegistroPorMateria(idMateria);
    }

    @Override
    public boolean buscarRegistroPorPeriodo(Periodo idPeriodo) {
       return docentesMallaContratoMateriaDao.buscarRegistroPorPeriodo(idPeriodo);
    }

    @Override
    public boolean buscarRegistroPorActividad(Actividad idActividad) {
       return docentesMallaContratoMateriaDao.buscarRegistroPorActividad(idActividad);
    }
    
}
