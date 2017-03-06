/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;


import com.ec.uce.medicina.docente.modelo.Ies;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.FormacionServicio;
import com.uce.uce.medicina.docente.servicio.IesServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean IesControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class IesControlador implements Serializable {
//Declarción de objeto tipo Ies
    private Ies ies;
//Servicios
    @EJB
    private IesServicio servIes;
    @EJB
    private FormacionServicio servFormacion;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
//Constructor por defecto
    public IesControlador() {

    }
//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        ies = new Ies();

    }
//Método que permite guardar un registro en la base de datos.
    public void crear() {
        try {
            servIes.insertarIes(ies);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoIesAgregar').hide();");//Cierra el dialogo para a agregar
            req = null;
            ies = new Ies();
            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
//Método que permite actulaizar un registro en la base de datos.
    public void actualizar() throws Exception {

        try {

            servIes.actualizarIes(ies);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoIesActualizar').hide();");//Cierra el dialogo para a actualizar
            req = null;
            ies = new Ies();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
//Método que permite eliminar un registro en la base de datos.
    public void eliminar(Ies ie) {
        try {
            boolean verificarFormacionEnIes = servFormacion.buscarRegistroPoriES(ie);        
            if (verificarFormacionEnIes) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servIes.eliminarIes(ie);
            ies = new Ies();
                MensajesFaces.informacion("Eliminado", "Exitoso");
                           }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

       
    }
// Metodo para cerrar
    public void cerrar() {
         ies = new Ies();
        
    }
    /**Método para buscar una IES por su id
     * @param id
     */
    public void buscarIesID(int id) {

        ies = servIes.buscarIesID(id);

        if (ies != null) {
            // idActividad=actividad.getIdActividad();
            MensajesFaces.informacion("Actividad", "Encontrada");
        } else {

            MensajesFaces.informacion("Actividad", "No encontrada");
        }
    }

 /**Método que devuelve una lista de todas las IES
     * @return servIes.listarIesporCodigo()
     */
    public List<Ies> recuperarTodos() {
        return servIes.listarIesporCodigo();

    }


    //GET y SET
/**Devuelve un objeto de tipo Ies
     * @return ies
 */
   
    public Ies getIes() {

        return ies;
    }
/**Modifica un objeto de tipo Ies
     * @param  ies
 */
    public void setIes(Ies ies) {
        this.ies = ies;
    }

}
