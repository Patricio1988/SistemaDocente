/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Categoria;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface CategoriaServicio {
    void insertarCategoria(Categoria Categoria);
  void eliminarCategoria(Categoria Categoria);
  void actualizarCategoria(Categoria Categoria);
  Categoria encontrarCategoria(Object id);
  List<Categoria> listarCategoria();
  List<SelectItem> oneMenuCategoria(List<Categoria> listaCategoria);
  List<Categoria> ListarCategoriapoNombre();

}
