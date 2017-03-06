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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaración de la clase PaisOrigen
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "pais_origen", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaisOrigen.findAll", query = "SELECT p FROM PaisOrigen p"),
    @NamedQuery(name = "PaisOrigen.findByIdPais", query = "SELECT p FROM PaisOrigen p WHERE p.idPais = :idPais"),
    @NamedQuery(name = "PaisOrigen.findByNomPais", query = "SELECT p FROM PaisOrigen p WHERE p.nomPais = :nomPais")})
public class PaisOrigen implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Id de la PaisOrigen de un docente generado automaticamente para
 identificar a un objeto de tipo PaisOrigen
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pais", nullable = false)
    private Integer idPais;
    /**Nombre del país
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre del país es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "nom_pais", nullable = false, length = 100)
    private String nomPais;
    /**Declaracion de una lista de objetos de tipo Docente
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPais")
    private List<Docente> docenteList;
/**
 * Constructor por defecto
 */
    public PaisOrigen() {
    }
/**
 * Constructor que recibe un parametro
     * @param idPais
 */
    public PaisOrigen(Integer idPais) {
        this.idPais = idPais;
    }
    /**
 * Constructorque recibe un parametro
     * @param idPais    /**
 * Constructorque recibe un parametro
     * @param idPais
 */

    /**
     * Constructorque recibe 2 parametro
     * @param idPais
     * @param nomPais
     */
    public PaisOrigen(Integer idPais, String nomPais) {
        this.idPais = idPais;
        this.nomPais = nomPais;
    }
/**
 * Retorna el id del país
     * @return idPais
 */
    public Integer getIdPais() {
        return idPais;
    }
    /**
 * Modifica el id del país
     * @param  idPais
 */

    public void setIdPais(Integer idPais) {
        this.idPais = idPais;
    }
    /**
 * Retorna el nombre del país
     * @return nomPais
 */

    public String getNomPais() {
        return nomPais;
    }
        /**
 * Modifica el nombre del país
     * @param  nomPais
 */


    public void setNomPais(String nomPais) {
        this.nomPais = nomPais;
    }
    /**
 * Retorna una lista de objetos de tipo Docente
     * @return docenteList
 */

    @XmlTransient
    public List<Docente> getDocenteList() {
        return docenteList;
    }
        /**
 * Modifica una lista de objetos de tipo Docente
     * @param  docenteList
 */

    public void setDocenteList(List<Docente> docenteList) {
        this.docenteList = docenteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPais != null ? idPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaisOrigen)) {
            return false;
        }
        PaisOrigen other = (PaisOrigen) object;
        if ((this.idPais == null && other.idPais != null) || (this.idPais != null && !this.idPais.equals(other.idPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.PaisOrigen[ idPais=" + idPais + " ]";
    }

}
