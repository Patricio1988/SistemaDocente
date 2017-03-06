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
 * Declaración de la clase Categoria
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "categoria", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_categoria"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c"),
    @NamedQuery(name = "Categoria.findByIdCategoria", query = "SELECT c FROM Categoria c WHERE c.idCategoria = :idCategoria"),
    @NamedQuery(name = "Categoria.findByNomCategoria", query = "SELECT c FROM Categoria c WHERE c.nomCategoria = :nomCategoria")})
public class Categoria implements Serializable {
// Atributos de la clase
    private static final long serialVersionUID = 1L;
    /**
     * Atributo para el id que se genera automáticamente para la clase
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_categoria", nullable = false)
    private Integer idCategoria;
    /**
     * Atributo para el nombre de la categoria
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la categoría es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "nom_categoria", nullable = false, length = 50)
    private String nomCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private List<RelacionLaboral> relacionlaboralList;
    /**
     * Constructor por defecto para la clase
     */

    public Categoria() {
    }
  /**
     * Constructor que recibe un parametro
     * @param idCategoria
     */
    public Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
  /**
     * Constructor que recibe 2 parametro
     * @param idCategoria
     * @param nomCategoria
     */
    public Categoria(Integer idCategoria, String nomCategoria) {
        this.idCategoria = idCategoria;
        this.nomCategoria = nomCategoria;
    }

    /**
     * Retorna el id de la Categoría
     * @return idCategoria
     */
    public Integer getIdCategoria() {
        return idCategoria;
    }
   /**
     * Modifica el id de la Categoría
     * @param  idCategoria
     */
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
/**
 * Retorna el nombre de la categoría
     * @return nomCategoria
 */
    public String getNomCategoria() {
        return nomCategoria;
    }
    /**
 * Modifica el nombre de la categoría
     * @param  nomCategoria
 */

    public void setNomCategoria(String nomCategoria) {
        this.nomCategoria = nomCategoria;
    }

    @XmlTransient
    public List<RelacionLaboral> getRelacionlaboralList() {
        return relacionlaboralList;
    }

    public void setRelacionlaboralList(List<RelacionLaboral> relacionlaboralList) {
        this.relacionlaboralList = relacionlaboralList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCategoria != null ? idCategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.idCategoria == null && other.idCategoria != null) || (this.idCategoria != null && !this.idCategoria.equals(other.idCategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Categoria[ idCategoria=" + idCategoria + " ]";
    }
    
}
