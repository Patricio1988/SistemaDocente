package com.ec.uce.medicina.docente.modelo;

import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Categoria;
import com.ec.uce.medicina.docente.modelo.DocentesMallaContratoMateria;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-11-29T11:49:29")
@StaticMetamodel(RelacionLaboral.class)
public class RelacionLaboral_ { 

    public static volatile SingularAttribute<RelacionLaboral, String> numDocumento;
    public static volatile SingularAttribute<RelacionLaboral, String> idDocRelacionLabo;
    public static volatile SingularAttribute<RelacionLaboral, Date> fechaInicioContrato;
    public static volatile SingularAttribute<RelacionLaboral, String> tipoPersonal;
    public static volatile SingularAttribute<RelacionLaboral, String> obsRelacionLaboral;
    public static volatile SingularAttribute<RelacionLaboral, CarreraDocente> idCarreraDocente;
    public static volatile SingularAttribute<RelacionLaboral, String> contratoRelacionado;
    public static volatile SingularAttribute<RelacionLaboral, BigDecimal> remuMensual;
    public static volatile SingularAttribute<RelacionLaboral, Date> fechaFinContrato;
    public static volatile ListAttribute<RelacionLaboral, DocentesMallaContratoMateria> docentesMallaContratoMateriaList;
    public static volatile SingularAttribute<RelacionLaboral, Integer> idReLaboral;
    public static volatile SingularAttribute<RelacionLaboral, Boolean> estadoRelacion;
    public static volatile SingularAttribute<RelacionLaboral, TipoDocumento> idTipoDocumento;
    public static volatile SingularAttribute<RelacionLaboral, Boolean> ingresoPorConcurso;
    public static volatile SingularAttribute<RelacionLaboral, String> tiempoDedicacion;
    public static volatile SingularAttribute<RelacionLaboral, Categoria> idCategoria;
    public static volatile SingularAttribute<RelacionLaboral, RelacionIes> idRelacionIes;
    public static volatile SingularAttribute<RelacionLaboral, String> nombreDocRelacionLabo;
    public static volatile SingularAttribute<RelacionLaboral, TiempoDedicacion> idTdedi;
    public static volatile SingularAttribute<RelacionLaboral, BigDecimal> remuHora;

}