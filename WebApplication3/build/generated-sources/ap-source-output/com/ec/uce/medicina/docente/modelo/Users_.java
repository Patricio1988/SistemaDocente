package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.modelo.Carrera;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SingularAttribute<Users, Carrera> idCarrera;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> apellidoUsuario;
    public static volatile SingularAttribute<Users, String> ci;
    public static volatile SingularAttribute<Users, Date> fechaRegistro;
    public static volatile SingularAttribute<Users, String> correo;
    public static volatile SingularAttribute<Users, String> nombreUsuario;
    public static volatile SingularAttribute<Users, Boolean> enabled;
    public static volatile SingularAttribute<Users, Authorities> authorities;
    public static volatile SingularAttribute<Users, String> username;

}