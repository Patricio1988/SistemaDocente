package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Universidad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Facultad.class)
public class Facultad_ { 

    public static volatile SingularAttribute<Facultad, String> directorFacultad;
    public static volatile SingularAttribute<Facultad, Universidad> idUniversidad;
    public static volatile SingularAttribute<Facultad, String> nomFacultad;
    public static volatile ListAttribute<Facultad, Carrera> carreraList;
    public static volatile SingularAttribute<Facultad, String> direccionFacultad;
    public static volatile SingularAttribute<Facultad, String> telefono;
    public static volatile SingularAttribute<Facultad, Integer> idFacultad;

}