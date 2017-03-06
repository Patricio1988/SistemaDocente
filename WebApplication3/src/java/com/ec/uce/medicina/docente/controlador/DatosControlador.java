/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Users;
import com.uce.uce.medicina.docente.servicio.UsersServicio;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Declaración del ManagedBean DatosControlador que sera manejada por jsf de
 * ámbito SessionScoped que permitira obtener los datos del usuario auténticado
 *
 * @author Patricio
 * @version 2.0
 */
@SessionScoped
@ManagedBean
public class DatosControlador implements Serializable {

    /**
     * Nombre del usuario
     */
    private String nombre;
    /**
     * Rol del usuario
     */
    private String rol;
    /**
     * Servicio
     */
    @EJB
    private UsersServicio userServicio;
    //Declaración de objeto tipo Users
    private Users usuario;

    private Authentication auth;

 //Inicializacíon de variables

    @PostConstruct
    public void inicializar() {
        auth = SecurityContextHolder.getContext().getAuthentication();//obtengo el rol del usuario logeado
        nombre = auth.getName();//Obtengo el nombre del usuario
        rol = auth.getAuthorities().toString();//Obtengo el nombre del rol del usuario
        usuario = userServicio.encontrarUsers(nombre);//busco al usuario por su nombre

    }

    public void estraerNombre() {
        nombre = auth.getName();
        System.out.print(nombre);
        System.out.print(nombre);

    }
    /**Devuelve el nombre del usuario
     * @return nombre
     */
       public String getNombre() {
        return nombre;
    }

        /**Modifica el nombre del usuario
     * @param  nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 /**Devuelve el rol del usuario
     * @return rol
     */
    public String getRol() {
        return rol;
    }
      /**Modifica el rol del usuario
     * @param  rol
     */
    public void setRol(String rol) {
        this.rol = rol;
    }
 /**Devuelve un objeto de tipo Users
     * @return usuario
     */
    public Users getUsuario() {
        return usuario;
    }
/**Modifica un objeto de tipo Users
     * @param  usuario
     */
    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }

}
