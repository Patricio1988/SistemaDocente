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
 * Declaración de la clase Formacion
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "formacion", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formacion.findAll", query = "SELECT f FROM Formacion f"),
    @NamedQuery(name = "Formacion.findByIdFormacion", query = "SELECT f FROM Formacion f WHERE f.idFormacion = :idFormacion"),
    @NamedQuery(name = "Formacion.findByPaisEstudio", query = "SELECT f FROM Formacion f WHERE f.paisEstudio = :paisEstudio"),
    @NamedQuery(name = "Formacion.findByCodIesEstudio", query = "SELECT f FROM Formacion f WHERE f.codIesEstudio = :codIesEstudio"),
    @NamedQuery(name = "Formacion.findByNombreIes", query = "SELECT f FROM Formacion f WHERE f.nombreIes = :nombreIes"),
    @NamedQuery(name = "Formacion.findByNivel", query = "SELECT f FROM Formacion f WHERE f.nivel = :nivel"),
    @NamedQuery(name = "Formacion.findByGrado", query = "SELECT f FROM Formacion f WHERE f.grado = :grado"),
    @NamedQuery(name = "Formacion.findByNomTitulo", query = "SELECT f FROM Formacion f WHERE f.nomTitulo = :nomTitulo"),
    @NamedQuery(name = "Formacion.findByCodSubareaEspecifica", query = "SELECT f FROM Formacion f WHERE f.codSubareaEspecifica = :codSubareaEspecifica"),
    @NamedQuery(name = "Formacion.findByEstadoTitulo", query = "SELECT f FROM Formacion f WHERE f.estadoTitulo = :estadoTitulo"),
    @NamedQuery(name = "Formacion.findByNumRegSenescyt", query = "SELECT f FROM Formacion f WHERE f.numRegSenescyt = :numRegSenescyt"),
    @NamedQuery(name = "Formacion.findByFechaTitulo", query = "SELECT f FROM Formacion f WHERE f.fechaTitulo = :fechaTitulo"),
    @NamedQuery(name = "Formacion.findByObsTitulo", query = "SELECT f FROM Formacion f WHERE f.obsTitulo = :obsTitulo"),
    @NamedQuery(name = "Formacion.findByNombreDocuFormacion", query = "SELECT f FROM Formacion f WHERE f.nombreDocFormacion = :nombreDocFormacion"),
    @NamedQuery(name = "Formacion.findByUrlDocuFormacion", query = "SELECT f FROM Formacion f WHERE f.idDocFormacion = :idDocFormacion")})
