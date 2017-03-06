/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.RelacionLaboralServicio;
import com.uce.uce.medicina.docente.servicio.TipoDocumentoServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean TipoDocumentoControleador que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class TipoDocumentoControleador {
    //Declaración del objeto tipo TipoDocumento 

    private TipoDocumento tipoDocumento;
    //Servicios
    @EJB
    private TipoDocumentoServicio servtpDocumento;
    @EJB
    private RelacionLaboralServicio servReLaboral;

    /**
     * Atributo RequestContext que encapsula la información de una petición HTTP
     */
    private RequestContext req;

//Constructor por dedecto
    public TipoDocumentoControleador() {

    }
/**
     * Inicializacion de variables 
     */
    @PostConstruct
    public void inicializar() {
        tipoDocumento = new TipoDocumento();

    }
//Método que permite guardar un registro en la base de datos.
    public void crear() {
        try {
            servtpDocumento.insertarTipoDocumento(tipoDocumento);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoTpDocuAgregar').hide();");//Cierra el dialogo para agregar
            req = null;
            tipoDocumento = new TipoDocumento();

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
/**Metodo que permite actualizar un registro en la base de datos*/
    public void actualizar()  {

        try {

            servtpDocumento.actualizarTipoDocumento(tipoDocumento);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoTpDocuActualizar').hide();");//Cierra el dialogo para actualizar
            req = null;
            tipoDocumento = new TipoDocumento();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
 /**
     * Metodo que permite eliminar un registro en la base de datos
     * @param tpDocu
     */
    public void eliminar(TipoDocumento tpDocu) {
        try {
            boolean verificarReLaboraldEnTpDocu = servReLaboral.buscarRegistroPorTpDocumento(tpDocu);
            if (verificarReLaboraldEnTpDocu) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servtpDocumento.eliminarTipoDocumento(tpDocu);
                tipoDocumento = new TipoDocumento();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }
    }

    public void buscarTipoDocumnetoID(int id) {
        tipoDocumento = new TipoDocumento();

        tipoDocumento = servtpDocumento.encontrarTipoDocumento(id);

        if (tipoDocumento != null) {
            MensajesFaces.informacion("Actividad", "Encontrada");
        } else {

            MensajesFaces.informacion("Actividad", "No encontrada");
        }
    }
/**Método de devuelve una lista con los diferentes tiempos de documento
     * @return servtpDocumento.listarTipoDocuporNombre()
 */
    public List<TipoDocumento> recuperarTodos() {
        return servtpDocumento.listarTipoDocuporNombre();

    }
    //Método para cerrar
    public void cerrar() {
        tipoDocumento = new TipoDocumento();

    }
    //GET y SET
  /**Devuelve un objeto de tipo TipoDocumento
     * @return tipoDocumento
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }
/**Modifica un objeto de tipo TipoDocumento
     * @param  tipoDocumento
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


}
