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
 * Declaracion de la clase Capacitacion
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "capacitacion", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacitacion.findAll", query = "SELECT c FROM Capacitacion c"),
    @NamedQuery(name = "Capacitacion.findByIdCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.idCapacitacion = :idCapacitacion"),
    @NamedQuery(name = "Capacitacion.findByNomEvento", query = "SELECT c FROM Capacitacion c WHERE c.nomEvento = :nomEvento"),
    @NamedQuery(name = "Capacitacion.findByFechaInicioEvento", query = "SELECT c FROM Capacitacion c WHERE c.fechaInicioEvento = :fechaInicioEvento"),
    @NamedQuery(name = "Capacitacion.findByFechaFinEvento", query = "SELECT c FROM Capacitacion c WHERE c.fechaFinEvento = :fechaFinEvento"),
    @NamedQuery(name = "Capacitacion.findByNomInstitucion", query = "SELECT c FROM Capacitacion c WHERE c.nomInstitucion = :nomInstitucion"),
    @NamedQuery(name = "Capacitacion.findByNumHoras", query = "SELECT c FROM Capacitacion c WHERE c.numHoras = :numHoras"),
    @NamedQuery(name = "Capacitacion.findByTipoCurso", query = "SELECT c FROM Capacitacion c WHERE c.tipoCurso = :tipoCurso"),
    @NamedQuery(name = "Capacitacion.findByLocalidad", query = "SELECT c FROM Capacitacion c WHERE c.localidad = :localidad"),
    @NamedQuery(name = "Capacitacion.findByPaisActualizacion", query = "SELECT c FROM Capacitacion c WHERE c.paisActualizacion = :paisActualizacion"),
    @NamedQuery(name = "Capacitacion.findByObsCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.obsCapacitacion = :obsCapacitacion"),
    @NamedQuery(name = "Capacitacion.findByNombreDocuCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.nombreDocCapacitacion = :nombreDocCapacitacion"),
    @NamedQuery(name = "Capacitacion.findByUrlDocuCapacitacion", query = "SELECT c FROM Capacitacion c WHERE c.idDocCapacitacion = :idDocCapacitacion")})
public class Capacitacion implements Serializable {
//Atributos

    private static final long serialVersionUID = 1L;
    //Id  generado automaticamente para identificar a un objeto de tipo Capacitacion
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_capacitacion", nullable = false)
    private Integer idCapacitacion;
    /**
     * Nombre de la evento de la capacitación
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre del evento es un campo obligatorio")
    @Size(min = 1, max = 600)
    @Column(name = "nom_evento", nullable = false, length = 600)
    private String nomEvento;
    /**
     * fecha de inicio del curso de capacitación
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    @Column(name = "fecha_inicio_evento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicioEvento;
    /**
     * fecha de fin del curso de capacitación
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de fin es un campo obligatorio")
    @Column(name = "fecha_fin_evento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinEvento;
    /**
     * Nombre de la instictución donde se realizo el evento
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la institución es un campo obligatorio")
    @Size(min = 1, max = 600, message = "El nombre de la institucion debe tener maximo 100 caracteres")
    @Column(name = "nom_institucion", nullable = false, length = 600)
    private String nomInstitucion;
    /**
     * Numero de hora que duro el evento
     */
    @Basic(optional = false)
    @NotNull(message = "El número de horas es un campo obligatorio")
    @Column(name = "num_horas", nullable = false)
    private int numHoras;
    /**
     * Tipo de curso del evento de capacitación
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un tipo de curso")
    @Size(min = 1, max = 100)
    @Column(name = "tipo_curso", nullable = false, length = 100)
    private String tipoCurso;
    /**
     * Localidad donde se realizo el evento
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione una localidad")
    @Size(min = 1, max = 100)
    @Column(name = "localidad", nullable = false, length = 100)
    private String localidad;
    /**
     * País donde se realizó el evento
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un país")
    @Size(min = 1, max = 100)
    @Column(name = "pais_actualizacion", nullable = false, length = 100)
    private String paisActualizacion;
    /**
     * Observaciones de realizadas al curso de capacitación
     */
    @Size(max = 200)
    @Column(name = "obs_capacitacion", length = 200)
    private String obsCapacitacion;
    /**
     * Nombre del archivo pdf que sirve como evidencia que siguio el curso
     */
    @Size(max = 150)
    @Column(name = "nombre_doc_capacitacion", length = 150)
    private String nombreDocCapacitacion;
    /**
     * Url del archivo pdf guardado para su posterior descarga
     */
    @Size(max = 200)
    @Column(name = "id_doc_capacitacion", length = 200)
    private String idDocCapacitacion;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    @ManyToOne(optional = false)
    private Docente idDocente;

    /**
     * Constructor por defecto
     */
    public Capacitacion() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idCapacitacion
     */
    public Capacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    /**
     * Construct de la clase Capacitacion que recibe 9 parametros
     *
     * @param idCapacitacion
     * @param nomEvento
     * @param fechaInicioEvento
     * @param tipoCurso
     * @param nomInstitucion
     * @param fechaFinEvento
     * @param paisActualizacion
     * @param numHoras
     * @param localidad
     */
    public Capacitacion(Integer idCapacitacion, String nomEvento, Date fechaInicioEvento, Date fechaFinEvento, String nomInstitucion, int numHoras, String tipoCurso, String localidad, String paisActualizacion) {
        this.idCapacitacion = idCapacitacion;
        this.nomEvento = nomEvento;
        this.fechaInicioEvento = fechaInicioEvento;
        this.fechaFinEvento = fechaFinEvento;
        this.nomInstitucion = nomInstitucion;
        this.numHoras = numHoras;
        this.tipoCurso = tipoCurso;
        this.localidad = localidad;
        this.paisActualizacion = paisActualizacion;
    }

