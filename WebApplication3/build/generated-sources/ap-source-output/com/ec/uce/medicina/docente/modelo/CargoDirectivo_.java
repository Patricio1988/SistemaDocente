package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(CargoDirectivo.class)
public class CargoDirectivo_ { 

    public static volatile SingularAttribute<CargoDirectivo, CarreraDocente> idCarreraDocente;
    public static volatile SingularAttribute<CargoDirectivo, Integer> idCargo;
    public static volatile SingularAttribute<CargoDirectivo, String> numDocumento;
    public static volatile SingularAttribute<CargoDirectivo, String> idDocCargo;
    public static volatile SingularAttribute<CargoDirectivo, String> obsCargoDirectivo;
    public static volatile SingularAttribute<CargoDirectivo, String> tipoCargo;
    public static volatile SingularAttribute<CargoDirectivo, String> observaciones;
    public static volatile SingularAttribute<CargoDirectivo, Date> fechaIniCargo;
    public static volatile SingularAttribute<CargoDirectivo, String> nombreDocCargo;
    public static volatile SingularAttribute<CargoDirectivo, Date> fechaFinCargo;

}