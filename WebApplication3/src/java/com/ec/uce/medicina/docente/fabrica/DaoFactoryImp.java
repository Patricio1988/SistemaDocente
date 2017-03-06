/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.fabrica;

import com.ec.uce.medicina.docente.Dao.ActividadDao;
import com.ec.uce.medicina.docente.Dao.ActividadDaoImp;
import com.ec.uce.medicina.docente.Dao.CapacitacionDao;
import com.ec.uce.medicina.docente.Dao.CapacitacionDaoImp;
import com.ec.uce.medicina.docente.Dao.CargoDirectivoDaoImp;
import com.ec.uce.medicina.docente.Dao.CarreraDao;
import com.ec.uce.medicina.docente.Dao.CarreraDaoImp;
import com.ec.uce.medicina.docente.Dao.CategoriaDao;
import com.ec.uce.medicina.docente.Dao.CategoriaDaoImp;
import com.ec.uce.medicina.docente.Dao.DocenteDao;
import com.ec.uce.medicina.docente.Dao.DocenteDaoImp;
import com.ec.uce.medicina.docente.Dao.DocentesMallaContratoMateriaDao;
import com.ec.uce.medicina.docente.Dao.DocentesMallaContratoMateriaDaoImp;
import com.ec.uce.medicina.docente.Dao.FacultadDao;
import com.ec.uce.medicina.docente.Dao.FacultadDaoImp;
import com.ec.uce.medicina.docente.Dao.FormacionDao;
import com.ec.uce.medicina.docente.Dao.FormacionDaoImp;
import com.ec.uce.medicina.docente.Dao.IesDao;
import com.ec.uce.medicina.docente.Dao.IesDaoImp;
import com.ec.uce.medicina.docente.Dao.MallaDao;
import com.ec.uce.medicina.docente.Dao.MallaDaoImp;
import com.ec.uce.medicina.docente.Dao.MateriaDao;
import com.ec.uce.medicina.docente.Dao.MateriaDaoImp;
import com.ec.uce.medicina.docente.Dao.PaisOrigenDaoImp;
import com.ec.uce.medicina.docente.Dao.AuthoritiesDao;
import com.ec.uce.medicina.docente.Dao.AuthoritiesDaoImp;
import com.ec.uce.medicina.docente.Dao.CarreraDocenteDaoImp;
import com.ec.uce.medicina.docente.Dao.PeriodoDao;
import com.ec.uce.medicina.docente.Dao.PeriodoDaoImp;
import com.ec.uce.medicina.docente.Dao.PublicacionesDao;
import com.ec.uce.medicina.docente.Dao.PublicacionesDaoImp;
import com.ec.uce.medicina.docente.Dao.RelacionIesDao;
import com.ec.uce.medicina.docente.Dao.RelacionIesDaoImp;
import com.ec.uce.medicina.docente.Dao.RelacionLaboralDaoImp;
import com.ec.uce.medicina.docente.Dao.TiempoDedicacionDao;
import com.ec.uce.medicina.docente.Dao.TiempoDedicacionDaoImp;
import com.ec.uce.medicina.docente.Dao.TipoDocumentoDao;
import com.ec.uce.medicina.docente.Dao.TipoDocumentoDaoImp;
import com.ec.uce.medicina.docente.Dao.TipoPublicacionDao;
import com.ec.uce.medicina.docente.Dao.TipoPublicacionDaoImp;
import com.ec.uce.medicina.docente.Dao.UniversidadDao;
import com.ec.uce.medicina.docente.Dao.UniversidadDaoImp;
import com.ec.uce.medicina.docente.Dao.UsersDao;
import com.ec.uce.medicina.docente.Dao.UsersDaoImp;
import com.ec.uce.medicina.docente.Dao.CargoDirectivoDao;
import com.ec.uce.medicina.docente.Dao.CarreraDocenteDao;
import com.ec.uce.medicina.docente.Dao.PaisOrigenDao;
import com.ec.uce.medicina.docente.Dao.RelacionLaboralDao;






/**
 *
 * @author Patricio
 */
public class DaoFactoryImp implements DaoFactory{

   

    @Override
    public CapacitacionDao getCapacitacionDao() {
       return new CapacitacionDaoImp();
    }

    @Override
    public CargoDirectivoDao getCargoDirectivoDao() {
     return new CargoDirectivoDaoImp();
    }

    @Override
    public CarreraDao getCarreraDao() {
        return new CarreraDaoImp();
    }

    @Override
    public CategoriaDao getCategoriaDao() {
       return new CategoriaDaoImp();
    }

    @Override
    public DocenteDao getDocenteDao() {
        return  new DocenteDaoImp();
    }

    @Override
    public FacultadDao getFacultadDao() {
      return new FacultadDaoImp();
    }

    @Override
    public FormacionDao getFormacionDao() {
     return new FormacionDaoImp();
    }

    @Override
    public IesDao getIesDao() {
        return new IesDaoImp();
    }

    @Override
    public MallaDao getMallaDao() {
       return new MallaDaoImp();
    }

    @Override
    public MateriaDao getMateriaDao() {
        return new MateriaDaoImp();
    }


    @Override
    public PaisOrigenDao getPaisOrigenDao() {
        return new PaisOrigenDaoImp();
    }

    @Override
    public AuthoritiesDao getAuthoritiesDao() {
       return new AuthoritiesDaoImp();
    }
 

    @Override
    public PeriodoDao getPeriodoDao() {
       return new PeriodoDaoImp();
    }

    @Override
    public PublicacionesDao getPublicacionesDao() {
        return new PublicacionesDaoImp();
    }

    @Override
    public RelacionIesDao getRelacionIesDao() {
       return new RelacionIesDaoImp();
    }

    @Override
    public RelacionLaboralDao getRelacionLaboralDao() {
        return new RelacionLaboralDaoImp();
    }

    @Override
    public TiempoDedicacionDao getTiempoDedicacionDao() {
      return new TiempoDedicacionDaoImp();
    }

    @Override
    public TipoDocumentoDao getTipoDocumentoDao() {
      return new TipoDocumentoDaoImp();
    }

    @Override
    public TipoPublicacionDao getTipoPublicacionDao() {
      return new TipoPublicacionDaoImp();
    }

    @Override
    public UniversidadDao getUniversidadDao() {
       return new UniversidadDaoImp();
    }

    @Override
    public UsersDao getUsersDao() {
      return  new UsersDaoImp();
    }

    @Override
    public ActividadDao getActividadDao() {
        return  new ActividadDaoImp();
    }

    @Override
    public DocentesMallaContratoMateriaDao getDocentesMallaContratoMateriaDao() {
        return new DocentesMallaContratoMateriaDaoImp();
    }

    @Override
    public CarreraDocenteDao getCarreraDocenteDao() {
        return new CarreraDocenteDaoImp();
    }

 

   

   

   }
