/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;


import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.PaisOrigenServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;


/**
 * Declaración del ManagedBean PaisControler que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean

public class PaisControler {
//Declaración del atributo PaisOrigen
    private PaisOrigen paisOrigen;
   //Servicios
    @EJB
    private PaisOrigenServicio servPais;
    @EJB
    private DocenteServicio servDocente;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
     private RequestContext req;
 
//Constructor por defecto
    public PaisControler() {

    }
//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        paisOrigen = new PaisOrigen();
       
     

    }
//Método que permite guardar un registro en la base de datos.
    public void crear() {
        try {
            servPais.insertarPaisOrigen(paisOrigen);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoPaisAgregar').hide();");//Cierra el dialogo para a agregar
            req = null;
            paisOrigen = new PaisOrigen();

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
/**Metodo que permite actualizar un objeto de tipo Actividad en la base de datos*/
    public void actualizar() {

        try {
           
            servPais.actualizarPaisOrigen(paisOrigen);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoPaisActualizar').hide();");//Cierra el dialogo para a actualizar
            req = null;
            paisOrigen = new PaisOrigen();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
/**Metodo que permite eliminar un registro 
     * @param pa
 */
    public void eliminar(PaisOrigen pa) {
        try {
            boolean verificarDocenteEnPais = servDocente.buscarRegistroPorPais(pa);         
            if (verificarDocenteEnPais) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servPais.eliminarPaisOrigen(pa);
            paisOrigen = new PaisOrigen();
                MensajesFaces.informacion("Eliminado", "Exitoso");
                           }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }
    }
    /**Método para buscar un país por su id
     * @param id
 */
  public void buscarPaisID(int id) {
       paisOrigen=new PaisOrigen();
                
        paisOrigen = servPais.encontrarPais(id);

        if (paisOrigen != null) {
            MensajesFaces.informacion("Pais Encontrado", "Encontrada");
        } else {

            MensajesFaces.informacion("Pais No Encontrado", "No encontrada");
        }
    }
   
/**Método que retorna una lista de objetos de tipo PaisOrigen
     * @return servPais.ListarPaisporNombre()
 */
    public List<PaisOrigen> recuperarTodos() {
        return servPais.ListarPaisporNombre();
    }
  // Método para cerrar
    public void cerrar() {
       paisOrigen=new PaisOrigen();
        
    }

    public PaisOrigen getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(PaisOrigen paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
 


   

}
