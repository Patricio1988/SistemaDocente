/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.RelacionLaboralServicio;
import com.uce.uce.medicina.docente.servicio.TiempoDedicacionServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean TiempoDedicacionControlador que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class TiempoDedicacionControlador {
    //Declaración del atributo tiempoDedicacion

    private TiempoDedicacion tiempoDedicacion;
    //Servicios
    @EJB
    private TiempoDedicacionServicio servtiempoDedicacion;
    @EJB
    private RelacionLaboralServicio servReLaboral;

    /**
     * Atributo RequestContext que encapsula la información de una petición HTTP
     */
    private RequestContext req;
    
//Constructor por dedecto
    public TiempoDedicacionControlador() {
    }

    /**
     * Inicializacion de variables 
     */
    @PostConstruct
    public void inicializar() {
        tiempoDedicacion = new TiempoDedicacion();

    }
//Método que permite guardar un registro en la base de datos.
    public void crear() {
        try {
            servtiempoDedicacion.insertarTiempoDedicacion(tiempoDedicacion);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoTDedicacionAgregar').hide();");//Cierra el dialogo para agregar
            req = null;
            tiempoDedicacion = new TiempoDedicacion();

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
 /**Metodo que permite actualizar un registro en la base de datos*/
    public void actualizar()  {

        try {

            servtiempoDedicacion.actualizarTiempoDedicacion(tiempoDedicacion);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoTDedicacionActualizar').hide();");//Cierra el dialogo para actualizar
            req = null;

            tiempoDedicacion = new TiempoDedicacion();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
 

    /**
     * Metodo que permite eliminar un registro en la base de datos
     * @param tpDedi
     */
    public void eliminar(TiempoDedicacion tpDedi) {
        try {
            boolean verificarReLaboraldEnTpDedicacion = servReLaboral.buscarRegistroPorTpDedicacion(tpDedi);
            if (verificarReLaboraldEnTpDedicacion) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servtiempoDedicacion.eliminarTiempoDedicacion(tpDedi);
                tiempoDedicacion = new TiempoDedicacion();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }
    //Método para cerrar

    public void cerrar() {
        tiempoDedicacion = new TiempoDedicacion();

    }
/**Método para buscar un tiempo de dedicación por su id
     * @param id
 */
    public void buscarTpDedicacionID(int id) {
        tiempoDedicacion = new TiempoDedicacion();

        tiempoDedicacion = servtiempoDedicacion.encontarTiempoDedicacionID(id);

        if (tiempoDedicacion != null) {
            MensajesFaces.informacion("TiempoDedicacion", "Encontrada");
        } else {

            MensajesFaces.informacion("TiempoDedicacion", "No encontrada");
        }
    }
/**Método de devuelve una lista con los diferentes tiempos de dedicación
     * @return servtiempoDedicacion.listarTiempoDediporNombre()
 */
    public List<TiempoDedicacion> recuperarTodos() {

        return servtiempoDedicacion.listarTiempoDediporNombre();

    }

//GET Y SET
    /**Devuelve un objeto de tipo TiempoDedicacion
     * @return tiempoDedicacion
     */
    public TiempoDedicacion getTiempoDedicacion() {
        return tiempoDedicacion;
    }
/**Modifica un objeto de tipo TiempoDedicacion
     * @param  tiempoDedicacion
     */
    public void setTiempoDedicacion(TiempoDedicacion tiempoDedicacion) {
        this.tiempoDedicacion = tiempoDedicacion;
    }

}
