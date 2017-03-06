/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Declaracion de la clase Cardodirectivo
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "cargo_directivo", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CargoDirectivo.findAll", query = "SELECT c FROM CargoDirectivo c"),
    @NamedQuery(name = "CargoDirectivo.findByIdCargo", query = "SELECT c FROM CargoDirectivo c WHERE c.idCargo = :idCargo"),
    @NamedQuery(name = "CargoDirectivo.findByTipoCargo", query = "SELECT c FROM CargoDirectivo c WHERE c.tipoCargo = :tipoCargo"),
    @NamedQuery(name = "CargoDirectivo.findByNumDocumento", query = "SELECT c FROM CargoDirectivo c WHERE c.numDocumento = :numDocumento"),
    @NamedQuery(name = "CargoDirectivo.findByFechaIniCargo", query = "SELECT c FROM CargoDirectivo c WHERE c.fechaIniCargo = :fechaIniCargo"),
    @NamedQuery(name = "CargoDirectivo.findByFechaFinCargo", query = "SELECT c FROM CargoDirectivo c WHERE c.fechaFinCargo = :fechaFinCargo"),
    @NamedQuery(name = "CargoDirectivo.findByObsCargoDirectivo", query = "SELECT c FROM CargoDirectivo c WHERE c.obsCargoDirectivo = :obsCargoDirectivo"),
    @NamedQuery(name = "CargoDirectivo.findByObservaciones", query = "SELECT c FROM CargoDirectivo c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "CargoDirectivo.findByNombreDocuCargo", query = "SELECT c FROM CargoDirectivo c WHERE c.nombreDocCargo = :nombreDocCargo"),
    @NamedQuery(name = "CargoDirectivo.findByUrlDocuCargo", query = "SELECT c FROM CargoDirectivo c WHERE c.idDocCargo = :idDoCargo")})
public class CargoDirectivo implements Serializable {
//Atributos

