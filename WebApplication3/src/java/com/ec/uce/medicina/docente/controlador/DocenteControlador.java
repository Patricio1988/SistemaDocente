/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.ec.uce.medicina.docente.util.ValidarCedula;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.PaisOrigenServicio;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean DocenteControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@SuppressWarnings("deprecation")
@ViewScoped
@ManagedBean

public class DocenteControlador {

      //Atributo list de docentes
    private List<Docente> listDocentes;
    //Declaración objeto tipo Docente
    private Docente docente;
     //Atributo id país origen
    private int idpaisOrigen;
    //Servicios
    @EJB
    private DocenteServicio serDocente;
    @EJB
    private PaisOrigenServicio servPais;
   //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
      //Atributo estado del combo box
    private String estadoComboBox;
    //Atributo estado para las habilitar las opciones cuando un docente tiene discapacidad
    private String estadoOpcionesDiscapacidad;
    /**Atributo para almacenar los tipos de discapcidad de un docente  como una estructura clave valor
     */
    private Map<String, String> tipodiscapacidades;
     /**Atributo para almacenar el sexo de un docente  como una estructura clave valor
     */
    private Map<String, String> sexos;
     /**Atributo para almacenar el tipo de identificación de un docente  como una estructura clave valor
     */
    private Map<String, String> tpIdentificacion;
//    /**Atributo para la identificacio del docente*/
//    private String identificacion;
//     /**Atributo para cambiar el esatdo de los txt al agregar un docente*/
//    private String estadoAgregar;
//
    /**
     * Constructor por defecto
     */
    public DocenteControlador() {
    }
//Inicialización de variables
    @PostConstruct
    public void inicializar() {
 //Instancia de objetos
  docente = new Docente();
        this.estadoComboBox = Constantes.INACTIVO;
        estadoOpcionesDiscapacidad = Constantes.INACTIVO;
        tipodiscapacidades = Tablas.tablaTipoDiscapacida();
        sexos = Tablas.retornarsexos();
        tpIdentificacion = Tablas.retornarTipoIdentificacion();
       // estadoAgregar=Constantes.INACTIVO;

    }

