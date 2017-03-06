/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Declaración de la clase DocentesMallaContratoMateria
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "docentes_malla_contrato_materia", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DocentesMallaContratoMateria.findAll", query = "SELECT d FROM DocentesMallaContratoMateria d"),
    @NamedQuery(name = "DocentesMallaContratoMateria.findByIdDocenteMateriaMalla", query = "SELECT d FROM DocentesMallaContratoMateria d WHERE d.idDocenteMateriaMalla = :idDocenteMateriaMalla"),
    @NamedQuery(name = "DocentesMallaContratoMateria.findByNumHorasAsignatura", query = "SELECT d FROM DocentesMallaContratoMateria d WHERE d.numHorasAsignatura = :numHorasAsignatura"),
    @NamedQuery(name = "DocentesMallaContratoMateria.findByNumParaleslos", query = "SELECT d FROM DocentesMallaContratoMateria d WHERE d.numParaleslos = :numParaleslos"),
    @NamedQuery(name = "DocentesMallaContratoMateria.findByNumEstudiantes", query = "SELECT d FROM DocentesMallaContratoMateria d WHERE d.numEstudiantes = :numEstudiantes"),
    @NamedQuery(name = "DocentesMallaContratoMateria.findByObsDocMatMalla", query = "SELECT d FROM DocentesMallaContratoMateria d WHERE d.obsDocMatMalla = :obsDocMatMalla")})
public class DocentesMallaContratoMateria implements Serializable {
//declaracion de Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id del DocentesMallaContratoMateria generado automaticamente para
     * identificar a un objeto de tipo DocentesMallaContratoMateria
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_docente_materia_malla", nullable = false)
    private Integer idDocenteMateriaMalla;
    /**
     * Número de horas que el docente dedica a impartir horas en la diferente
     * actividad
     */
    @Basic(optional = false)
    @NotNull(message = "El número de horas es un campo obligatorio")
    @Column(name = "num_horas_asignatura", nullable = false)
    private int numHorasAsignatura;
    /**
     * Número de parralelos en el cual el docente imparte clases
     */
    @Column(name = "num_paraleslos")
    private Integer numParaleslos;
    /**
     * Número de estudiantes a los cuales el docente imparte clases
     */
    @Column(name = "num_estudiantes")
    private Integer numEstudiantes;
    /**
     * Observaciones
     */
    @Size(max = 200)
    @Column(name = "obs_doc_mat_malla", length = 200)
    private String obsDocMatMalla;
    /**
     * Instancia de la clase Actividad
     */
    @JoinColumn(name = "id_activida", referencedColumnName = "id_activida", nullable = false)
    @ManyToOne(optional = false)
    private Actividad idActivida;
    /**
     * Instancia de la clase Materia
     */
    @JoinColumn(name = "id_materia", referencedColumnName = "id_materia")
    @ManyToOne
    private Materia idMateria;

    @JoinColumn(name = "id_re_laboral", referencedColumnName = "id_re_laboral", nullable = false)
    @ManyToOne(optional = false)
    /**
     * Instancia de la clase Relacionlaboral
     */
    private RelacionLaboral idReLaboral;

    /**
     * Constructor por defecto
     */
    public DocentesMallaContratoMateria() {
    }

    /**
     * Constructor que recibe como parametros
     *
     * @param idDocenteMateriaMalla
     */
    public DocentesMallaContratoMateria(Integer idDocenteMateriaMalla) {
        this.idDocenteMateriaMalla = idDocenteMateriaMalla;
    }

    /**
     * Constructor que recibe como parametros
     *
     * @param idDocenteMateriaMalla
     * @param numHorasAsignatura
     */
    public DocentesMallaContratoMateria(Integer idDocenteMateriaMalla, int numHorasAsignatura) {
        this.idDocenteMateriaMalla = idDocenteMateriaMalla;
        this.numHorasAsignatura = numHorasAsignatura;
    }

