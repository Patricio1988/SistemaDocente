/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Periodo;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.PeriodoServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.uce.uce.medicina.docente.servicio.DocenteContratoMateriaServicio;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean PeriodoControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class PeriodoControlador {
//Declaración del atributo Periodo
    private Periodo periodo;
//Servicios
    @EJB
    private PeriodoServicio serv_periodo;
     @EJB
    private DocenteContratoMateriaServicio servCarga;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
//Constructor por defecto
    public PeriodoControlador() {

    }
//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        periodo = new Periodo();

      

    }

// Metodos
//Método que permite guardar un registro en la base de datos.
    public void crear() {


        try {

                serv_periodo.insertarPeriodo(periodo);
                periodo = new Periodo();
                req=RequestContext.getCurrentInstance();
                req.execute("PF('dialogoPeriodoAgregar').hide()");//Cierra el dialogo para a agregar
                req=null;

                MensajesFaces.informacion("Guardado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
       
        }
     

    }

/**Metodo que permite actualizar un objeto de tipo Actividad en la base de datos
 */
    public void actualizar() {

        try {


                serv_periodo.actualizarPeriodo(periodo);
                periodo = new Periodo();
                req=RequestContext.getCurrentInstance();
                req.execute("PF('dialogoDatosActualizar').hide()");//Cierra el dialogo para a actualizar
                req=null;

                MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
/**Método para buscar un período por su id
     * @param id
 */
    public void encontrarPeriodoID(Object id) {

        periodo = serv_periodo.encontrarPeriodoID(id);

        if (periodo != null) {
            MensajesFaces.informacion("Periodo Encontrado", "Encontrada");
        } else {

            MensajesFaces.informacion("Periodo No Encontrado", "No encontrada");
        }
    }
/**Metodo que permite eliminar un registro 
     * @param per
 */
    public void eliminar(Periodo per) {
         try {
            boolean verificarCargaEnPeriodo = servCarga.buscarRegistroPorPeriodo(per);         
            if (verificarCargaEnPeriodo) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
               serv_periodo.eliminarPeriodo(per);
            periodo = new Periodo();
                MensajesFaces.informacion("Eliminado", "Exitoso");
                           }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

        
    }
/**Método que retorna una lista de objetos de tipo Periodo
     * @return serv_periodo.listarPeriodoporFechaInicio()
 */
   

    public List<Periodo> recuperarTodos() {
        return serv_periodo.listarPeriodoporFechaInicio();

    }
    /**Método que retorna una lista de periodos activos
     * @return serv_periodo.listarPeriodoporActivos();
 */
    public List<Periodo> listaPeridosActivos()
    {
        return serv_periodo.listarPeriodoporActivos();
    }
    /**Método para cerrar*/
    public void cerrar()
    {
         periodo = new Periodo();
    }

    //GET Y SET

 /**Devuelve un objeto tipo Periodo
     * @return periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }
/**Modifica un objeto tipo Periodo
     * @param  periodo
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }


   

}
