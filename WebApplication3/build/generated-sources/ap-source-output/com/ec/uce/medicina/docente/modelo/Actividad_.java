package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, Integer> idActivida;
    public static volatile ListAttribute<Actividad, DocentesMallaContratoMateria> docentesMallaContratoMateriaList;
    public static volatile SingularAttribute<Actividad, String> nomActividad;
    public static volatile SingularAttribute<Actividad, Boolean> materia;

}