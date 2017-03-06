/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Declaración de la clase RelacionLaboral
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "relacion_laboral", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RelacionLaboral.findAll", query = "SELECT r FROM RelacionLaboral r"),
    @NamedQuery(name = "RelacionLaboral.findByIdReLaboral", query = "SELECT r FROM RelacionLaboral r WHERE r.idReLaboral = :idReLaboral"),
    @NamedQuery(name = "RelacionLaboral.findByTipoPersonal", query = "SELECT r FROM RelacionLaboral r WHERE r.tipoPersonal = :tipoPersonal"),
    @NamedQuery(name = "RelacionLaboral.findByNumDocumento", query = "SELECT r FROM RelacionLaboral r WHERE r.numDocumento = :numDocumento"),
    @NamedQuery(name = "RelacionLaboral.findByContratoRelacionado", query = "SELECT r FROM RelacionLaboral r WHERE r.contratoRelacionado = :contratoRelacionado"),
    @NamedQuery(name = "RelacionLaboral.findByFechaInicioContrato", query = "SELECT r FROM RelacionLaboral r WHERE r.fechaInicioContrato = :fechaInicioContrato"),
    @NamedQuery(name = "RelacionLaboral.findByFechaFinContrato", query = "SELECT r FROM RelacionLaboral r WHERE r.fechaFinContrato = :fechaFinContrato"),
    @NamedQuery(name = "RelacionLaboral.findByIngresoPorConcurso", query = "SELECT r FROM RelacionLaboral r WHERE r.ingresoPorConcurso = :ingresoPorConcurso"),
    @NamedQuery(name = "RelacionLaboral.findByTiempoDedicacion", query = "SELECT r FROM RelacionLaboral r WHERE r.tiempoDedicacion = :tiempoDedicacion"),
    @NamedQuery(name = "RelacionLaboral.findByRemuMensual", query = "SELECT r FROM RelacionLaboral r WHERE r.remuMensual = :remuMensual"),
    @NamedQuery(name = "RelacionLaboral.findByRemuHora", query = "SELECT r FROM RelacionLaboral r WHERE r.remuHora = :remuHora"),
    @NamedQuery(name = "RelacionLaboral.findByObsRelacionLaboral", query = "SELECT r FROM RelacionLaboral r WHERE r.obsRelacionLaboral = :obsRelacionLaboral"),
    @NamedQuery(name = "RelacionLaboral.findByNombreDocuRelacionLabo", query = "SELECT r FROM RelacionLaboral r WHERE r.nombreDocRelacionLabo = :nombreDocRelacionLabo"),
    @NamedQuery(name = "RelacionLaboral.findByUrlDocuRelacionLabo", query = "SELECT r FROM RelacionLaboral r WHERE r.idDocRelacionLabo = :idDocRelacionLabo")})
