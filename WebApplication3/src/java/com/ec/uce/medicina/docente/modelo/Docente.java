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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaración de la clase Docente
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "docente", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"identificacion"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Docente.findAll", query = "SELECT d FROM Docente d"),
    @NamedQuery(name = "Docente.findByIdDocente", query = "SELECT d FROM Docente d WHERE d.idDocente = :idDocente"),
    @NamedQuery(name = "Docente.findByTIdentificacion", query = "SELECT d FROM Docente d WHERE d.tIdentificacion = :tIdentificacion"),
    @NamedQuery(name = "Docente.findByIdentificacion", query = "SELECT d FROM Docente d WHERE d.identificacion = :identificacion"),
    @NamedQuery(name = "Docente.findByApellidoPaterno", query = "SELECT d FROM Docente d WHERE d.apellidoPaterno = :apellidoPaterno"),
    @NamedQuery(name = "Docente.findByApellidoMaterno", query = "SELECT d FROM Docente d WHERE d.apellidoMaterno = :apellidoMaterno"),
    @NamedQuery(name = "Docente.findByNombres", query = "SELECT d FROM Docente d WHERE d.nombres = :nombres"),
    @NamedQuery(name = "Docente.findBySexo", query = "SELECT d FROM Docente d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "Docente.findByDireccionDocente", query = "SELECT d FROM Docente d WHERE d.direccionDocente = :direccionDocente"),
    @NamedQuery(name = "Docente.findByFechaNacimiento", query = "SELECT d FROM Docente d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Docente.findByDiscapasidad", query = "SELECT d FROM Docente d WHERE d.discapacidad = :discapacidad"),
    @NamedQuery(name = "Docente.findByTipoDiscapacidad", query = "SELECT d FROM Docente d WHERE d.tipoDiscapacidad = :tipoDiscapacidad"),
    @NamedQuery(name = "Docente.findByNumConadis", query = "SELECT d FROM Docente d WHERE d.numConadis = :numConadis"),
    @NamedQuery(name = "Docente.findByPorcentajeDiscapacidad", query = "SELECT d FROM Docente d WHERE d.porcentajeDiscapacidad = :porcentajeDiscapacidad"),
    @NamedQuery(name = "Docente.findByCorrePersonal", query = "SELECT d FROM Docente d WHERE d.correoPersonal = :correoPersonal"),
    @NamedQuery(name = "Docente.findByCorreoInstitucional", query = "SELECT d FROM Docente d WHERE d.correoInstitucional = :correoInstitucional"),
    @NamedQuery(name = "Docente.findByObsDocente", query = "SELECT d FROM Docente d WHERE d.obsDocente = :obsDocente"),
    @NamedQuery(name = "Docente.findByPaisDocente", query = "SELECT d FROM Docente d WHERE d.paisDocente = :paisDocente"),
    @NamedQuery(name = "Docente.findByTelefonoDocente", query = "SELECT d FROM Docente d WHERE d.telefonoDocente = :telefonoDocente"),
    @NamedQuery(name = "Docente.findByCelularDocente", query = "SELECT d FROM Docente d WHERE d.celularDocente = :celularDocente")})
