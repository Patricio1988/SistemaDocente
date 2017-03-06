/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.modelo.Periodo;
import com.uce.uce.medicina.docente.servicio.PeriodoServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patricio
 */
@SessionScoped
@ManagedBean
public class SelecionPeriodo implements Serializable {

    private int idPeriodo;
    private String nombrePeriodo;
    @EJB
    private PeriodoServicio servPeriodo;

    @PostConstruct
    public void inicializar() {
        idPeriodo = 0;

    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getNombrePeriodo() {
        return nombrePeriodo;
    }

    public void setNombrePeriodo(String nombrePeriodo) {
        this.nombrePeriodo = nombrePeriodo;
    }

    public List<Periodo> listaPeriodoSeleccionar(Authorities au) {
        List<Periodo> listaPeriodosSeleccionar;
        if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR") == true) {
            listaPeriodosSeleccionar = servPeriodo.listarPeriodo();

        } else {
            listaPeriodosSeleccionar = servPeriodo.listarPeriodoporActivos();

        }
        return listaPeriodosSeleccionar;
    }

    public void accederAInicio() {
        try {
            nombrePeriodo = servPeriodo.encontrarPeriodoID(idPeriodo).getNombrePeriodo();
            FacesContext contex = FacesContext.getCurrentInstance();

            contex.getExternalContext().redirect("/SistemaADocente4/inicio/inicio.xhtml");
        } catch (Exception e) {

            System.out.println("Error" + e.getMessage());
        }
    }

}
