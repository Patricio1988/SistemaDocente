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
 * Declaración de la clase TipoDocumento
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "tipo_documento", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_tipo_documento"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t"),
    @NamedQuery(name = "TipoDocumento.findByIdTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDocumento = :idTipoDocumento"),
    @NamedQuery(name = "TipoDocumento.findByNomTipoDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.nomTipoDocumento = :nomTipoDocumento")})
public class TipoDocumento implements Serializable {
//Declaración de atributos
    private static final long serialVersionUID = 1L;
      /**
     * Id de la TipoDocumento de un docente generado automaticamente para identificar a un
     * objeto de tipo TipoDocumento
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_documento", nullable = false)
    private Integer idTipoDocumento;
    /**Nombre del tipo de documento*/
    @Basic(optional = false)
    @NotNull(message = "El nombre es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "nom_tipo_documento", nullable = false, length = 50)
    private String nomTipoDocumento;
    /**Declaración de una lista de objetos de tipo RelacionLaboral*/
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDocumento")
    private List<RelacionLaboral> relacionlaboralList;
/**Constructor por defecto*/
    public TipoDocumento() {
    }
   /**
     * Constructor que recibe un parametro
     * @param idTipoDocumento
     */
    public TipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }
  /**
     * Constructor que recibe 2 parametros
     * @param idTipoDocumento
     * @param nomTipoDocumento
     */
    public TipoDocumento(Integer idTipoDocumento, String nomTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
        this.nomTipoDocumento = nomTipoDocumento;
    }
    /**
     * Retorna el id del tipo de documento
     *
     * @return idTipoDocumento
     */
    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }
    /**
     * Modifica el id del tipo de documento
     *
     * @param  idTipoDocumento
     */

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }
/**
     * Retorna el nombre del tipo de documento
     *
     * @return nomTipoDocumento
     */
    public String getNomTipoDocumento() {
        return nomTipoDocumento;
    }
/**
     * Modifica el nombre del tipo de documento
     *
     * @param  nomTipoDocumento
     */
    public void setNomTipoDocumento(String nomTipoDocumento) {
        this.nomTipoDocumento = nomTipoDocumento;
    }
/**
     * Retorna una lista de objetos  del tipo de RelacionLaboral
     *
     * @return relacionlaboralList
     */
    @XmlTransient
    public List<RelacionLaboral> getRelacionlaboralList() {
        return relacionlaboralList;
    }
    /**
     * Modifica una lista de objetos  del tipo de Relacionlaboral
     *
     * @param  relacionlaboralList    /**
     * Modifica una lista de objetos  del tipo de Relacionlaboral
     *
     * @param  relacionlaboralList
     */

    /**
     * Modifica una lista de objetos  del tipo de RelacionLaboral
     * @param relacionlaboralList
     */
    public void setRelacionlaboralList(List<RelacionLaboral> relacionlaboralList) {
        this.relacionlaboralList = relacionlaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDocumento != null ? idTipoDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDocumento == null && other.idTipoDocumento != null) || (this.idTipoDocumento != null && !this.idTipoDocumento.equals(other.idTipoDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.TipoDocumento[ idTipoDocumento=" + idTipoDocumento + " ]";
    }
    
}
