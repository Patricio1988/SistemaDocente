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
 * Declaración de la clase Universidad
 *
 * @author Patricio
 * @version 2.0
 */
@Entity
@Table(name = "universidad", catalog = "docente_medicina", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nom_universidad"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Universidad.findAll", query = "SELECT u FROM Universidad u"),
    @NamedQuery(name = "Universidad.findByIdUniversidad", query = "SELECT u FROM Universidad u WHERE u.idUniversidad = :idUniversidad"),
    @NamedQuery(name = "Universidad.findByCodUniversidad", query = "SELECT u FROM Universidad u WHERE u.codUniversidad = :codUniversidad"),
    @NamedQuery(name = "Universidad.findByTipoUniversidad", query = "SELECT u FROM Universidad u WHERE u.tipoUniversidad = :tipoUniversidad"),
    @NamedQuery(name = "Universidad.findByNomUniversidad", query = "SELECT u FROM Universidad u WHERE u.nomUniversidad = :nomUniversidad"),
    @NamedQuery(name = "Universidad.findByDireccionUniversidad", query = "SELECT u FROM Universidad u WHERE u.direccionUniversidad = :direccionUniversidad"),
    @NamedQuery(name = "Universidad.findByTelefonoUniversidad", query = "SELECT u FROM Universidad u WHERE u.telefonoUniversidad = :telefonoUniversidad"),
    @NamedQuery(name = "Universidad.findBySitioWeb", query = "SELECT u FROM Universidad u WHERE u.sitioWeb = :sitioWeb"),
    @NamedQuery(name = "Universidad.findByPais", query = "SELECT u FROM Universidad u WHERE u.pais = :pais"),
    @NamedQuery(name = "Universidad.findByProvincia", query = "SELECT u FROM Universidad u WHERE u.provincia = :provincia"),
    @NamedQuery(name = "Universidad.findByCiudad", query = "SELECT u FROM Universidad u WHERE u.ciudad = :ciudad"),
    @NamedQuery(name = "Universidad.findByAutoridad", query = "SELECT u FROM Universidad u WHERE u.autoridad = :autoridad")})
public class Universidad implements Serializable {
//Declaración de atributos