public class RelacionLaboral implements Serializable {
//Declaración de Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la RelacionLaboral de un docente generado automaticamente para
 identificar a un objeto de tipo RelacionLaboral
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_re_laboral", nullable = false)
    private Integer idReLaboral;
    /**
     * Tipo de personal con el que cuenta la isntitución
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un tipo de personal")
    @Size(min = 1, max = 50)
    @Column(name = "tipo_personal", nullable = false, length = 50)
    private String tipoPersonal;
    /**
     * Número de documento del nombramiento, contrato o su equivalente del
     * docente.
     */
    @Basic(optional = false)
    @NotNull(message = "El número de documento es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "num_documento", nullable = false, length = 100)
    private String numDocumento;
    /**
     * Número del contrato relacionado
     */
    @Size(max = 50)
    @Column(name = "contrato_relacionado", length = 50)
    private String contratoRelacionado;
    /**
     * Fecha de inicio del contrato o documento equivalente del docente
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de inicio es un campo obligatorio")
    @Column(name = "fecha_inicio_contrato", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicioContrato;
    /**
     * Fecha de fin del contrato o documento equivalente del docente
     */
    @Column(name = "fecha_fin_contrato")
    @Temporal(TemporalType.DATE)
    private Date fechaFinContrato;
    /**
     * El docente ingreso a la Institución por concurso tenemos: true Si, false
     * No
     */
    @Column(name = "ingreso_por_concurso")
    private Boolean ingresoPorConcurso;
    /**
     * Tiempo de dedicación del docente con a la IES
     */
    @Size(max = 100)
    @Column(name = "tiempo_dedicacion", length = 100)
    private String tiempoDedicacion;
    /**
     * Remuneración mensual del docente
     */
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "remu_mensual", precision = 6, scale = 2)
    private BigDecimal remuMensual;
    /**
     * Remuneración por hora del docente
     */
    @Column(name = "remu_hora", precision = 5, scale = 2)
    private BigDecimal remuHora;
    /**
     * Observaciones
     */
    @Size(max = 200)
    @Column(name = "obs_relacion_laboral", length = 200)
    private String obsRelacionLaboral;
    /**
     * Estado de la relacion laboral true:vigente en proceso de evaluación.
     * false: no vigente en el proceso de evaluación.
     */
    @Basic(optional = false)
    @NotNull(message = "El estado del docuemnto es un campo obligatorio")
    @Column(name = "estado_relacion", nullable = false)
    private boolean estadoRelacion;
    /**
     * Nombre del archivo pdf que servira como evidencia
     */
    @Size(max = 150)
    @Column(name = "nombre_doc_relacion_labo", length = 150)
    private String nombreDocRelacionLabo;
    /**
     * url del archivo pdf que servira como evidencia
     */

    @Size(max = 200)
    @Column(name = "id_doc_relacion_labo", length = 200)
    private String idDocRelacionLabo;
     @JoinColumn(name = "id_carrera_docente", referencedColumnName = "id_carrera_docente", nullable = false)
    @ManyToOne(optional = false)
      /**
     * Declaración del un objeto Carrera Docente
     */
    private CarreraDocente idCarreraDocente;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id_categoria", nullable = false)
    @ManyToOne(optional = false)
    /**
     * Declaración del un objeto de tipo Categoria
     */
    private Categoria idCategoria;
    /**
     * Declaración del un objeto de tipo RelacionIes
     */
    @JoinColumn(name = "id_relacion_ies", referencedColumnName = "id_relacion_ies", nullable = false)
    @ManyToOne(optional = false)
    private RelacionIes idRelacionIes;
    @JoinColumn(name = "id_tdedi", referencedColumnName = "id_tdedi", nullable = false)
    @ManyToOne(optional = false)
    /**
     * Declaración del un objeto de tipo TiempoDedicacion
     */
    private TiempoDedicacion idTdedi;
    /**
     * Declaración del un objeto de tipo TipoDocumento
     */
    @JoinColumn(name = "id_tipo_documento", referencedColumnName = "id_tipo_documento", nullable = false)
    @ManyToOne(optional = false)
    private TipoDocumento idTipoDocumento;
    /**
     * Declaración de una lista de objetos de tipo DocentesMallaContratoMateria
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idReLaboral")
    private List<DocentesMallaContratoMateria> docentesMallaContratoMateriaList;

    /**
     * Cosntructor por defecto
     */
    public RelacionLaboral() {
    }

    /**
     * Cosntructor que recibe un parámetro
     *
     * @param idReLaboral
     */
    public RelacionLaboral(Integer idReLaboral) {
        this.idReLaboral = idReLaboral;
    }

    /**
     * Cosntructor que recibe 4 parámetros
     *
     * @param idReLaboral
     * @param tipoPersonal
     * @param numDocumento
     * @param fechaInicioContrato
     */
    public RelacionLaboral(Integer idReLaboral, String tipoPersonal, String numDocumento, Date fechaInicioContrato) {
        this.idReLaboral = idReLaboral;
        this.tipoPersonal = tipoPersonal;
        this.numDocumento = numDocumento;
        this.fechaInicioContrato = fechaInicioContrato;
    }

    /**
     * Retorna el id de la relación laboral
     *
     * @return idReLaboral
     */
    public Integer getIdReLaboral() {
        return idReLaboral;
    }