public class Docente implements Serializable {
// Declaración de  Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id del docente generado automaticamente para identificar a un objeto de
     * tipo Docente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_docente", nullable = false)
    private Integer idDocente;
    /**
     * Tipo de indentificacion del Docente
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un tipo de identificación")
    @Size(min = 1, max = 50)
    @Column(name = "t_identificacion", nullable = false, length = 50)
    private String tIdentificacion;
    /**
     * Atributo número de identificación del Docente
     */
    @Basic(optional = false)
    @NotNull(message = "La identificación es un campo obligatorio")
    @Size(min = 1, max = 20)
    @Column(name = "identificacion", nullable = false, length = 20)
    private String identificacion;
    //Apellido paterno del Docente
    @Basic(optional = false)
    @NotNull(message = "El apellido paterno es un campo obligatorio")
    @Size(min = 1, max = 50)
    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;
    /**
     * Apellido materno del Docente
     */
    @Size(max = 50)
    @Column(name = "apellido_materno", length = 50)
    private String apellidoMaterno;
    /**
     * Nombres del Docente
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;
    /**
     * Genero de sexo del Docente
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un sexo")
    @Size(min = 1, max = 50)
    @Column(name = "sexo", nullable = false, length = 50)
    private String sexo;
    /**
     * Dirección del Docente
     */
    @Size(max = 200)
    @Column(name = "direccion_docente", length = 200)
    private String direccionDocente;
    /**
     * Fecha de nacimiento del Docente
     */
    @Basic(optional = false)
    @NotNull(message = "La fecha de nacimiento es un campo obligatorio")
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    /**
     * Discapacidad del docente tenemos true: si el docente tiene algun tipo de
     * discapacidad false: si no posee discapacidad
     */
    @Column(name = "discapacidad")
    private Boolean discapacidad;
    /**
     * Tipo de discapacidad del docente
     */
    @Size(max = 50)
    @Column(name = "tipo_discapacidad", length = 50)
    private String tipoDiscapacidad;
    /**
     * Número de carnét de conadis
     */
    @Size(max = 50)
    @Column(name = "num_conadis", length = 50)
    private String numConadis;
    /**
     * Porcentaje de discapacidad del docente
     */
    @Column(name = "porcentaje_discapacidad")
    private Integer porcentajeDiscapacidad;
    /**
     * Correo Personal del docente
     */
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "El correo no es válido")
    @Size(max = 100)
    @Column(name = "correo_personal", length = 100)
    private String correoPersonal;
    /**
     * Correo institucional del docente
     */
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "El correo no es válido")
    @Size(max = 100)
    @Column(name = "correo_institucional", length = 100)
    private String correoInstitucional;
    /**
     * Algún tipo de observación al ingresar los datos del docente
     */
    @Size(max = 200)
    @Column(name = "obs_docente", length = 200)
    private String obsDocente;
    /**
     * País donde vive el docente
     */
    @Size(max = 100)
    @Column(name = "pais_docente", length = 100)
    private String paisDocente;
    /**
     * Número de teléfono de casa del docente
     */
    @Size(max = 10)
    @Column(name = "telefono_docente", length = 10)
    private String telefonoDocente;
    /**
     * Número de celular del docente
     */
    @Size(max = 10)
    @Column(name = "celular_docente", length = 10)
    private String celularDocente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocente")
    private List<CarreraDocente> carreradocenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocente")
    private List<Capacitacion> capacitacionList;
    @JoinColumn(name = "id_pais", referencedColumnName = "id_pais", nullable = false)
    @ManyToOne(optional = false)
    private PaisOrigen idPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocente")
    private List<Formacion> formacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDocente")
    private List<Publicaciones> publicacionesList;


    /**
     * Constructor por defecto
     */
    public Docente() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idDocente
     */
    public Docente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    /**
     * Constructor que recibe 7 parametros
     *
     * @param idDocente
     * @param tIdentificacion
     * @param identificacion
     * @param apellidoPaterno
     * @param nombres
     * @param sexo
     * @param fechaNacimiento
     */
    public Docente(Integer idDocente, String tIdentificacion, String identificacion, String apellidoPaterno, String nombres, String sexo, Date fechaNacimiento) {
        this.idDocente = idDocente;
        this.tIdentificacion = tIdentificacion;
        this.identificacion = identificacion;
        this.apellidoPaterno = apellidoPaterno;
        this.nombres = nombres;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el id del docente
     *
     * @return idDocente
     */
    public Integer getIdDocente() {
        return idDocente;
    }

    /**
     * Modifica el id del docente
     *
     * @param idDocente
     */
    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }

    /**
     * Retorna el tipo de identificación del docente
     *
     * @return tIdentificacion
     */
    public String getTIdentificacion() {
        return tIdentificacion;
    }

    /**
     * Retorna el tipo de identificación del docente
     *
     * @param tIdentificacion
     */
    public void setTIdentificacion(String tIdentificacion) {
        this.tIdentificacion = tIdentificacion;
    }

    /**
     * Retorna la identificación del docente
     *
     * @return identificacion
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Modifica la identificación del docente
     *
     * @param identificacion
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Retorna el apellido paterno del docente
     *
     * @return apellidoPaterno
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Modifica el apellido paterno del docente
     *
     * @param apellidoPaterno
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Retorna el apellido Materno del docente
     *
     * @return apellidoMaterno
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Modifica el apellido Materno del docente
     *
     * @param apellidoMaterno
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Retorna los nombres del docente
     *
     * @return nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Modifica los nombres del docente
     *
     * @param nombres
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Retorna el sexo del docente
     *
     * @return sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Modifica el sexo del docente
     *
     * @param sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Retorna la dirección del docente
     *
     * @return direccionDocente
     */
    public String getDireccionDocente() {
        return direccionDocente;
    }

    /**
     * Modifica la dirección del docente
     *
     * @param direccionDocente
     */
    public void setDireccionDocente(String direccionDocente) {
        this.direccionDocente = direccionDocente;
    }

    /**
     * Retorna la fecha de nacimiento del docente
     *
     * @return fechaNacimiento
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Modifica la fecha de nacimiento del docente
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el tipo de identificación del docente
     *
     * @return tIdentificacion
     */

    public String gettIdentificacion() {
        return tIdentificacion;
    }

    /**
     * Modifica el tipo de identificación del docente
     *
     * @param tIdentificacion
     */
    public void settIdentificacion(String tIdentificacion) {
        this.tIdentificacion = tIdentificacion;
    }

    /**
     * Retorna si el docente tiene algún tipo de discapacidad
     *
     * @return <ul>
     * <li>true: el docente tiene discapacidad</li>
     * <li>false: el docente no tiene discapacidad</li>
     * </ul>
     */

    public Boolean getDiscapacidad() {
        return discapacidad;
    }

    /**
     * Modifica si el docente tiene discapacidad
     *
     * @param discapacidad
     */
    public void setDiscapacidad(Boolean discapacidad) {
        this.discapacidad = discapacidad;
    }

    /**
     * Retorna el correo personal del docente
     * @return correoPersonal
     */
    public String getCorreoPersonal() {
        return correoPersonal;
    }
     /**
     * Modifica el correo personal del docente
     * @param  correoPersonal
     */

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }
     /**
     * Retorna el tipo de discapacidad  del docente
     * @return tipoDiscapacidad
     */

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }
    /**
     * Modifica el tipo de discapacidad  del docente
     * @param  tipoDiscapacidad
     */

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }
    /**
     * Retorna el número de carnét de conadis del docente
     * @return numConadis
     */

    public String getNumConadis() {
        return numConadis;
    }
 /**
     * Modifica el número de carnét de conadis del docente
     * @param  numConadis
     */
    public void setNumConadis(String numConadis) {
        this.numConadis = numConadis;
    }
 /**
     * Retorna el porcentaje de discapacidad del docente
     * @return porcentajeDiscapacidad
     */
    public Integer getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }
     /**
     * Modifica el porcentaje de discapacidad del docente
     * @param  porcentajeDiscapacidad
     */

    public void setPorcentajeDiscapacidad(Integer porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }
 /**
     * Retorna el correo institucional del docente
     * @return correoInstitucional
     */
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }
    
