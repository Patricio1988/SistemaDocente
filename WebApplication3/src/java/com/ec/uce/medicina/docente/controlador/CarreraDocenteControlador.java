/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.ValidarCedula;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.CarreraServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.FacultadServicio;
import com.uce.uce.medicina.docente.servicio.PaisOrigenServicio;
import com.uce.uce.medicina.docente.servicio.PeriodoServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean CarreraDocenteControlador que sera manejada por jsf
 * de ámbito ViewScoped que permitira realizar las operaciones en las paginas
 * xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class CarreraDocenteControlador {

//Atributo items de la Facultad
    private List<SelectItem> itemsFacultad;
    //Atributo items de la Carrera
    private List<SelectItem> itemsCarrera;
    //Atributo lista de docentes
    private List<CarreraDocente> listDocentes;
    //Declaración de un objetod e tipo Docente
    private Docente docente;
    //Declaración de un objetod e tipo Carrera
    private Carrera carrera;
    //Declaración de un objetod e tipo CarreraDocente
    private CarreraDocente carreraDocente;
     //Atributo id de la facultad
    private int idFacultad;
    //Atributo id de la carrera
    private int idCarrera;
    //Atributo id de la docente
    private int idDocente;
     //Atributo id país origen
    private int idpaisOrigen;
    //Servicios
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private CarreraServicio servCarrera;
    @EJB
    private FacultadServicio servFacultad;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;
    @EJB
    private PaisOrigenServicio servPais;
     @EJB
    private PeriodoServicio servPeriodo;
    //Atributo RequestContext que encapsula la informcion de una peticion HTTP
    private RequestContext req;
     //Atributo estado del combo box
    private String estadoComboBox;
        /**Atributo para la identificacio del docente*/
    private String identificacion;
     /**Atributo para cambiar el esatdo de los txt al agregar un docente*/
    private String estadoAgregar;
   //Atributo estado para las habilitar las opciones cuando un docente tiene discapacidad
    private String estadoOpcionesDiscapacidad;
     private String  estadoAgregarIes;
    public CarreraDocenteControlador() {
    }
//Inicialización de variables
    @PostConstruct
    public void inicializar() {

        this.estadoComboBox = Constantes.INACTIVO; //inicializaamos el estado del combo box en inactivo 
        //instacia de objetos
        docente = new Docente();
        carreraDocente=new CarreraDocente();
        carreraDocente.setEstadoDocente(true);
        carrera=new Carrera();
        //lista con los items de la facultad y carrera
        itemsFacultad = servFacultad.oneMenuFacultad(servFacultad.listarFacultad());
       itemsCarrera = servCarrera.oneMenuCarrera(servCarrera.listarCarrera());
        idFacultad = 0;
        idCarrera = 0;
        estadoAgregar=Constantes.INACTIVO;
        estadoOpcionesDiscapacidad=Constantes.INACTIVO;
        estadoAgregarIes=Constantes.INACTIVO;
        identificacion=Constantes.VACIO;

    }

/**Método que permite obtener todas las carreras de acuerdo al id de una facultad
     * @param ent
 */
    public void getCarreras(AjaxBehaviorEvent ent) {
        this.estadoComboBox = Constantes.ACTIVO;
        this.itemsCarrera = servCarrera.oneMenuCarrera(this.servCarrera.buscarCarreraPorFacultad(idFacultad));

    }
/**
 * Método que permite crear un registro en la base de datos,se cierra el dialogo 
 * para agregar una carreradocente si todo sale bien caso contrario no se cierra el dialogo
     * @param au
     * @param idPeriodo
 */
    public void crear(Authorities au,int idPeriodo) {
   
        try {
            /**Si el rol del usuario es ROLE_SUPERADMINISTRADOR se asigna como id de la carrera la que 
             * selecciona en el combo box caso contrario se coje el id de la carrera asignado al crear el usuario
             */
             if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR") == false) {
                idCarrera = au.getUsername().getIdCarrera().getIdCarrera();
            }
            req = RequestContext.getCurrentInstance();
            carreraDocente.setIdPeriodo(servPeriodo.encontrarPeriodoID(idPeriodo));
            carreraDocente.setIdCarrera(servCarrera.encontrarCarrera(idCarrera));
            
            crearDocente();
            carreraDocente.setIdDocente(servDocente.encontrarDocente(docente.getIdDocente()));
            if(servCarreraDocente.buscarDocenteCarreraporPeriodo(docente.getIdentificacion(), idPeriodo, idCarrera)==null)
            {
                servCarreraDocente.insertarCarreraDocente(carreraDocente);    
            }
            else{
                carreraDocente=servCarreraDocente.buscarDocenteCarreraporPeriodo(docente.getIdentificacion(), idPeriodo, idCarrera);
                carreraDocente.setEstadoDocente(true);
                servCarreraDocente.actulizarCarreraDocente(carreraDocente);          
            }
            carreraDocente=new CarreraDocente();
            docente=new Docente();
            carrera=new Carrera();
            idCarrera=0;
            idFacultad=0;
            idDocente=0;
            identificacion=Constantes.VACIO;
            MensajesFaces.informacion("Guardado", "Exitoso");
            req.execute("PF('dialogoCarreraDocenteAgregar').hide();");   //Cierra el dialogo          
            req = null;

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e.getMessage()+" "+ e.getCause().getMessage());
            req.execute("PF('dialogoCarreraDocenteAgregar').hide();");
           
        }
       
    }
    
