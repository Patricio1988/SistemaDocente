package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Publicaciones;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(TipoPublicacion.class)
public class TipoPublicacion_ { 

    public static volatile SingularAttribute<TipoPublicacion, String> nomTipoPublicacion;
    public static volatile ListAttribute<TipoPublicacion, Publicaciones> publicacionesList;
    public static volatile SingularAttribute<TipoPublicacion, Integer> idTipoPublicacion;
    public static volatile SingularAttribute<TipoPublicacion, Boolean> estadoHabilitarRevista;

}