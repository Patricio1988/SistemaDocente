/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Materia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface MateriaServicio {
      void insertarMateria (Materia materia );
  void eliminarMateria (Materia materia );
  void actualizarMateria (Materia materia  );
  Materia encontrarMateriaID(Object id);
  List<Materia> listarMateria ();
  List<Materia> listarMateriaporMalla (int id);
  boolean buscarRegistroPorMalla(Malla idMalla);
    
}
