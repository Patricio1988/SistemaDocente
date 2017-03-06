/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controllers;

import com.ec.uce.medicina.docente.modelo.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Patricio
 */
public class MyAuthenticationProvider implements AuthenticationProvider {
 
    private UserDetailsService userDetailsService;
 
    public void setUserDetailsService(UserDetailsService userDetailsService) {
 
        this.userDetailsService = userDetailsService;
    }

  
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
           String userLogin = authentication.getPrincipal().toString();
        String passwordLogin = authentication.getCredentials().toString();
        System.out.println("User: " + userLogin);
        System.out.println("Password: " + passwordLogin);
        Users user = new Users();
        user.setUsername(userLogin);
        user.setPassword(passwordLogin);
//        if(userExist(user)) {
//            List<GrantedAuthority> autoridades = new ArrayList<GrantedAuthority>();
//            //autoridades.add(new SimpleGrantedAuthority("ROLE_USER"));
//            //autoridades.add(new SimpleGrantedAuthority("ROLE_VIP"));
//            //autoridades.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//            //autoridades.add(new SimpleGrantedAuthority("ROLE_VENDEDOR"));
//            autoridades.add(new SimpleGrantedAuthority("ROLE_ALUMNO"));
//            Usuario userDetails = new Usuario();
//            userDetails.setUsername(userLogin);
//            userDetails.setPassword(passwordLogin);
//            userDetails.setFecha(new Date());
//            userDetails.setNombre(userLogin);
//            Authentication customAuthentication = new UsernamePasswordAuthenticationToken(userDetails, 
//                    passwordLogin, autoridades);
//            BeanMenu menu = new BeanMenu();
//            menu.setUser(user);
//            menu.setVisibleMenu(true);
//            menu.setDisInicio(true);
//            ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//            HttpSession session = attr.getRequest().getSession(true);
//            session.setAttribute("beanMenu", menu);
//            return customAuthentication;
//        } else {
//            System.out.println("Usuario o Contraseña Inválidos.");
            throw new BadCredentialsException("Usuario o Contraseña Inválidos.");
//        }
    }

    @Override
    public boolean supports(Class<?> type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
