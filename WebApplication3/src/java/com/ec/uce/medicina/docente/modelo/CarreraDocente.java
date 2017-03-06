/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaración de la clase CarreraDocente
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "carrera_docente", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_carrera", "id_docente","id_periodo"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarreraDocente.findAll", query = "SELECT c FROM CarreraDocente c"),
    @NamedQuery(name = "CarreraDocente.findByIdCarreraDocente", query = "SELECT c FROM CarreraDocente c WHERE c.idCarreraDocente = :idCarreraDocente"),
    @NamedQuery(name = "CarreraDocente.findByFechaIngresoIes", query = "SELECT c FROM CarreraDocente c WHERE c.fechaIngresoIes = :fechaIngresoIes"),
    @NamedQuery(name = "CarreraDocente.findByFechaSalidaIes", query = "SELECT c FROM CarreraDocente c WHERE c.fechaSalidaIes = :fechaSalidaIes"),
    @NamedQuery(name = "CarreraDocente.findByEstadoDocente", query = "SELECT c FROM CarreraDocente c WHERE c.estadoDocente = :estadoDocente"),
    @NamedQuery(name = "CarreraDocente.findByObservaciones", query = "SELECT c FROM CarreraDocente c WHERE c.observaciones = :observaciones")})
public class CarreraDocente implements Serializable {
//Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Atributo entero para el id generado automáticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carrera_docente", nullable = false)
    private Integer idCarreraDocente;
    /**
     * Atributo para la fecha en la cual un docente ingreso a la IES
     */
    @Column(name = "fecha_ingreso_ies")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoIes;
    /**
     * Atributo para la fecha en la cual un docente salió de la IES
     */
    @Column(name = "fecha_salida_ies")
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaIes;
    /**
     * Atributo que indica el estado de un docente true: si el docente esta
     * activo false si el docente esta inactivo
     */
    @Basic(optional = false)
    @NotNull(message = "El estado del docente es una campo obligatorio")
    @Column(name = "estado_docente", nullable = false)
    private boolean estadoDocente;
    //Atributo que indica cualquier observación al ingresar los datos del docente
    @Size(max = 500)
    @Column(name = "observaciones", length = 500)
    private String observaciones;
    //Instacia de la clase Carrera 

    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    @ManyToOne(optional = false)
    private Carrera idCarrera;
    //Instancia de la clase Docente
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    @ManyToOne(optional = false)
    private Docente idDocente;
        //Instancia de la clase Periodo
    @JoinColumn(name = "id_periodo", referencedColumnName = "id_periodo", nullable = false)
    @ManyToOne(optional = false)
    private Periodo idPeriodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarreraDocente")
    private List<CargoDirectivo> cargoDirectivoList;
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarreraDocente")
    private List<RelacionLaboral> relacionLaboralList;

    /**
     * Constructor por defecto
     */
    public CarreraDocente() {
    }

    /**
     * Constructor que recibe un parametro
     */

    /**
     * Constructor que recibe un parametro
     *
     * @param idCarreraDocente
     */
    public CarreraDocente(Integer idCarreraDocente) {
        this.idCarreraDocente = idCarreraDocente;
    }

    /**
     * Constructor que recibe 2 parametros
     *
     * @param idCarreraDocente
     */

    /**
     * Constructor que recibe 2 parametros
     *
     * @param idCarreraDocente
     * @param estadoDocente
     */
    public CarreraDocente(Integer idCarreraDocente, boolean estadoDocente) {
        this.idCarreraDocente = idCarreraDocente;
        this.estadoDocente = estadoDocente;
    }

    /**
     * Retorna el id de la Carrera Docente
     *
     * @return idCarreraDocente
     */
    public Integer getIdCarreraDocente() {
        return idCarreraDocente;
    }

    /**
     * Modifica el id de la Carrera Docente
     *
     * @param idCarreraDocente
     */
    public void setIdCarreraDocente(Integer idCarreraDocente) {
        this.idCarreraDocente = idCarreraDocente;
    }

    /**
     * Retorna la fecha en la cual el docente ingreso a la Carrera
     *
     * @return fechaIngresoIes
     */
    public Date getFechaIngresoIes() {
        return fechaIngresoIes;
    }

    /**
     * Modifica la fecha en la cual el docente ingreso a la Carrera
     *
     * @param fechaIngresoIes
     */

    public void setFechaIngresoIes(Date fechaIngresoIes) {
        this.fechaIngresoIes = fechaIngresoIes;
    }

    /**
     * Retorna la fecha en la cual el docente salió a la Carrera
     *
     * @return fechaSalidaIes
     */

    public Date getFechaSalidaIes() {
        return fechaSalidaIes;
    }

    /**
     * Modifica la fecha en la cual el docente salió a la Carrera
     *
     * @param fechaSalidaIes
     */

    public void setFechaSalidaIes(Date fechaSalidaIes) {
        this.fechaSalidaIes = fechaSalidaIes;
    }

    /**
     * Retorna el estado del Docente en la Carrera
     *
     * @return  <ul>
     * <li>true: si el docente esta activo en la Carrera</li>
     * <li>false: si el docente esta inactivo en la Carrera</li>
     * </ul>
     */
    public boolean getEstadoDocente() {
        return estadoDocente;
    }

    /**
     * Modifica el estado del Docente en la Carrera
     */

    /**
     * Modifica el estado del Docente en la Carrera
     *
     * @param estadoDocente
     */
    public void setEstadoDocente(boolean estadoDocente) {
        this.estadoDocente = estadoDocente;
    }

    /**
     * Retorna alguna observacion al ingresar un Docente en una Carrera
     *
     * @return observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Modifica alguna observacion al ingresar un Docente en una Carrera
     *
     * @param observaciones
     */

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * Retorno un objeto de tipo Carrera
     *
     * @return idCarrera
     */
    public Carrera getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica un objeto de tipo Carrera
     *
     * @param idCarrera
     */

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Retorna un objeto de tipo Docente
     *
     * @return idDocente
     */
    public Docente getIdDocente() {
        return idDocente;
    }

    /**
     * Modifica un objeto de tipo Docente
     *
     * @param idDocente
     */

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    /**
     * Retorna un objeto de tipo período
     *
     * @return idPeriodo
     */
    public Periodo getIdPeriodo() {
        return idPeriodo;
    }

    /**
     * Permite modificar el valor del obejto Periodo
     *
     * @param idPeriodo
     */
    public void setIdPeriodo(Periodo idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    
     @XmlTransient
    public List<RelacionLaboral> getRelacionLaboralList() {
        return relacionLaboralList;
    }

    public void setRelacionLaboralList(List<RelacionLaboral> relacionLaboralList) {
        this.relacionLaboralList = relacionLaboralList;
    }
    
    @XmlTransient
    public List<CargoDirectivo> getCargoDirectivoList() {
        return cargoDirectivoList;
    }

    public void setCargoDirectivoList(List<CargoDirectivo> cargoDirectivoList) {
        this.cargoDirectivoList = cargoDirectivoList;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarreraDocente != null ? idCarreraDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarreraDocente)) {
            return false;
        }
        CarreraDocente other = (CarreraDocente) object;
        if ((this.idCarreraDocente == null && other.idCarreraDocente != null) || (this.idCarreraDocente != null && !this.idCarreraDocente.equals(other.idCarreraDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.CarreraDocente[ idCarreraDocente=" + idCarreraDocente + " ]";
    }

}