public class Formacion implements Serializable {
//Declaración de Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Formacion de un docente generado automaticamente para
     * identificar a un objeto de tipo Formacion
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_formacion", nullable = false)
    private Integer idFormacion;
    /**
     * País donde realizo los estudios el docente
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un país")
    @Size(min = 1, max = 100)
    @Column(name = "pais_estudio", nullable = false, length = 100)
    private String paisEstudio;
    /**
     * Código de la IES
     */
    @Column(name = "cod_ies_estudio")
    private Integer codIesEstudio;
    @Size(max = 100)
    @Column(name = "nombre_ies", length = 100)
    /**
     * Nombre de la IES
     */
    private String nombreIes;
    @Basic(optional = false)
    @NotNull(message = "Seleccione un nivel")
    @Size(min = 1, max = 50)
    @Column(name = "nivel", nullable = false, length = 50)
    /**
     * Nivel de formación que tiene el título
     */
    private String nivel;
    /**
     * Grado que tiene el título
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un grado")
    @Size(min = 1, max = 50)
    @Column(name = "grado", nullable = false, length = 50)
    private String grado;
    /**
     * Nombre del título
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre del título es un campo obligatorio")
    @Size(min = 1, max = 200)
    @Column(name = "nom_titulo", nullable = false, length = 200)
    private String nomTitulo;
    /**
     * Subárea específica del título
     */
    @Size(max = 500)
    @Column(name = "cod_subarea_especifica", length = 500)
    private String codSubareaEspecifica;
    /**
     * Estado del título
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un estado")
    @Size(min = 1, max = 50)
    @Column(name = "estado_titulo", nullable = false, length = 50)
    private String estadoTitulo;
    /**
     * Número del registro senescyt del título
     */
    @Basic(optional = false)
    @NotNull(message = "El número de registro es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "num_reg_senescyt", nullable = false, length = 100)
    private String numRegSenescyt;
    /**
     * fecha que obtuvo del título
     */
    @Column(name = "fecha_titulo")
    @Temporal(TemporalType.DATE)
    private Date fechaTitulo;
    /**
     * Observaciones al ingresar datos dle título
     */
    @Size(max = 200)
    @Column(name = "obs_titulo", length = 200)
    private String obsTitulo;
    /**
     * nombre del archivo pdf que sirve como evidencia de los datos cargados
     */
    @Size(max = 150)
    @Column(name = "nombre_doc_formacion", length = 150)
    private String nombreDocFormacion;
    /**
     * id del archivo pdf que sirve como evidencia de los datos cargados
     */
    @Size(max = 200)
    @Column(name = "id_doc_formacion", length = 200)
    private String idDocFormacion;
    /**
     * Declaración del objeto de tipo Docente
     */
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    @ManyToOne(optional = false)
    private Docente idDocente;
    /**
     * Declaración del objeto de tipo Ies
     */
    @JoinColumn(name = "id_ies", referencedColumnName = "id_ies", nullable = false)
    @ManyToOne(optional = false)
    private Ies idIes;

    /**
     * Construcctor por defecto
     */
    public Formacion() {
    }

    /**
     * Construcctor que recibe un parametro
     *
     * @param idFormacion
     */
    public Formacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    /**
     * Construcctor que recibe 7 parametros
     *
     * @param idFormacion
     * @param paisEstudio
     * @param nivel
     * @param grado
     * @param nomTitulo
     * @param estadoTitulo
     * @param numRegSenescyt
     */
    public Formacion(Integer idFormacion, String paisEstudio, String nivel, String grado, String nomTitulo, String estadoTitulo, String numRegSenescyt) {
        this.idFormacion = idFormacion;
        this.paisEstudio = paisEstudio;
        this.nivel = nivel;
        this.grado = grado;
        this.nomTitulo = nomTitulo;
        this.estadoTitulo = estadoTitulo;
        this.numRegSenescyt = numRegSenescyt;
    }

    /**
     * Retorna el id de la formación
     *
     * @return idFormacion
     */
    public Integer getIdFormacion() {
        return idFormacion;
    }

    /**
     * Retorna el id de la formación
     *
     * @param idFormacion
     */
    public void setIdFormacion(Integer idFormacion) {
        this.idFormacion = idFormacion;
    }

    /**
     * Retorna el país donde el docente realizo los estudios
     *
     * @return paisEstudio
     */
    public String getPaisEstudio() {
        return paisEstudio;
    }

    /**
     * Modifica el país título donde el docente realizo sus estudios
     *
     * @param paisEstudio
     */
    public void setPaisEstudio(String paisEstudio) {
        this.paisEstudio = paisEstudio;
    }

    /**
     * Retorna el código de la Ies
     *
     * @return codIesEstudio
     */

    public Integer getCodIesEstudio() {
        return codIesEstudio;
    }

    /**
     * Modifica el código de la Ies
     *
     * @param codIesEstudio
     */

    public void setCodIesEstudio(Integer codIesEstudio) {
        this.codIesEstudio = codIesEstudio;
    }

    /**
     * Retorna el nombre de la Ies
     *
     * @return nombreIes
     */

    public String getNombreIes() {
        return nombreIes;
    }

    /**
     * Modifica el nombre de la Ies
     *
     * @param nombreIes
     */

    public void setNombreIes(String nombreIes) {
        this.nombreIes = nombreIes;
    }

    /**
     * Retorna el nivel del título
     *
     * @return nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Modifica el nivel del título
     *
     * @param nivel
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Retorna el grado del título
     *
     * @return grado
     */

    public String getGrado() {
        return grado;
    }

    /**
     * Modifica el grado del título
     *
     * @param grado
     */

    public void setGrado(String grado) {
        this.grado = grado;
    }

    /**
     * Retorna el nombre del título
     *
     * @return nomTitulo
     */

    public String getNomTitulo() {
        return nomTitulo;
    }

    /**
     * Modifica el nombre del título
     *
     * @param nomTitulo
     */

    public void setNomTitulo(String nomTitulo) {
        this.nomTitulo = nomTitulo;
    }

    /**
     * Retorna el código de la subáreaespecífica del título
     *
     * @return codSubareaEspecifica
     */

    public String getCodSubareaEspecifica() {
        return codSubareaEspecifica;
    }

    /**
     * Modifica el código de la subáreaespecífica del título
     *
     * @param codSubareaEspecifica
     */
    public void setCodSubareaEspecifica(String codSubareaEspecifica) {
        this.codSubareaEspecifica = codSubareaEspecifica;
    }

    /**
     * Retorna el estado en el cual se encuentra el título
     *
     * @return estadoTitulo
     */

    public String getEstadoTitulo() {
        return estadoTitulo;
    }

    /**
     * Modifica el estado en el cual se encuentra el título
     *
     * @param estadoTitulo
     */

    public void setEstadoTitulo(String estadoTitulo) {
        this.estadoTitulo = estadoTitulo;
    }

    /**
     * Retorna el número de registro del senescyt del título
     *
     * @return numRegSenescyt
     */
    public String getNumRegSenescyt() {
        return numRegSenescyt;
    }

    /**
     * Modifica el número de registro del senescyt del título
     *
     * @param numRegSenescyt
     */
    public void setNumRegSenescyt(String numRegSenescyt) {
        this.numRegSenescyt = numRegSenescyt;
    }

    /**
     * Retorna el fecha en la cual se obtuvo el título
     *
     * @return fechaTitulo
     */
    public Date getFechaTitulo() {
        return fechaTitulo;
    }

    /**
     * Modifica el fecha en la cual se obtuvo el título
     *
     * @param fechaTitulo
     */
    public void setFechaTitulo(Date fechaTitulo) {
        this.fechaTitulo = fechaTitulo;
    }

    /**
     * Retorna las observaciones el título
     *
     * @return obsTitulo
     */

    public String getObsTitulo() {
        return obsTitulo;
    }

    /**
     * Modifica las observaciones el título
     *
     * @param obsTitulo
     */

    public void setObsTitulo(String obsTitulo) {
        this.obsTitulo = obsTitulo;
    }
/**
 * Devuleve el nombre del documento almacenado en el repositorio
     * @return nombreDocFormacion
 */
    public String getNombreDocFormacion() {
        return nombreDocFormacion;
    }
/**
 * Modifica el nombre del documento almacenado en el repositorio
     * @param  nombreDocFormacion
 */
    public void setNombreDocFormacion(String nombreDocFormacion) {
        this.nombreDocFormacion = nombreDocFormacion;
    }
/**
 * Devuleve el id del documento almacenado en el repositorio
     * @return idDocFormacion
 */
    public String getIdDocFormacion() {
        return idDocFormacion;
    }
/**
 * Modifica el id del documento almacenado en el repositorio
     * @param  idDocFormacion
 */
    public void setIdDocFormacion(String idDocFormacion) {
        this.idDocFormacion = idDocFormacion;
    }
    
 
/**Retorna un objeto de tipo Docente
     * @return idDocente
 */
    public Docente getIdDocente() {
        return idDocente;
    }
    /**Modifica un objeto de tipo Docente
     * @param  idDocente
 */

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }
    /**Retorna un objeto de tipo Ies
     * @return idIes
 */

    public Ies getIdIes() {
        return idIes;
    }
       /**Modifica un objeto de tipo Ies
     * @param  idIes
 */

    public void setIdIes(Ies idIes) {
        this.idIes = idIes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormacion != null ? idFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacion)) {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.idFormacion == null && other.idFormacion != null) || (this.idFormacion != null && !this.idFormacion.equals(other.idFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Formacion[ idFormacion=" + idFormacion + " ]";
    }

}
