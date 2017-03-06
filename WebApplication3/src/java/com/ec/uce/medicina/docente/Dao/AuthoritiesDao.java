/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.Dao;

import com.ec.uce.medicina.docente.modelo.Authorities;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Patricio
 */
@Local
public interface AuthoritiesDao {

    void create(Authorities authorities);

    void edit(Authorities authorities);

    void remove(Authorities authorities);

    Authorities find(Object id);

    List<Authorities> findAll();

    List<Authorities> findRange(int[] range);

    int count();
    
}