    /**
     * Modifica el id de la relación laboral
     *
     * @param idReLaboral
     */
    public void setIdReLaboral(Integer idReLaboral) {
        this.idReLaboral = idReLaboral;
    }

    /**
     * Retorna el tipo de personal con el que cuenta la IES
     *
     * @return tipoPersonal
     */
    public String getTipoPersonal() {
        return tipoPersonal;
    }

    /**
     * Modifica el tipo de personal con el que cuenta la IES
     *
     * @param tipoPersonal
     */
    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
    }

    /**
     * Retorna el número del contrato
     *
     * @return numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * Modifica el número del contrato
     *
     * @param numDocumento
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * Retorna el número del contrato relacionado
     *
     * @return contratoRelacionado
     */
    public String getContratoRelacionado() {
        return contratoRelacionado;
    }

    /**
     * Modifica el número del contrato relacionado
     *
     * @param contratoRelacionado
     */
    public void setContratoRelacionado(String contratoRelacionado) {
        this.contratoRelacionado = contratoRelacionado;
    }

    /**
     * Retorna la fecha de inicio del contrato
     *
     * @return fechaInicioContrato
     */
    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    /**
     * Modifica la fecha de inicio del contrato
     *
     * @param fechaInicioContrato
     */
    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    /**
     * Retorna la fecha de fin del contrato
     *
     * @return fechaFinContrato
     */
    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    /**
     * Modifica la fecha de fin del contrato
     *
     * @param fechaFinContrato
     */

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    /**
     * Retorna si el docente ingreso por concurso a la IES
     *
     * @return <ul><li>true:si</li>
     * <li>false:No</li>
     * </ul>
     */
    public Boolean getIngresoPorConcurso() {
        return ingresoPorConcurso;
    }

    /**
     * Modifica si el docente ingreso por concurso a la IES
     *
     * @param ingresoPorConcurso
     */
    public void setIngresoPorConcurso(Boolean ingresoPorConcurso) {
        this.ingresoPorConcurso = ingresoPorConcurso;
    }

    /**
     * Retorna el tiempo de Dedicacion del Docente en la IES
     *
     * @return tiempoDedicacion
     */
    public String getTiempoDedicacion() {
        return tiempoDedicacion;
    }

    /**
     * Modifica el tiempo de Dedicacion del Docente en la IES
     *
     * @param tiempoDedicacion
     */
    public void setTiempoDedicacion(String tiempoDedicacion) {
        this.tiempoDedicacion = tiempoDedicacion;
    }

    /**
     * Retorna la remuneración mensual del Docente en la IES
     *
     * @return remuMensual
     */
    public BigDecimal getRemuMensual() {
        return remuMensual;
    }

    /**
     * Modifica la remuneración mensual del Docente en la IES
     *
     * @param remuMensual
     */
    public void setRemuMensual(BigDecimal remuMensual) {
        this.remuMensual = remuMensual;
    }

    /**
     * Retorna la remuneración por hora del Docente en la IES
     *
     * @return remuHora
     */

    public BigDecimal getRemuHora() {
        return remuHora;
    }

    /**
     * Modifica la remuneración por hora del Docente en la IES
     *
     * @param remuHora
     */

    public void setRemuHora(BigDecimal remuHora) {
        this.remuHora = remuHora;
    }

    /**
     * Retorna algún tipo Observación
     *
     * @return obsRelacionLaboral
     */
    public String getObsRelacionLaboral() {
        return obsRelacionLaboral;
    }

    /**
     * Modifica algún tipo Observación
     *
     * @param obsRelacionLaboral
     */

    public void setObsRelacionLaboral(String obsRelacionLaboral) {
        this.obsRelacionLaboral = obsRelacionLaboral;
    }

    /**
     * Retorna si el estado de relación laboral esta activo en proceso de
     * evaluación
     *
     * @return <ul><li>true:activo</li>
     * <li>false:inactivo</li>
     * </ul>
     */
    public boolean isEstadoRelacion() {
        return estadoRelacion;
    }

    /**
     * Modifica si el estado de relación laboral esta activo en proceso de
     * evaluación
     *
     * @param estadoRelacion
     */

    public void setEstadoRelacion(boolean estadoRelacion) {
        this.estadoRelacion = estadoRelacion;
    }
