package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Materia;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Malla.class)
public class Malla_ { 

    public static volatile SingularAttribute<Malla, Integer> creditosConTesis;
    public static volatile SingularAttribute<Malla, String> nombreDocMalla;
    public static volatile SingularAttribute<Malla, String> nomMalla;
    public static volatile SingularAttribute<Malla, String> codMalla;
    public static volatile SingularAttribute<Malla, String> organizacionMalla;
    public static volatile SingularAttribute<Malla, Integer> creditosSinTesis;
    public static volatile SingularAttribute<Malla, String> idDocMalla;
    public static volatile SingularAttribute<Malla, Integer> mesesConTesis;
    public static volatile SingularAttribute<Malla, Integer> idMalla;
    public static volatile SingularAttribute<Malla, Carrera> idCarrera;
    public static volatile SingularAttribute<Malla, Integer> mesesSinTesis;
    public static volatile SingularAttribute<Malla, Boolean> estadoMalla;
    public static volatile ListAttribute<Malla, Materia> materiaList;
    public static volatile SingularAttribute<Malla, Date> fechaIniMalla;
    public static volatile SingularAttribute<Malla, Integer> numSemestres;
    public static volatile SingularAttribute<Malla, Date> fechaFinMalla;

}