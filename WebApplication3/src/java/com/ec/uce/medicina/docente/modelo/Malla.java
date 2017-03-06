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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * Declaración de la clase Malla
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "malla", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Malla.findAll", query = "SELECT m FROM Malla m"),
    @NamedQuery(name = "Malla.findByIdMalla", query = "SELECT m FROM Malla m WHERE m.idMalla = :idMalla"),
    @NamedQuery(name = "Malla.findByCodMalla", query = "SELECT m FROM Malla m WHERE m.codMalla = :codMalla"),
    @NamedQuery(name = "Malla.findByNomMalla", query = "SELECT m FROM Malla m WHERE m.nomMalla = :nomMalla"),
    @NamedQuery(name = "Malla.findByOrganizacionMalla", query = "SELECT m FROM Malla m WHERE m.organizacionMalla = :organizacionMalla"),
    @NamedQuery(name = "Malla.findByMesesSinTesis", query = "SELECT m FROM Malla m WHERE m.mesesSinTesis = :mesesSinTesis"),
    @NamedQuery(name = "Malla.findByMesesConTesis", query = "SELECT m FROM Malla m WHERE m.mesesConTesis = :mesesConTesis"),
    @NamedQuery(name = "Malla.findByCreditosSinTesis", query = "SELECT m FROM Malla m WHERE m.creditosSinTesis = :creditosSinTesis"),
    @NamedQuery(name = "Malla.findByCreditosConTesis", query = "SELECT m FROM Malla m WHERE m.creditosConTesis = :creditosConTesis"),
    @NamedQuery(name = "Malla.findByFechaIniMalla", query = "SELECT m FROM Malla m WHERE m.fechaIniMalla = :fechaIniMalla"),
    @NamedQuery(name = "Malla.findByFechaFinMalla", query = "SELECT m FROM Malla m WHERE m.fechaFinMalla = :fechaFinMalla"),
    @NamedQuery(name = "Malla.findByNumSemestres", query = "SELECT m FROM Malla m WHERE m.numSemestres = :numSemestres"),
    @NamedQuery(name = "Malla.findByNombreDocuMalla", query = "SELECT m FROM Malla m WHERE m.nombreDocMalla = :nombreDocMalla"),
    @NamedQuery(name = "Malla.findByUrlDocuMalla", query = "SELECT m FROM Malla m WHERE m.idDocMalla = :idDocMalla"),
    @NamedQuery(name = "Malla.findByEstadoMalla", query = "SELECT m FROM Malla m WHERE m.estadoMalla = :estadoMalla")})