/**Devuelve el nombre del documento almacenado en el repositorio
     * @return nombreDocRelacionLabo
 */
    public String getNombreDocRelacionLabo() {
        return nombreDocRelacionLabo;
    }
/**Modifica el nombre del documento almacenado en el repositorio
     * @param  nombreDocRelacionLabo
 */
    public void setNombreDocRelacionLabo(String nombreDocRelacionLabo) {
        this.nombreDocRelacionLabo = nombreDocRelacionLabo;
    }
/**Devuelve el id del documento almacenado en el repositorio
     * @return idDocRelacionLabo
 */
    public String getIdDocRelacionLabo() {
        return idDocRelacionLabo;
    }
/**Modifica el id del documento almacenado en el repositorio
     * @param  idDocRelacionLabo
 */
    public void setIdDocRelacionLabo(String idDocRelacionLabo) {
        this.idDocRelacionLabo = idDocRelacionLabo;
    }
      /**
     * Retorna un objeto de tipo CarreraDocente
     *
     * @return idCarreraDocente
     */
    public CarreraDocente getIdCarreraDocente() {
        return idCarreraDocente;
    }
   /**
     * Modifica un objeto de tipo CarreraDocente
     *
     * @param  idCarreraDocente
     */
    public void setIdCarreraDocente(CarreraDocente idCarreraDocente) {
        this.idCarreraDocente = idCarreraDocente;
    }
   
    /**
     * Retorna un objeto de tipo Categoria
     *
     * @return idCategoria
     */
    public Categoria getIdCategoria() {
        return idCategoria;
    }
     /**
     * Modifica un objeto de tipo Categoria
     *
     * @param  idCategoria
     */

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

  

    /**
     * Retorna un objeto de tipo RelacionIes
     *
     * @return idRelacionIes
     */

    public RelacionIes getIdRelacionIes() {
        return idRelacionIes;
    }
       /**
     * Modifica un objeto de tipo RelacionIes
     *
     * @param  idRelacionIes
     */


    public void setIdRelacionIes(RelacionIes idRelacionIes) {
        this.idRelacionIes = idRelacionIes;
    }

    /**
     * Retorna un objeto de tipo TiempoDedicacion
     *
     * @return idTdedi
     */
    public TiempoDedicacion getIdTdedi() {
        return idTdedi;
    }
      /**
     * Modifica un objeto de tipo TiempoDedicacion
     *
     * @param  idTdedi
     */

    public void setIdTdedi(TiempoDedicacion idTdedi) {
        this.idTdedi = idTdedi;
    }

    /**
     * Retorna un objeto de tipo TipoDocumento
     *
     * @return idTipoDocumento
     */
    public TipoDocumento getIdTipoDocumento() {
        return idTipoDocumento;
    }
     /**
     * Modifica un objeto de tipo TipoDocumento
     *
     * @param  idTipoDocumento
     */

    public void setIdTipoDocumento(TipoDocumento idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    /**
     * Retorna una lista de objetos de tipo DocentesMallaContratoMateria
     *
     * @return docentesMallaContratoMateriaList
     */

    @XmlTransient
    public List<DocentesMallaContratoMateria> getDocentesMallaContratoMateriaList() {
        return docentesMallaContratoMateriaList;
    }
   /**
     * Modifica una lista de objetos de tipo DocentesMallaContratoMateria
     *
     * @param  docentesMallaContratoMateriaList
     */
    public void setDocentesMallaContratoMateriaList(List<DocentesMallaContratoMateria> docentesMallaContratoMateriaList) {
        this.docentesMallaContratoMateriaList = docentesMallaContratoMateriaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReLaboral != null ? idReLaboral.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelacionLaboral)) {
            return false;
        }
        RelacionLaboral other = (RelacionLaboral) object;
        if ((this.idReLaboral == null && other.idReLaboral != null) || (this.idReLaboral != null && !this.idReLaboral.equals(other.idReLaboral))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.RelacionLaboral[ idReLaboral=" + idReLaboral + " ]";
    }

}
