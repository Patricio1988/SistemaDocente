/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.Dao.CategoriaDao;
import com.ec.uce.medicina.docente.fabrica.DaoAbstractFactory;
import com.ec.uce.medicina.docente.fabrica.DaoFactory;
import com.ec.uce.medicina.docente.modelo.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Stateless
public class CategoriaServicioImp  implements CategoriaServicio{
    @EJB
    private CategoriaDao categoriaDao=null;

    public CategoriaServicioImp() {
        DaoFactory factoria=DaoAbstractFactory.getInstance();
        this.categoriaDao=factoria.getCategoriaDao();
    }

    @Override
    public void insertarCategoria(Categoria Categoria) {
        categoriaDao.create(Categoria);
    }

    @Override
    public void eliminarCategoria(Categoria Categoria) {
        categoriaDao.remove(Categoria);
    }

    @Override
    public void actualizarCategoria(Categoria Categoria) {
        categoriaDao.edit(Categoria);
    }

    @Override
    public List<Categoria> listarCategoria() {
       return categoriaDao.findAll();
    }

    @Override
    public Categoria encontrarCategoria(Object id) {
        return  categoriaDao.find(id);
    }

    @Override
    public List<SelectItem> oneMenuCategoria(List<Categoria> listaCategoria) {
       
        List<SelectItem> itemsOneMenu = new ArrayList<SelectItem>();
        for (Categoria c : listaCategoria) {
            SelectItem tmp = new SelectItem(c.getIdCategoria(), c.getNomCategoria());
            itemsOneMenu.add(tmp);
        }
        return itemsOneMenu;
    
    }

    @Override
    public List<Categoria> ListarCategoriapoNombre() {
        return categoriaDao.ListarCategoriapoNombre();
    }
    
}
