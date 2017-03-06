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
 * Declaracion de la clase Actividad
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "actividad", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_actividad"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actividad.findAll", query = "SELECT a FROM Actividad a"),
    @NamedQuery(name = "Actividad.findByIdActivida", query = "SELECT a FROM Actividad a WHERE a.idActivida = :idActivida"),
    @NamedQuery(name = "Actividad.findByNomActividad", query = "SELECT a FROM Actividad a WHERE a.nomActividad = :nomActividad"),
    @NamedQuery(name = "Actividad.findByMateria", query = "SELECT a FROM Actividad a WHERE a.materia = :materia")})
public class Actividad implements Serializable {
    //Declaración de los atributos privados

    private static final long serialVersionUID = 1L;
    /**
     * Atributo entero para el id generado automáticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_activida", nullable = false)
    private Integer idActivida;
    /**
     * Atributo de tipo String para identificar el nombre de la actividad
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la actividad es un campo obligatorio")
    @Size(min = 1, max = 80, message = "El nombre de actividad debe tener maximo 80 caracteres")
    @Column(name = "nom_actividad", nullable = false, length = 80)
    private String nomActividad;
    /**
     * atributo de tipo boolean que indica si el tipo de actividad va a estar
     * asociado al ingreso de materias o no tenemos true que la actividad si
     * esta asociado al ingreso de materias false cuando no esta asociado al
     * ingreso de materias
     */
    @Basic(optional = false)
    @NotNull(message = "El campo materia es obligatorio")
    @Column(name = "materia", nullable = false)
    private boolean materia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idActivida")
    /**
     * Atributo de tipo list que indica que la actividad puede tener una o
     * varios carga horaria
     */
    private List<DocentesMallaContratoMateria> docentesMallaContratoMateriaList;

    /**
     * creacion del constructor por defecto que no recibe parametros
     */
    public Actividad() {
    }

    /**
     * Constructor de la clase Actividad que recibe como parametros el valor un
     * id
     *
     * @param idActivida
     */
    public Actividad(Integer idActivida) {
        this.idActivida = idActivida;
    }

    /**
     * Constructor de la clase Actividad que recibe como parametros el valor un
     * @param idActivida identificador de la clase actividad
     * @param nomActividad nombre de la actividad
     * @param materia para verificar sila actividad esta asociada o no al
     * ingreso de materias
     */
    public Actividad(Integer idActivida, String nomActividad, boolean materia) {
        this.idActivida = idActivida;
        this.nomActividad = nomActividad;
        this.materia = materia;
    }

    /**
     * Devuelve el id de la Actividad
     *
     * @return idActivida
     */
    public Integer getIdActivida() {
        return idActivida;
    }

    /**
     * Permite modificar el id de la Actividad
     *
     * @param idActivida
     */
    public void setIdActivida(Integer idActivida) {
        this.idActivida = idActivida;
    }

    /**
     * Devulelve el nombre de una actividad
     *
     * @return nomActividad
     */
    public String getNomActividad() {
        return nomActividad;
    }

    /**
     * Permite modificar el valor del nombre de una actividad
     *
     * @param nomActividad
     */
    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }

    /**
     * Retorna el valor del atributo materia true si permite el ingreso de
     * materia y false y no lo permite
     *
     * @return <ul>
     * <li>true: si permite el ingreso de mateias</li>
     * <li>false: no permite el ingreso de materias</li>
     * </ul>
     */
    public boolean getMateria() {
        return materia;
    }

    /**
     * Permite modificar el valor del atributo materia para ver si la actividad
     * permite o no el ingreso de materias
     *
     * @param materia
     */
    public void setMateria(boolean materia) {
        this.materia = materia;
    }

    @XmlTransient
    public List<DocentesMallaContratoMateria> getDocentesMallaContratoMateriaList() {
        return docentesMallaContratoMateriaList;
    }

    public void setDocentesMallaContratoMateriaList(List<DocentesMallaContratoMateria> docentesMallaContratoMateriaList) {
        this.docentesMallaContratoMateriaList = docentesMallaContratoMateriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActivida != null ? idActivida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.idActivida == null && other.idActivida != null) || (this.idActivida != null && !this.idActivida.equals(other.idActivida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Actividad[ idActivida=" + idActivida + " ]";
    }

}
