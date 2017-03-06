package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import com.ec.uce.medicina.docente.modelo.Malla;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Materia.class)
public class Materia_ { 

    public static volatile SingularAttribute<Materia, Integer> numHoras;
    public static volatile ListAttribute<Materia, DocentesMallaContratoMateria> docentesMallaContratoMateriaList;
    public static volatile SingularAttribute<Materia, Integer> idMateria;
    public static volatile SingularAttribute<Materia, String> obserMateria;
    public static volatile SingularAttribute<Materia, String> nomMateria;
    public static volatile SingularAttribute<Materia, Integer> creditos;
    public static volatile SingularAttribute<Materia, String> semestre;
    public static volatile SingularAttribute<Materia, String> codMateria;
    public static volatile SingularAttribute<Materia, Malla> idMalla;

}