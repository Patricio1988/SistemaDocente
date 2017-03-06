/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaración de la clase Periodo
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "periodo", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Periodo.findAll", query = "SELECT p FROM Periodo p"),
    @NamedQuery(name = "Periodo.findByIdPeriodo", query = "SELECT p FROM Periodo p WHERE p.idPeriodo = :idPeriodo"),
    @NamedQuery(name = "Periodo.findByFeInPer", query = "SELECT p FROM Periodo p WHERE p.feInPer = :feInPer"),
    @NamedQuery(name = "Periodo.findByFeFinPer", query = "SELECT p FROM Periodo p WHERE p.feFinPer = :feFinPer"),
    @NamedQuery(name = "Periodo.findByNombreperiodo", query = "SELECT p FROM Periodo p WHERE p.nombrePeriodo = :nombrePeriodo"),
    @NamedQuery(name = "Periodo.findByEstadoPeriodo", query = "SELECT p FROM Periodo p WHERE p.estadoPeriodo = :estadoPeriodo")})
public class Periodo implements Serializable {
//Declaración de atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Periodo de un docente generado automaticamente para identificar
     * a un objeto de tipo Periodo
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_periodo", nullable = false)
    private Integer idPeriodo;
    /**
     * Fecha en la cual inicio el periodo de clases
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de inicio es una campo obligatorio")
    @Column(name = "fe_in_per", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date feInPer;
    /**
     * Fecha en la cual finalizó el periodo de clases
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de fin es una campo obligatorio")
    @Column(name = "fe_fin_per", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date feFinPer;
    /**
     * Nombre del periodo
     */
    @Basic(optional = false)
    @Column(name = "nombre_periodo")
    @NotNull(message = "El nombre del período es un campo obligatorio")
    @Size(min = 1, max = 50, message = "El periodo debe tener menos de 50 caracteres")
    private String nombrePeriodo;
    /**
     * estado del periodo tenemos: true activo, false:inactivo
     */
    @Column(name = "estado_periodo")
    private Boolean estadoPeriodo;
    /**
     * Declaracion de una lista de objetos de tipo docentesMallaContratoMateria
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodo")
    private List<CarreraDocente> carreraDocenteList;


    /**
     * Constructor por defecto
     */
    public Periodo() {
    }

    /**
     * Constructor que recibe un parámetro
     *
     * @param idPeriodo
     */
    public Periodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    /**
     * Constructor que recibe un parámetro
     *
     * @param idPeriodo
     * @param feInPer
     * @param feFinPer
     * @param nombreperiodo
     */

    public Periodo(Integer idPeriodo, Date feInPer, Date feFinPer, String nombreperiodo) {
        this.idPeriodo = idPeriodo;
        this.feInPer = feInPer;
        this.feFinPer = feFinPer;
        this.nombrePeriodo = nombreperiodo;
    }

    /**
     * Retorna el id del Periodo
     *
     * @return idPeriodo
     */
    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    /**
     * Modifica el id del Periodo
     *
     * @param idPeriodo
     */
    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    /**
     * Retorna la fecha de inicio de un período académico
     *
     * @return feInPer
     */
    public Date getFeInPer() {
        return feInPer;
    }

    /**
     * Modifica la fecha de fin de un período académico
     *
     * @param feInPer
     */

    public void setFeInPer(Date feInPer) {
        this.feInPer = feInPer;
    }

    /**
     * Retorna la fecha de fin de un período académico
     *
     * @return feFinPer
     */

    public Date getFeFinPer() {
        return feFinPer;
    }

    /**
     * Modifica la fecha de fin de un período académico
     *
     * @param feFinPer
     */
    public void setFeFinPer(Date feFinPer) {
        this.feFinPer = feFinPer;
    }

    /**
     * Retorna el nombre de un período académico
     *
     * @return nombrePeriodo
     */
    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    /**
     * Modifica el nombre de un período académico
     *
     * @param nombrePeriodo
     */
    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    /**
     * Retorna el estado de un período académico
     *
     * @return <ul><li>true:período activo</li>
     * <li>false:período inactivo</li>
     * </ul>
     */
    public Boolean getEstadoPeriodo() {
        return estadoPeriodo;
    }

    /**
     * Modifica el estado de un período académico
     *
     * @param estadoPeriodo
     */

    public void setEstadoPeriodo(Boolean estadoPeriodo) {
        this.estadoPeriodo = estadoPeriodo;
    }
/**Retorna una lista de objetos de tipo DocentesMallaContratoMateria
     * @return docentesMallaContratoMateriaList
 */
    @XmlTransient
    public List<CarreraDocente> getcarreraDocenteList() {
        return carreraDocenteList;
    }
/**Modifica una lista de objetos de tipo DocentesMallaContratoMateria
     * @param  carreraDocenteList
 */
    public void setcarreraDocenteListList(List<CarreraDocente> carreraDocenteList) {
        this.carreraDocenteList = carreraDocenteList;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodo != null ? idPeriodo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.idPeriodo == null && other.idPeriodo != null) || (this.idPeriodo != null && !this.idPeriodo.equals(other.idPeriodo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Periodo[ idPeriodo=" + idPeriodo + " ]";
    }

}
