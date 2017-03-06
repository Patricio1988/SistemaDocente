package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.Ies;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Formacion.class)
public class Formacion_ { 

    public static volatile SingularAttribute<Formacion, String> grado;
    public static volatile SingularAttribute<Formacion, String> nomTitulo;
    public static volatile SingularAttribute<Formacion, String> numRegSenescyt;
    public static volatile SingularAttribute<Formacion, Integer> idFormacion;
    public static volatile SingularAttribute<Formacion, String> codSubareaEspecifica;
    public static volatile SingularAttribute<Formacion, Ies> idIes;
    public static volatile SingularAttribute<Formacion, Docente> idDocente;
    public static volatile SingularAttribute<Formacion, Date> fechaTitulo;
    public static volatile SingularAttribute<Formacion, String> idDocFormacion;
    public static volatile SingularAttribute<Formacion, String> nombreIes;
    public static volatile SingularAttribute<Formacion, Integer> codIesEstudio;
    public static volatile SingularAttribute<Formacion, String> obsTitulo;
    public static volatile SingularAttribute<Formacion, String> nombreDocFormacion;
    public static volatile SingularAttribute<Formacion, String> estadoTitulo;
    public static volatile SingularAttribute<Formacion, String> nivel;
    public static volatile SingularAttribute<Formacion, String> paisEstudio;

}