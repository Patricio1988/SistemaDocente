/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Facultad;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.CarreraServicio;
import com.uce.uce.medicina.docente.servicio.FacultadServicio;
import com.uce.uce.medicina.docente.servicio.UniversidadServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean FacultadControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean

public class FacultadControlador {

    /**
     * Atributo items de la universidad
     */
    private List<SelectItem> itemsUniversidad;
    /**
     * Atributo id de la Universidad
     */
    private int idUniversidad;
    // Declaracioón del objeto facultad
    private Facultad facultad;
    //Servicios
    @EJB
    private FacultadServicio servFacultad;
    @EJB
    private UniversidadServicio servUniversidad;
    @EJB
    private CarreraServicio servCarrera;
    /**
     * Atributo para una lista de  facultades
     */
    private List<Facultad> listfacultad;
    //Atributo RequestContext que encapsula información de una petición HTTP
    private RequestContext req;
//Constructor por defecto

    public FacultadControlador() {

    }
//Inicializacón de variables

    @PostConstruct
    public void inicializar() {
        //Instancia de objetos
        facultad = new Facultad();
        itemsUniversidad = servUniversidad.oneMenuUniversidad(servUniversidad.listarUniversidad());
        idUniversidad = 0;

    }

    /**
     * Método que permite crear un registro en la base de datos, si todo sale
     * bien cierra el dialogo para crear caso contrario no
     */
    public void crear() {

        try {

            facultad.setIdUniversidad(servUniversidad.encontrarUniversidadID(idUniversidad));

            servFacultad.insertarFacultad(facultad);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoFacultadAgregar').hide();");//Cierra el dialogo para agregar
            req = null;
            facultad = new Facultad();
            idUniversidad = 0;
            MensajesFaces.informacion("Agregado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos, si todo
     * sale bien cierra el dialogo para actualizar caso contrario no
     */
    public void actualizar() {

        try {
            facultad.setIdUniversidad(servUniversidad.encontrarUniversidadID(idUniversidad));
            // facultad.setNomFacultad(nombreFacultad);

            servFacultad.actualizarFacultad(facultad);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide();");
            req = null;
            facultad = new Facultad();
            idUniversidad = 0;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Metodo que permite eliminar un registro
     *
     * @param facu
     */
    public void eliminar(Facultad facu) {

        try {
            boolean verificarcarreraEnFacultad = servCarrera.buscarRegistroPorFacultad(facu);  //verifica que no exista un registro de una facultad en una carrera       
            if (verificarcarreraEnFacultad) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servFacultad.eliminarFacultad(facu);
                facultad = new Facultad();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }
    }

    /**
     * Método que busca en facultad por su id
     *
     * @param id
     */
    public void encontrarFacultaddID(int id) {
        facultad = servFacultad.buscarFacultadID(id);

        if (facultad != null) {
            //  nombreFacultad=facultad.getNomFacultad();
            idUniversidad = facultad.getIdUniversidad().getIdUniversidad();

            MensajesFaces.informacion("Facultad", "Encontrada");
        } else {

            MensajesFaces.informacion("Facultad", "No encontrada");
        }
    }

    /**
     * Método para cerrar
     */
    public void cerrar() {
        facultad = new Facultad();
        idUniversidad = 0;
    }
/**Método que lista todas las facultades
     * @return listfacultad
 */
    public List<Facultad> recuperarTodos() {
        listfacultad = servFacultad.listarFacultad();

        return listfacultad;
    }
/**Devuelve una lista con  items de la universidad
     * @return itemsUniversidad
 */
    public List<SelectItem> getItemsUniversidad() {
        return itemsUniversidad;
    }
/**Modifica una lista con  items de la universidad
     * @param  itemsUniversidad
 */
    public void setItemsUniversidad(List<SelectItem> itemsUniversidad) {
        this.itemsUniversidad = itemsUniversidad;
    }
/**Devuelve el id de la universidad
     * @return idUniversidad
 */
    public int getIdUniversidad() {
        return idUniversidad;
    }
/**Modifica el id de la universidad
     * @param  idUniversidad
 */
    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }
/**Devuelve un objeto de tipo facultad
     * @return facultad
 */
    public Facultad getFacultad() {
        return facultad;
    }
/**Modifica un objeto de tipo facultad
     * @param  facultad
 */
    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
    /**Devuelve una lista con las facultades
     * @return listfacultad
 */
    public List<Facultad> getListfacultad() {
        return listfacultad;
    }
    /**Modifica una lista con las facultades
     * @param  listfacultad
 */
    public void setListfacultad(List<Facultad> listfacultad) {
        this.listfacultad = listfacultad;
    }

}
