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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Declaracion de la clase Authorities
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "authorities", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authorities.findAll", query = "SELECT a FROM Authorities a"),
    @NamedQuery(name = "Authorities.findByIdRol", query = "SELECT a FROM Authorities a WHERE a.idRol = :idRol"),
    @NamedQuery(name = "Authorities.findByAuthority", query = "SELECT a FROM Authorities a WHERE a.authority = :authority")})
public class Authorities implements Serializable {
// Atributos
    private static final long serialVersionUID = 1L;
    /**
     * Id del rol generado automaticamente para identificar a un objeto de tipo Authorities
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol", nullable = false)
    private Integer idRol;
    /**
     * Nombre del Rol para un usuario
     */
    @Basic(optional = false)
    @NotNull(message = "El rol es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "authority", nullable = false, length = 100)
    private String authority;
    /**
     * Instancia del usuario perteneciente a un determinado rol
     */
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    @OneToOne(optional = false)
    private Users username;
    /**Declaracion del constructor por defecto
     */

    public Authorities() {
    }
/**
 * constructor de la clase Authorities  que recibe como parametros el id 
     * @param idRol
 */
    public Authorities(Integer idRol) {
        this.idRol = idRol;
    }
/**Constructor de la clase auntorities que recibe como parametros un id del rol,un nombre del rol
     * @param idRol
     * @param authority
 */
    public Authorities(Integer idRol, String authority) {
        this.idRol = idRol;
        this.authority = authority;
    }
/**
 * Retorna el id de un Rol o Authorities
     * @return idRol
 */
    public Integer getIdRol() {
        return idRol;
    }


    /**
     * Permite modificar el id de un Rol
     * @param idRol
     */
    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }
/** Retorna el nombre de un rol 
     * @return authority 
 */
    public String getAuthority() {
        return authority;
    }
/**Permite modificar el nombre de un rol
     * @param authority
 */
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     *Devuleve un objeto de tipo User
     * @return username
     */
    public Users getUsername() {
        return username;
    }
/**
 * Permite modicar  de un objeto User
     * @param username
 */
    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authorities)) {
            return false;
        }
        Authorities other = (Authorities) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Authorities[ idRol=" + idRol + " ]";
    }
    
}
