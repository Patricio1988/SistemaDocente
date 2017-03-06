package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Docente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Capacitacion.class)
public class Capacitacion_ { 

    public static volatile SingularAttribute<Capacitacion, String> tipoCurso;
    public static volatile SingularAttribute<Capacitacion, String> paisActualizacion;
    public static volatile SingularAttribute<Capacitacion, Date> fechaInicioEvento;
    public static volatile SingularAttribute<Capacitacion, Date> fechaFinEvento;
    public static volatile SingularAttribute<Capacitacion, Docente> idDocente;
    public static volatile SingularAttribute<Capacitacion, String> idDocCapacitacion;
    public static volatile SingularAttribute<Capacitacion, String> obsCapacitacion;
    public static volatile SingularAttribute<Capacitacion, Integer> numHoras;
    public static volatile SingularAttribute<Capacitacion, Integer> idCapacitacion;
    public static volatile SingularAttribute<Capacitacion, String> nomInstitucion;
    public static volatile SingularAttribute<Capacitacion, String> localidad;
    public static volatile SingularAttribute<Capacitacion, String> nomEvento;
    public static volatile SingularAttribute<Capacitacion, String> nombreDocCapacitacion;

}