/**
 * Método que permite actualizar un registro en la base de datos,se cierra el dialogo 
 * para actualizar una carreradocente si todo sale bien caso contrario no se cierra el dialogo
     * @param au
     * @param idPeriodo
 */
    public void actualizar(Authorities au,int idPeriodo) {

        try {
           /**Si el rol del usuario es ROLE_SUPERADMINISTRADOR se asigna como id de la carrera la que 
             * selecciona en el combo box caso contrario se coje el id de la carrera asignado al crear el usuario
             */
            if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR") == false) {
                idCarrera = au.getUsername().getIdCarrera().getIdCarrera();
            }

            req = RequestContext.getCurrentInstance();
             carreraDocente.setIdPeriodo(servPeriodo.encontrarPeriodoID(idPeriodo));
            carreraDocente.setIdCarrera(servCarrera.encontrarCarrera(idCarrera));
            actualizarDocente();
            carreraDocente.setIdDocente(servDocente.encontrarDocente(docente.getIdDocente()));
            servCarreraDocente.actulizarCarreraDocente(carreraDocente);
            carreraDocente=new CarreraDocente();
            idCarrera=0;
            idDocente=0;
            MensajesFaces.informacion("Actualizado", "Exitoso");
            req.execute("PF('dialogoDatosActualizar').hide();"); //Cierra el dialogo            
            req = null;

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
/**
 * Método que permite eliminar un registro en la base de datos
     * @param cd
 */
    public void eliminar(CarreraDocente cd) {

        try {
            cd.setEstadoDocente(false);
            servCarreraDocente.actulizarCarreraDocente(cd);
            MensajesFaces.informacion("Eliminado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }
    /**Método que permite crear un registro en la base de datos, si todo sale bien cierra el dialogo para crear caso contrario no
     * 
 */
    public void crearDocente() {
        try {
            
           
            if (servDocente.buscarDocenteporCedula(docente.getIdentificacion()) != null) {
            } else {
                docente.setIdPais(servPais.encontrarPais(idpaisOrigen));
                //s el docent es Ecuatoriano se valida la cédula
                if (docente.getTIdentificacion().equalsIgnoreCase("CEDULA") && docente.getIdPais().getNomPais().equalsIgnoreCase("ECUADOR")) {
                    if (ValidarCedula.validacionCedula(docente.getIdentificacion()) == true) {
                        servDocente.insertarDocente(docente);
                        idpaisOrigen = 0;                       
                        MensajesFaces.informacion("Docente Guardado", "Exitoso");
                    } else {
                        MensajesFaces.error("Error", "La Cedula es Incorrecta");
                    }
                } else {
                    servDocente.insertarDocente(docente);
                    idpaisOrigen = 0;
                    MensajesFaces.informacion("Docente Guardado", "Exitoso");
                }
            }

        } catch (Exception e) {
            MensajesFaces.error("Guardado", e.getMessage());
        }
      
    }
    /**Método que permite actualizar un registro en la base de datos, si todo sale bien cierra el dialogo para actualizar caso contrario no
 */
    public void actualizarDocente() {

        try {
            docente.setIdPais(servPais.encontrarPais(idpaisOrigen));
          
            // si el docente es ecuatoriano se valida el número de cedula
            if (docente.getTIdentificacion().equalsIgnoreCase("CEDULA") && docente.getIdPais().getNomPais().equalsIgnoreCase("ECUADOR")) {
                if (ValidarCedula.validacionCedula(docente.getIdentificacion()) == true) {
                    servDocente.actualizarDocente(docente);
                    idpaisOrigen = 0;
                    MensajesFaces.informacion("Actualizado", "Exitoso");
                } else {
                    MensajesFaces.error("Error", "La Cedula es Incorrecta");
                }
            } else {
                servDocente.actualizarDocente(docente);
                idpaisOrigen = 0;
                MensajesFaces.informacion("Actualizado", "Exitoso");
            }


        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
/**
 * Método que buscar un registro CarreraDocente por su id 
     * @param id
 */
    public void encontrarCarreraDocenteID(int id) {
        carreraDocente = servCarreraDocente.buscarCarreraDocenterporId(id);
        if (carreraDocente != null) {
            docente=servDocente.buscarDocenteporCedula(carreraDocente.getIdDocente().getIdentificacion());
            //nuevaCedula = docente.getIdentificacion();
            idFacultad=carreraDocente.getIdCarrera().getIdFacultad().getIdFacultad();
            idCarrera = carreraDocente.getIdCarrera().getIdCarrera();
            idDocente = carreraDocente.getIdDocente().getIdDocente();
            idpaisOrigen=carreraDocente.getIdDocente().getIdPais().getIdPais();
            //idFacultad = docente.getIdCarrera().getIdFacultad().getIdFacultad();
            estadoComboBox=Constantes.INACTIVO;
            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            carreraDocente = new CarreraDocente();
            docente= new Docente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }
    }
    
    
/**Metodo que permite cerrar el cualquier dialogo
 */
    public void cerrar() {

        docente = new Docente();
        carreraDocente=new CarreraDocente();
        idDocente=0;
        idCarrera = 0;
        idFacultad = 0;
        identificacion=Constantes.VACIO;
        estadoAgregar=Constantes.INACTIVO;
        estadoOpcionesDiscapacidad=Constantes.INACTIVO;
        estadoAgregarIes=Constantes.INACTIVO;

    }
/**
 * Método que devuelve todos los docentes de Carrera cuyo  de la base de datos
     * @param au
     * @param idPeriodo
     * @return listDocentes
 */
    public List<CarreraDocente> listaCarreraDocente(Authorities au,int idPeriodo) {
          if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR")) {
            listDocentes = servCarreraDocente.listarDocenteCarreraporApellido(idPeriodo);
        } else {
            listDocentes = servCarreraDocente.listarDocentesCarreraTodos(au.getUsername().getIdCarrera().getIdCarrera(),idPeriodo);
       }
        return listDocentes;
    }
    /**
 * Método que devuelve todos los docentes viegentes de una carrera de la base de datos
     * @param au
     * @param idPeriodo
     * @return listDocentes
 */
    public List<CarreraDocente> listarDocenteCarreraVigentes(Authorities au,int idPeriodo) {
        if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR")) {
            listDocentes = servCarreraDocente.listarDocenteCarreraporApellido(idPeriodo);
        } else {
            listDocentes = servCarreraDocente.listarDocenteCarreraVigente(au.getUsername().getIdCarrera().getIdCarrera(),idPeriodo);
       }
        return listDocentes;
    }

    /**Busca un docente por su número de identificación
     * 
     */
    public void buscarDocenteIdentificacion()
    {
      
       docente= servDocente.buscarDocenteporCedula(identificacion);
        if(docente!=null)
        {
            MensajesFaces.informacion("Docente", "Encontrado"); 
            identificacion=Constantes.VACIO;
            estadoAgregar=Constantes.INACTIVO;
            estadoAgregarIes=Constantes.ACTIVO;
            estadoComboBox=Constantes.INACTIVO;
        }
        else{
            docente=new Docente();
            docente.setIdentificacion(identificacion);
            identificacion=Constantes.VACIO;
            estadoAgregar=Constantes.ACTIVO;
            estadoOpcionesDiscapacidad=Constantes.INACTIVO;
             estadoAgregarIes=Constantes.ACTIVO;
             estadoComboBox=Constantes.INACTIVO;
             MensajesFaces.informacion("Docente", "No Encontrado");
            }
       
    }
     public void habilitarDiscapacidad(AjaxBehaviorEvent ent) {
        if (docente.getDiscapacidad()== true) {
            estadoOpcionesDiscapacidad = Constantes.ACTIVO;
        } else {

            estadoOpcionesDiscapacidad = Constantes.INACTIVO;
        }
    }
     

/**Devuelve un objeto de tipo CarreraDocente
     * @return carreraDocente
 */
    public CarreraDocente getCarreraDocente() {
        return carreraDocente;
    }

    public void setCarreraDocente(CarreraDocente carreraDocente) {
        this.carreraDocente = carreraDocente;
    }
/**Devuelve el id de la facultad
     * @return idFacultad
 */
    public int getIdFacultad() {
        return idFacultad;
    }
/**Modifica el id de la facultad
     * @param  idFacultad
 */
    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }
/**Devuelve el id de la carrera
     * @return idCarrera
 */
    public int getIdCarrera() {
        return idCarrera;
    }
/**Modifica el id de la carrera
     * @param  idCarrera
 */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }
/**Devuelve el id del docente
     * @return idDocente
 */
    public int getIdDocente() {
        return idDocente;
    }
/**Modifica el id del docente
     * @param  idDocente
 */
    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
/**Devuelve el estado del combo box
     * @return estadoComboBox
 */
    public String getEstadoComboBox() {
        return estadoComboBox;
    }
    /**Modifica el estado del combo box
     * @param  estadoComboBox
 */

    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }
/**Devuelve los items de la facultad
     * @return itemsFacultad
 */
    public List<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }
/**Modifica los items de la facultad
     * @param  itemsFacultad
 */
    public void setItemsFacultad(List<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }
/**Devuelve los items de la carrera
     * @return itemsCarrera
 */
    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }
/**Modifica los items de la carrera
     * @param  itemsCarrera
 */
    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEstadoAgregar() {
        return estadoAgregar;
    }

    public void setEstadoAgregar(String estadoAgregar) {
        this.estadoAgregar = estadoAgregar;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public int getIdpaisOrigen() {
        return idpaisOrigen;
    }

    public void setIdpaisOrigen(int idpaisOrigen) {
        this.idpaisOrigen = idpaisOrigen;
    }

    public String getEstadoOpcionesDiscapacidad() {
        return estadoOpcionesDiscapacidad;
    }

    public void setEstadoOpcionesDiscapacidad(String estadoOpcionesDiscapacidad) {
        this.estadoOpcionesDiscapacidad = estadoOpcionesDiscapacidad;
    }

    public String getEstadoAgregarIes() {
        return estadoAgregarIes;
    }

    public void setEstadoAgregarIes(String estadoAgregarIes) {
        this.estadoAgregarIes = estadoAgregarIes;
    }


  
    
}
