/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Categoria;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.ec.uce.medicina.docente.Dao.RelacionLaboralDao;


/**
 *
 * @author Patricio
 */
@Stateless
public class RelacionLaboralServicioImp implements RelacionLaboralServicio{
    @EJB
     private RelacionLaboralDao relacionLaboralDao=null;

    public RelacionLaboralServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.relacionLaboralDao=factoria.getRelacionLaboralDao();
    }

    @Override
    public void insertarRelacionLaboral(RelacionLaboral relacionLaboral) {
        relacionLaboralDao.create(relacionLaboral);
    }

    @Override
    public void eliminarRelacionLaboral(RelacionLaboral relacionLaboral) {
        relacionLaboralDao.remove(relacionLaboral);
    }

    @Override
    public void actualizarRelacionLaboral(RelacionLaboral relacionLaboral) {
        relacionLaboralDao.edit(relacionLaboral);
    }

    @Override
    public List<RelacionLaboral> listarRelacionLaboral() {
        return  relacionLaboralDao.findAll();
    }

    @Override
    public RelacionLaboral encontrarRelacionLaboral(Object id) {
        return relacionLaboralDao.find(id);
    }

    @Override
    public List<RelacionLaboral> listaRelacionporDocente(String cedula,int idCarrera,int idPeriodo) {
        return relacionLaboralDao.listaRelacionporDocente(cedula,idCarrera,idPeriodo);
    }

    @Override
    public boolean buscarRegistroPorCategoria(Categoria idCategoria) {
        return relacionLaboralDao.buscarRegistroPorCategoria(idCategoria);
    }

    @Override
    public boolean buscarRegistroPorTpDocumento(TipoDocumento idTpDocumento) {
    return relacionLaboralDao.buscarRegistroPorTpDocumento(idTpDocumento);
    }

    @Override
    public boolean buscarRegistroPorTpRelacioIes(RelacionIes idRelacionIes) {
       return relacionLaboralDao.buscarRegistroPorTpRelacioIes(idRelacionIes);
    }

    @Override
    public boolean buscarRegistroPorTpDedicacion(TiempoDedicacion idTpDedicacion) {
        return relacionLaboralDao.buscarRegistroPorTpDedicacion(idTpDedicacion);
    }

  

   
    
}
