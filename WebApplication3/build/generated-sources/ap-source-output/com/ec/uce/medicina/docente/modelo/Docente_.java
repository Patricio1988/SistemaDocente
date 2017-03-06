package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Capacitacion;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Formacion;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import com.ec.uce.medicina.docente.modelo.Publicaciones;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Docente.class)
public class Docente_ { 

    public static volatile SingularAttribute<Docente, String> apellidoPaterno;
    public static volatile SingularAttribute<Docente, String> telefonoDocente;
    public static volatile SingularAttribute<Docente, String> correoPersonal;
    public static volatile ListAttribute<Docente, Formacion> formacionList;
    public static volatile SingularAttribute<Docente, Date> fechaNacimiento;
    public static volatile SingularAttribute<Docente, Integer> porcentajeDiscapacidad;
    public static volatile SingularAttribute<Docente, PaisOrigen> idPais;
    public static volatile SingularAttribute<Docente, String> identificacion;
    public static volatile ListAttribute<Docente, Capacitacion> capacitacionList;
    public static volatile SingularAttribute<Docente, Integer> idDocente;
    public static volatile SingularAttribute<Docente, String> apellidoMaterno;
    public static volatile SingularAttribute<Docente, String> nombres;
    public static volatile SingularAttribute<Docente, String> tipoDiscapacidad;
    public static volatile SingularAttribute<Docente, String> direccionDocente;
    public static volatile SingularAttribute<Docente, String> celularDocente;
    public static volatile SingularAttribute<Docente, String> correoInstitucional;
    public static volatile ListAttribute<Docente, CarreraDocente> carreradocenteList;
    public static volatile SingularAttribute<Docente, String> obsDocente;
    public static volatile ListAttribute<Docente, Publicaciones> publicacionesList;
    public static volatile SingularAttribute<Docente, String> numConadis;
    public static volatile SingularAttribute<Docente, Boolean> discapacidad;
    public static volatile SingularAttribute<Docente, String> paisDocente;
    public static volatile SingularAttribute<Docente, String> tIdentificacion;
    public static volatile SingularAttribute<Docente, String> sexo;

}