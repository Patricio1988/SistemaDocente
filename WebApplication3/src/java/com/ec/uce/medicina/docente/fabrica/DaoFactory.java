/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.fabrica;

import com.ec.uce.medicina.docente.Dao.ActividadDao;
import com.ec.uce.medicina.docente.Dao.CapacitacionDao;
import com.ec.uce.medicina.docente.Dao.CarreraDao;
import com.ec.uce.medicina.docente.Dao.CategoriaDao;
import com.ec.uce.medicina.docente.Dao.DocenteDao;
import com.ec.uce.medicina.docente.Dao.DocentesMallaContratoMateriaDao;
import com.ec.uce.medicina.docente.Dao.FacultadDao;
import com.ec.uce.medicina.docente.Dao.FormacionDao;
import com.ec.uce.medicina.docente.Dao.IesDao;
import com.ec.uce.medicina.docente.Dao.MallaDao;
import com.ec.uce.medicina.docente.Dao.MateriaDao;

import com.ec.uce.medicina.docente.Dao.AuthoritiesDao;

import com.ec.uce.medicina.docente.Dao.PeriodoDao;
import com.ec.uce.medicina.docente.Dao.PublicacionesDao;
import com.ec.uce.medicina.docente.Dao.RelacionIesDao;
import com.ec.uce.medicina.docente.Dao.TiempoDedicacionDao;
import com.ec.uce.medicina.docente.Dao.TipoDocumentoDao;
import com.ec.uce.medicina.docente.Dao.TipoPublicacionDao;
import com.ec.uce.medicina.docente.Dao.UniversidadDao;
import com.ec.uce.medicina.docente.Dao.UsersDao;
import com.ec.uce.medicina.docente.Dao.CargoDirectivoDao;
import com.ec.uce.medicina.docente.Dao.CarreraDocenteDao;
import com.ec.uce.medicina.docente.Dao.PaisOrigenDao;
import com.ec.uce.medicina.docente.Dao.RelacionLaboralDao;

/**
 *
 * @author Patricio
 */
public interface DaoFactory {

    public ActividadDao getActividadDao();

    public CapacitacionDao getCapacitacionDao();

    public CargoDirectivoDao getCargoDirectivoDao();

    public CarreraDao getCarreraDao();

    public CategoriaDao getCategoriaDao();

    public DocenteDao getDocenteDao();

    public DocentesMallaContratoMateriaDao getDocentesMallaContratoMateriaDao();

    public FacultadDao getFacultadDao();

    public FormacionDao getFormacionDao();

    public IesDao getIesDao();

    public MallaDao getMallaDao();

    public MateriaDao getMateriaDao();

    public PaisOrigenDao getPaisOrigenDao();

    public AuthoritiesDao getAuthoritiesDao();

    public PeriodoDao getPeriodoDao();

    public PublicacionesDao getPublicacionesDao();

    public RelacionIesDao getRelacionIesDao();

    public RelacionLaboralDao getRelacionLaboralDao();

    public TiempoDedicacionDao getTiempoDedicacionDao();

    public TipoDocumentoDao getTipoDocumentoDao();

    public TipoPublicacionDao getTipoPublicacionDao();

    public UniversidadDao getUniversidadDao();

    public UsersDao getUsersDao();

    public CarreraDocenteDao getCarreraDocenteDao();

}