/**
     * Modifica el correo institucional del docente
     * @param  correoInstitucional
     */
    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }
/**
     * Retorna las observaciones al ingresar datos del docente
     * @return obsDocente
     */
    public String getObsDocente() {
        return obsDocente;
    }
/**
     * Modifica las observaciones al ingresar datos del docente
     * @param  obsDocente
     */
    public void setObsDocente(String obsDocente) {
        this.obsDocente = obsDocente;
    }
/**
 * Retorna el país del docente
     * @return paisDocente
 */
    public String getPaisDocente() {
        return paisDocente;
    }
/**
 * Modifica el país del docente
     * @param  paisDocente
 */
    public void setPaisDocente(String paisDocente) {
        this.paisDocente = paisDocente;
    }
/**
 * Retorna el teléfono de casa del docente
     * @return telefonoDocente
 */
    public String getTelefonoDocente() {
        return telefonoDocente;
    }
/**
 * Modifica el teléfono de casa del docente
     * @param  telefonoDocente
 */
    public void setTelefonoDocente(String telefonoDocente) {
        this.telefonoDocente = telefonoDocente;
    }
/**
 * Retorna el teléfono celular del docente
     * @return celularDocente
 */
    public String getCelularDocente() {
        return celularDocente;
    }
/**
 * Modifica el teléfono celular del docente
     * @param  celularDocente
 */
    public void setCelularDocente(String celularDocente) {
        this.celularDocente = celularDocente;
    }

    @XmlTransient
    public List<CarreraDocente> getCarreradocenteList() {
        return carreradocenteList;
    }

    public void setCarreradocenteList(List<CarreraDocente> carreradocenteList) {
        this.carreradocenteList = carreradocenteList;
    }

    @XmlTransient
    public List<Capacitacion> getCapacitacionList() {
        return capacitacionList;
    }

    public void setCapacitacionList(List<Capacitacion> capacitacionList) {
        this.capacitacionList = capacitacionList;
    }


    public PaisOrigen getIdPais() {
        return idPais;
    }

    public void setIdPais(PaisOrigen idPais) {
        this.idPais = idPais;
    }

    @XmlTransient
    public List<Formacion> getFormacionList() {
        return formacionList;
    }

    public void setFormacionList(List<Formacion> formacionList) {
        this.formacionList = formacionList;
    }

    @XmlTransient
    public List<Publicaciones> getPublicacionesList() {
        return publicacionesList;
    }

    public void setPublicacionesList(List<Publicaciones> publicacionesList) {
        this.publicacionesList = publicacionesList;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Docente[ idDocente=" + idDocente + " ]";
    }

}
