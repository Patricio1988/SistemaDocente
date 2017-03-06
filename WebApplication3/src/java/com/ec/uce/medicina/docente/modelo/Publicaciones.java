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
 * Declaración de la clase Publicaciones
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "publicaciones", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicaciones.findAll", query = "SELECT p FROM Publicaciones p"),
    @NamedQuery(name = "Publicaciones.findByIdPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.idPublicacion = :idPublicacion"),
    @NamedQuery(name = "Publicaciones.findByCodPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.codPublicacion = :codPublicacion"),
    @NamedQuery(name = "Publicaciones.findByFiliacion", query = "SELECT p FROM Publicaciones p WHERE p.filiacion = :filiacion"),
    @NamedQuery(name = "Publicaciones.findByAreaConocimiento", query = "SELECT p FROM Publicaciones p WHERE p.areaConocimiento = :areaConocimiento"),
    @NamedQuery(name = "Publicaciones.findBySubareaConocimiento", query = "SELECT p FROM Publicaciones p WHERE p.subareaConocimiento = :subareaConocimiento"),
    @NamedQuery(name = "Publicaciones.findBySubareaEspeConocimiento", query = "SELECT p FROM Publicaciones p WHERE p.subareaEspeConocimiento = :subareaEspeConocimiento"),
    @NamedQuery(name = "Publicaciones.findByNomPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.nomPublicacion = :nomPublicacion"),
    @NamedQuery(name = "Publicaciones.findByFechaPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.fechaPublicacion = :fechaPublicacion"),
    @NamedQuery(name = "Publicaciones.findByParticipacion", query = "SELECT p FROM Publicaciones p WHERE p.participacion = :participacion"),
    @NamedQuery(name = "Publicaciones.findByRevisonPares", query = "SELECT p FROM Publicaciones p WHERE p.revisonPares = :revisonPares"),
    @NamedQuery(name = "Publicaciones.findByNumIsbnIssn", query = "SELECT p FROM Publicaciones p WHERE p.numIsbnIssn = :numIsbnIssn"),
    @NamedQuery(name = "Publicaciones.findByNombreRevista", query = "SELECT p FROM Publicaciones p WHERE p.nombreRevista = :nombreRevista"),
    @NamedQuery(name = "Publicaciones.findByEstadoPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.estadoPublicacion = :estadoPublicacion"),
    @NamedQuery(name = "Publicaciones.findByRevistaIndexada", query = "SELECT p FROM Publicaciones p WHERE p.revistaIndexada = :revistaIndexada"),
    @NamedQuery(name = "Publicaciones.findByNomBaseIndexada", query = "SELECT p FROM Publicaciones p WHERE p.nomBaseIndexada = :nomBaseIndexada"),
    @NamedQuery(name = "Publicaciones.findByObsPublicaciones", query = "SELECT p FROM Publicaciones p WHERE p.obsPublicaciones = :obsPublicaciones"),
    @NamedQuery(name = "Publicaciones.findByNombreDocuPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.nombreDocPublicacion = :nombreDocPublicacion"),
    @NamedQuery(name = "Publicaciones.findByUrlDocuPublicacion", query = "SELECT p FROM Publicaciones p WHERE p.idDocPublicacion = :idDocPublicacion")})
