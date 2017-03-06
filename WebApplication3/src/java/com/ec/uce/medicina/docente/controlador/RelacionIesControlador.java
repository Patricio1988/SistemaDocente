/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.RelacionIes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.RelacionIesServicio;
import com.uce.uce.medicina.docente.servicio.RelacionLaboralServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
/**
 * Declaración del ManagedBean RelacionIesControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class RelacionIesControlador {
//Declaración del atributo RelacionIes
    private RelacionIes relacionIes;
//Servicios
    @EJB
    private RelacionIesServicio servRelacionIes;
     @EJB
    private RelacionLaboralServicio servReLaboral;

    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
//Constructor por defecto
    public RelacionIesControlador() {

    }
//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        relacionIes = new RelacionIes();


    }
//Método que permite guardar un registro en la base de datos.
    public void crear() {
        try {
            servRelacionIes.insertarRelacionIes(relacionIes);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoReIesAgregar').hide();");//Cierra el dialogo para a agregar
            req = null;
            relacionIes = new RelacionIes();

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
/**Metodo que permite actualizar un objeto de tipo Actividad en la base de datos*/
    public void actualizar() {

        try {

            servRelacionIes.actualizarRelacionIes(relacionIes);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoReIesActualizar').hide();");//Cierra el dialogo para a actualizar
            req = null;

            relacionIes = new RelacionIes();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
/**Metodo que permite eliminar un registro 
     * @param re
 */
    public void eliminar(RelacionIes re) {
         try {
            boolean verificarReLaboraldEnReIes = servReLaboral.buscarRegistroPorTpRelacioIes(re);
            if (verificarReLaboraldEnReIes) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                   servRelacionIes.eliminarRelacionIes(re);
            relacionIes = new RelacionIes();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        } 

        

    }
       // Método para cerrar
    public void cerrar() {
        relacionIes = new RelacionIes();

    }
/**Método para buscar una relación IES por su id
     * @param id
 */
    public void buscarRelacionIesID(int id) {

        relacionIes = servRelacionIes.encontrarRelacion(id);

        if (relacionIes != null) {
            // idActividad=actividad.getIdActividad();
            MensajesFaces.informacion("Actividad", "Encontrada");
        } else {

            MensajesFaces.informacion("Actividad", "No encontrada");
        }
    }

/**Método que retorna una lista de objetos de tipo RelacionIes
     * @return servRelacionIes.listarRelacionIes()
 */

    public List<RelacionIes> recuperarTodos() {

        return servRelacionIes.listarRelacionIes();
    }

    //Método para cerrar  un dialogo de acuerdo a un evento handleClose
    public void handleClose(CloseEvent event) {
        relacionIes = new RelacionIes();

    }

    //GET y SET
    /**Devuelve un objeto tipo RelacionIes
     * @return relacionIes
     */
    public RelacionIes getRelacionIes() {
        return relacionIes;
    }
/**Modifica un objeto tipo RelacionIes
     * @param  relacionIes
     */
    public void setRelacionIes(RelacionIes relacionIes) {
        this.relacionIes = relacionIes;
    }



}
