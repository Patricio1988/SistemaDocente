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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Declaracion de la clase Carrera
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "carrera", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_carrera"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrera.findAll", query = "SELECT c FROM Carrera c"),
    @NamedQuery(name = "Carrera.findByIdCarrera", query = "SELECT c FROM Carrera c WHERE c.idCarrera = :idCarrera"),
    @NamedQuery(name = "Carrera.findByNomCarrera", query = "SELECT c FROM Carrera c WHERE c.nomCarrera = :nomCarrera"),
    @NamedQuery(name = "Carrera.findByTelefono", query = "SELECT c FROM Carrera c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Carrera.findByModalidad", query = "SELECT c FROM Carrera c WHERE c.modalidad = :modalidad"),
    @NamedQuery(name = "Carrera.findByAreaunesco", query = "SELECT c FROM Carrera c WHERE c.areaunesco = :areaunesco"),
    @NamedQuery(name = "Carrera.findBySubareaunesco", query = "SELECT c FROM Carrera c WHERE c.subareaunesco = :subareaunesco"),
    @NamedQuery(name = "Carrera.findByNivel", query = "SELECT c FROM Carrera c WHERE c.nivel = :nivel"),
    @NamedQuery(name = "Carrera.findByTituloOtorga", query = "SELECT c FROM Carrera c WHERE c.tituloOtorga = :tituloOtorga"),
    @NamedQuery(name = "Carrera.findByNumRegOrganoColegiado", query = "SELECT c FROM Carrera c WHERE c.numRegOrganoColegiado = :numRegOrganoColegiado"),
    @NamedQuery(name = "Carrera.findByFechaOrganoColegiado", query = "SELECT c FROM Carrera c WHERE c.fechaOrganoColegiado = :fechaOrganoColegiado"),
    @NamedQuery(name = "Carrera.findByNumRegConesup", query = "SELECT c FROM Carrera c WHERE c.numRegConesup = :numRegConesup"),
    @NamedQuery(name = "Carrera.findByFechaRegConesup", query = "SELECT c FROM Carrera c WHERE c.fechaRegConesup = :fechaRegConesup"),
    @NamedQuery(name = "Carrera.findByDuracionCarrera", query = "SELECT c FROM Carrera c WHERE c.duracionCarrera = :duracionCarrera"),
    @NamedQuery(name = "Carrera.findByNomDirector", query = "SELECT c FROM Carrera c WHERE c.nomDirector = :nomDirector")})
public class Carrera implements Serializable {
//Atributos

    private static final long serialVersionUID = 1L;
    /**
     * Atributo entero para el id generado automáticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_carrera", nullable = false)
    private Integer idCarrera;
    /**
     * Nombre de la carrera
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la Carrera es un campo obligatorio")
    @Size(min = 1, max = 300)
    @Column(name = "nom_carrera", nullable = false, length = 300)
    private String nomCarrera;
    /**
     * Telefóno de la carrera
     */
    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;
    /**
     * Modalidad de la carrera
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione una Modalidad")
    @Size(min = 1, max = 80)
    @Column(name = "modalidad", nullable = false, length = 80)
    private String modalidad;
    //nombre del área unesco
    @Size(max = 100)
    @Column(name = "areaunesco", length = 100)
    private String areaunesco;
    //nombre del subárea unesco
    @Size(max = 100)
    @Column(name = "subareaunesco", length = 100)
    private String subareaunesco;
    /**
     * Nivel de formacion que ofrece la carrera
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un Nivel")
    @Size(min = 1, max = 80)
    @Column(name = "nivel", nullable = false, length = 80)
    private String nivel;
    /**
     * Nivel del título que ofrece la carrera
     */
    @Basic(optional = false)
    @NotNull(message = "El título que otorga es una campo obligatorio")
    @Size(min = 1, max = 500)
    @Column(name = "titulo_otorga", nullable = false, length = 500)
    private String tituloOtorga;
    /**
     * Número de acta o documento similar del máximo organismo académico
     * colegiado de la Universidad mediante el cual se aprobó la carrera
     */
    @Size(max = 40)
    @Column(name = "num_reg_organo_colegiado", length = 40)
    private String numRegOrganoColegiado;
    /**
     * Fecha del acta o documento similar del máximo organismo académico
     * colegiado de la Universidad mediante el cual se aprobó la carrera
     */
    @Column(name = "fecha_organo_colegiado")
    @Temporal(TemporalType.DATE)
    private Date fechaOrganoColegiado;
    /**
     * Número de resolución o registro (número de oficio de notificación) del
     * CONUEP, CONESUP o CES de aprobación de la carrera o programa académico.
     */
    @Size(max = 40)
    @Column(name = "num_reg_conesup", length = 40)
    private String numRegConesup;
    /**
     * Fecha de aprobación de las carreras o programas académicos por el CONUEP,
     * CONESUP o CES
     */
    @Column(name = "fecha_reg_conesup")
    @Temporal(TemporalType.DATE)
    private Date fechaRegConesup;
    /**
     * tiempo de duracion de la carrera
     */
    @Basic(optional = false)
    @NotNull(message = "La Duración de la Carrera es campo obligatorio")
    @Column(name = "duracion_carrera", nullable = false)
    private int duracionCarrera;
    /**
     * Nombre de la autoridad que ejerce como director en la carrera
     */
    @Size(max = 225)
    @Column(name = "nom_director", length = 225)
    private String nomDirector;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrera")
    private List<Malla> mallaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrera")
    private List<CarreraDocente> carreradocenteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCarrera")
    private List<Users> usersList;
    @JoinColumn(name = "id_facultad", referencedColumnName = "id_facultad", nullable = false)
    @ManyToOne(optional = false)
    private Facultad idFacultad;

