/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.GeneradorReportes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.CarreraServicio;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 * Declaración del ManagedBean ReporteGeneralControlador1 que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class ReporteGeneralControlador1 {

    private static final long serialVersionUID = 1L;
    //Atributo para el nombre del reporte
    private String NOMBRE_REPORTE_JASPER = null;
    //Atributo opcion del reporte
    private int opcionReporte;
    //Atributo id de la carrera
    private int idCarrera;
    //Atributo id del período
    private int idPeriodo;
    //Atributo id de la actividad
    private int idActividad;
    //Atributo fecha de inicio
    private Date fechainicio;
    //Atributo estado del combo de la carrera
    private String estadoComboCarrera;
    //Atributo estado del combo del período
    private String estadoComboPeriodo;
    //Atributo estado de la fecha
    private String estadofecha;
    //Atributo estado del combo de la actividad
    private String estadoComboActividad;
    //Servicios
    @EJB
    private CarreraServicio servCarrera;
    //instancia del obejto carrera
    private Carrera carrera;
    @ManagedProperty("#{generadorReportes}")
    private GeneradorReportes generadorJasper;
    //Atributo nombre del reporte
    private String nombreReporte;
//Inicialización de variables

    @PostConstruct
    private void ini() {
        this.estadoComboCarrera = Constantes.INACTIVO;
        this.estadoComboPeriodo = Constantes.INACTIVO;
        this.estadofecha = Constantes.INACTIVO;
        this.estadoComboActividad = Constantes.INACTIVO;
    }

    /**
     * Método que segun la opcion del reporte habilitara los diferentes combos
     * de la vista para realizar el reporte
     *
     * @param ent
     */
    public void getCombo(AjaxBehaviorEvent ent) {
        //this.estadoCombo = Constantes.ACTIVO;
        switch (opcionReporte) {
            case 0:
                this.estadoComboCarrera = Constantes.INACTIVO;
                this.estadoComboPeriodo = Constantes.INACTIVO;
                this.estadofecha = Constantes.INACTIVO;
                this.estadoComboActividad = Constantes.INACTIVO;
                break;
            case 1:
            case 5:
            case 6:
                //PARA EL REPORTE DE DOCENTE,fORMACION Y Relacion laboral
                this.estadoComboCarrera = Constantes.ACTIVO;
                this.estadofecha = Constantes.INACTIVO;
                this.estadoComboPeriodo = Constantes.ACTIVO;
                this.estadoComboActividad = Constantes.INACTIVO;
                break;
            case 2:
            case 4:
                //PARA EL REPORTE DE CAPACITACION
                this.estadoComboCarrera = Constantes.ACTIVO;
                this.estadofecha = Constantes.ACTIVO;
                this.estadoComboPeriodo = Constantes.ACTIVO;
                this.estadoComboActividad = Constantes.INACTIVO;
                break;
            case 3:
            case 8:
                this.estadoComboCarrera = Constantes.ACTIVO;
                this.estadofecha = Constantes.INACTIVO;
                this.estadoComboPeriodo = Constantes.ACTIVO;
                this.estadoComboActividad = Constantes.INACTIVO;
                break;
            case 7:
                //PARA EL REPORTE DE CARGA HORARIA
                this.estadoComboCarrera = Constantes.ACTIVO;
                this.estadofecha = Constantes.INACTIVO;
                this.estadoComboPeriodo = Constantes.ACTIVO;
                this.estadoComboActividad = Constantes.ACTIVO;
                break;

        }

    }

    /**
     * Método que segun la opcion del reporte asignara el nombre del reporte que
     * se va a generar, si el usuario no es de rol ROLE_SUPERADMINISTRADOR el id
     * de la carrera se asignara el id con el que fue creado el usuario
     *
     * @param au
     */
    public void ImprimirXls1(Authorities au) {
        if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR") == false) {
            idCarrera = au.getUsername().getIdCarrera().getIdCarrera();

        }
        this.carrera = servCarrera.encontrarCarrera(this.idCarrera);
        if (carrera != null) {
            switch (opcionReporte) {
                case 0:
                    MensajesFaces.informacion("INFO", "Seleccione un tipo de Reporte");
                    break;
                case 1:
                case 5:
                case 6:

                    if (opcionReporte == 1) {
                        this.nombreReporte = "ReporteGeneralDocente-" + carrera.getNomCarrera();

                    }
                    if (opcionReporte == 5) {
                        this.nombreReporte = "ReporteGeneralFormacion-" + carrera.getNomCarrera();

                    }
                    if (opcionReporte == 6) {
                        this.nombreReporte = "ReporteGeneralContratos-" + carrera.getNomCarrera();

                    }
                    exportarXls1();
                    idCarrera = 0;
                    idPeriodo=0;

                    break;
                case 2:
                case 4:
                    if (opcionReporte == 2) {
                        this.nombreReporte = "ReporteGeneralCapacitacion-" + carrera.getNomCarrera();

                    }

                    if (opcionReporte == 4) {
                        this.nombreReporte = "ReporteGeneralCargoDirectivo-" + carrera.getNomCarrera();

                    }
                    exportarXls1();
                    idCarrera = 0;
                    fechainicio = null;
                    idPeriodo=0;
                    break;

                case 3:

                    this.nombreReporte = "ReporteGeneralCargoDirectivo-" + carrera.getNomCarrera();

                    exportarXls1();
                    idCarrera = 0;
                    idPeriodo = 0;
                    break;
                case 7:
                    this.nombreReporte = "ReporteGeneralActividad-" + carrera.getNomCarrera();
                    exportarXls1();
                    idCarrera = 0;
                    idActividad = 0;
                    idPeriodo = 0;
                    break;
                case 8:

                    this.nombreReporte = "ReporteGeneralCargaHoraria-" + carrera.getNomCarrera();

                    exportarXls1();
                    idCarrera = 0;
                    idPeriodo = 0;
                    break;
            }
        }

    }

    /**
     * Método para poder exporta el reporte en excel
     */
    public void exportarXls1() {
        @SuppressWarnings("rawtypes")
        Map parametros = new HashMap();
        try {
            switch (opcionReporte) {
                case 0:
                    MensajesFaces.informacion("INFO", "Seleccione un tipo de reporte");
                    break;
                case 1:
                case 5:
                case 6:

                    if (opcionReporte == 1) {
                        NOMBRE_REPORTE_JASPER = "ReporteDocenteCarrera.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);

                    }
                    if (opcionReporte == 5) {
                        NOMBRE_REPORTE_JASPER = "ReporteFormacionDocente.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);

                    }
                    if (opcionReporte == 6) {
                        NOMBRE_REPORTE_JASPER = "ReporteContratoDocente.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);

                    }
                    parametros.put("idCarrera", idCarrera);
                    parametros.put("idPeriodo", idPeriodo);
                    generadorJasper.setParametrosReporte(parametros);
                    generadorJasper.generarXLSX();

                    break;
                case 2:
                case 4:
                    if (opcionReporte == 2) {
                        NOMBRE_REPORTE_JASPER = "ReporteCapacitacionDocente.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                    }

                    if (opcionReporte == 4) {
                        NOMBRE_REPORTE_JASPER = "ReportPublicacionDocente.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                    }
                    parametros.put("idCarrera", idCarrera);
                    parametros.put("fechaInicio", fechainicio);
                    parametros.put("idPeriodo", idPeriodo);
                    generadorJasper.setParametrosReporte(parametros);
                    generadorJasper.generarXLSX();

                    break;
                case 3:
                case 8:
                    if (opcionReporte == 3) {

                        NOMBRE_REPORTE_JASPER = "ReporteCargoDirectivo.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                    }
                    if (opcionReporte == 8) {
                        NOMBRE_REPORTE_JASPER = "ReporteCargaDocente.jasper";
                        generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                        generadorJasper.setNombreReporte(nombreReporte);
                    }

                    parametros.put("idCarrera", idCarrera);
                    parametros.put("idPeriodo", idPeriodo);
                    generadorJasper.setParametrosReporte(parametros);
                    generadorJasper.generarXLSX();
                    break;

                case 7:
                    NOMBRE_REPORTE_JASPER = "ReporteActividadDocente.jasper";
                    generadorJasper.setNombreJasper(NOMBRE_REPORTE_JASPER);
                    generadorJasper.setNombreReporte(nombreReporte);
                    parametros.put("idCarrera", idCarrera);
                    parametros.put("idPeriodo", idPeriodo);
                    parametros.put("idActividad", idActividad);
                    generadorJasper.setParametrosReporte(parametros);
                    generadorJasper.generarXLSX();
                    break;

            }

        } catch (Exception e) {
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE", null);
        }

    }

    /**
     * Devuelve la opción del reporte
     *
     * @return opcionReporte
     */
    public int getOpcionReporte() {
        return opcionReporte;
    }

    /**
     * Modifica la opción del reporte
     *
     * @param opcionReporte
     */
    public void setOpcionReporte(int opcionReporte) {
        this.opcionReporte = opcionReporte;
    }

    /**
     * Devuelve el id de la carrera
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica el id de la carrera
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Devuelve un objeto de tipo Carrrera
     *
     * @return carrera
     */
    public Carrera getCarrera() {
        return carrera;
    }

    /**
     * Modifica un objeto de tipo Carrrera
     *
     * @param carrera
     */
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public GeneradorReportes getGeneradorJasper() {
        return generadorJasper;
    }

    public void setGeneradorJasper(GeneradorReportes generadorJasper) {
        this.generadorJasper = generadorJasper;
    }

    /**
     * Devuelve el nombre del reporte
     *
     * @return nombreReporte
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * Modifica el nombre del reporte
     *
     * @param nombreReporte
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    /**
     * Devuelve la fecha de inicio para el reporte
     *
     * @return fechainicio
     */
    public Date getFechainicio() {
        return fechainicio;
    }

    /**
     * Modifica la fecha de inicio para el reporte
     *
     * @param fechainicio
     */
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    /**
     * Devuelve el id del periodo
     *
     * @return idPeriodo
     */
    public int getIdPeriodo() {
        return idPeriodo;
    }

    /**
     * Modifica el id del periodo
     *
     * @param idPeriodo
     */
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    /**
     * Devuelve el id de la actividad
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
     * Devuelve el estado del combo de la carrera
     *
     * @return estadoComboCarrera
     */
    public String getEstadoComboCarrera() {
        return estadoComboCarrera;
    }

    /**
     * Modifica el estado del combo de la carrera
     *
     * @param estadoComboCarrera
     */
    public void setEstadoComboCarrera(String estadoComboCarrera) {
        this.estadoComboCarrera = estadoComboCarrera;
    }

    /**
     * Devuelve el estado del combo del período
     *
     * @return estadoComboPeriodo
     */
    public String getEstadoComboPeriodo() {
        return estadoComboPeriodo;
    }

    /**
     * Modifica el estado del combo del período
     *
     * @param estadoComboPeriodo
     */
    public void setEstadoComboPeriodo(String estadoComboPeriodo) {
        this.estadoComboPeriodo = estadoComboPeriodo;
    }

    /**
     * Devuelve el estado de txt fecha
     *
     * @return estadofecha
     */
    public String getEstadofecha() {
        return estadofecha;
    }

    /**
     * Modifica el estado de txt fecha
     *
     * @param estadofecha
     */
    public void setEstadofecha(String estadofecha) {
        this.estadofecha = estadofecha;
    }

    /**
     * Devuelve el estado del combo actividad
     *
     * @return estadoComboActividad
     */
    public String getEstadoComboActividad() {
        return estadoComboActividad;
    }

    /**
     * Modifica el estado del combo actividad
     *
     * @param estadoComboActividad
     */

    public void setEstadoComboActividad(String estadoComboActividad) {
        this.estadoComboActividad = estadoComboActividad;
    }

}
