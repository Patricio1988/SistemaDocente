/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Categoria;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.CategoriaServicio;
import com.uce.uce.medicina.docente.servicio.RelacionLaboralServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean CategoriaControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class CategoriaControlador {
//Declaración de un objeto tipo Categoria
    private Categoria categoria;
//Servicios
    @EJB
    private CategoriaServicio servCategoria;
    @EJB
    private RelacionLaboralServicio servReLaboral;
    //Atributo RequestContext que encapsula la información de una petición HTTP
    private RequestContext req;
//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        categoria = new Categoria();//Instancia de objetos

    }
  /**
     * Método que permite crear un registro en la base de datos,se cierra el dialogo agregar siempre y cuando todo haya salido bien
     *
     */
    public void crear() {
        try {
            servCategoria.insertarCategoria(categoria);
            categoria = new Categoria();
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCategoriaAgregar').hide();");//Cierra el dialogo
            req = null;

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
  /**
     * Método que permite actualizar un registro en la base de datos,se cierra el dialogo actualizar siempre y cuando todo haya salido bien
     *
     */
    public void actualizar() throws Exception {

        try {

            servCategoria.actualizarCategoria(categoria);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCategoriaActualizar').hide();");//Cierra el dialogo
            req = null;

            categoria = new Categoria();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
  /**
     * Método que permite eliminar un registro en la base de datos
     *
     * @param ca
     */
    public void eliminar(Categoria ca) {
        try {
            //Verifica si la no exista una catego´ria ralacionada en la tabla Relacion laboral
            boolean verificarReLaboraldEnCategoria = servReLaboral.buscarRegistroPorCategoria(ca);
            if (verificarReLaboraldEnCategoria) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servCategoria.eliminarCategoria(ca);
                categoria = new Categoria();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }
/**Metodo que permite cerrar cualquier dialogo
 */
    public void cerrar() {
        categoria = new Categoria();
    }
/**Método que busca una categoría por su id
     * @param id
 */
    public void buscarCategoriadID(int id) {

        categoria = servCategoria.encontrarCategoria(id);

        if (categoria != null) {
            MensajesFaces.informacion("Actividad", "Encontrada");
        } else {

            MensajesFaces.informacion("Actividad", "No encontrada");
        }
    }

/**Método que recupera una lista de todas las actividades 
     * @return  servCategoria.ListarCategoriapoNombre()
 */
    public List<Categoria> recuperarTodos() {
        return servCategoria.ListarCategoriapoNombre();

    }
    //GET Y SET
/**Devuelve un objeto de tipo Categoria
     * @return categoria
 */
    public Categoria getCategoria() {
        return categoria;
    }
/**Modifica un objeto de tipo Categoria
     * @param  categoria
 */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

 


}