    /**
     * Retorna el id del carga horaria
     *
     * @return idDocenteMateriaMalla
     */
    public Integer getIdDocenteMateriaMalla() {
        return idDocenteMateriaMalla;
    }

    /**
     * Modifica el id del carga horaria
     *
     * @param idDocenteMateriaMalla
     */
    public void setIdDocenteMateriaMalla(Integer idDocenteMateriaMalla) {
        this.idDocenteMateriaMalla = idDocenteMateriaMalla;
    }

    /**
     * Retorna el número de horas que imparte el docente por asignatura
     *
     * @return numHorasAsignatura
     */
    public int getNumHorasAsignatura() {
        return numHorasAsignatura;
    }

    /**
     * Modifica el número de horas que imparte el docente por asignatura
     *
     * @param numHorasAsignatura
     */
    public void setNumHorasAsignatura(int numHorasAsignatura) {
        this.numHorasAsignatura = numHorasAsignatura;
    }

    /**
     * Retorna el número de paralelos que imparte el docente por asignatura
     *
     * @return numParaleslos
     */

    public Integer getNumParaleslos() {
        return numParaleslos;
    }

    /**
     * Modifica el número de paralelos que imparte el docente por asignatura
     *
     * @param numParaleslos
     */

    public void setNumParaleslos(Integer numParaleslos) {
        this.numParaleslos = numParaleslos;
    }

    /**
     * Retorna el número de estudiantes a los cuales el docente imparte clases
     * por asignatura
     *
     * @return numEstudiantes
     */
    public Integer getNumEstudiantes() {
        return numEstudiantes;
    }

    /**
     * Modifica el número de estudiantes a los cuales el docente imparte clases
     * por asignatura
     *
     * @param numEstudiantes
     */
    public void setNumEstudiantes(Integer numEstudiantes) {
        this.numEstudiantes = numEstudiantes;
    }

    /**
     * Retorna las observaciones al ingresar los datos
     *
     * @return obsDocMatMalla
     */
    public String getObsDocMatMalla() {
        return obsDocMatMalla;
    }

    /**
     * Modifica las observaciones al ingresar los datos
     *
     * @param obsDocMatMalla
     */
    public void setObsDocMatMalla(String obsDocMatMalla) {
        this.obsDocMatMalla = obsDocMatMalla;
    }

    /**
     * Retorna el objeto de tipo Actividad
     *
     * @return idActivida
     */
    public Actividad getIdActivida() {
        return idActivida;
    }

    /**
     * Modifica el objeto de tipo Actividad
     *
     * @param idActivida
     */
    public void setIdActivida(Actividad idActivida) {
        this.idActivida = idActivida;
    }
     /**
     * Retorna el objeto de tipo Materia
     *
     * @return idMateria
     */

    public Materia getIdMateria() {
        return idMateria;
    }
/**
     * Modifica el objeto de tipo Materia
     *
     * @param  idMateria
     */
    public void setIdMateria(Materia idMateria) {
        this.idMateria = idMateria;
    }

/**
     * Retorna el objeto de tipo RelacionLaboral
     *
     * @return idReLaboral
     */
    public RelacionLaboral getIdReLaboral() {
        return idReLaboral;
    }
/**
     * Modifica el objeto de tipo RelacionLaboral
     *
     * @param  idReLaboral
     */
    public void setIdReLaboral(RelacionLaboral idReLaboral) {
        this.idReLaboral = idReLaboral;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocenteMateriaMalla != null ? idDocenteMateriaMalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DocentesMallaContratoMateria)) {
            return false;
        }
        DocentesMallaContratoMateria other = (DocentesMallaContratoMateria) object;
        if ((this.idDocenteMateriaMalla == null && other.idDocenteMateriaMalla != null) || (this.idDocenteMateriaMalla != null && !this.idDocenteMateriaMalla.equals(other.idDocenteMateriaMalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria[ idDocenteMateriaMalla=" + idDocenteMateriaMalla + " ]";
    }

}
