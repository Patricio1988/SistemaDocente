package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(TiempoDedicacion.class)
public class TiempoDedicacion_ { 

    public static volatile SingularAttribute<TiempoDedicacion, Integer> numHoras;
    public static volatile ListAttribute<TiempoDedicacion, RelacionLaboral> relacionlaboralList;
    public static volatile SingularAttribute<TiempoDedicacion, String> nomTdedi;
    public static volatile SingularAttribute<TiempoDedicacion, Integer> idTdedi;

}