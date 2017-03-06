/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.modelo.Users;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.AuthoritiesServicio;
import com.uce.uce.medicina.docente.servicio.UsersServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean AuthoritiesControlador que sera manejada por jsf
 * de ámbito ViewScoped que permitira realizar las operaciones en las paginas
 * xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class AuthoritiesControlador {

    /**
     * Declaración del atributo de tipo authorities
     */
    private Authorities authorities;
    /**
     * Declaración del atributo de tipo Users
     */
    private Users usuario;
    /**
     * Atributo para la cedula del usuario
     */
    private String cedula;
    /**
     * Declaración de una lista de objetos de tipo Authorities
     */
    private List<Authorities> listAuthorities;
//Declaración de los servicios
    @EJB
    private AuthoritiesServicio serAuthorities;
    @EJB
    private UsersServicio serUsuario;

    /**
     * Atribuo Request context para encapsular información sobre una solicitud
     * HTTP
     */
    private RequestContext req;

    /**
     * Cosntructor por defecto
     */
    public AuthoritiesControlador() {
    }

    /**
     * Inicialización de variables
     */
    @PostConstruct
    public void inicializar() {

        authorities = new Authorities();//Instaciamos la clase Authorities
        cedula = Constantes.VACIO;

    }

    /**
     * Método que permite crear un registro en la base de datos
     */
    public void crear() {

        try {
            authorities.setUsername(serUsuario.encontrarUsuarioporCI(cedula));
            /**
             * 
             */
            serAuthorities.insertarAuthorities(authorities);
            /**
             * Llama al método que tenemos en nuestro servicio y que permite
             * crear el regsitro
             */
            req = RequestContext.getCurrentInstance();
            req.execute("PF('DialogoAgregarRol').hide();");
            /**
             * Cerramos el dialogo
             */
            req = null;
            authorities = new Authorities();
            usuario = new Users();
            cedula = Constantes.VACIO;

            MensajesFaces.informacion("Guardado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos
     */

    public void actualizar() throws Exception {

        try {

            serAuthorities.actualizarAuthorities(authorities);
            /**
             * llama el metodo aculaizar que se encuentra en nuestro servicio
             */
            req = RequestContext.getCurrentInstance();
            req.execute("PF('DialogoActualizarRol').hide();");
            /**
             * Cierrar el dialogo sino no existio ningun error
             */
            req = null;
            authorities = new Authorities();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Elimina un registro de la base de datos
     *
     * @param au
     */
    public void eliminar(Authorities au) {

        try {

            serAuthorities.eliminarAuthorities(au);
            /**
             * Llama el método que se encuentra en nuestro servicioy que permite
             * eliminar un registro de la base de datos
             */
            au = new Authorities();

            MensajesFaces.informacion("Eliminado", "Exitoso");
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite encontar un rol por si id
     *
     * @param id
     */
    public void encontrarRol(int id) {

        authorities = serAuthorities.encontrarAuthorities(id);
        if (authorities != null) {
            MensajesFaces.informacion("Rol", "Encontrado");
        } else {
            MensajesFaces.informacion("Rol", "No Encontrado");
        }
    }
    //Método que permite cerrar 

    public void cerrar() {
        authorities = new Authorities();
        usuario = new Users();
        cedula = Constantes.VACIO;

    }

    /**
     * Método que permite en contrar un usuario por su número de cédula
     */
    public void encontrarUsuario() {
        usuario = serUsuario.encontrarUsuarioporCI(cedula);
        /**
         * Encuentro un usuario por número de cédula y lo asigno en la variable usuario
         */
        if (usuario != null) {
            /**
             * Retorna un mensaje si el usuario es encontrado
             */
            MensajesFaces.informacion("Usuario", "Encontrado");
        } else {
            /**
             * Retorna un mensaje si el usuario no es encontrado
             */
            MensajesFaces.informacion("Usuario", "No Encontrado");
        }
    }

    /**
     * Método que retorna una lista de objetos Authorities
     *
     * @return listAuthorities
     */
    public List<Authorities> recuperarTodos() {
        listAuthorities = serAuthorities.listarAuthorities();

        return listAuthorities;
    }

    /**
     * Retorna un objeto de tipo Authorities
     *
     * @return authorities
     */
    public Authorities getAuthorities() {
        return authorities;
    }

    /**
     * Modifica un objeto de tipo Authorities
     *
     * @param  authorities
     */

    public void setAuthorities(Authorities authorities) {
        this.authorities = authorities;
    }

    /**
     * Retorna una lista de objetos de tipo Authorities
     *
     * @return listAuthorities
     */
    public List<Authorities> getListAuthorities() {
        return listAuthorities;
    }

    /**
     * Modifica una lista de objetos de tipo Authorities
     *
     * @param  listAuthorities
     */

    public void setListAuthorities(List<Authorities> listAuthorities) {
        this.listAuthorities = listAuthorities;
    }

    /**
     * Retorna un objeto de tipo User
     *
     * @return usuario
     */
    public Users getUsuario() {
        return usuario;
    }

    /**
     * Modifica un objeto de tipo User
     *
     * @param  usuario
     */

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }

    /**
     * Retorna la cedula del usuario
     *
     * @return cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Modifica la cedula del usuario
     *
     * @param  cedula
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

}