    private static final long serialVersionUID = 1L;
    /**
     * Id de la Universidad de un docente generado automaticamente para
     * identificar a un objeto de tipo Universidad
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_universidad", nullable = false)
    private Integer idUniversidad;
    /**
     * código de la universidad
     */
    @Basic(optional = false)
    @NotNull(message = "El código de la Universidad es un campo obligatorio")
    @Column(name = "cod_universidad", nullable = false)
    private int codUniversidad;
    /**
     * tipo de universidad
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un tipo de Universidad")
    @Size(min = 1, max = 70)
    @Column(name = "tipo_universidad", nullable = false, length = 70)
    private String tipoUniversidad;
    /**
     * Nombre de la universidad
     */
    @Basic(optional = false)
    @NotNull(message = "El nombre de la Universidad es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "nom_universidad", nullable = false, length = 100)
    private String nomUniversidad;
    /**
     * Dirrección de la universidad
     */
    @Basic(optional = false)
    @NotNull(message = "La dirección de la Universidad es un campo obligatorio")
    @Size(min = 1, max = 100)
    @Column(name = "direccion_universidad", nullable = false, length = 100)
    private String direccionUniversidad;
    /**
     * Teléfono de la universidad
     */
    @Size(max = 15)
    @Column(name = "telefono_universidad", length = 15)
    private String telefonoUniversidad;
    /**
     * Sitio web de la universidad
     */
    @Size(max = 120)
    @Column(name = "sitio_web", length = 120)
    private String sitioWeb;
    /**
     * País donde se encuentra de la universidad
     */
    @Basic(optional = false)
    @NotNull(message = "Seleccione un país")
    @Size(min = 1, max = 80)
    @Column(name = "pais", nullable = false, length = 80)
    private String pais;
    /**
     * Provincia de la universidad
     */
    @Basic(optional = false)
    @NotNull(message = "La provincia de la Universidad es un campo obligatorio")
    @Size(min = 1, max = 80)
    @Column(name = "provincia", nullable = false, length = 80)
    private String provincia;
    /**
     * Ciudad de la universidad
     */
    @Basic(optional = false)
    @NotNull(message = "La ciudad de la Universidad es un campo obligatorio")
    @Size(min = 1, max = 80)
    @Column(name = "ciudad", nullable = false, length = 80)
    private String ciudad;
    /**
     * nombre de la autoridad que ejerce como rector de la universidad
     */
    @Size(max = 80)
    @Column(name = "autoridad", length = 80)
    private String autoridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUniversidad")
    private List<Facultad> facultadList;

    /**
     * Constructor por defecto
     */
    public Universidad() {
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idUniversidad
     */
    public Universidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    /**
     * Constructor que recibe un parametro
     *
     * @param idUniversidad
     */

    /**
     * Constructor que recibe 8 parametros
     *
     * @param idUniversidad
     * @param codUniversidad
     * @param tipoUniversidad
     * @param nomUniversidad
     * @param direccionUniversidad
     * @param pais
     * @param provincia
     * @param ciudad
     */
    public Universidad(Integer idUniversidad, int codUniversidad, String tipoUniversidad, String nomUniversidad, String direccionUniversidad, String pais, String provincia, String ciudad) {
        this.idUniversidad = idUniversidad;
        this.codUniversidad = codUniversidad;
        this.tipoUniversidad = tipoUniversidad;
        this.nomUniversidad = nomUniversidad;
        this.direccionUniversidad = direccionUniversidad;
        this.pais = pais;
        this.provincia = provincia;
        this.ciudad = ciudad;
    }

    /**
     * Retorna el id de la Universidad
     *
     * @return idUniversidad
     */
    public Integer getIdUniversidad() {
        return idUniversidad;
    }

    public void setIdUniversidad(Integer idUniversidad) {
        this.idUniversidad = idUniversidad;
    }

    /**
     * Retorna el código de la Universidad
     *
     * @return codUniversidad
     */

    public int getCodUniversidad() {
        return codUniversidad;
    }

    /**
     * Modifica el código de la Universidad
     *
     * @param codUniversidad
     */
    public void setCodUniversidad(int codUniversidad) {
        this.codUniversidad = codUniversidad;
    }

    /**
     * Retorna el tipo de Universidad
     *
     * @return tipoUniversidad
     */
    public String getTipoUniversidad() {
        return tipoUniversidad;
    }

    /**
     * Modifica el tipo de Universidad
     *
     * @param tipoUniversidad
     */
    public void setTipoUniversidad(String tipoUniversidad) {
        this.tipoUniversidad = tipoUniversidad;
    }

    /**
     * Retorna el tipo de Universidad
     *
     * @return nomUniversidad
     */

    public String getNomUniversidad() {
        return nomUniversidad;
    }

    /**
     * Modifica el tipo de Universidad
     *
     * @param nomUniversidad
     */

    public void setNomUniversidad(String nomUniversidad) {
        this.nomUniversidad = nomUniversidad;
    }

    /**
     * Retorna la dirección de Universidad
     *
     * @return direccionUniversidad
     */
    public String getDireccionUniversidad() {
        return direccionUniversidad;
    }

    /**
     * Modifica la dirección de Universidad
     *
     * @param direccionUniversidad
     */
    public void setDireccionUniversidad(String direccionUniversidad) {
        this.direccionUniversidad = direccionUniversidad;
    }

    /**
     * Retorna el teléfono de Universidad
     *
     * @return telefonoUniversidad
     */

    public String getTelefonoUniversidad() {
        return telefonoUniversidad;
    }

    /**
     * Modifica el teléfono de Universidad
     *
     * @param telefonoUniversidad
     */
    public void setTelefonoUniversidad(String telefonoUniversidad) {
        this.telefonoUniversidad = telefonoUniversidad;
    }

    /**
     * Retorna el sitio web de la Universidad
     *
     * @return sitioWeb
     */
    public String getSitioWeb() {
        return sitioWeb;
    }

    /**
     * Modifica el sitio web de la Universidad
     *
     * @param sitioWeb
     */

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    /**
     * Retorna el país de Universidad
     *
     * @return pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Modifica el país de Universidad
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Retorna la provincia de la Universidad
     *
     * @return provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Modifica la provincia de la Universidad
     *
     * @param provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Retorna la ciudad de la Universidad
     *
     * @return ciudad
     */

    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modifica la ciudad de la Universidad
     *
     * @param ciudad
     */

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Retorna la autoridad que ejerce como rector de la Universidad
     *
     * @return autoridad
     */

    public String getAutoridad() {
        return autoridad;
    }

    /**
     * Modifica la autoridad que ejerce como rector de la Universidad
     *
     * @param autoridad
     */

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    /**
     * Retorna una lista de objetos de tipo Facultad
     *
     * @return facultadList
     */
    @XmlTransient
    public List<Facultad> getFacultadList() {
        return facultadList;
    }

    /**
     * Modifica una lista de objetos de tipo Facultad
     *
     * @param  facultadList
     */
    public void setFacultadList(List<Facultad> facultadList) {
        this.facultadList = facultadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUniversidad != null ? idUniversidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.idUniversidad == null && other.idUniversidad != null) || (this.idUniversidad != null && !this.idUniversidad.equals(other.idUniversidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ec.uce.medicina.docente.modelo.Universidad[ idUniversidad=" + idUniversidad + " ]";
    }

}
