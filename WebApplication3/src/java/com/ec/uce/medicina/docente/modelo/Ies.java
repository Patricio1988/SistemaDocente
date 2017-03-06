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
 * Declaración de la clase Ies
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "ies", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_ies"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ies.findAll", query = "SELECT i FROM Ies i"),
    @NamedQuery(name = "Ies.findByIdIes", query = "SELECT i FROM Ies i WHERE i.idIes = :idIes"),
    @NamedQuery(name = "Ies.findByCodIes", query = "SELECT i FROM Ies i WHERE i.codIes = :codIes"),
    @NamedQuery(name = "Ies.findByNomIes", query = "SELECT i FROM Ies i WHERE i.nomIes = :nomIes")})
public class Ies implements Serializable {
//Declaración de Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Ies de un docente generado automaticamente para identificar a un
     * objeto de tipo Ies
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ies", nullable = false)
    private Integer idIes;
    /**
     * Código de la IES
     */
    @Column(name = "cod_ies")
    private Integer codIes;
    /**
     * Nombre de la IES
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la Ies es un campo obligatorio")
    @Size(min = 1, max = 200)
    @Column(name = "nom_ies", nullable = false, length = 200)
    private String nomIes;
    /**
     * Declaración de una lista de objetos de tipo Formacion
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idIes")
    private List<Formacion> formacionList;

    /**
     * Constructor por defecto
     */
    public Ies() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idIes
     */
    public Ies(Integer idIes) {
        this.idIes = idIes;
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idIes
     * @param nomIes
     */
    public Ies(Integer idIes, String nomIes) {
        this.idIes = idIes;
        this.nomIes = nomIes;
    }

    /**
     * Retorna el id de la IES
     *
     * @return idIes
     */
    public Integer getIdIes() {
        return idIes;
    }

    /**
     * Modifica el id de la IES
     *
     * @param idIes
     */

    public void setIdIes(Integer idIes) {
        this.idIes = idIes;
    }

    /**
     * Retorna el código de la IES
     *
     * @return codIes
     */
    public Integer getCodIes() {
        return codIes;
    }

    /**
     * Modifica el código de la IES
     *
     * @param codIes
     */
    public void setCodIes(Integer codIes) {
        this.codIes = codIes;
    }

    /**
     * Retorna el nombre de la IES
     *
     * @return nomIes
     */

    public String getNomIes() {
        return nomIes;
    }

    /**
     * Modifica el nombre de la IES
     *
     * @param nomIes
     */

    public void setNomIes(String nomIes) {
        this.nomIes = nomIes;
    }

    /**
     * Retorna una lista de obejtos de tipo Formacion
     *
     * @return nomIes
     */
    @XmlTransient
    public List<Formacion> getFormacionList() {
        return formacionList;
    }

    /**
     * Modifica una lista de obejtos de tipo Formacion
     *
     * @param formacionList
     */

    public void setFormacionList(List<Formacion> formacionList) {
        this.formacionList = formacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIes != null ? idIes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ies)) {
            return false;
        }
        Ies other = (Ies) object;
        if ((this.idIes == null && other.idIes != null) || (this.idIes != null && !this.idIes.equals(other.idIes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Ies[ idIes=" + idIes + " ]";
    }

}
