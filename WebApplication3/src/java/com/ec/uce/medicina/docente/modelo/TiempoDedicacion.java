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
 * Declaración de la clase TiempoDedicacion
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "tiempo_dedicacion", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_tdedi"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiempoDedicacion.findAll", query = "SELECT t FROM TiempoDedicacion t"),
    @NamedQuery(name = "TiempoDedicacion.findByIdTdedi", query = "SELECT t FROM TiempoDedicacion t WHERE t.idTdedi = :idTdedi"),
    @NamedQuery(name = "TiempoDedicacion.findByNomTdedi", query = "SELECT t FROM TiempoDedicacion t WHERE t.nomTdedi = :nomTdedi"),
    @NamedQuery(name = "TiempoDedicacion.findByNumHoras", query = "SELECT t FROM TiempoDedicacion t WHERE t.numHoras = :numHoras")})
public class TiempoDedicacion implements Serializable {
    //Declaración de atributos

    private static final long serialVersionUID = 1L;
     /**
     * Id de la TiempoDedicacion de un docente generado automaticamente para identificar a un
     * objeto de tipo TiempoDedicacion
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tdedi", nullable = false)
    private Integer idTdedi;
    /** Nombre del tiempo de dedicación*/
    @Basic(optional = false)
    @NotNull(message = "El nombre es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "nom_tdedi", nullable = false, length = 50)
    private String nomTdedi;
    /**Número de horas del tiempo de dedicación*/
    @Basic(optional = false)
    @NotNull(message = "El número de horas es un campo obligatorio")
    @Column(name = "num_horas", nullable = false)
    private int numHoras;
    /**
     * Declaración de una lista de objetos de tipo RelacionLaboral
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTdedi")
    private List<RelacionLaboral> relacionlaboralList;
/**Constructor por defecto*/
    public TiempoDedicacion() {
    }


    /**
     * Constructor que recibe un parametro
     * @param idTdedi
     */
    public TiempoDedicacion(Integer idTdedi) {
        this.idTdedi = idTdedi;
    }
   /**
     * Constructor que recibe 3 parametros
     * @param idTdedi
     * @param nomTdedi
     * @param numHoras
     */
    public TiempoDedicacion(Integer idTdedi, String nomTdedi, int numHoras) {
        this.idTdedi = idTdedi;
        this.nomTdedi = nomTdedi;
        this.numHoras = numHoras;
    }
 
    /**
     * Retorna el id del tiempo de dedicación
     * @return idTdedi
     */
    public Integer getIdTdedi() {
        return idTdedi;
    }
 /**
     * Modifica el id del tiempo de dedicación
     * @param  idTdedi
     */
    public void setIdTdedi(Integer idTdedi) {
        this.idTdedi = idTdedi;
    }
   /**
     * Retorna el nombre del tiempo de dedicación
     * @return nomTdedi
     */
    public String getNomTdedi() {
        return nomTdedi;
    }
 /**
     * Modifica el nombre del tiempo de dedicación
     * @param  nomTdedi
     */
    public void setNomTdedi(String nomTdedi) {
        this.nomTdedi = nomTdedi;
    }
 /**
     * Retorna el número de horas del tiempo de dedicación
     * @return numHoras
     */
    public int getNumHoras() {
        return numHoras;
    }
/**
     * Modifica el número de horas del tiempo de dedicación
     * @param  numHoras
     */
    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
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
     * Modifica una lista de objetos de tipo RelacionLaboral
     * @param  relacionlaboralList
     */
    public void setRelacionlaboralList(List<RelacionLaboral> relacionlaboralList) {
        this.relacionlaboralList = relacionlaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTdedi != null ? idTdedi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiempoDedicacion)) {
            return false;
        }
        TiempoDedicacion other = (TiempoDedicacion) object;
        if ((this.idTdedi == null && other.idTdedi != null) || (this.idTdedi != null && !this.idTdedi.equals(other.idTdedi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.TiempoDedicacion[ idTdedi=" + idTdedi + " ]";
    }
    
}