    public void habilitarDiscapacidad(AjaxBehaviorEvent ent) {

        if (docente.getDiscapacidad()== true) {
            estadoOpcionesDiscapacidad = Constantes.ACTIVO;
        } else {

            estadoOpcionesDiscapacidad = Constantes.INACTIVO;
        }
    }

/**Método que permite crear un registro en la base de datos, si todo sale bien cierra el dialogo para crear caso contrario no
     * 
 */
    public void crear() {
        try {
            
            req = RequestContext.getCurrentInstance();
            if (serDocente.buscarDocenteporCedula(docente.getIdentificacion()) != null) {
                MensajesFaces.informacion("El docente", "Ya Existe");
            } else {
           
                docente.setIdPais(servPais.encontrarPais(idpaisOrigen));
                //s el docent es Ecuatoriano se valida la cédula
                if (docente.getTIdentificacion().equalsIgnoreCase("CEDULA") && docente.getIdPais().getNomPais().equalsIgnoreCase("ECUADOR")) {
                    if (ValidarCedula.validacionCedula(docente.getIdentificacion()) == true) {
                        serDocente.insertarDocente(docente);
                        req.execute("PF('dialogoDocenteAgregar').hide();"); //Cierra el dialogo
                        docente = new Docente();
                        idpaisOrigen = 0;                       
                        MensajesFaces.informacion("Guardado", "Exitoso");
                    } else {
                        MensajesFaces.error("Error", "La Cedula es Incorrecta");
                    }
                } else {
                    serDocente.insertarDocente(docente);
                    req.execute("PF('dialogoDocenteAgregar').hide();");//Cierra el dialogo
                    docente = new Docente();
                    idpaisOrigen = 0;
                    MensajesFaces.informacion("Guardado", "Exitoso");
                }
            }
            req = null;

        } catch (Exception e) {
            MensajesFaces.error("Guardado", e.getMessage());
        }
      
    }
/**Método que permite actualizar un registro en la base de datos, si todo sale bien cierra el dialogo para actualizar caso contrario no
 */
    public void actualizar() {

        try {
            docente.setIdPais(servPais.encontrarPais(idpaisOrigen));
            req = RequestContext.getCurrentInstance();
            // si el docente es ecuatoriano se valida el número de cedula
            if (docente.getTIdentificacion().equalsIgnoreCase("CEDULA") && docente.getIdPais().getNomPais().equalsIgnoreCase("ECUADOR")) {
                if (ValidarCedula.validacionCedula(docente.getIdentificacion()) == true) {
                    serDocente.actualizarDocente(docente);
                    req.execute("PF('dialogoDatosActualizar').hide();");// se cierra el dialogo
                    docente = new Docente();
                    idpaisOrigen = 0;
                    MensajesFaces.informacion("Actualizado", "Exitoso");
                } else {
                    MensajesFaces.error("Error", "La Cedula es Incorrecta");
                }
            } else {
                serDocente.actualizarDocente(docente);
                req.execute("PF('dialogoDatosActualizar').hide();");
                 docente = new Docente();
                idpaisOrigen = 0;
                MensajesFaces.informacion("Actualizado", "Exitoso");
            }

            req = null;

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
    /**Método que encuentra un docente por su id
     * @param id
     */
    public void encontrarDocenteID(int id) {
        docente = serDocente.encontrarDocente(id);
        if (docente != null) {
            idpaisOrigen = docente.getIdPais().getIdPais();
            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            docente = new Docente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }
    }
//    /**Busca un docente por su número de identificación*/
//    public void buscarDocenteIdentificacion()
//    {
//       docente= serDocente.buscarDocenteporCedula(identificacion);
//        if(docente!=null)
//        {
//            MensajesFaces.informacion("Docente", "Encontrado");
//        }
//        else{
//            estadoAgregar=Constantes.ACTIVO;
//             MensajesFaces.informacion("Docente", "No Encontrado");
//            }
//    }
    
//Método que permite cerrar
    public void cerrar() {

        docente = new Docente();
       // estadoAgregar=Constantes.INACTIVO;
       // identificacion=Constantes.VACIO;
        idpaisOrigen = 0;

    }

    public List<Docente> listaDocente() {
        listDocentes = serDocente.listaDocentesTodosporApellido();

        return listDocentes;
    }


//GET Y SET
    /**Devuelve los tipos de identificación
     * @return tpIdentificacion
     */
    public Map<String, String> getTpIdentificacion() {
        return tpIdentificacion;
    }
 /**Modifica los tipos de identificación
     * @param  tpIdentificacion
     */
    public void setTpIdentificacion(Map<String, String> tpIdentificacion) {
        this.tpIdentificacion = tpIdentificacion;
    }
 /**Devuelve el estado para habilitar las opciones cuando un docente tiene discapacidad
     * @return estadoOpcionesDiscapacidad
     */
    public String getEstadoOpcionesDiscapacidad() {
        return estadoOpcionesDiscapacidad;
    }
 /**Modifica el estado para habilitar las opciones cuando un docente tiene discapacidad
     * @param  estadoOpcionesDiscapacidad
     */
    public void setEstadoOpcionesDiscapacidad(String estadoOpcionesDiscapacidad) {
        this.estadoOpcionesDiscapacidad = estadoOpcionesDiscapacidad;
    }
 /**Devuelve los tipos de sexo de un docente
     * @return sexos
     */
    public Map<String, String> getSexos() {
        return sexos;
    }
 /**Modifica los tipos de sexo de un docente
     * @param  sexos
     */
    public void setSexos(Map<String, String> sexos) {
        this.sexos = sexos;
    }
 /**Devuelve los tipos de discapacidad 
     * @return tipodiscapacidades
     */
    public Map<String, String> getTipodiscapacidades() {
        return tipodiscapacidades;
    }
/**Modifica los tipos de discapacidad 
     * @param  tipodiscapacidades
     */
    public void setTipodiscapacidades(Map<String, String> tipodiscapacidades) {
        this.tipodiscapacidades = tipodiscapacidades;
    }

/**Retorna un objeto de tipoDocente
     * @return docente
 */
    public Docente getDocente() {
        return docente;
    }
/**Modifica un objeto de tipoDocente
     * @param  docente
 */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
/**Devuelve el id del país
     * @return idpaisOrigen
 */
    public int getIdpaisOrigen() {
        return idpaisOrigen;
    }
/**Modifica el id del país
     * @param  idpaisOrigen
 */
    public void setIdpaisOrigen(int idpaisOrigen) {
        this.idpaisOrigen = idpaisOrigen;
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

//    public String getIdentificacion() {
//        return identificacion;
//    }
//
//    public void setIdentificacion(String identificacion) {
//        this.identificacion = identificacion;
//    }
//
//    public String getEstadoAgregar() {
//        return estadoAgregar;
//    }
//
//    public void setEstadoAgregar(String estadoAgregar) {
//        this.estadoAgregar = estadoAgregar;
//    }
    

}
