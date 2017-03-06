/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Declaración de la clase Tablas que contendra las tablas auxiliares para el
 * ingreso de datos
 *
 * @author Patricio
 * @version 2.0
 */
public class Tablas {

    /**
     * Atributo para los tipos de discapacidades que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> tipoDiscapacidades ;
    /**
     * Atributo para los tipos de niveles que se almacenan como un estructura
     * clave valor
     */
    private static Map<String, String> niveles;
    /**
     * Atributo para los tipos de grado que se almacenan como un estructura
     * clave valor
     */
    private static Map<String, String> grado;
    /**
     * Atributo para los tipos de modalidades que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> modalidades ;
    /**
     * Atributo para los tipos de sexos que se almacenan como un estructura
     * clave valor
     */
    private static Map<String, String> sexos ;
    /**
     * Atributo para los tipos de cursos que se almacenan como un estructura
     * clave valor
     */
    private static Map<String, String> tipodeCursos ;
    /**
     * Atributo para los tipos de localidades del curso que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> localidadcurso ;
    /**
     * Atributo para los tipos de organización que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> organizacion ;
    /**
     * Atributo para los tipos de cargo directivo que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> tipoCargoDirectivo;
    /**
     * Atributo para los tipos de participación que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> tiposParticipacion ;
    /**
     * Atributo para los tipos de estado de publicación que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> tiposEstadoPublicacion ;
    /**
     * Atributo para los tipos de base de datos de la revista que se almacenan
     * como un estructura clave valor
     */
    private static Map<String, String> tiposBaseDatosRevista ;
    /**
     * Atributo para los tipos de estado del curso que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> estadoCurso ;
    /**
     * Atributo para los tipos de personal con el que cuenta la IES que se
     * almacenan como un estructura clave valor
     */
    private static Map<String, String> tipoPersonal ;
    /**
     * Atributo para los tipos de institución que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> tipoInstitucion ;
    /**
     * Atributo para los tipos de identificación que se almacenan como un
     * estructura clave valor
     */
    private static Map<String, String> tpIdentificacion ;
    /**
     * Atributo para los semestres que se almacenan como un estructura clave valor
     */
    private static Map<String, String> semestres ;
/**
 * Método que devuelve los tipos de Discapacidad que puede tener un docente
     * @return tipoDiscapacidades
 */
    public static Map<String, String> tablaTipoDiscapacida() {
        tipoDiscapacidades = new HashMap<String, String>();
        tipoDiscapacidades.put("AUDITIVA", "AUDITIVA");
        tipoDiscapacidades.put("FÍSICA", "FÍSICA");
        tipoDiscapacidades.put("INTELECTUAL", "INTELECTUAL");
        tipoDiscapacidades.put("LENGUAJE", "LENGUAJE");
        tipoDiscapacidades.put("PSICOLÓGICO", "PSICOLÓGICO");
        tipoDiscapacidades.put("VISUAL", "VISUAL");
        tipoDiscapacidades.put("NINGUNA", "NINGUNA");
        return tipoDiscapacidades;
    }
/**
 * Método que devuelve los tipos de niveles  
     * @return niveles
 */
    public static Map<String, String> retornarniveles() {
        niveles = new HashMap<String, String>();
        niveles.put("TERCER NIVEL", "TERCER NIVEL");
        niveles.put("CUARTO NIVEL", "CUARTO NIVEL");

        return niveles;
    }
/**
 * Método que devuelve las modalidades que puede tener una carrera 
     * @return modalidades
 */
    public static Map<String, String> retornarmodalidades() {
        modalidades = new HashMap<String, String>();
        modalidades.put("PRESENCIAL", "PRESENCIAL ");
        modalidades.put("SEMIPRESENCIAL", "SEMIPRESENCIAL");
        modalidades.put("DISTANCIA", "DISTANCIA");
        modalidades.put("VIRTUAL O EN LINEA", "VIRTUAL O EN LINEA");
        return modalidades;
    }
/**
 * Método que devuelve los tipos de sexos que puede tener un docente
     * @return sexos
 */
    public static Map<String, String> retornarsexos() {
        sexos = new HashMap<String, String>();
        sexos.put("MASCULINO", "MASCULINO");
        sexos.put("FEMENINO", "FEMENINO");

        return sexos;
    }
/**
 * Método que devuelve los tipos de cursos que tener una publicación
     * @return tipodeCursos
 */
    public static Map<String, String> retornartiposCursos() {

        tipodeCursos = new HashMap<String, String>();
        tipodeCursos.put("CURSO A FIN A LA CÁTEDRA", "CURSO A FIN A LA CÁTEDRA");
        tipodeCursos.put("CURSO DE METODOLOGIA DE LA ENSEÑANZA", "CURSO DE METODOLOGIA DE LA ENSEÑANZA");

        return tipodeCursos;
    }
/**
 * Método que devuelve los tipos de localidades  que tener una publicación
     * @return localidadcurso
 */
    public static Map<String, String> retornarLocalidadCursos() {

        localidadcurso = new HashMap<String, String>();
        localidadcurso.put("NACIONAL", "NACIONAL");
        localidadcurso.put("INTERNACIONAL", "INTERNACIONAL");

        return localidadcurso;
    }
/**
 * Método que devuelve los tipos de organización  que tener una malla
     * @return organizacion
 */
    public static Map<String, String> retornarOrganizacion() {
        organizacion = new HashMap<String, String>();
        organizacion.put("CICLOS", "CICLOS");
        organizacion.put("ANUAL", "ANUAL");
        organizacion.put("SEMESTRAL", "SEMESTRAL");
        organizacion.put("TRIMESTRAL", "TRIMESTRAL");
        organizacion.put("MODULAR", "MODULAR");

        organizacion.put("OTROS", "OTROS");

        return organizacion;
    }
/**
 * Método que devuelve los tipos de cargos  que tener un docente
     * @return tipoCargoDirectivo
 */
    public static Map<String, String> retornarTiposCargos() {
        tipoCargoDirectivo = new HashMap<String, String>();
        tipoCargoDirectivo.put("RECTOR(A)", "RECTOR(A)");
        tipoCargoDirectivo.put("VICERRECTOR(A)", "VICERRECTOR(A)");
        tipoCargoDirectivo.put("DECANO(A)", "DECANO(A)");
        tipoCargoDirectivo.put("SUBDECANO(A)", "SUBDECANO(A)");
        tipoCargoDirectivo.put("COORDINADOR(A)", "COORDINADOR(A)");
        tipoCargoDirectivo.put("DIRECTOR(A)", "DIRECTOR(A)");
        tipoCargoDirectivo.put("JEFE(A) DEPARTAMENTAL", "JEFE(A) DEPARTAMENTAL");
        tipoCargoDirectivo.put("CONSEJO DE CARRERA", "CONSEJO DE CARRERA");
        tipoCargoDirectivo.put("COORDINADORA", "COORDINADORAL");
        tipoCargoDirectivo.put("OTRO", "OTRO");
        return tipoCargoDirectivo;
    }
/**
 * Método que devuelve los tipos de participación que el docente puede tener un docente en una publicación
     * @return tiposParticipacion
 */
    public static Map<String, String> retornarTiposParticipacion() {
        tiposParticipacion = new HashMap<String, String>();
        tiposParticipacion.put("AUTOR", "AUTOR");
        tiposParticipacion.put("COAUTOR", "COAUTOR");

        return tiposParticipacion;
    }
/**
 * Método que devuelve los tipos de estado de publicación.
     * @return tiposEstadoPublicacion
 */
    public static Map<String, String> retornarTiposEstadoPubli() {
        tiposEstadoPublicacion = new HashMap<String, String>();
        tiposEstadoPublicacion.put("PUBLICADO", "PUBLICADO");
        tiposEstadoPublicacion.put("ACEPTADO PARA PUBLICACION", "ACEPTADO PARA PUBLICACION");

        return tiposEstadoPublicacion;
    }
/**
 * Método que devuelve los tipos de base de datos en la cual puede una publicación puede ser indexada.
     * @return tiposBaseDatosRevista
 */
    public static Map<String, String> retornarBaseDatos() {
        tiposBaseDatosRevista = new HashMap<String, String>();
        tiposBaseDatosRevista.put("SCOPUS", "SCOPUS");
        tiposBaseDatosRevista.put("LATIN INDEX", "LATIN INDEX");
        tiposBaseDatosRevista.put("OTRA", "OTRA");

        return tiposBaseDatosRevista;
    }
/**
 * Método que devuelve los tipos de grado de un título 
     * @return grado
 */
    public static Map<String, String> retornarGrado() {
        grado = new HashMap<String, String>();
        grado.put("DIPLOMA SUPERIOR", "DIPLOMA SUPERIOR");
        grado.put("DOCTORADO (Ph.D)", "DOCTORADO (Ph.D)");
        grado.put("ESPECIALISTA", "ESPECIALISTA");
        grado.put("MAGISTER O EQUIVALENTE", "MAGISTER O EQUIVALENTE");
        grado.put("TERCER NIVEL", "TERCER NIVEL");

        return grado;

    }
/**
 * Método que devuelve el estado de un título   
     * @return estadoCurso
 */
    public static Map<String, String> retornarEstado() {
        estadoCurso = new HashMap<String, String>();
        estadoCurso.put("TERMINADO", "TERMINADO");
        estadoCurso.put("EN CURSO", "EN CURSO");
        //tiposBaseDatosRevista.put("OTRA","OTRA");

        return estadoCurso;
    }
/**
 * Método que devuelve el tipo de personal con el cual cuenta la IES  
     * @return tipoPersonal
 */
    public static Map<String, String> retornarTipoPersonal() {
        tipoPersonal = new HashMap<String, String>();
        tipoPersonal.put("PROFESOR", "PROFESOR");
        tipoPersonal.put("INVESTIGADOR", "INVESTIGADOR");
        //tiposBaseDatosRevista.put("OTRA","OTRA");

        return tipoPersonal;
    }
/**
 * Método que devuelve el tipo de institución de una IES  
     * @return tipoInstitucion
 */
    public static Map<String, String> retornarTipoInstitucion() {
        tipoInstitucion = new HashMap<String, String>();
        tipoInstitucion.put("PUBLICA", "PUBLICA");
        tipoInstitucion.put("PRIVADA", "PRIVADA");
        //tiposBaseDatosRevista.put("OTRA","OTRA");

        return tipoInstitucion;
    }
/**
 * Método que devuelve los tipo de identificación que un docente puede tener
 * @return tpIdentificacion
 */
    public static Map<String, String> retornarTipoIdentificacion() {
        tpIdentificacion = new HashMap<String, String>();
        tpIdentificacion.put("CEDULA", "CEDULA");
        tpIdentificacion.put("PASAPORTE", "PASAPORTE");
        //tiposBaseDatosRevista.put("OTRA","OTRA");

        return tpIdentificacion;
    }
/**
 * Método que devuelve los semestres que puede tener una materia
 * @return semestres
 */
    public static Map<String, String> retornarSemestres() {
        semestres = new HashMap<String, String>();
        semestres.put("PRIMERO", "PRIMERO");
        semestres.put("SEGUNDO", "SEGUNDO");
        semestres.put("TERCERO", "TERCERO");
        semestres.put("CUARTO", "CUARTO");
        semestres.put("QUINTO", "QUINTO");
        semestres.put("SEXTO", "SEXTO");
        semestres.put("SEPTIMO", "SEPTIMO");
        semestres.put("OCTAVO", "OCTAVO");
        semestres.put("NOVENO", "NOVENO");
        semestres.put("DECIMO", "DECIMO");
        semestres.put("OTRO", "OTRO");

        //tiposBaseDatosRevista.put("OTRA","OTRA");
        return semestres;
    }
}
