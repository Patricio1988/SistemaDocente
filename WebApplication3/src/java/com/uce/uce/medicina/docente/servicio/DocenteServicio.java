/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface DocenteServicio {

    void insertarDocente(Docente docente);

    void eliminarDocente(Docente docente);

    void actualizarDocente(Docente docente);

    Docente encontrarDocente(Object id);

    Docente buscarDocenteporCedula(String cedula);

    List<Docente> listarDocente();

    List<Docente> listaDocentesTodosporApellido();

    boolean buscarRegistroPorPais(PaisOrigen idpais);
    Docente buscarDocentdIDCarrera(int idDocente,int idCarrera);

}
