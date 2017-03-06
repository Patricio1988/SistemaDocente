package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Publicaciones.class)
public class Publicaciones_ { 

    public static volatile SingularAttribute<Publicaciones, String> estadoPublicacion;
    public static volatile SingularAttribute<Publicaciones, Boolean> revisonPares;
    public static volatile SingularAttribute<Publicaciones, String> numIsbnIssn;
    public static volatile SingularAttribute<Publicaciones, String> subareaEspeConocimiento;
    public static volatile SingularAttribute<Publicaciones, String> participacion;
    public static volatile SingularAttribute<Publicaciones, String> nomPublicacion;
    public static volatile SingularAttribute<Publicaciones, Docente> idDocente;
    public static volatile SingularAttribute<Publicaciones, String> nombreDocPublicacion;
    public static volatile SingularAttribute<Publicaciones, String> codPublicacion;
    public static volatile SingularAttribute<Publicaciones, String> obsPublicaciones;
    public static volatile SingularAttribute<Publicaciones, String> idDocPublicacion;
    public static volatile SingularAttribute<Publicaciones, Boolean> filiacion;
    public static volatile SingularAttribute<Publicaciones, String> nomBaseIndexada;
    public static volatile SingularAttribute<Publicaciones, String> nombreRevista;
    public static volatile SingularAttribute<Publicaciones, String> subareaConocimiento;
    public static volatile SingularAttribute<Publicaciones, TipoPublicacion> idTipoPublicacion;
    public static volatile SingularAttribute<Publicaciones, Date> fechaPublicacion;
    public static volatile SingularAttribute<Publicaciones, Integer> idPublicacion;
    public static volatile SingularAttribute<Publicaciones, String> areaConocimiento;
    public static volatile SingularAttribute<Publicaciones, Boolean> revistaIndexada;

}