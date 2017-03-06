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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Declaración de la clase Facultad
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "facultad", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_facultad"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facultad.findAll", query = "SELECT f FROM Facultad f"),
    @NamedQuery(name = "Facultad.findByIdFacultad", query = "SELECT f FROM Facultad f WHERE f.idFacultad = :idFacultad"),
    @NamedQuery(name = "Facultad.findByNomFacultad", query = "SELECT f FROM Facultad f WHERE f.nomFacultad = :nomFacultad"),
    @NamedQuery(name = "Facultad.findByTelefono", query = "SELECT f FROM Facultad f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Facultad.findByDireccionFacultad", query = "SELECT f FROM Facultad f WHERE f.direccionFacultad = :direccionFacultad"),
    @NamedQuery(name = "Facultad.findByDirectorFacultad", query = "SELECT f FROM Facultad f WHERE f.directorFacultad = :directorFacultad")})
public class Facultad implements Serializable {
//Declaración de atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Facultad generado automaticamente para identificar a un objeto
     * de tipo Facultad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_facultad", nullable = false)
    private Integer idFacultad;
    /**
     * Nombre de la facultad
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la Facultad es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "nom_facultad", nullable = false, length = 100)
    private String nomFacultad;
    /**
     * Teléfono de la facultad
     */
    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;
    /**
     * Dirección de la facultad
     */
    @Basic(optional = false)
    @NotNull(message = "La dirección de la Facultad es un campo obligatorio")
    @Size(min = 1, max = 225)
    @Column(name = "direccion_facultad", nullable = false, length = 225)
    private String direccionFacultad;
    /**
     * Director de la facultad
     */
    @Size(max = 300)
    @Column(name = "director_facultad", length = 300)
    private String directorFacultad;
    /**
     * Declaración de una lista de obejtos de tipo Carrera
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFacultad")
    private List<Carrera> carreraList;
    /**
     * Declaración del objeto de tipo Universidad
     */

    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad", nullable = false)
    @ManyToOne(optional = false)
    private Universidad idUniversidad;

    /**
     * Constructor por defecto
     */
    public Facultad() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idFacultad
     */
    public Facultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idFacultad
     * @param nomFacultad
     * @param direccionFacultad
     */
    public Facultad(Integer idFacultad, String nomFacultad, String direccionFacultad) {
        this.idFacultad = idFacultad;
        this.nomFacultad = nomFacultad;
        this.direccionFacultad = direccionFacultad;
    }

    /**
     * Retorna el id de la facultad
     *
     * @return idFacultad
     */
    public Integer getIdFacultad() {
        return idFacultad;
    }

    /**
     * Modifica el id de la facultad
     *
     * @param idFacultad
     */
    public void setIdFacultad(Integer idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * Retorna el nombre de la facultad
     *
     * @return nomFacultad
     */
    public String getNomFacultad() {
        return nomFacultad;
    }

    /**
     * Modifica el nombre de la facultad
     *
     * @param nomFacultad
     */
    public void setNomFacultad(String nomFacultad) {
        this.nomFacultad = nomFacultad;
    }

    /**
     * Retorna el teléfono de la facultad
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el teléfono de la facultad
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna la dirección de la facultad
     *
     * @return direccionFacultad
     */
    public String getDireccionFacultad() {
        return direccionFacultad;
    }

    /**
     * Modifica la dirección de la facultad
     *
     * @param direccionFacultad
     */
    public void setDireccionFacultad(String direccionFacultad) {
        this.direccionFacultad = direccionFacultad;
    }

    /**
     * Retorna el nombre del director de la facultad
     *
     * @return directorFacultad
     */
    public String getDirectorFacultad() {
        return directorFacultad;
    }

    /**
     * Modifica el nombre del director de la facultad
     *
     * @param directorFacultad
     */
    public void setDirectorFacultad(String directorFacultad) {
        this.directorFacultad = directorFacultad;
    }

    /**
     * Retorna una lista de Carreras
     *
     * @return carreraList
     */
    @XmlTransient
    public List<Carrera> getCarreraList() {
        return carreraList;
    }
      /**
     * Modifica una lista de Carreras
     *
     * @param  carreraList
     */

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }
  /**
     * Retorna una Universidad
     *
     * @return idUniversidad
     */
    public Universidad getIdUniversidad() {
        return idUniversidad;
    }
  /**
     * Modifica una Universidad
     *
     * @param  idUniversidad
     */
    public void setIdUniversidad(Universidad idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacultad != null ? idFacultad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facultad)) {
            return false;
        }
        Facultad other = (Facultad) object;
        if ((this.idFacultad == null && other.idFacultad != null) || (this.idFacultad != null && !this.idFacultad.equals(other.idFacultad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Facultad[ idFacultad=" + idFacultad + " ]";
    }

}
