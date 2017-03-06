/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Actividad;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.Materia;
import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Periodo;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.ActividadServicio;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.DocenteContratoMateriaServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.MallaServicio;
import com.uce.uce.medicina.docente.servicio.MateriaServicio;
import com.uce.uce.medicina.docente.servicio.PeriodoServicio;
import com.uce.uce.medicina.docente.servicio.RelacionLaboralServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean CargaHorariaControlador que sera manejada por jsf
 * de ámbito ViewScoped que permitira realizar las operaciones en las paginas
 * xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class CargaHorariaControlador {

    /**
     * Atributo id del docente
     */
    private int idCarreraDocente;
    /**
     * Declaración de un objeto de tipo Docente
     */
    private CarreraDocente carreraDocente;
    /**
     * Declaración de un objeto de tipo Malla
     */
    /**
     * Atributo id de la Malla
     */
    private int idMalla;
    /**
     * Declaración del objeto de tipo Malla
     */
    private Malla malla;

    /**
     * Atributo id de la Relacion Laboral
     */
    private int idRelacionLaboral;
    /**
     * Declaracion del objeto Relacion laboral
     */
    private RelacionLaboral relacionlaboral;
    // Atributo del id de la Materia

    private int idMateria;
    //Declaración del objeto de tipo Materia
    private Materia materia;
    /**
     * Atributo que obtiene el id de una actividad
     */
    private int idActividad;
    //Declaracion de un objeto de tipo Actividad
    Actividad actividad;
    //Atributo que obtiene el id del período
    private int idCarrera;
    //Declaracion de un objeto de tipo Periodo

    private Periodo periodo;
    //Atributo estado del boton para seleccionar la malla
    private String estadoBoton;
    //Declaración del objeto de tipo docentesMallaContratoMateria
    private DocentesMallaContratoMateria docentesMallaContratoMateria;
    //Servicios
    @EJB
    private DocenteContratoMateriaServicio servDocentesMallaContratoMateria;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private RelacionLaboralServicio servrelacionLaboral;
    @EJB
    private MateriaServicio servMateria;
    @EJB
    private ActividadServicio servActividad;
    @EJB
    private MallaServicio servMalla;
    @EJB
    private PeriodoServicio servPeriodo;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;

    //Atributo Número de cedula del docente
    private String numeroCedula;
    /**
     * Atribuo Request context para encapsular información sobre una solicitud
     * HTTP
     */
    private RequestContext req;
    //Atributo numero de horas totales por carga horaria
    private int numeroHorasTotales;

//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        carreraDocente = new CarreraDocente();//Instacia del objeto Docente
        relacionlaboral = new RelacionLaboral();//Instacia del objeto RelacionLaboral
        materia = new Materia();//Instacia del objeto Materia
        actividad = new Actividad();//Instacia del objeto Actividad
        periodo = new Periodo();//Instacia del objeto Periodo
        docentesMallaContratoMateria = new DocentesMallaContratoMateria();//Instacia del objeto DocentesMallaContratoMateria
        numeroCedula = Constantes.VACIO;//inicializamos el atributo numero de cedula
        idCarreraDocente = 0;
        idCarrera = 0;
        idMateria = 0;
        idMalla = 0;
        idRelacionLaboral = 0;
        this.estadoBoton = Constantes.INACTIVO;//inicializamos el estado del boton en inactivo

    }

    /**
     * Método que me permite crear un registro en la base de datos
     */
    public void crear() {
        try {
            docentesMallaContratoMateria.setIdActivida(servActividad.buscarActividad(idActividad));
            docentesMallaContratoMateria.setIdReLaboral(servrelacionLaboral.encontrarRelacionLaboral(idRelacionLaboral));

            docentesMallaContratoMateria.setIdMateria(servMateria.encontrarMateriaID(idMateria));
            //docentesMallaContratoMateria.setIdPeriodo(servPeriodo.encontrarPeriodoID(idPeriodo));

            servDocentesMallaContratoMateria.insertarDocente_contrato_materia(docentesMallaContratoMateria);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCargaAgregar').hide()");//Cerramos el dialogo
            req = null;
            idMateria = 0;
            materia = new Materia();
            docentesMallaContratoMateria = new DocentesMallaContratoMateria();
            MensajesFaces.informacion("Guardado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que habilitara el boton de seleccion de la malla si
     * actividad.getMateria()==true
     *
     * @param ent
     */
    public void getEstadoActividad(AjaxBehaviorEvent ent) {
        this.estadoBoton = Constantes.INACTIVO;
        actividad = servActividad.buscarActividad(idActividad);
        if (actividad == null || actividad.getMateria() == false) {
            this.estadoBoton = Constantes.INACTIVO;
            malla = new Malla();
            materia = new Materia();
            idMateria = 0;
            idMalla = 0;

        } else {
            this.estadoBoton = Constantes.ACTIVO;
        }
    }

    /**
     * Método para actualizar un registro en la base de datos, si la transaccion
     * se realiza sin proble cerramos el dialogo caso contrario no se cierra el
     * dialogo
     *
     * @throws java.lang.Exception
     */
    public void actualizar() throws Exception {

        try {
            docentesMallaContratoMateria.setIdActivida(servActividad.buscarActividad(idActividad));
            docentesMallaContratoMateria.setIdReLaboral(servrelacionLaboral.encontrarRelacionLaboral(idRelacionLaboral));

            docentesMallaContratoMateria.setIdMateria(servMateria.encontrarMateriaID(idMateria));
            servDocentesMallaContratoMateria.actualizarDocente_contrato_materia(docentesMallaContratoMateria);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCargaAc').hide()");//Cerramos el dialogo
            req = null;
            idMateria = 0;
            idActividad = 0;
            docentesMallaContratoMateria = new DocentesMallaContratoMateria();

            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }
    }

    /**
     * Método que elimina un registro de la base de datos
     *
     * @param con
     */
    public void eliminar(DocentesMallaContratoMateria con) {

        try {
            servDocentesMallaContratoMateria.eliminarDocente_contrato_materia(con);
            docentesMallaContratoMateria = new DocentesMallaContratoMateria();

            MensajesFaces.informacion("Eliminado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método para abrir el dialogo contrato cuando se selecciona un docente
     */
    public void abrirDialogoContrato() {
        try {
            carreraDocente.getIdDocente().getIdentificacion();
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoContrato').show()");
            req = null;
        } catch (NullPointerException ex) {
            MensajesFaces.informacion("Seleccione un Docente", "Primero");
        } catch (Exception e) {
            MensajesFaces.informacion("Error", e.getMessage());
        }

    }

    /**
     * Método para abrir el dialogo para crear un registro en la base de datos
     */
    public void abrirDialogoNuevo() {

        if (carreraDocente.getIdDocente().getIdentificacion() == null || relacionlaboral.getNumDocumento() == null) {
            MensajesFaces.informacion("Seleccione un Docente o una Malla", "Primero");

        } else {
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCargaAgregar').show()");
            req = null;
        }
    }

    /**
     * Método que cierra el dialogo
     */
    public void cerrar() {
        idMateria = 0;
        materia = new Materia();
        docentesMallaContratoMateria = new DocentesMallaContratoMateria();
    }

    /**
     * Método que encuentra un docente por su numero de cedula
     *
     * @param idCarreraDocente
     */
    public void recuperarDatosCarreraDocenteId(int idCarreraDocente) {
        carreraDocente = servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente);
        if (carreraDocente != null) {
            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();
            idCarrera=carreraDocente.getIdCarrera().getIdCarrera();        
            relacionlaboral = new RelacionLaboral();
            malla = new Malla();
            idRelacionLaboral=0; 
            numeroHorasTotales=0;
            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            numeroCedula = Constantes.VACIO;
            idCarrera = 0;
            idRelacionLaboral=0;
             numeroHorasTotales=0;
            carreraDocente = new CarreraDocente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }

    }

    /**
     * Método que recupera una lista de mallas
     *
     * @return servMalla.listarMalla()
     */
    public List<Malla> recuperarMallas() {

        return servMalla.listarMalla();
    }

    /**
     * Método que encuentra una malla por id
     *
     * @param id
     */
    public void encontrarMallaID(Object id) {
        malla = servMalla.encontrarMallaID(id);
        if (malla != null) {
            idMalla = malla.getIdMalla();
            MensajesFaces.informacion("Malla", "Encontrada");
        } else {
            malla = new Malla();
            MensajesFaces.informacion("Malla", "No encontrada");
        }
    }

    /**
     * Método que retorna una lista de Materia por el id de la malla
     *
     * @return servMateria.listarMateriaporMalla(idMalla)
     */
    public List<Materia> listarMateriaporMalla() {
        return servMateria.listarMateriaporMalla(idMalla);
    }

    /**
     * Método que retorna una lista de Contratos por docente
     *
     * @param idPeriodo
     * @return servrelacionLaboral.listaRelacionporDocente(numeroCedula)
     */
    public List<RelacionLaboral> listaContratosDocente(int idPeriodo) {

        return servrelacionLaboral.listaRelacionporDocente(numeroCedula, idCarrera, idPeriodo);
       

    }

    /**
     * Método que recupera los contratos por id
     *
     * @param id
     */
    public void recuperardatosContratoID(Object id) {
        relacionlaboral = servrelacionLaboral.encontrarRelacionLaboral(id);
        if (relacionlaboral != null) {
            idRelacionLaboral = relacionlaboral.getIdReLaboral();
            MensajesFaces.informacion("Documento", "Encontrada");
        } else {
            numeroCedula = Constantes.VACIO;
            carreraDocente = new CarreraDocente();
            MensajesFaces.informacion("Documento", "No encontrada");
        }

    }

    /**
     * Método que recupera la carga horaria por id
     *
     * @param id
     */
    public void buscarCargaporID(int id) {

        docentesMallaContratoMateria = servDocentesMallaContratoMateria.encontarDocente_contrato_materiaId(id);
        if (docentesMallaContratoMateria != null) {
            idActividad = docentesMallaContratoMateria.getIdActivida().getIdActivida();

            if (docentesMallaContratoMateria.getIdMateria() != null) {
                idMateria = docentesMallaContratoMateria.getIdMateria().getIdMateria();
                materia = servMateria.encontrarMateriaID(idMateria);
                idMalla = docentesMallaContratoMateria.getIdMateria().getIdMalla().getIdMalla();
                malla = servMalla.encontrarMallaID(idMalla);
            } else {
                materia = new Materia();
                malla = new Malla();
            }
            idRelacionLaboral = docentesMallaContratoMateria.getIdReLaboral().getIdReLaboral();
            // idPeriodo = docentesMallaContratoMateria.getIdPeriodo().getIdPeriodo();
            relacionlaboral = servrelacionLaboral.encontrarRelacionLaboral(idRelacionLaboral);
            listarMateriaporMalla();

            MensajesFaces.informacion("DocentesMallaContratoMateria", "Encontrada");
        } else {
            docentesMallaContratoMateria = new DocentesMallaContratoMateria();
            MensajesFaces.informacion("DocentesMallaContratoMateria", "No encontrada");
        }
    }

    /**
     * Método que encuentra una matería por id
     *
     * @param id
     */
    public void encontrarMateriaID(int id) {
        materia = servMateria.encontrarMateriaID(id);
        if (materia != null) {

            docentesMallaContratoMateria.setIdActivida(servActividad.buscarActividad(idActividad));
            docentesMallaContratoMateria.setIdMateria(servMateria.encontrarMateriaID(id));
            idMateria = materia.getIdMateria();
            id = 0;
            MensajesFaces.informacion("Materia Encontrada", "Encontrada");
        } else {
            MensajesFaces.informacion("Materia No encontrada", "No encontrada");
        }

    }

    /**
     * Método de retorna una lista de carga horaria por docente por periodo y
     * por carrera
     *
     * @param idPeriodo
     * @return lista
     */
    public List<DocentesMallaContratoMateria> listaCargaporDocente (int idPeriodo) {
        numeroHorasTotales = 0;

        List<DocentesMallaContratoMateria> lista = servDocentesMallaContratoMateria.listarCargaporDocente(numeroCedula, idPeriodo, idCarrera,idRelacionLaboral);
        for (int i = 0; i < lista.size(); i++) {
            //suma el numero de horas totales por carga horaria
            numeroHorasTotales += lista.get(i).getNumHorasAsignatura();
        }

        return lista;
    }

    /**
     * Retorna el número de horas totales por carga horaria
     *
     * @return numeroHorasTotales
     */
    public int getNumeroHorasTotales() {
        return numeroHorasTotales;
    }

    /**
     * Modifica el número de horas totales por carga horaria
     *
     * @param numeroHorasTotales
     */
    public void setNumeroHorasTotales(int numeroHorasTotales) {
        this.numeroHorasTotales = numeroHorasTotales;
    }

    /**
     * Retorna un objeto de tipo Malla
     *
     * @return malla
     */
    public Malla getMalla() {
        return malla;
    }

    /**
     * Modifica un objeto de tipo Malla
     *
     * @param malla
     */
    public void setMalla(Malla malla) {
        this.malla = malla;
    }

   
    

    /**
     * Retorna el id de la carrera del docente
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }


    /**
     * Modifica el id de la carrera al cual pertenece el docente
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Retorna un objeto de tipo Periodo
     *
     * @return periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * Modifica un objeto de tipo Periodo
     *
     * @param periodo
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * Retorna el id de la malla
     *
     * @return idMalla
     */
    public int getIdMalla() {
        return idMalla;
    }

    /**
     * Modifica el id de la malla
     *
     * @param idMalla
     */
    public void setIdMalla(int idMalla) {
        this.idMalla = idMalla;
    }

    /**
     * Método que retorna una lista de docentes
     *
     * @return servDocente.listarDocente()
     */
    public List<Docente> recuperartodosDocentes() {

        return servDocente.listarDocente();
    }
/**
     * Retorna el id de la docente de una carrera
     *
     * @return idCarreraDocente
     */
    public int getIdCarreraDocente() {
        return idCarreraDocente;
    }
    /**
     * Modifica el id del docente de una carrera
     *
     * @param idCarreraDocente
     */
    public void setIdCarreraDocente(int idCarreraDocente) {
        this.idCarreraDocente = idCarreraDocente;
    }
  /**
     * Retorna un objeto de tipo CarreraDocente
     *
     * @return carreraDocente
     */
    public CarreraDocente getCarreraDocente() {
        return carreraDocente;
    }
   /**
     * Modifica un objeto de tipo CarreraDocente
     *
     * @param carreraDocente
     */
    public void setCarreraDocente(CarreraDocente carreraDocente) {
        this.carreraDocente = carreraDocente;
    }

    /**
     * Retorna el id de la relacion laboral
     *
     * @return idRelacionLaboral
     */
    public int getIdRelacionLaboral() {
        return idRelacionLaboral;
    }

    /**
     * Modifica el id de la relacion laboral
     *
     * @param idRelacionLaboral
     */
    public void setIdRelacionLaboral(int idRelacionLaboral) {
        this.idRelacionLaboral = idRelacionLaboral;
    }

    /**
     * Retorna un objeto de tipo RelacionLaboral
     *
     * @return relacionlaboral
     */
    public RelacionLaboral getRelacionlaboral() {
        return relacionlaboral;
    }

    /**
     * Modifica un objeto de tipo RelacionLaboral
     *
     * @param relacionlaboral
     */
    public void setRelacionlaboral(RelacionLaboral relacionlaboral) {
        this.relacionlaboral = relacionlaboral;
    }

    /**
     * Retorna el id de la matería
     *
     * @return idMateria
     */
    public int getIdMateria() {
        return idMateria;
    }

    /**
     * Modifica el id de la matería
     *
     * @param idMateria
     */
    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    /**
     * Retorna un objeto de tipo Materia
     *
     * @return materia
     */
    public Materia getMateria() {
        return materia;
    }

    /**
     * Modificar un objeto de tipo Materia
     *
     * @param materia
     */
    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    /**
     * Retorna el id de la actividad
     *
     * @return idActividad
     */
    public int getIdActividad() {
        return idActividad;
    }

    /**
     * Modifica el id de la actividad
     *
     * @param idActividad
     */
    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    /**
     * Retorna un objeto de tipo actividad
     *
     * @return actividad
     */
    public Actividad getActividad() {
        return actividad;
    }

    /**
     * Modifica un objeto de tipo actividad
     *
     * @param actividad
     */
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    /**
     * Retorna el estado del boton de
     *
     * @return estadoBoton
     */
    public String getEstadoBoton() {
        return estadoBoton;
    }

    /**
     * Modifica el estado del boton de
     *
     * @param estadoBoton
     */
    public void setEstadoBoton(String estadoBoton) {
        this.estadoBoton = estadoBoton;
    }

    /**
     * Retorna un objeto de tipo DocentesMallaContratoMateria
     *
     * @return docentesMallaContratoMateria
     */
    public DocentesMallaContratoMateria getDocentesMallaContratoMateria() {
        return docentesMallaContratoMateria;
    }

    /**
     * Modifica un objeto de tipo DocentesMallaContratoMateria
     *
     * @param docentesMallaContratoMateria
     */
    public void setDocentesMallaContratoMateria(DocentesMallaContratoMateria docentesMallaContratoMateria) {
        this.docentesMallaContratoMateria = docentesMallaContratoMateria;
    }

    /**
     * Retorna el número de cedula del docente
     *
     * @return numeroCedula
     */
    public String getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * Modifica el número de cedula del docente
     *
     * @param numeroCedula
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

}