    private static final long serialVersionUID = 1L;
    //Id  generado automaticamente para identificar a un objeto de tipo CargoDirectivo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cargo", nullable = false)
    private Integer idCargo;
    /**
     * Tipo de cargo que ocupa el docente
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un tipo de cargo")
    @Size(min = 1, max = 100)
    @Column(name = "tipo_cargo", nullable = false, length = 100)
    private String tipoCargo;
    /**
     * Número de documento
     */
    @Basic(optional = false)
    @NotNull(message = "El número de documento es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "num_documento", nullable = false, length = 100)
    private String numDocumento;
    /**
     * Fecha de inicio en la cual ocupo el cargo
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    @Column(name = "fecha_ini_cargo", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIniCargo;
    //Fecha de fin en la cual termino el caro
    @Column(name = "fecha_fin_cargo")
    @Temporal(TemporalType.DATE)
    private Date fechaFinCargo;
    /**
     * Observaciones al ingresar los datos del cargo directivo.
     */
    @Size(max = 200)

    @Column(name = "obs_cargo_directivo", length = 200)
    private String obsCargoDirectivo;
    /**
     * Observaciones al ingresar los datos del cargo directivo.
     */
    @Size(max = 200)
    @Column(name = "observaciones", length = 200)
    private String observaciones;
    /**
     * Nombre del documento pdf que sirve como evidencia
     */
    @Size(max = 150)
    @Column(name = "nombre_doc_cargo", length = 150)
    private String nombreDocCargo;
    /**
     * url del docuemhnto pdf almacenado en Disco.
     */
    @Size(max = 200)
    @Column(name = "id_doc_cargo", length = 200)
    private String idDocCargo;
    //Instancia de la clase Carrera Docente
   @JoinColumn(name = "id_carrera_docente", referencedColumnName = "id_carrera_docente", nullable = false)
    @ManyToOne(optional = false)
    private CarreraDocente idCarreraDocente;


    /**
     * constructor por defecto
     */
    public CargoDirectivo() {
    }

    /**
     * Constructor de la clase CargoDirectivo que recibe un parametro
     *
     * @param idCargo
     */
    public CargoDirectivo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    /**
     * Constructor de la clase CargoDirectivo que recibe un parametro
     *
     * @param idCargo
     */
    /**
     * Constructor de la clase CargoDirectivo que recibe 4 parametro
     *
     * @param idCargo
     * @param tipoCargo
     * @param numDocumento
     * @param fechaIniCargo
     */
    public CargoDirectivo(Integer idCargo, String tipoCargo, String numDocumento, Date fechaIniCargo) {
        this.idCargo = idCargo;
        this.tipoCargo = tipoCargo;
        this.numDocumento = numDocumento;
        this.fechaIniCargo = fechaIniCargo;
    }

    /**
     * Retorna el id del Cargodierectivo
     *
     * @return idCargo
     */
    public Integer getIdCargo() {
        return idCargo;
    }

    /**
     * Permite modificar el valor del id del CargoDirectivo
     *
     * @param idCargo
     */
    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    /**
     * Retorna el valor del tipo de Cargo Directivo
     *
     * @return tipoCargo
     */
    public String getTipoCargo() {
        return tipoCargo;
    }

    /**
     * Permite modificar el valor del tipo de Cargo Directivo
     *
     * @param tipoCargo
     */
    public void setTipoCargo(String tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    /**
     * Retorna el número de documento del Cargo Directivo
     *
     * @return numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * Permite modificar el valor del número de documento
     *
     * @param numDocumento
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * Retorna la fecha de inicio en la que ocupo el cargo Directivo
     *
     * @return fechaIniCargo
     */
    public Date getFechaIniCargo() {
        return fechaIniCargo;
    }

    /**
     * Permite modificar la fecha en la cual inició el cargo Directivo
     *
     * @param fechaIniCargo
     */
    public void setFechaIniCargo(Date fechaIniCargo) {
        this.fechaIniCargo = fechaIniCargo;
    }

    /**
     * Retorna la fecha de Fin del cargo Directivo
     *
     * @return fechaFinCargo
     */
    public Date getFechaFinCargo() {
        return fechaFinCargo;
    }

    /**
     * permite modificar la fecha en la cual finalizó su cargo Directivo
     *
     * @param fechaFinCargo
     */
    public void setFechaFinCargo(Date fechaFinCargo) {
        this.fechaFinCargo = fechaFinCargo;
    }

    /**
     * Retorna algun tipo de observaciones
     *
     * @return obsCargoDirectivo
     */
    public String getObsCargoDirectivo() {
        return obsCargoDirectivo;
    }

    /**
     * Permite modificar las observaciones del cargoDirectivo
     *
     * @param obsCargoDirectivo
     */
    public void setObsCargoDirectivo(String obsCargoDirectivo) {
        this.obsCargoDirectivo = obsCargoDirectivo;
    }

    /**
     * Retorna algun tipo de observaciones
     *
     * @return observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * Permite modificar las observaciones del cargoDirectivo
     *
     * @param observaciones
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
/**Devuelve el nombre del documento que se encuentra en el repositorio
     * @return nombreDocCargo 
 */
    public String getNombreDocCargo() {
        return nombreDocCargo;
    }
/**Modifica el nombre del documento que se encuentra en el repositorio
     * @param  nombreDocCargo 
 */
    public void setNombreDocCargo(String nombreDocCargo) {
        this.nombreDocCargo = nombreDocCargo;
    }
/**Devuelve el nombre el id del documento que se encuentra en el repositorio
     * @return idDocCargo 
 */
    public String getIdDocCargo() {
        return idDocCargo;
    }
/**Modifica el nombre el id del documento que se encuentra en el repositorio
     * @param  idDocCargo 
 */
    public void setIdDocCargo(String idDocCargo) {
        this.idDocCargo = idDocCargo;
    } 
       /**
     * Retorna un objeto de tipo docente
     *
     * @return idCarreraDocente
     */
public CarreraDocente getIdCarreraDocente() {
        return idCarreraDocente;
    }
/**
     * Permite modificar el valor del Objeto docente
     *
     * @param idCarreraDocente
     */
    public void setIdCarreraDocente(CarreraDocente idCarreraDocente) {
        this.idCarreraDocente = idCarreraDocente;
    }

 
   

    
    


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCargo != null ? idCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CargoDirectivo)) {
            return false;
        }
        CargoDirectivo other = (CargoDirectivo) object;
        if ((this.idCargo == null && other.idCargo != null) || (this.idCargo != null && !this.idCargo.equals(other.idCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.CargoDirectivo[ idCargo=" + idCargo + " ]";
    }

}
