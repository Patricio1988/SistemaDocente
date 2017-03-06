/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Malla;
import java.util.List;
import javax.ejb.Local;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patricio
 */
@Local
public interface MallaServicio {

    void insertarMalla(Malla malla);

    void eliminarMalla(Malla malla);

    void actualizarMalla(Malla malla);

    List<Malla> listarMalla();

    Malla encontrarMallaID(Object id);

    List<SelectItem> oneMenuMalla(List<Malla> listaMalla);

    List<Malla> ListaMallaVigentes();

    boolean buscarRegistroPorCarrera(Carrera idCarrera);

    List<Malla> ListaMallaPorCarreraVigentes(int idCarrera);

}
