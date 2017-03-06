package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Facultad;
import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Carrera.class)
public class Carrera_ { 

    public static volatile ListAttribute<Carrera, Users> usersList;
    public static volatile SingularAttribute<Carrera, String> areaunesco;
    public static volatile SingularAttribute<Carrera, Date> fechaRegConesup;
    public static volatile SingularAttribute<Carrera, String> tituloOtorga;
    public static volatile SingularAttribute<Carrera, Date> fechaOrganoColegiado;
    public static volatile SingularAttribute<Carrera, Integer> duracionCarrera;
    public static volatile SingularAttribute<Carrera, Facultad> idFacultad;
    public static volatile SingularAttribute<Carrera, Integer> idCarrera;
    public static volatile SingularAttribute<Carrera, String> numRegOrganoColegiado;
    public static volatile ListAttribute<Carrera, CarreraDocente> carreradocenteList;
    public static volatile SingularAttribute<Carrera, String> nomCarrera;
    public static volatile SingularAttribute<Carrera, String> modalidad;
    public static volatile ListAttribute<Carrera, Malla> mallaList;
    public static volatile SingularAttribute<Carrera, String> numRegConesup;
    public static volatile SingularAttribute<Carrera, String> telefono;
    public static volatile SingularAttribute<Carrera, String> nomDirector;
    public static volatile SingularAttribute<Carrera, String> nivel;
    public static volatile SingularAttribute<Carrera, String> subareaunesco;

}