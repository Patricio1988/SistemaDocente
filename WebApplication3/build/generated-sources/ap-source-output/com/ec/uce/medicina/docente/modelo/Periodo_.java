package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Periodo.class)
public class Periodo_ { 

    public static volatile SingularAttribute<Periodo, String> nombrePeriodo;
    public static volatile SingularAttribute<Periodo, Date> feInPer;
    public static volatile ListAttribute<Periodo, CarreraDocente> carreraDocenteList;
    public static volatile SingularAttribute<Periodo, Integer> idPeriodo;
    public static volatile SingularAttribute<Periodo, Date> feFinPer;
    public static volatile SingularAttribute<Periodo, Boolean> estadoPeriodo;

}