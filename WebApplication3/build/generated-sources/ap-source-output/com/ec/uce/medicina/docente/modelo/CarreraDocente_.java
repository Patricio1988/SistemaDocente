package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.CargoDirectivo;
import com.ec.uce.medicina.docente.modelo.Carrera;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.Periodo;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(CarreraDocente.class)
public class CarreraDocente_ { 

    public static volatile SingularAttribute<CarreraDocente, Integer> idCarreraDocente;
    public static volatile SingularAttribute<CarreraDocente, Carrera> idCarrera;
    public static volatile SingularAttribute<CarreraDocente, Date> fechaIngresoIes;
    public static volatile SingularAttribute<CarreraDocente, String> observaciones;
    public static volatile SingularAttribute<CarreraDocente, Boolean> estadoDocente;
    public static volatile SingularAttribute<CarreraDocente, Periodo> idPeriodo;
    public static volatile SingularAttribute<CarreraDocente, Docente> idDocente;
    public static volatile SingularAttribute<CarreraDocente, Date> fechaSalidaIes;
    public static volatile ListAttribute<CarreraDocente, CargoDirectivo> cargoDirectivoList;
    public static volatile ListAttribute<CarreraDocente, RelacionLaboral> relacionLaboralList;

}