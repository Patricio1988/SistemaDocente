/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Patricio
 */
@Controller
public class UserControler {
    @Autowired
	private PasswordEncoder passwordEncoder;
    
}