public class Publicaciones implements Serializable {
//Declaración de atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Publicaciones de un docente generado automaticamente para
     * identificar a un objeto de tipo Publicaciones
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_publicacion", nullable = false)
    private Integer idPublicacion;
    /**
     * código de la publicación
     */
    @Basic(optional = false)
    @NotNull(message = "El código de publicación es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "cod_publicacion", nullable = false, length = 50)
    private String codPublicacion;
    /**
     * Filiacción de la publicacion
     */
    @Column(name = "filiacion")
    private Boolean filiacion;
    /**
     * área de conocimiento de la publicación
     */
    @Size(max = 100)
    @Column(name = "area_conocimiento", length = 100)
    private String areaConocimiento;
    /**
     * subárea de conocimiento de la publicación
     */
    @Size(max = 200)
    @Column(name = "subarea_conocimiento", length = 200)
    private String subareaConocimiento;
    /**
     * subárea específica de conocimiento de la publicación
     */
    @Size(max = 200)
    @Column(name = "subarea_espe_conocimiento", length = 200)
    private String subareaEspeConocimiento;
    /**
     * Nombre de la publicación
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de publicación es un campo obligatorio")
    @Size(min = 1, max = 500)
    @Column(name = "nom_publicacion", nullable = false, length = 500)
    private String nomPublicacion;
    /**
     * fecha de publicación
     */
    @Basic(optional = false)
    @NotNull(message = "El fecha de publicación es un campo obligatorio")
    @Column(name = "fecha_publicacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    /**
     * partcipacion del docente en la publicación
     */
    @Size(max = 50)
    @Column(name = "participacion", length = 50)
    private String participacion;
    /**
     * La publicación fue revisado por pares
     */
    @Column(name = "revison_pares")
    private Boolean revisonPares;
    /**
     * Número de ISSN o ISBN de la publicación
     */
    @Basic(optional = false)
    @NotNull(message = "El número de ISBN/ISSN es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "num_isbn_issn", nullable = false, length = 100)
    private String numIsbnIssn;
    /**
     * Nombre de la revista
     */
    @Size(max = 100)
    @Column(name = "nombre_revista", length = 100)
    private String nombreRevista;
    /**
     * Estado de la publicación
     */
    @Basic(optional = false)
    @NotNull(message = "El estado de publicación es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "estado_publicacion", nullable = false, length = 50)
    private String estadoPublicacion;
    /**
     * La revista fue indexada
     */
    @Column(name = "revista_indexada")
    private Boolean revistaIndexada;
    /**
     * Nombre de la base en la cual fue indexada la revista
     */
    @Size(max = 50)
    @Column(name = "nom_base_indexada", length = 50)
    private String nomBaseIndexada;
    /**
     * Observaciones
     */
    @Size(max = 200)
    @Column(name = "obs_publicaciones", length = 200)
    private String obsPublicaciones;
    /**
     * Nombre del archivo pdf que servira como evidencia
     */
    @Size(max = 150)
    @Column(name = "nombre_doc_publicacion", length = 150)
    private String nombreDocPublicacion;
    /**
     * url del archivo pdf que servira como evidencia
     */
    @Size(max = 200)
    @Column(name = "id_doc_publicacion", length = 200)
    private String idDocPublicacion;
    /**
     * Declaracion de un objeto de tipo Docente
     */
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    @ManyToOne(optional = false)
    private Docente idDocente;
    /**
     * Declaracion de un objeto de tipo TipoPublicacion
     */
    @JoinColumn(name = "id_tipo_publicacion", referencedColumnName = "id_tipo_publicacion", nullable = false)
    @ManyToOne(optional = false)
    private TipoPublicacion idTipoPublicacion;

    /**
     * Constructor por defecto
     */
    public Publicaciones() {
    }

    /**
     * Constructor que recibe como parametro
     *
     * @param idPublicacion
     */
    public Publicaciones(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    /**
     * Constructor que recibe 6 parametros
     *
     * @param idPublicacion
     * @param codPublicacion
     * @param nomPublicacion
     * @param fechaPublicacion
     * @param numIsbnIssn
     * @param estadoPublicacion
     */
    public Publicaciones(Integer idPublicacion, String codPublicacion, String nomPublicacion, Date fechaPublicacion, String numIsbnIssn, String estadoPublicacion) {
        this.idPublicacion = idPublicacion;
        this.codPublicacion = codPublicacion;
        this.nomPublicacion = nomPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.numIsbnIssn = numIsbnIssn;
        this.estadoPublicacion = estadoPublicacion;
    }

    /**
     * Retorna el id de la publicación
     *
     * @return idPublicacion
     */
    public Integer getIdPublicacion() {
        return idPublicacion;
    }

    /**
     * Modifica el id de la publicación
     *
     * @param idPublicacion
     */
    public void setIdPublicacion(Integer idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    /**
     * Retorna el código de la publicación
     *
     * @return codPublicacion
     */
    public String getCodPublicacion() {
        return codPublicacion;
    }

    /**
     * Modifica el código de la publicación
     *
     * @param codPublicacion
     */
    public void setCodPublicacion(String codPublicacion) {
        this.codPublicacion = codPublicacion;
    }

    /**
     * Retorna la filiación de la publicación
     *
     * @return filiacion
     */
    public Boolean getFiliacion() {
        return filiacion;
    }

    /**
     * Modifica la filiación de la publicación
     *
     * @param filiacion
     */
    public void setFiliacion(Boolean filiacion) {
        this.filiacion = filiacion;
    }

    /**
     * Retorna el área de conocimiento de la publicación
     *
     * @return filiacion
     */
    public String getAreaConocimiento() {
        return areaConocimiento;
    }

    /**
     * Modifica el área de conocimiento de la publicación
     *
     * @param areaConocimiento
     */
    public void setAreaConocimiento(String areaConocimiento) {
        this.areaConocimiento = areaConocimiento;
    }

    /**
     * Retorna el subárea de conocimiento de la publicación
     *
     * @return subareaConocimiento
     */
    public String getSubareaConocimiento() {
        return subareaConocimiento;
    }

    /**
     * Modifica el subárea de conocimiento de la publicación
     *
     * @param subareaConocimiento
     */
    public void setSubareaConocimiento(String subareaConocimiento) {
        this.subareaConocimiento = subareaConocimiento;
    }

    /**
     * Retorna el subárea específica de conocimiento de la publicación
     *
     * @return subareaEspeConocimiento
     */
    public String getSubareaEspeConocimiento() {
        return subareaEspeConocimiento;
    }

    /**
     * Modifica el subárea específica de conocimiento de la publicación
     *
     * @param subareaEspeConocimiento
     */
    public void setSubareaEspeConocimiento(String subareaEspeConocimiento) {
        this.subareaEspeConocimiento = subareaEspeConocimiento;
    }

    /**
     * Retorna el nombre de la publicación
     *
     * @return nomPublicacion
     */
    public String getNomPublicacion() {
        return nomPublicacion;
    }

    /**
     * Modifica el nombre de la publicación
     *
     * @param nomPublicacion
     */
    public void setNomPublicacion(String nomPublicacion) {
        this.nomPublicacion = nomPublicacion;
    }

    /**
     * Retorna la Fecha de la publicación
     *
     * @return fechaPublicacion
     */
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Modifica la Fecha de la publicación
     *
     * @param fechaPublicacion
     */
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * Retorna la partcipación del docente en la de la publicación
     *
     * @return participacion
     */
    public String getParticipacion() {
        return participacion;
    }

    /**
     * Modifica la partcipación del docente en la de la publicación
     *
     * @param participacion
     */
    public void setParticipacion(String participacion) {
        this.participacion = participacion;
    }

    /**
     * Retorna si la publicación fue revisada por pares
     *
     *
     * @return <ul>
     * <li>
     * true:la publicacion fue revisada por pares
     * </li>
     *  * <li>
     * false:la publicacion no fue revisada por pares
     * </li>
     * </ul>
     */
    public Boolean getRevisonPares() {
        return revisonPares;
    }

    /**
     * Modifica si la publicación fue revisada por pares
     *
     * @param revisonPares
     */
    public void setRevisonPares(Boolean revisonPares) {
        this.revisonPares = revisonPares;
    }

    /**
     * Retorna el número ISSN o ISBN de la publicación
     *
     * @return numIsbnIssn
     */
    public String getNumIsbnIssn() {
        return numIsbnIssn;
    }

    /**
     * Modifica el número ISSN o ISBN de la publicación
     *
     * @param numIsbnIssn
     */

    public void setNumIsbnIssn(String numIsbnIssn) {
        this.numIsbnIssn = numIsbnIssn;
    }

    /**
     * Retorna el nombre de la revista
     *
     * @return nombreRevista
     */

    public String getNombreRevista() {
        return nombreRevista;
    }

    /**
     * Modifica el nombre de la revista
     *
     * @param nombreRevista
     */

    public void setNombreRevista(String nombreRevista) {
        this.nombreRevista = nombreRevista;
    }

    /**
     * Retorna el estado de publicación
     *
     * @return estadoPublicacion
     */

    public String getEstadoPublicacion() {
        return estadoPublicacion;
    }

    /**
     * Modifica el estado de publicación
     *
     * @param estadoPublicacion
     */

    public void setEstadoPublicacion(String estadoPublicacion) {
        this.estadoPublicacion = estadoPublicacion;
    }

    /**
     * Retorna si la revista fue indexada
     *
     * @return revistaIndexada
     */
    public Boolean getRevistaIndexada() {
        return revistaIndexada;
    }

    /**
     * Modifica si la revista fue indexada
     *
     * @param revistaIndexada
     */

    public void setRevistaIndexada(Boolean revistaIndexada) {
        this.revistaIndexada = revistaIndexada;
    }

    /**
     * Retorna el nombre de la base donde la revista fue indexada
     *
     * @return nomBaseIndexada
     */
    public String getNomBaseIndexada() {
        return nomBaseIndexada;
    }

    /**
     * Modifica el nombre de la base donde la revista fue indexada
     *
     * @param nomBaseIndexada
     */
    public void setNomBaseIndexada(String nomBaseIndexada) {
        this.nomBaseIndexada = nomBaseIndexada;
    }

    /**
     * Retorna las observaciones
     *
     * @return obsPublicaciones
     */

    public String getObsPublicaciones() {
        return obsPublicaciones;
    }

    /**
     * Modifica las observaciones
     *
     * @param obsPublicaciones
     */

    public void setObsPublicaciones(String obsPublicaciones) {
        this.obsPublicaciones = obsPublicaciones;
    }
/**Devuelve el nombre del documento almacenado en el repositorio
     * @return nombreDocPublicacion
 */
    public String getNombreDocPublicacion() {
        return nombreDocPublicacion;
    }
/**Modifica el nombre del documento almacenado en el repositorio
     * @param nombreDocPublicacion
 */
    public void setNombreDocPublicacion(String nombreDocPublicacion) {
        this.nombreDocPublicacion = nombreDocPublicacion;
    }
/**Devuelve el id del documento almacenado en el repositorio
     * @return idDocPublicacion
 */
    public String getIdDocPublicacion() {
        return idDocPublicacion;
    }
/**Modifica el id del documento almacenado en el repositorio
     * @param  idDocPublicacion
 */
    public void setIdDocPublicacion(String idDocPublicacion) {
        this.idDocPublicacion = idDocPublicacion;
    }

   
    /**
     * Retorna un objeto de tipo docente
     *
     * @return idDocente
     */

    public Docente getIdDocente() {
        return idDocente;
    }

    /**
     * Modifica un objeto de tipo docente
     *
     * @param idDocente
     */

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    /**
     * Retorna un objeto de tipo TipoPublicacion
     *
     * @return idTipoPublicacion
     */
    public TipoPublicacion getIdTipoPublicacion() {
        return idTipoPublicacion;
    }

    /**
     * Modifica un objeto de tipo TipoPublicacion
     *
     * @param idTipoPublicacion
     */
    public void setIdTipoPublicacion(TipoPublicacion idTipoPublicacion) {
        this.idTipoPublicacion = idTipoPublicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPublicacion != null ? idPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicaciones)) {
            return false;
        }
        Publicaciones other = (Publicaciones) object;
        if ((this.idPublicacion == null && other.idPublicacion != null) || (this.idPublicacion != null && !this.idPublicacion.equals(other.idPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Publicaciones[ idPublicacion=" + idPublicacion + " ]";
    }

}
