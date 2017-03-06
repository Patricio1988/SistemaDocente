/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.MateriaDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Materia;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Patricio
 */
@Stateless
public class MateriaServicioImp implements MateriaServicio{
     @EJB
    private MateriaDao materiaDao=null;

    public MateriaServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.materiaDao=factoria.getMateriaDao();
    }

    @Override
    public void insertarMateria(Materia materia) {
        materiaDao.create(materia);
    }

    @Override
    public void eliminarMateria(Materia materia) {
        materiaDao.remove(materia);
    }

    @Override
    public void actualizarMateria(Materia materia) {
        materiaDao.edit(materia);
    }

    @Override
    public List<Materia> listarMateria() {
        return materiaDao.findAll();
    }

    @Override
    public Materia encontrarMateriaID(Object id) {
        return materiaDao.find(id);
    }

    @Override
    public List<Materia> listarMateriaporMalla(int id) {
        return materiaDao.listaMateriasporMalla(id);
    }

    @Override
    public boolean buscarRegistroPorMalla(Malla idMalla) {
        return materiaDao.buscarRegistroPorMalla(idMalla);
    }
}
