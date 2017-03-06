package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Facultad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Universidad.class)
public class Universidad_ { 

    public static volatile SingularAttribute<Universidad, Integer> codUniversidad;
    public static volatile SingularAttribute<Universidad, String> tipoUniversidad;
    public static volatile SingularAttribute<Universidad, Integer> idUniversidad;
    public static volatile SingularAttribute<Universidad, String> sitioWeb;
    public static volatile SingularAttribute<Universidad, String> ciudad;
    public static volatile SingularAttribute<Universidad, String> telefonoUniversidad;
    public static volatile SingularAttribute<Universidad, String> provincia;
    public static volatile ListAttribute<Universidad, Facultad> facultadList;
    public static volatile SingularAttribute<Universidad, String> direccionUniversidad;
    public static volatile SingularAttribute<Universidad, String> autoridad;
    public static volatile SingularAttribute<Universidad, String> nomUniversidad;
    public static volatile SingularAttribute<Universidad, String> pais;

}