public class Malla implements Serializable {
    //Declaracón de Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Malla de un docente generado automaticamente para identificar a
     * un objeto de tipo Malla
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_malla", nullable = false)
    private Integer idMalla;
    /**
     * Código de la malla
     */
    @Basic(optional = false)
    @NotNull(message = "El código de la Malla es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "cod_malla", nullable = false, length = 100)
    private String codMalla;
    /**
     * Nombre de la malla
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la Malla es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "nom_malla", nullable = false, length = 100)
    private String nomMalla;
    /**
     * Organización d de la malla curricular
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un tipo de Organización")
    @Size(min = 1, max = 40)
    @Column(name = "organizacion_malla", nullable = false, length = 40)
    private String organizacionMalla;
    /**
     * Duración de la carrera en meses sin tomar en cuenta los meses dedicados a
     * la realización de la tesis
     */
    @Basic(optional = false)
    @NotNull(message = "Los meses sin Tesis es un campo obligatorio")
    @Column(name = "meses_sin_tesis", nullable = false)
    private int mesesSinTesis;
    /**
     * Duración de la carrera en meses tomando en cuenta los meses dedicados a
     * la realización de la tesis
     */
    @Basic(optional = false)
    @NotNull(message = "Los meses con Tesis es un campo obligatorio")
    @Column(name = "meses_con_tesis", nullable = false)
    private int mesesConTesis;
    /**
     * Número de créditos totales de la carrera sin tomar en cuenta los créditos
     * asignados a la realización de la tesis
     */
    @Basic(optional = false)
    @NotNull(message = "Los creditos sin Tesis es un campo obligatorio")
    @Column(name = "creditos_sin_tesis", nullable = false)
    private int creditosSinTesis;
    /**
     * Número de créditos totales de la carrera tomando en cuenta los créditos
     * asignados a la realización de la tesis
     */
    @Basic(optional = false)
    @NotNull(message = "Los creditos con Tesis es un campo obligatorio")
    @Column(name = "creditos_con_tesis", nullable = false)
    private int creditosConTesis;
    /**
     * Fecha de inicio de la malla curricular
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    @Column(name = "fecha_ini_malla", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIniMalla;
    /**
     * Fecha de Fin de la malla curricular
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de fin es un campo obligatorio")
    @Column(name = "fecha_fin_malla", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinMalla;
    /**
     * Número de semestres que posee la malla curricular
     */
    @Basic(optional = false)
    @NotNull(message = "El numero de semestre es un campo obligatorio")
    @Column(name = "num_semestres", nullable = false)
    private int numSemestres;
    /**
     * nombre del archivo pdf que sirve como evidencia
     */
    @Size(max = 150)
    @Column(name = "nombre_doc_malla", length = 150)
    private String nombreDocMalla;
    /**
     * url del archivo pdf que sirve como evidencia
     */
    @Size(max = 200)
    @Column(name = "id_doc_malla", length = 200)
    private String idDocMalla;
    /**
     * Estado en la qeu se encuentra la malla. tenemos:true:vigente,false:no
     * vigente
     */
    @Column(name = "estado_malla")
    private Boolean estadoMalla;
    /**
     * Declaración del objeto de tipo Carrera
     */
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    @ManyToOne(optional = false)
    private Carrera idCarrera;
    /**
     * Declaración de una lista de objetos de tipo Materia
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMalla")
    private List<Materia> materiaList;

    /**
     * Constructor por defecto
     */
    public Malla() {
    }

    /**
     * Constructorr que recibe un parametro
     *
     * @param idMalla
     */
    public Malla(Integer idMalla) {
        this.idMalla = idMalla;
    }

    /**
     * Constructorr que recibe 11 parametros
     *
     * @param idMalla
     * @param codMalla
     * @param nomMalla
     * @param mesesSinTesis
     * @param organizacionMalla
     * @param numSemestres
     * @param mesesConTesis
     * @param creditosSinTesis
     * @param fechaIniMalla
     * @param creditosConTesis
     * @param fechaFinMalla
     */
    public Malla(Integer idMalla, String codMalla, String nomMalla, String organizacionMalla, int mesesSinTesis, int mesesConTesis, int creditosSinTesis, int creditosConTesis, Date fechaIniMalla, Date fechaFinMalla, int numSemestres) {
        this.idMalla = idMalla;
        this.codMalla = codMalla;
        this.nomMalla = nomMalla;
        this.organizacionMalla = organizacionMalla;
        this.mesesSinTesis = mesesSinTesis;
        this.mesesConTesis = mesesConTesis;
        this.creditosSinTesis = creditosSinTesis;
        this.creditosConTesis = creditosConTesis;
        this.fechaIniMalla = fechaIniMalla;
        this.fechaFinMalla = fechaFinMalla;
        this.numSemestres = numSemestres;
    }

    /**
     * Retorna el id de la malla
     *
     * @return idMalla
     */
    public Integer getIdMalla() {
        return idMalla;
    }

    /**
     * Modifica el id de la malla
     *
     * @param idMalla
     */
    public void setIdMalla(Integer idMalla) {
        this.idMalla = idMalla;
    }

    /**
     * Retorna el código de la malla
     *
     * @return codMalla
     */
    public String getCodMalla() {
        return codMalla;
    }

    /**
     * Modifica el código de la malla
     *
     * @param codMalla
     */
    public void setCodMalla(String codMalla) {
        this.codMalla = codMalla;
    }

    /**
     * Retorna el nombre de la malla
     *
     * @return nomMalla
     */
    public String getNomMalla() {
        return nomMalla;
    }

    /**
     * Modifica el nombre de la malla
     *
     * @param nomMalla
     */
    public void setNomMalla(String nomMalla) {
        this.nomMalla = nomMalla;
    }

    /**
     * Retorna la organización de la malla
     *
     * @return organizacionMalla
     */
    public String getOrganizacionMalla() {
        return organizacionMalla;
    }

    /**
     * Modifica la organización de la malla
     *
     * @param organizacionMalla
     */
    public void setOrganizacionMalla(String organizacionMalla) {
        this.organizacionMalla = organizacionMalla;
    }

    /**
     * Retorna los mesis sin contar los meses que dura la realización de la
     * tesis
     *
     * @return mesesSinTesis
     */

    public int getMesesSinTesis() {
        return mesesSinTesis;
    }

    /**
     * Modifica los mesis sin contar los meses que dura la realización de la
     * tesis
     *
     * @param mesesSinTesis
     */
    public void setMesesSinTesis(int mesesSinTesis) {
        this.mesesSinTesis = mesesSinTesis;
    }
    /**
     * Retorna los mesis contando con los meses que dura la realización de la
     * tesis
     *
     * @return mesesConTesis
     */
    public int getMesesConTesis() {
        return mesesConTesis;
    }

    /**
     * Modifica los mesis contando con los meses que dura la realización de la
     * tesis
     *
     * @param  mesesConTesis
     */

    public void setMesesConTesis(int mesesConTesis) {
        this.mesesConTesis = mesesConTesis;
    }
    /**
     * Retorna el número de créditos sin contar los créditos en la realización de la
     * tesis
     *
     * @return creditosSinTesis
     */
    public int getCreditosSinTesis() {
        return creditosSinTesis;
    }
  /**
     * Modifica el número de créditos  sin contar los créditos en la realización de la
     * tesis
     *
     * @param  creditosSinTesis
     */
    public void setCreditosSinTesis(int creditosSinTesis) {
        this.creditosSinTesis = creditosSinTesis;
    }
      /**
     * Retorna el número de créditos  contando los créditos en la realización de la
     * tesis
     *
     * @return creditosConTesis
     */

    public int getCreditosConTesis() {
        return creditosConTesis;
    }
      /**
     * Modifica el número de créditos  contando los créditos en la realización de la
     * tesis
     *
     * @param  creditosConTesis
     */

    public void setCreditosConTesis(int creditosConTesis) {
        this.creditosConTesis = creditosConTesis;
    }
  /**
     * Retorna la fecha de inicio de la malla
     * 
     *
     * @return fechaIniMalla
     */
    public Date getFechaIniMalla() {
        return fechaIniMalla;
    }
  /**
     * Modifica la fecha de inicio  de la malla
     * 
     *
     * @param  fechaIniMalla
     */
    public void setFechaIniMalla(Date fechaIniMalla) {
        this.fechaIniMalla = fechaIniMalla;
    }
  /**
     * Retorna la fecha de fin de vigencia de la malla
     * 
     *
     * @return fechaFinMalla
     */
    public Date getFechaFinMalla() {
        return fechaFinMalla;
    }
      /**
     * Modifica la fecha de fin de vigencia de la malla
     * 
     *
     * @param  fechaFinMalla
     */

    public void setFechaFinMalla(Date fechaFinMalla) {
        this.fechaFinMalla = fechaFinMalla;
    }
  /**
     * Retorna el número de semestres de duración de la malla
     * 
     *
     * @return numSemestres
     */
    public int getNumSemestres() {
        return numSemestres;
    }
 /**
     * Modifica el número de semestres de duración de la malla
     * 
     *
     * @param  numSemestres
     */
    public void setNumSemestres(int numSemestres) {
        this.numSemestres = numSemestres;
    }
/**Devuelve el nombre del documento almacenado en el repositorio
     * @return nombreDocMalla
 */
    public String getNombreDocMalla() {
        return nombreDocMalla;
    }
/**Modifica el nombre del documento almacenado en el repositorio
     * @param  nombreDocMalla
 */
    public void setNombreDocMalla(String nombreDocMalla) {
        this.nombreDocMalla = nombreDocMalla;
    }
/**Devuelve el id del documento almacenado en el repositorio
     * @return idDocMalla
 */
    public String getIdDocMalla() {
        return idDocMalla;
    }
/**Modifica el id del documento almacenado en el repositorio
     * @param  idDocMalla
 */
    public void setIdDocMalla(String idDocMalla) {
        this.idDocMalla = idDocMalla;
    }
     
         /**
     * Retorna el estado de la malla
     * 
     *
     * @return <ul>
     * <li> true: Si la malla esta vigente</li>
     * *<li> false: Si la malla  no esta vigente</li>
     * </ul>
     */

    public Boolean getEstadoMalla() {
        return estadoMalla;
    }


    /**
     * Modifica el estado de la malla
     * @param estadoMalla
     */
    public void setEstadoMalla(Boolean estadoMalla) {
        this.estadoMalla = estadoMalla;
    }
    
      /**
     * Retorna el un objeto de tipo Carrera
     * @return idCarrera
     */
    public Carrera getIdCarrera() {
        return idCarrera;
    }
       /**
     * Modifica el un objeto de tipo Carrera
     * @param  idCarrera
     */

    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }
   /**
     * Retorna una lista de obejtos de tipo Materia
     * @return materiaList
     */
    @XmlTransient
    public List<Materia> getMateriaList() {
        return materiaList;
    }
 /**
     * Modifica una lista de obejtos de tipo Materia
     * @param  materiaList
     */
    public void setMateriaList(List<Materia> materiaList) {
        this.materiaList = materiaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMalla != null ? idMalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Malla)) {
            return false;
        }
        Malla other = (Malla) object;
        if ((this.idMalla == null && other.idMalla != null) || (this.idMalla != null && !this.idMalla.equals(other.idMalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Malla[ idMalla=" + idMalla + " ]";
    }

}
