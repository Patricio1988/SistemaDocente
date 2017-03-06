/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Ies;
import com.ec.uce.medicina.docente.modelo.Universidad;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.FacultadServicio;
import com.uce.uce.medicina.docente.servicio.IesServicio;
import com.uce.uce.medicina.docente.servicio.UniversidadServicio;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean UniversidadControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ManagedBean
@ViewScoped
public class UniversidadControlador {
// Declaracioón del objeto univesrsidad
    private Universidad universidad;
    // Declaracioón del objeto ies
     private Ies ies;
     //Servicios
    @EJB
    private UniversidadServicio servUniversidad;
   @EJB
    private IesServicio servIes;
    @EJB
    private FacultadServicio servFacultad;
    /**Atributo para una lista de universidades*/

    private List<Universidad> listuni;
    /**Atributo id de la IES*/
    private int idIes;
    /**Atributo tipo de institución de la univesidad  como una estructura clave valor
     */
    private Map<String, String> tipoInstitucion;
    //Atributo estado del combo box
     private String estadoComboBox;  
     /**Atributo id de la universidad
     */
    private int idUniversidad ;
     //Atributo RequestContext que encapsula información de una petición HTTP
   private RequestContext req;
   //Constructor por defecto

    public UniversidadControlador() {

    }
//Inicializacón de variables
    @PostConstruct
    public void inicializar() {
        //Instancia de objetos
        universidad = new Universidad();
       ies=new Ies();
        tipoInstitucion = Tablas.retornarTipoInstitucion();
          this.estadoComboBox = Constantes.INACTIVO;

    }
    
    /**Método que permite crear un registro en la base de datos, si todo sale bien cierra el dialogo para crear caso contrario no
 */
    public void crear() {
        
        try {
            universidad.setNomUniversidad(ies.getNomIes());
            universidad.setCodUniversidad(ies.getCodIes());
           
            servUniversidad.insertarUniversidad(universidad);
            req=RequestContext.getCurrentInstance();
            req.execute("PF('dialogoUniversidadAgregar').hide()");//Cierra el dialogo
            universidad = new Universidad();
            ies=new Ies();
            idIes=0;
            req=null;
            MensajesFaces.informacion("Guardado", "Exitoso");
         
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
          
        }
        catch(Error er)
        {
           MensajesFaces.error("Error", "detalle" + er); 
        }
      

    }
   /**Método que permite actualizar un registro en la base de datos, si todo sale bien cierra el dialogo para actualizar, caso contrario no
 */
    public void actualizar() {

        try {
            universidad.setNomUniversidad(ies.getNomIes());
            universidad.setCodUniversidad(ies.getCodIes());

            servUniversidad.actualizarUniversidad(universidad);
            req=RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide()");//Cierra el dialogo
            universidad = new Universidad();
            ies=new Ies();
            idIes=0;
            req=null;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }
    /**Metodo que permite eliminar un registro 
     * @param uni
 */

    public void eliminar(Universidad uni) {
        try {
            boolean verificarfacultadEnUniversidad = servFacultad.buscarRegistroPorUniversidad(uni); //verifica que no exista un registro de de universidad en una facultad        
            if (verificarfacultadEnUniversidad) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                servUniversidad.eliminarUniversidad(uni);
            universidad = new Universidad();
                MensajesFaces.informacion("Eliminado", "Exitoso");
                           }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }
/**Método que devuelve una lista de universidades
     * @return listuni
 */
    public List<Universidad> recuperarTodos() {
        listuni = servUniversidad.listarUniversidad();

        return listuni;
    }

/**Método que busca un universidad por su id
     * @param id
 */
    public void buscarUniversidadID(int id) {
        universidad = servUniversidad.encontrarUniversidadID(id);

        if (universidad != null) {
           
            ies =servIes.buscarporCodigo(universidad.getCodUniversidad());
            idIes=ies.getIdIes();
            // idUniversidad=universidad.getIdUniversidad();
            MensajesFaces.informacion("Universidad", "Encontrada");
        } else {

            MensajesFaces.informacion("Universidad", "No encontrada");
        }
    }
    //Método para cerrar
    public void cerrar()
    {
            universidad = new Universidad();
             ies=new Ies();
            idIes=0;                 
    }
    /**Método que devuelve permite buscar una Ies y que se ejecuta con un evento ajax
     * @param ent
     */
    public void getIES(AjaxBehaviorEvent ent)
{
                ies=new Ies();
		ies=servIes.buscarIesID(idIes);               					   
}

   
    //GET AND SET
    
/**Devuelve un objeto de tipo Universidad
     * @return universidad
 */
    public Universidad getUniversidad() {
        return universidad;
    }
/**Modifica un objeto de tipo Universidad
     * @param  universidad
 */
    public void setUniversidad(Universidad universidad) {
        this.universidad = universidad;
    }
/**Devuelve un lista  con las univesidades
     * @return listuni
 */
    public List<Universidad> getListuni() {
        return listuni;
    }
/**Modifica un lista  con las univesidades
     * @param  listuni
 */
    public void setListuni(List<Universidad> listuni) {
        this.listuni = listuni;
    }
/**Devuelve el id de la IES
     * @return idIes
 */
    public int getIdIes() {
        return idIes;
    }
/**Modifica el id de la IES
     * @param  idIes
 */
    public void setIdIes(int idIes) {
        this.idIes = idIes;
    }
/**Devuelve el estado del combo box
     * @return estadoComboBox
 */
    public String getEstadoComboBox() {
        return estadoComboBox;
    }
/**Modifica el estado del combo box
     * @param  estadoComboBox
 */
    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

  /**Devuelve el tipo de institución que puede tener una universidad
     * @return tipoInstitucion
 */
    public Map<String, String> getTipoInstitucion() {
        return tipoInstitucion;
    }
 /**Modifica el tipo de institución que puede tener una universidad
     * @param  tipoInstitucion
 */
    public void setTipoInstitucion(Map<String, String> tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }
/**Devuelve el id de la universidad
     * @return idUniversidad
 */
    public int getIdUniversidad() {
        return idUniversidad;
    }
/**Modifica el id de la universidad
     * @param  idUniversidad
 */
    public void setIdUniversidad(int idUniversidad) {
        this.idUniversidad = idUniversidad;
    }
/**Devuelve un objeto de tipo Universidad
     * @return ies
 */
    public Ies getIes() {
        return ies;
    }
/**Modifica un objeto de tipo Universidad
     * @param  ies
 */
    public void setIes(Ies ies) {
        this.ies = ies;
    }
    

}
