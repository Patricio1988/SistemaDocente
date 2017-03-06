package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Docente;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(PaisOrigen.class)
public class PaisOrigen_ { 

    public static volatile SingularAttribute<PaisOrigen, Integer> idPais;
    public static volatile SingularAttribute<PaisOrigen, String> nomPais;
    public static volatile ListAttribute<PaisOrigen, Docente> docenteList;

}