    /**
     * Constructor por defecto
     */
    public Carrera() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idCarrera
     */
    public Carrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Constructor que recibe un parametro 6 parametros
     *
     * @param idCarrera
     * @param nomCarrera
     * @param modalidad
     * @param nivel
     * @param duracionCarrera
     * @param tituloOtorga
     */
    public Carrera(Integer idCarrera, String nomCarrera, String modalidad, String nivel, String tituloOtorga, int duracionCarrera) {
        this.idCarrera = idCarrera;
        this.nomCarrera = nomCarrera;
        this.modalidad = modalidad;
        this.nivel = nivel;
        this.tituloOtorga = tituloOtorga;
        this.duracionCarrera = duracionCarrera;
    }

    /**
     * Retorna el valor del id de la carrera
     *
     * @return idCarrera
     */
    public Integer getIdCarrera() {
        return idCarrera;
    }

    /**
     * Permite modificar el valor del id de la Carrera
     *
     * @param idCarrera
     */
    public void setIdCarrera(Integer idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Retorna el nombre de la Carrera
     *
     * @return nomCarrera
     */
    public String getNomCarrera() {
        return nomCarrera;
    }

    /**
     * Permite modificar el nombre de la Carrera
     *
     * @param nomCarrera
     */
    public void setNomCarrera(String nomCarrera) {
        this.nomCarrera = nomCarrera;
    }

    /**
     * Retorna el teléfono de la Carrera
     *
     * @return telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Permite Modificar el numero de teléfono de la Carrera
     *
     * @param telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna el tipo de modalidad de estudios de la Carrera
     *
     * @return modalidad
     */
    public String getModalidad() {
        return modalidad;
    }

    /**
     * Permite modifica el valor de la modalidad de estudios de la Carrera
     *
     * @param modalidad
     */
    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    /**
     * Retorna el Área de estudio de la carrera o programa académico (UNESCO)
     *
     * @return areaunesco
     */
    public String getAreaunesco() {
        return areaunesco;
    }

    /**
     * Permite modificar el Área de estudio de la carrera o programa académico
     * (UNESCO)
     *
     * @param areaunesco
     */

    public void setAreaunesco(String areaunesco) {
        this.areaunesco = areaunesco;
    }

    /**
     * Retorna el subárea de estudio de la carrera o programa académico (UNESCO)
     *
     * @return subareaunesco
     */

    public String getSubareaunesco() {
        return subareaunesco;
    }

    /**
     * Modifica el nombre la subárea de estudio de la carrera o programa
     * académico (UNESCO)
     *
     * @param subareaunesco
     */

    public void setSubareaunesco(String subareaunesco) {
        this.subareaunesco = subareaunesco;
    }

    /**
     * Retorna el Nivel de formación de la carrera o programa que cursa el
     * estudiante:
     *
     * @return nivel
     */
    public String getNivel() {
        return nivel;
    }

    /**
     * Modifica el Nivel de formación de la carrera o programa que cursa el
     * estudiante:
     *
     * @param nivel
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    /**
     * Retorna nombre o denominación del titulo que se obtiene al finalizar la
     * carrera.
     *
     * @return tituloOtorga
     */
    public String getTituloOtorga() {
        return tituloOtorga;
    }

    /**
     * Modifica el nombre o denominación del titulo que se obtiene al finalizar
     * la carrera.
     *
     * @param tituloOtorga
     */

    public void setTituloOtorga(String tituloOtorga) {
        this.tituloOtorga = tituloOtorga;
    }

    /**
     * Retorna número de acta o documento similar del máximo organismo académico
     * colegiado de la Universidad mediante el cual se aprobó la carrera
     *
     * @return numRegOrganoColegiado
     */
    public String getNumRegOrganoColegiado() {
        return numRegOrganoColegiado;
    }

    /**
     * Modifica el número de acta o documento similar del máximo organismo
     * académico colegiado de la Universidad mediante el cual se aprobó la
     * carrera
     *
     * @param numRegOrganoColegiado
     */

    public void setNumRegOrganoColegiado(String numRegOrganoColegiado) {
        this.numRegOrganoColegiado = numRegOrganoColegiado;
    }

    /**
     * Retorna la fecha del acta o documento similar del máximo organismo
     * académico colegiado de la Universidad mediante el cual se aprobó la
     * carrera
     *
     * @return fechaOrganoColegiado
     */
    public Date getFechaOrganoColegiado() {
        return fechaOrganoColegiado;
    }

    /**
     * Modifica la fecha del acta o documento similar del máximo organismo
     * académico colegiado de la Universidad mediante el cual se aprobó la
     * carrera
     *
     * @param fechaOrganoColegiado
     */
    public void setFechaOrganoColegiado(Date fechaOrganoColegiado) {
        this.fechaOrganoColegiado = fechaOrganoColegiado;
    }

    /**
     * Retorna el número de resolución o registro (número de oficio de
     * notificación) del CONUEP, CONESUP o CES de aprobación de la carrera o
     * programa académico
     *
     * @return numRegConesup
     */
    public String getNumRegConesup() {
        return numRegConesup;
    }

    /**
     * Modifica el número de resolución o registro (número de oficio de
     * notificación) del CONUEP, CONESUP o CES de aprobación de la carrera o
     * programa académico
     *
     * @param numRegConesup
     */
    public void setNumRegConesup(String numRegConesup) {
        this.numRegConesup = numRegConesup;
    }

    /**
     * Retorna la fecha de aprobación de las carreras o programas académicos por
     * el CONUEP
     *
     * @return fechaRegConesup
     */
    public Date getFechaRegConesup() {
        return fechaRegConesup;
    }

    /**
     * Modifica la fecha de aprobación de las carreras o programas académicos
     * por el CONUEP
     *
     * @param fechaRegConesup
     */
    public void setFechaRegConesup(Date fechaRegConesup) {
        this.fechaRegConesup = fechaRegConesup;
    }

    /**
     * Retorna la duracion de carrera
     *
     * @return duracionCarrera
     */
    public int getDuracionCarrera() {
        return duracionCarrera;
    }

    /**
     * Modifica la duracion de carrera
     *
     * @param duracionCarrera
     */

    public void setDuracionCarrera(int duracionCarrera) {
        this.duracionCarrera = duracionCarrera;
    }

    /**
     * Retorna el nombre de la autoridad que jerece como Director en la carrera
     *
     * @return nomDirector
     */
    public String getNomDirector() {
        return nomDirector;
    }

    /**
     * Modifica el nombre de la autoridad que jerece como Director en la carrera
     *
     * @param nomDirector
     */

    public void setNomDirector(String nomDirector) {
        this.nomDirector = nomDirector;
    }

    @XmlTransient
    public List<Malla> getMallaList() {
        return mallaList;
    }

    public void setMallaList(List<Malla> mallaList) {
        this.mallaList = mallaList;
    }

    @XmlTransient
    public List<CarreraDocente> getCarreradocenteList() {
        return carreradocenteList;
    }

    public void setCarreradocenteList(List<CarreraDocente> carreradocenteList) {
        this.carreradocenteList = carreradocenteList;
    }

    @XmlTransient
    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public Facultad getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(Facultad idFacultad) {
        this.idFacultad = idFacultad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCarrera != null ? idCarrera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrera)) {
            return false;
        }
        Carrera other = (Carrera) object;
        if ((this.idCarrera == null && other.idCarrera != null) || (this.idCarrera != null && !this.idCarrera.equals(other.idCarrera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Carrera[ idCarrera=" + idCarrera + " ]";
    }

}
