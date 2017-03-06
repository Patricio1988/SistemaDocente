/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 *
 * @author Patricio
 */
@SessionScoped
@ManagedBean
@Named
@RequestScoped
public class AuthenticationBean implements Serializable{
      @Autowired
	private PasswordEncoder passwordEncoder;
      @Autowired
	private UserDetailsManager userDetailsManager;
    public  String doLogin() throws ServletException, IOException
    {
        ExternalContext context=FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher=((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest)context.getRequest(),(ServletResponse)context.getResponse());      
        FacesContext.getCurrentInstance().responseComplete();
        
        return null;
    }
    public  String doLogout()
    {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml";
    }
    
}
