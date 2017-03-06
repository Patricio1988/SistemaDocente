/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Declaración de la clase Users
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "users", catalog = "docente_medicina", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByNombreUsuario", query = "SELECT u FROM Users u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Users.findByApellidoUsuario", query = "SELECT u FROM Users u WHERE u.apellidoUsuario = :apellidoUsuario"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByCi", query = "SELECT u FROM Users u WHERE u.ci = :ci"),
    @NamedQuery(name = "Users.findByCorreo", query = "SELECT u FROM Users u WHERE u.correo = :correo"),
    @NamedQuery(name = "Users.findByFechaRegistro", query = "SELECT u FROM Users u WHERE u.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled")})
public class Users implements Serializable {
//Declaración de atributos
    private static final long serialVersionUID = 1L;
      /**
     * Nombre del usario
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre del usuario es un campo obligatorio")
    @Size(min = 1, max = 300)
    @Column(name = "nombre_usuario", nullable = false, length = 300)
    private String nombreUsuario;
    /** apellido del usuario*/
    @Basic(optional = false)
    @NotNull(message = "El apellido del usuario es un campo obligatorio")
    @Size(min = 1, max = 300)
    @Column(name = "apellido_usuario", nullable = false, length = 300)
    private String apellidoUsuario;
    /** Username del usuario*/
    @Id
    @Basic(optional = false)
    @NotNull(message = "El username name del usuario es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "username", nullable = false, length = 100)
    private String username;
    /**contraseña  de usuario*/
    @Basic(optional = false)
    @NotNull(message = "La contraseña del usuario es un campo obligatorio")
    @Size(min = 1, max = 300)
    @Column(name = "password", nullable = false, length = 300)
    private String password;
    /**Cedula del usuario*/
    @Basic(optional = false)
    @NotNull(message = "La cedula es un campo obligatorio")
    @Size(min = 1, max = 15)
    @Column(name = "ci", nullable = false, length = 15)
    private String ci;
    /**Correo del usuario*/
    @Pattern(regexp ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "El correo no es válido")
    @Size(max = 255)
    @Column(name = "correo", length = 255)
    private String correo;
    /** Fecha de registro del usuario*/
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    //estado del usuario
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    /**Declaración de objeto de tipo Authorities o rol*/
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "username")
    private Authorities authorities;
    /**Declaración de un obejto de tipo Carrera*/
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera", nullable = false)
    @ManyToOne(optional = false)
    private Carrera idCarrera;
/**Constructor por defecto*/
    public Users() {
    }

    /**
     * Constructor que recibe un parametro
     * @param username
     */
    public Users(String username) {
        this.username = username;
    }
       /**
     * Constructor que recibe un parametro
     * @param username
     */

    /**
     * Constructor que recibe 7 parametros
     * @param username
     * @param nombreUsuario
     * @param apellidoUsuario
     * @param password
     * @param ci
     * @param fechaRegistro
     * @param enabled
     */
    public Users(String username, String nombreUsuario, String apellidoUsuario, String password, String ci, Date fechaRegistro, boolean enabled) {
        this.username = username;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.password = password;
        this.ci = ci;
        this.fechaRegistro = fechaRegistro;
        this.enabled = enabled;
    }


    /**
     * Retorna el nombre del usuario
     * @return nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }
     /**
     * Modifica el nombre del usuario
     * @param  nombreUsuario
     */

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
     /**
     * Retorna el apellido del usuario
     * @return apellidoUsuario
     */

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }
   /**
     * Modifica el apellido del usuario
     * @param  apellidoUsuario
     */

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }
 /**
     * Retorna el username del usuario
     * @return username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Modifica el username del usuario
     * @param  username
     */

    public void setUsername(String username) {
        this.username = username;
    }
/**
     * Retorna la contraseña del usuario
     * @return password
     */
    public String getPassword() {
        return password;
    }
    /**
     * Modifica la contraseña del usuario
     * @param  password
     */

    public void setPassword(String password) {
        this.password = password;
    }
/**
     * Retorna la cédula del usuario
     * @return ci
     */
    public String getCi() {
        return ci;
    }
    /**
     * Modifica la cédula del usuario
     * @param  ci
     */

    public void setCi(String ci) {
        this.ci = ci;
    }
/**
     * Retorna el correo del usuario
     * @return correo
     */
    public String getCorreo() {
        return correo;
    }
    /**
     * Modifica el correo del usuario
     * @param  correo
     */

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    /**
     * Retorna la fecha de registro del usuario
     * @return fechaRegistro
     */

    public Date getFechaRegistro() {
        return fechaRegistro;
    }
        /**
     * Modifica la fecha de registro del usuario
     * @param  fechaRegistro
     */

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
/**
     * Retorna el estado del usuario
     * @return enabled
     */
    public boolean getEnabled() {
        return enabled;
    }
    /**
     * Modifica el estado del usuario
     * @param  enabled
     */

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
/**
     * Retorna lun objeto de tipo Authorities
     * @return authorities
     */
    public Authorities getAuthorities() {
        return authorities;
    }
/**
     * Modifica lun objeto de tipo Authorities
     * @param  authorities
     */
    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }
/**
     * Retorna un objeto de tipo Carrera
     * @return idCarrera
     */
    public Carrera getIdCarrera() {
        return idCarrera;
    }
/**
     * Modifica un objeto de tipo Carrera
     * @param  idCarrera
     */
    public void setIdCarrera(Carrera idCarrera) {
        this.idCarrera = idCarrera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Users[ username=" + username + " ]";
    }
    
}