    /**
     * Retorna el id de la Capacitacion
     *
     * @return
     */
    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    /**
     * Permite modificar el id de la Capacitación
     *
     * @param idCapacitacion
     */
    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    /**
     * Retorna el nombre del evento
     *
     * @return
     */
    public String getNomEvento() {
        return nomEvento;
    }

    /**
     * Permite modificar el nombre del evento
     *
     * @param nomEvento
     */
    public void setNomEvento(String nomEvento) {
        this.nomEvento = nomEvento;
    }

    /**
     * Devuelve la fecha de inicio del evento
     *
     * @return
     */
    public Date getFechaInicioEvento() {
        return fechaInicioEvento;
    }

    /**
     * permite modificar la fecha de inicio del evento
     *
     * @param fechaInicioEvento
     */
    public void setFechaInicioEvento(Date fechaInicioEvento) {
        this.fechaInicioEvento = fechaInicioEvento;
    }

    /**
     * Retorna la ficha de fin del evento
     *
     * @return fechaFinEvento
     */
    public Date getFechaFinEvento() {
        return fechaFinEvento;
    }

    /**
     * Permite modificar la fecha de fin del evento
     *
     * @param fechaFinEvento
     */
    public void setFechaFinEvento(Date fechaFinEvento) {
        this.fechaFinEvento = fechaFinEvento;
    }

    /**
     * Retorna el nombre de la institución donde se realizó el evento
     *
     * @return nomInstitucion
     */
    public String getNomInstitucion() {
        return nomInstitucion;
    }

    /**
     * Permite modificar el nombre de la institución donde se realizó el evento
     *
     * @param nomInstitucion
     */
    public void setNomInstitucion(String nomInstitucion) {
        this.nomInstitucion = nomInstitucion;
    }

    /**
     * Retorna el numero de horas de duración del evento
     *
     * @return numHoras
     */
    public int getNumHoras() {
        return numHoras;
    }

    /**
     * Permite modificar el número de horas del evento
     *
     * @param numHoras
     */
    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }

    /**
     * Retorna el tipo de curso
     *
     * @return tipoCurso
     */
    public String getTipoCurso() {
        return tipoCurso;
    }

    /**
     * Permite modifica el valor del tipo de curso
     *
     * @param tipoCurso
     */
    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    /**
     * Devuelve la localidad donde se realizó el curso
     *
     * @return
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     * Permite modificar la localidad donde se realizó el curso
     *
     * @param localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     * Retorna el país donde se realizó el evento
     *
     * @return paisActualizacion
     */
    public String getPaisActualizacion() {
        return paisActualizacion;
    }

    /**
     * Permite modificar el país donde se realizo el evento
     *
     * @param paisActualizacion
     */
    public void setPaisActualizacion(String paisActualizacion) {
        this.paisActualizacion = paisActualizacion;
    }

    /**
     * Retorna las observaciones
     * @return 
     */
    public String getObsCapacitacion() {
        return obsCapacitacion;
    }

    /**
     * permite modificar algun tipo de observacones
     *
     * @param obsCapacitacion
     */
    public void setObsCapacitacion(String obsCapacitacion) {
        this.obsCapacitacion = obsCapacitacion;
    }
 /**
     * Devuelve el nombre del documento en el repositorio
     * @return  nombreDocCapacitacion
     */
    public String getNombreDocCapacitacion() {
        return nombreDocCapacitacion;
    }
 /**
     * Mofifica el nombre del documento en el repositorio
     * @param   nombreDocCapacitacion
     */
    public void setNombreDocCapacitacion(String nombreDocCapacitacion) {
        this.nombreDocCapacitacion = nombreDocCapacitacion;
    }
 /**
     * Devuelve el valor del id del archivo que se encuentra en el repositorio
     * @return  idDocCapacitacion
     */
    public String getIdDocCapacitacion() {
        return idDocCapacitacion;
    }
   /**
     * permite modificar el valor del id del archivo que se encuentra en el repositorio
     * @param idDocCapacitacion
     */
    public void setIdDocCapacitacion(String idDocCapacitacion) {
        this.idDocCapacitacion = idDocCapacitacion;
    }

    
    /**
     * Retorna el objeto de tipo Docente
     */

    /**
     * Retorna el objeto de tipo Docente
     * @return idDocente
     */
    public Docente getIdDocente() {
        return idDocente;
    }
/**
 * Permite modificar el objeto de tipo Docente
     * @param idDocente
 */
    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCapacitacion != null ? idCapacitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacitacion)) {
            return false;
        }
        Capacitacion other = (Capacitacion) object;
        if ((this.idCapacitacion == null && other.idCapacitacion != null) || (this.idCapacitacion != null && !this.idCapacitacion.equals(other.idCapacitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Capacitacion[ idCapacitacion=" + idCapacitacion + " ]";
    }

}
