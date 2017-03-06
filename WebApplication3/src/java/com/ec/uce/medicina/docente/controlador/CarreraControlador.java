/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import static com.ec.uce.medicina.docente.util.Tablas.retornarmodalidades;
import static com.ec.uce.medicina.docente.util.Tablas.retornarniveles;
import com.uce.uce.medicina.docente.servicio.CarreraServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.FacultadServicio;
import com.uce.uce.medicina.docente.servicio.MallaServicio;
import com.uce.uce.medicina.docente.servicio.UniversidadServicio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean CarreraControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ManagedBean
@ViewScoped
public class CarreraControlador {
   //Atributo items de la Facultad

    private List<SelectItem> itemsFacultad;
    //Atributo id de la facultad
    private int idFacultad;
    //Atributo items de la universidad
    private List<SelectItem> itemsUniversidad;
    private int idUniversidad;
    /**
     * Atributo estado del combo box
     */
    private String estadoComboBox;
    /**
     * Declaración de un objeto carrera
     */
    private Carrera carrera;
    //Servicios
    @EJB
    private CarreraServicio servCarrera;
    @EJB
    private FacultadServicio servFaculta;
    @EJB
    private UniversidadServicio servUniversidad;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private MallaServicio servMalla;
    //Atributo para una lista de carreras
    private List<Carrera> listCarrera;
    //Atributo niveles de la carrera
    private Map<String, String> niveles ;
    //Atributo modalidades de la carrera
    private Map<String, String> modalidades;
    // Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;

