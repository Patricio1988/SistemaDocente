package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Actividad;
import com.ec.uce.medicina.docente.modelo.Materia;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(DocentesMallaContratoMateria.class)
public class DocentesMallaContratoMateria_ { 

    public static volatile SingularAttribute<DocentesMallaContratoMateria, Actividad> idActivida;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, Materia> idMateria;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, RelacionLaboral> idReLaboral;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, String> obsDocMatMalla;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, Integer> numParaleslos;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, Integer> numEstudiantes;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, Integer> idDocenteMateriaMalla;
    public static volatile SingularAttribute<DocentesMallaContratoMateria, Integer> numHorasAsignatura;

}