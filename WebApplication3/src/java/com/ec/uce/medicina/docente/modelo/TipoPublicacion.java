/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaración de la clase TipoPublicacion
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "tipo_publicacion", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_tipo_publicacion"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPublicacion.findAll", query = "SELECT t FROM TipoPublicacion t"),
    @NamedQuery(name = "TipoPublicacion.findByIdTipoPublicacion", query = "SELECT t FROM TipoPublicacion t WHERE t.idTipoPublicacion = :idTipoPublicacion"),
    @NamedQuery(name = "TipoPublicacion.findByNomTipoPublicacion", query = "SELECT t FROM TipoPublicacion t WHERE t.nomTipoPublicacion = :nomTipoPublicacion")})
public class TipoPublicacion implements Serializable {
//Declaración de atributos
    private static final long serialVersionUID = 1L;
      /**
     * Id de la TipoPublicacion de un docente generado automaticamente para identificar a un
     * objeto de tipo TipoPublicacion
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_publicacion", nullable = false)
    private Integer idTipoPublicacion;
    /**Nombre del tipo de publicación*/
    @Basic(optional = false)
    @NotNull(message = "El nombre es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "nom_tipo_publicacion", nullable = false, length = 50)
    private String nomTipoPublicacion;
    /** Estado para habilitar el engreso de revista*/
    @Basic(optional = false)
    @NotNull(message = "El estado habilitar  revista es un campo obligatorio")
    @Column(name = "estado_habilitar_revista", nullable = false)
    private boolean estadoHabilitarRevista;
    //Declaració de una lista  objetos de tipo Publicaciones
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoPublicacion")
    private List<Publicaciones> publicacionesList;
/**Constructor por defecto*/
    public TipoPublicacion() {
    }


    /**
     * Constructor que recibe un parametro
     * @param idTipoPublicacion
     */
    public TipoPublicacion(Integer idTipoPublicacion) {
        this.idTipoPublicacion = idTipoPublicacion;
    }
        /**
     * Constructor que recibe un parametro
     * @param idTipoPublicacion
     */

    /**
     * Constructor que recibe 2 parametros
     * @param idTipoPublicacion
     * @param nomTipoPublicacion
     */
    public TipoPublicacion(Integer idTipoPublicacion, String nomTipoPublicacion) {
        this.idTipoPublicacion = idTipoPublicacion;
        this.nomTipoPublicacion = nomTipoPublicacion;
    }
/** Retorna el id del tipo de publicación
     * @return idTipoPublicacion
 */
    public Integer getIdTipoPublicacion() {
        return idTipoPublicacion;
    }
/** Modifica el id del tipo de publicación
     * @param  idTipoPublicacion
 */
    public void setIdTipoPublicacion(Integer idTipoPublicacion) {
        this.idTipoPublicacion = idTipoPublicacion;
    }
    /** Retorna el nombre del tipo de publicación
     * @return nomTipoPublicacion
 */

    public String getNomTipoPublicacion() {
        return nomTipoPublicacion;
    }
    /** Modifica el nombre del tipo de publicación
     * @param  nomTipoPublicacion
 */
    public void setNomTipoPublicacion(String nomTipoPublicacion) {
        this.nomTipoPublicacion = nomTipoPublicacion;
    }
    /** Retorna si alingreasr un apublicacionse habilta las opciones de la revista
     * @return <ul><li>true:Si</li>
     * <li>False:No</li></ul>
 */
    public boolean isEstadoHabilitarRevista() {
        return estadoHabilitarRevista;
    }


    /**
     * Modifica si alingreasr un apublicacionse habilta las opciones de la revista
     * @param estadoHabilitarRevista
     */
    public void setEstadoHabilitarRevista(boolean estadoHabilitarRevista) {
        this.estadoHabilitarRevista = estadoHabilitarRevista;
    }
    
    /** Retorna una lista de objetos de tipo Publicaciones
     * @return publicacionesList
 */
    @XmlTransient
    public List<Publicaciones> getPublicacionesList() {
        return publicacionesList;
    }
    /** Retorna una lista de objetos de tipo Publicaciones
     * @param  publicacionesList
 */
    public void setPublicacionesList(List<Publicaciones> publicacionesList) {
        this.publicacionesList = publicacionesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoPublicacion != null ? idTipoPublicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPublicacion)) {
            return false;
        }
        TipoPublicacion other = (TipoPublicacion) object;
        if ((this.idTipoPublicacion == null && other.idTipoPublicacion != null) || (this.idTipoPublicacion != null && !this.idTipoPublicacion.equals(other.idTipoPublicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.TipoPublicacion[ idTipoPublicacion=" + idTipoPublicacion + " ]";
    }
    
}
