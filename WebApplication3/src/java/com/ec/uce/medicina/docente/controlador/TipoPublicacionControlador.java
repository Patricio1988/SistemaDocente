/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.PublicacionesServicio;
import com.uce.uce.medicina.docente.servicio.TipoPublicacionServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean TipoPublicacionControlador que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class TipoPublicacionControlador {
    //Declaración del objeto tipo TipoPublicacion 

    private TipoPublicacion tipoPublicacion;
    //Servicios
    @EJB
    private TipoPublicacionServicio servtpPublicacion;
    @EJB
    private PublicacionesServicio servPublicaciones;
    /**
     * Atributo RequestContext que encapsula la información de una petición HTTP
     */
    private RequestContext req;
    
//Constructor por dedecto
    public TipoPublicacionControlador() {

    }
/**
     * Inicializacion de variables 
     */
    @PostConstruct
    public void inicializar() {
        tipoPublicacion = new TipoPublicacion();

    }
//Método que permite guardar un registro en la base de datos.
    public void crear() {
        try {
            servtpPublicacion.insertarTipoPublicacion(tipoPublicacion);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoTpPubliAgregar').hide();");//Cierra el dialogo para agregar
            req = null;
            tipoPublicacion = new TipoPublicacion();

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
/**Método que permite actualizar un registro en la base de datos*/
    public void actualizar()  {

        try {

            servtpPublicacion.actualizarTipoPublicacion(tipoPublicacion);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoTpPubliActualizar').hide();");//Cierra el dialogo para actualizar
            req = null;
            tipoPublicacion = new TipoPublicacion();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
 /**
     * Metodo que permite eliminar un registro en la base de datos
     * @param tp
     */
    public void eliminar(TipoPublicacion tp) {
        try {
            boolean verificarPublicacionEnTpPublicacion = servPublicaciones.buscarRegistroPorTpPublicacion(tp);
            if (verificarPublicacionEnTpPublicacion) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servtpPublicacion.eliminarTipoPublicacion(tp);
                tipoPublicacion = new TipoPublicacion();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }
/**Método para buscar un tipo de publicación por su id
     * @param id
 */
    public void buscarTpPublicacionID(int id) {

        tipoPublicacion = servtpPublicacion.encontrarTipoPublicacionID(id);

        if (tipoPublicacion != null) {
            // idActividad=actividad.getIdActividad();
            MensajesFaces.informacion("Actividad", "Encontrada");
        } else {

            MensajesFaces.informacion("Actividad", "No encontrada");
        }
    }
/**Método de devuelve una lista con los diferentes tiempos de documento
     * @return servtpPublicacion.listarTipoPublicacionporNombre()
 */
    public List<TipoPublicacion> recuperarTodos() {

        return servtpPublicacion.listarTipoPublicacionporNombre();
    }

     //Método para cerrar
    public void cerrar() {
        tipoPublicacion = new TipoPublicacion();

    }
//Get and SET
/**Devuelve un objeto de tipo TipoPublicacion
     * @return tipoPublicacion
     */
    public TipoPublicacion getTipoPublicacion() {
        return tipoPublicacion;
    }
/**Modifica un objeto de tipo TipoPublicacion
     * @param  tipoPublicacion
     */
    public void setTipoPublicacion(TipoPublicacion tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

}
