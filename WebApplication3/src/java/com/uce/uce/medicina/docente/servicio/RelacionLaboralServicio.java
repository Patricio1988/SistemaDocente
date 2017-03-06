/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uce.uce.medicina.docente.servicio;

import com.ec.uce.medicina.docente.modelo.Categoria;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface RelacionLaboralServicio {

    void insertarRelacionLaboral(RelacionLaboral relacionLaboral);

    void eliminarRelacionLaboral(RelacionLaboral relacionLaboral);

    void actualizarRelacionLaboral(RelacionLaboral relacionLaboral);

    List<RelacionLaboral> listarRelacionLaboral();

    RelacionLaboral encontrarRelacionLaboral(Object id);

    public List<RelacionLaboral> listaRelacionporDocente(String cedula,int idCarrera,int idPeriodo);

    boolean buscarRegistroPorCategoria(Categoria idCategoria);

    boolean buscarRegistroPorTpDocumento(TipoDocumento idTpDocumento);

    boolean buscarRegistroPorTpRelacioIes(RelacionIes idRelacionIes);

    boolean buscarRegistroPorTpDedicacion(TiempoDedicacion idTpDedicacion);

}
