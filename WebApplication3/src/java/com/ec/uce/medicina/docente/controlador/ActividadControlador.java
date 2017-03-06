/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Actividad;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.ActividadServicio;
import com.uce.uce.medicina.docente.servicio.DocenteContratoMateriaServicio;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.postgresql.util.PSQLException;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean ActividadControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class ActividadControlador {
//Declaración del atributo actividad

    private Actividad actividad;
//servicios
    @EJB
    private ActividadServicio servActividad;
    @EJB
    private DocenteContratoMateriaServicio servCarga;
    /**
     * Atributo RequestContext que encapsula la información de una petición HTTP
     */

    private RequestContext req;

    /**
     * Constructor por defecto
     */

    public ActividadControlador() {

    }

    /**
     * Inicializacion de variables
     */

    @PostConstruct
    public void inicializar() {
        actividad = new Actividad();
        /**
         * Instacia del objeto de tipo actividad
         */

    }
//Método que permite guardar un registro de tipo Actividad en la base de datos.

    public void crear()  {

        try {
            servActividad.insertarActividad(actividad);
            /**
             * llamamos la metodo qde nuestro servicio que nos permite guardar
             * un registro de tipo Actividad
             */
            actividad = new Actividad();
            /**
             * Istanciamos el objeto
             */
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoActividadAgregar').hide();");
            /**
             * Cerraramos el dialogo declarado en nuestra pagina xhtml
             */
            req = null;
            MensajesFaces.informacion("Guardado", "Exitoso");

        } 
        catch (Exception ejb) {
            MensajesFaces.error("Error",  ejb.getMessage());

        }
     

    }

    /**
     * Metodo que permite actualizar un registro de tipo Actividad en la base de datos
     */

    public void actualizar() {

        try {

            servActividad.actualizarActividad(actividad);
            /**
             * llamamos al método que nos permite realizar la actualizacion de
             * nuestro registro
             */

            actividad = new Actividad();
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoActividadActualizar').hide();");
            /**
             * cerramos el dialogo
             */
            req = null;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Metodo que permite eliminar un registro siempre y cuando no este
     * relacionado en nuestra base de datos
     *
     * @param ac
     */
    public void eliminar(Actividad ac) {
        try {
            boolean verificarCargaEnActividad = servCarga.buscarRegistroPorActividad(ac);
            /**
             * Primero buscamos que no existan registros relacionados en la base
             * de datos
             */
            if (verificarCargaEnActividad) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servActividad.eliminarActividad(ac);
                actividad = new Actividad();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }

    /**
     * Método que retorna una lista de objetos de tipo Actividad
     *
     * @return servActividad.listaActividadporNombre()
     */
    public List<Actividad> recuperarTodos() {

        return servActividad.listaActividadporNombre();
    }

    /**
     * Método que busca un objeto de tipo Actividad por su id
     *
     * @param id
     */
    public void buscarActividadID(int id) {

        actividad = servActividad.buscarActividadporID(id);

        if (actividad != null) {
            MensajesFaces.informacion("Actividad", "Encontrada");
        } else {

            MensajesFaces.informacion("Actividad", "No encontrada");
        }
    }

    // Método para cerrar cerrar 
    public void cerrar() {
        actividad = new Actividad();

    }
    //GET AND SET

    /**
     * Retorna un Objeto de tipo actividad
     *
     * @return actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Modifica un Objeto de tipo actividad
     *
     * @param actividad
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

}
