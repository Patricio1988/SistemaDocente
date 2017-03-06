/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaración de la clase Materia
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "materia", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Materia.findAll", query = "SELECT m FROM Materia m"),
    @NamedQuery(name = "Materia.findByIdMateria", query = "SELECT m FROM Materia m WHERE m.idMateria = :idMateria"),
    @NamedQuery(name = "Materia.findByNomMateria", query = "SELECT m FROM Materia m WHERE m.nomMateria = :nomMateria"),
    @NamedQuery(name = "Materia.findByCodMateria", query = "SELECT m FROM Materia m WHERE m.codMateria = :codMateria"),
    @NamedQuery(name = "Materia.findBySemestre", query = "SELECT m FROM Materia m WHERE m.semestre = :semestre"),
    @NamedQuery(name = "Materia.findByNumHoras", query = "SELECT m FROM Materia m WHERE m.numHoras = :numHoras"),
    @NamedQuery(name = "Materia.findByCreditos", query = "SELECT m FROM Materia m WHERE m.creditos = :creditos"),
    @NamedQuery(name = "Materia.findByObserMateria", query = "SELECT m FROM Materia m WHERE m.obserMateria = :obserMateria")})
public class Materia implements Serializable {
//Declaración de Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Materia de un docente generado automaticamente para identificar
     * a un objeto de tipo Materia
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_materia", nullable = false)
    private Integer idMateria;
    /**
     * Nombre de la matería
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la materia es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "nom_materia", nullable = false, length = 100)
    private String nomMateria;
    /**
     * Código de la matería
     */
    @Basic(optional = false)
    @NotNull(message = "El código de la materia es un campo obligatorio")
    @Size(min = 1, max = 10)
    @Column(name = "cod_materia", nullable = false, length = 10)
    private String codMateria;
    /**
     * Semestre de la materia
     */
    @Size(max = 40)
    @Column(name = "semestre", length = 40)
    private String semestre;
    /**
     * Número de horas de la matería
     */
    @Basic(optional = false)
    @NotNull(message = "El número de horas es un campo obligatorio")
    @Column(name = "num_horas", nullable = false)
    private int numHoras;
    /**
     * Número de créditos de la materia
     */
    @Basic(optional = false)
    @NotNull(message = "El número de créditos es un campo obligatorio")
    @Column(name = "creditos", nullable = false)
    private int creditos;
    /**
     * Observaciones de la materia
     */
    @Size(max = 200)
    @Column(name = "obser_materia", length = 200)
    private String obserMateria;
    /**
     * Declaracion de una lista de DocentesMallaContratoMateria
     */
    @OneToMany(mappedBy = "idMateria")
    private List<DocentesMallaContratoMateria> docentesMallaContratoMateriaList;
    /**
     * Declaración de objeto de tipo matería
     */
    @JoinColumn(name = "id_malla", referencedColumnName = "id_malla", nullable = false)
    @ManyToOne(optional = false)
    private Malla idMalla;

    /**
     * Constructor por defecto
     */
    public Materia() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idMateria
     */
    public Materia(Integer idMateria) {
        this.idMateria = idMateria;
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idMateria
     */
    /**
     * Constructor que recibe 5 parametros
     *
     * @param idMateria
     * @param nomMateria
     * @param codMateria
     * @param numHoras
     * @param creditos
     */
    public Materia(Integer idMateria, String nomMateria, String codMateria, int numHoras, int creditos) {
        this.idMateria = idMateria;
        this.nomMateria = nomMateria;
        this.codMateria = codMateria;
        this.numHoras = numHoras;
        this.creditos = creditos;
    }

    /**
     * Retorna el id de la matería
     *
     * @return idMateria
     */
    public Integer getIdMateria() {
        return idMateria;
    }

    /**
     * Modifica el id de la matería
     *
     * @param idMateria
     */
    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    /**
     * Retorna el nombre de la matería
     *
     * @return nomMateria
     */

    public String getNomMateria() {
        return nomMateria;
    }

    /**
     * Modifica el nombre de la matería
     *
     * @param nomMateria
     */

    public void setNomMateria(String nomMateria) {
        this.nomMateria = nomMateria;
    }

    /**
     * Retorna el código de la matería
     *
     * @return codMateria
     */

    public String getCodMateria() {
        return codMateria;
    }

    /**
     * Modifica el código de la matería
     *
     * @param codMateria
     */
    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    /**
     * Retorna el semestre de la matería
     *
     * @return semestre
     */
    public String getSemestre() {
        return semestre;
    }

    /** Modifica el semestre de la matería
     *@param semestre
     */
    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
     /**
     * Retorna el número de horas de la matería
     *
     * @return numHoras
     */

    public int getNumHoras() {
        return numHoras;
    }
       /**
     * Modifica el número de horas de la matería
     *
     * @param  numHoras
     */

    public void setNumHoras(int numHoras) {
        this.numHoras = numHoras;
    }
   /**
     * Retorna el número de créditos de la matería
     *
     * @return creditos
     */
    public int getCreditos() {
        return creditos;
    }
 /**
     * Modifica el número de créditos de la matería
     *
     * @param  creditos
     */
    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }
     /**
     * Retorna observaciones de la matería
     *
     * @return obserMateria
     */

    public String getObserMateria() {
        return obserMateria;
    }
  /**
     * Modifica observaciones de la matería
     *
     * @param  obserMateria
     */
    public void setObserMateria(String obserMateria) {
        this.obserMateria = obserMateria;
    }
  /**
     * Retorna una lista de obejtos de tipo DocentesMallaContratoMateria
     *
     * @return docentesMallaContratoMateriaList
     */
    @XmlTransient
    public List<DocentesMallaContratoMateria> getDocentesMallaContratoMateriaList() {
        return docentesMallaContratoMateriaList;
    }
     /**
     * Modifica una lista de obejtos de tipo DocentesMallaContratoMateria
     *
     * @param  docentesMallaContratoMateriaList
     */

    public void setDocentesMallaContratoMateriaList(List<DocentesMallaContratoMateria> docentesMallaContratoMateriaList) {
        this.docentesMallaContratoMateriaList = docentesMallaContratoMateriaList;
    }
 /**
     * Retorna un objeto de tipo Malla
     *
     * @return idMalla
     */
    public Malla getIdMalla() {
        return idMalla;
    }
     /**
     * Modifica un objeto de tipo Malla
     *
     * @param  idMalla
     */

    public void setIdMalla(Malla idMalla) {
        this.idMalla = idMalla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMateria != null ? idMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Materia)) {
            return false;
        }
        Materia other = (Materia) object;
        if ((this.idMateria == null && other.idMateria != null) || (this.idMateria != null && !this.idMateria.equals(other.idMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Materia[ idMateria=" + idMateria + " ]";
    }

}
