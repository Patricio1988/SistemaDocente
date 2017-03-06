/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.fabrica;

/**
 *
 * @author Patricio
 */
public class DaoAbstractFactory {
     public static DaoFactory getInstance(){
        return new DaoFactoryImp();
    }
}
