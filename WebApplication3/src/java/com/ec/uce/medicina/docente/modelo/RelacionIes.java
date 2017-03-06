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
 * Declaración de la clase RelacionIes
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "relacion_ies", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_relacion_ies"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionIes.findAll", query = "SELECT r FROM RelacionIes r"),
    @NamedQuery(name = "RelacionIes.findByIdRelacionIes", query = "SELECT r FROM RelacionIes r WHERE r.idRelacionIes = :idRelacionIes"),
    @NamedQuery(name = "RelacionIes.findByNomRelacionIes", query = "SELECT r FROM RelacionIes r WHERE r.nomRelacionIes = :nomRelacionIes")})
public class RelacionIes implements Serializable {
//Declaración de Atributos
    private static final long serialVersionUID = 1L;
     /**
     * Id de la RelacionIes de un docente generado automaticamente para identificar a un
     * objeto de tipo RelacionIes
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_relacion_ies", nullable = false)
    private Integer idRelacionIes;
    /**Nombre de la relación con la iES
     */
    @Basic(optional = false)
     @NotNull(message = "El nombre es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "nom_relacion_ies", nullable = false, length = 50)
    private String nomRelacionIes;
     /**Declaración de una lista de objetos de tipo RelacionLaboral
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRelacionIes")
    private List<RelacionLaboral> relacionlaboralList;
/**
 * Constructor por defecto
 */
    public RelacionIes() {
    }


    /**
     * Constructor que recibe un parámetro
     * @param idRelacionIes
     */
    public RelacionIes(Integer idRelacionIes) {
        this.idRelacionIes = idRelacionIes;
    }
    /**
     * Constructor que recibe 2 parámetros
     * @param idRelacionIes
     * @param nomRelacionIes
     */
    public RelacionIes(Integer idRelacionIes, String nomRelacionIes) {
        this.idRelacionIes = idRelacionIes;
        this.nomRelacionIes = nomRelacionIes;
    }
/**
 * Retorna el id de la relación con la IES
     * @return idRelacionIes
 */
    public Integer getIdRelacionIes() {
        return idRelacionIes;
    }
    /**
 * Modifica el id de la relación con la IES
     * @param  idRelacionIes
 */

    public void setIdRelacionIes(Integer idRelacionIes) {
        this.idRelacionIes = idRelacionIes;
    }
    /**
 * Retorna el nombre de la relación con la IES
     * @return nomRelacionIes
 */

    public String getNomRelacionIes() {
        return nomRelacionIes;
    }
        /**
 * Modifica el nombre de la relación con la IES
     * @param  nomRelacionIes
 */

    public void setNomRelacionIes(String nomRelacionIes) {
        this.nomRelacionIes = nomRelacionIes;
    }
    /**
 * Retorna una lista de objetos de tipo RelacionLaboral
     * @return relacionlaboralList
 */
    @XmlTransient
    public List<RelacionLaboral> getRelacionlaboralList() {
        return relacionlaboralList;
    }
        /**
 * Modifica una lista de objetos de tipo Relacionlaboral
     * @param  relacionlaboralList        /**
 * Modifica una lista de objetos de tipo Relacionlaboral
     * @param  relacionlaboralList
 */

    /**
     * Modifica una lista de objetos de tipo RelacionLaboral
     * @param relacionlaboralList
     */
    public void setRelacionlaboralList(List<RelacionLaboral> relacionlaboralList) {
        this.relacionlaboralList = relacionlaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRelacionIes != null ? idRelacionIes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionIes)) {
            return false;
        }
        RelacionIes other = (RelacionIes) object;
        if ((this.idRelacionIes == null && other.idRelacionIes != null) || (this.idRelacionIes != null && !this.idRelacionIes.equals(other.idRelacionIes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.RelacionIes[ idRelacionIes=" + idRelacionIes + " ]";
    }
    
}