    /**
     * Constructor por defecto
     */
    public CarreraControlador() {

    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {
        carrera = new Carrera();//instancia de una carrera
        itemsUniversidad = servUniversidad.oneMenuUniversidad(servUniversidad.listarUniversidad());//Carga una lista con los items de la universidad
        itemsFacultad = servFaculta.oneMenuFacultad(servFaculta.listarFacultad());//Carga una lista con los items de la facultad

        niveles = retornarniveles();//se cargan los niveles
        modalidades = retornarmodalidades();//se cargan las modalida
        listCarrera = servCarrera.listarCarrera();//se carga una lista de carreras
        idFacultad = 0;
        idUniversidad = 0;
        this.estadoComboBox = Constantes.INACTIVO;
    }

    /**
     * Método que permite obtener todas las carreras de acuerdo al id de una
     * facultad
     *
     * @param ent
     */
    public void getFacultades(AjaxBehaviorEvent ent) {
        this.estadoComboBox = Constantes.ACTIVO;
        this.itemsFacultad = servFaculta.oneMenuFacultad(this.servFaculta.listfacultadaPorUniversidad(idUniversidad));

    }

    /**
     * Método que permite crear un registro en la base de datos,se cierra el
     * dialogo para agregar una carrera si todo sale bien caso contrario no se
     * cierra el dialogo
     */
    public void crear() {
        try {

            carrera.setIdFacultad(servFaculta.encontrarFacultad(idFacultad));//Setea el valor de una facultad 
            servCarrera.insertarCarrera(carrera);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCarreraAgregar').hide();");//cierra el dialogo para agregar
            req = null;

            idUniversidad = 0;
            idFacultad = 0;
            this.estadoComboBox = Constantes.INACTIVO;
            carrera = new Carrera();

            MensajesFaces.informacion("Guardado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos,se cierra
     * el dialogo para actualizar una carrera si todo sale bien caso contrario
     * no se cierra el dialogo
     *
     */
    public void actualizar() {

        try {
            carrera.setIdFacultad(servFaculta.encontrarFacultad(idFacultad));//Busca la carrera por su id
            servCarrera.actualizarCarrera(carrera);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide();");//Cierra el dialogo para actualizar
            req = null;
            carrera = new Carrera();
            idUniversidad = 0;
            idFacultad = 0;
            this.estadoComboBox = Constantes.INACTIVO;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Método que permite eliminar un registro de la bse de datos siempre y
     * cuando no exista un registro relacionado en otra tabla en la base de
     * datos
     *
     * @param carre
     */
    public void eliminar(Carrera carre) {
        try {
     
            boolean verificarMallaEnCarrera = servMalla.buscarRegistroPorCarrera(carre);//Verifico que no haya una malla relacionada con una carrera
            if ( verificarMallaEnCarrera) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servCarrera.eliminarCarrera(carre);
                carrera = new Carrera();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }

    /**
     * Método que me permite cerrar cualquier dialogo
     */
    public void cerrar() {
        idUniversidad = 0;
        idFacultad = 0;
        this.estadoComboBox = Constantes.INACTIVO;
        carrera = new Carrera();
    }

    /**
     * Método que me permite buscar una carrera por su id
     *
     * @param id
     */
    public void buscarCarrera(Object id) {
        /**
         * if encuentra una carrera asigno los valores de la carrera y de la
         * facultad a las variables idFacultad e idUniversidad
         */
        carrera = servCarrera.encontrarCarrera(id);

        if (carrera != null) {
            idFacultad = carrera.getIdFacultad().getIdFacultad();
            idUniversidad = carrera.getIdFacultad().getIdUniversidad().getIdUniversidad();
            MensajesFaces.informacion("Carrera", "Encontrada");
        } else {

            MensajesFaces.informacion("Carrera", "No encontrada");
            carrera = new Carrera();
        }

    }

    /**
     * Método que devuelve una lista de todas la carreras de la base de datos
     *
     * @return listCarrera
     */
    public List<Carrera> recuperarTodos() {
        listCarrera = servCarrera.listarCarrera();
        return listCarrera;
    }

    /**
     * Devuelve todos los niveles de la carrera
     *
     * @return niveles
     */
    public Map<String, String> getNiveles() {
        return niveles;
    }

    /**
     * Devuelve todos los niveles de la carrera
     *
     * @param niveles
     */
    public void setNiveles(Map<String, String> niveles) {
        this.niveles = niveles;
    }

    /**
     * Devuelve todos las modalidades de la carrera
     *
     * @return modalidades
     */
    public Map<String, String> getModalidades() {
        return modalidades;
    }

    /**
     * Devuelve todos las modalidades de la carrera
     *
     * @param modalidades
     */
    public void setModalidades(Map<String, String> modalidades) {
        this.modalidades = modalidades;
    }

    /**
     * Devuelve el estad del combo box
     *
     * @return estadoComboBox
     */
    public String getEstadoComboBox() {
        return estadoComboBox;
    }

    /**
     * Devuelve el estad del combo box
     *
     * @param estadoComboBox
     */
    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

    /**
     * Devuelve una lista con los items de la universidad
     *
     * @return itemsUniversidad
     */
    public List<SelectItem> getItemsUniversidad() {
        return itemsUniversidad;
    }

    /**
     * Devuelve una lista con los items de la universidad
     *
     * @param itemsUniversidad
     */
    public void setItemsUniversidad(List<SelectItem> itemsUniversidad) {
        this.itemsUniversidad = itemsUniversidad;
    }

    /**
     * Devuelve el id de la universidad
     *
     * @return idUniversidad
     */
    public int getIdUniversidad() {
        return idUniversidad;
    }

    /**
     * Devuelve el id de la universidad
     *
     * @param idUniversidad
     */
    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    /**
     * Devuelve una lista con los items de la facultad
     *
     * @return itemsFacultad
     */
    public List<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    /**
     * Devuelve una lista con los items de la facultad
     *
     * @param itemsFacultad
     */
    public void setItemsFacultad(List<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

    /**
     * Devuelve id de la facultad
     *
     * @return idFacultad
     */
    public int getIdFacultad() {
        return idFacultad;
    }

    /**
     * Devuelve id de la facultad
     *
     * @param idFacultad
     */
    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * Devuelve un objeto de tipo Carrera
     *
     * @return carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * Devuelve un objeto de tipo Carrera
     *
     * @param carrera
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    /**
     * Devuelve una lista de carreras
     *
     * @return listCarrera
     */
    public List<Carrera> getListCarrera() {
        return listCarrera;
    }

    /**
     * Devuelve una lista de carreras
     *
     * @param listCarrera
     */
    public void setListCarrera(List<Carrera> listCarrera) {
        this.listCarrera = listCarrera;
    }

}
