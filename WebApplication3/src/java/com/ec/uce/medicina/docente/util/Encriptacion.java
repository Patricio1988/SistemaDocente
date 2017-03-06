/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.util;







import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <b>
 * Clase que permite encryptar la contraseña.
 * </b>
 *
 *
 * @author Patricio
 * @version 2.0
 */
public class Encriptacion {


    public static String encrypt(String contrasenia) throws Exception {

BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
		System.out.println(encoder.encode(contrasenia));
                
                return new String(encoder.encode(contrasenia));
    }
 

    public static void main(String[] args) throws Exception {

 }
    
}
