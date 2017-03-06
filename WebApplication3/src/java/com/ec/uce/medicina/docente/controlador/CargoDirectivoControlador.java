/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.alfresco.archivo.AlfrescoConexion;
import com.ec.uce.medicina.docente.modelo.CargoDirectivo;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.Periodo;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.CargoDirectivoServicio;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.PeriodoServicio;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.runtime.DocumentImpl;
import org.apache.chemistry.opencmis.client.util.FileUtils;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConstraintException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 * Declaración del ManagedBean CargoDirectivoControlador que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class CargoDirectivoControlador extends AlfrescoConexion {

    //Atributo id docente
    private int idCarreraDocente;
    //Atributo id período
    private int idPeriodo;
    //Atributo id Carrera
    private int idCarrera;
    //Declaración objeto tipo Docente
    private Docente docente;
    //Declaración objeto tipo Periodo
    private Periodo periodo;
    //Declaración objeto tipo CarreraDocente
    private CarreraDocente carreraDocente;
    //Declaración objeto tipo CargoDirectivo
    private CargoDirectivo cargoDirectivo;
    //Atributo estado del combo box
    private String estadoComboBox;
    //Servicios
    @EJB
    private CargoDirectivoServicio servCargoDirectivo;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;
    @EJB
    private PeriodoServicio servPeriodo;
    /**
     * Atributo para almacenar el tipo de cargo de un docente como una
     * estructura clave valor
     */
    private Map<String, String> tiposCargo;
    //Número de cedula del Docente
    private String numeroCedula;

    private StreamedContent file = null;
    /**
     * Evento UploadedFile para subir un archivo
     */
    private UploadedFile event;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
    private String vacio;
//Inicialización de variables

    @PostConstruct
    public void inicializar() {
        //Instancia de objetos
        docente = new Docente();
        cargoDirectivo = new CargoDirectivo();
        //se carga los tipos de cargos
        tiposCargo = Tablas.retornarTiposCargos();
        numeroCedula = Constantes.VACIO;
        idCarreraDocente = 0;
        idPeriodo = 0;
        file = null;
        event = null;
        vacio = " ";
    }

    /**
     * Método que permite crear un registro en la base de datos, si todo sale
     * bien cierra el dialogo para crear caso contrario no
     */
    public void crear() {
        try {

            idCarreraDocente = carreraDocente.getIdCarreraDocente();
           // cargoDirectivo.setIdPeriodo(servPeriodo.encontrarPeriodoID(idPeriodo));
            cargoDirectivo.setIdCarreraDocente(servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente));
            servCargoDirectivo.insertarCargoDirectivo(cargoDirectivo);
            MensajesFaces.informacion("Guardado", "Exitoso");
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCargoDirectivoAgregar').hide()");//Cierra el dialogo para agregar
            req = null;
            idCarreraDocente = 0;
            cargoDirectivo = new CargoDirectivo();
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos, si todo
     * sale bien cierra el dialogo para crear caso contrario no
     */
    public void actualizar() {

        try {

            idCarreraDocente = carreraDocente.getIdCarreraDocente();;
            cargoDirectivo.setIdCarreraDocente(servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente));
            servCargoDirectivo.actualizarCargoDirectivo(cargoDirectivo);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide();");
            Document document = (Document) buscarDocumento(cargoDirectivo.getIdDocCargo());
            if (document != null) {
                actualizarMetadatosCargo(document);
            }
            req = null;
            cargoDirectivo = new CargoDirectivo();
            idCarreraDocente=0;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Método que permite elimnar un registro de tipo Capacitación
     *
     * @param cargo
     */
    public void eliminar(CargoDirectivo cargo) {

        try {
            //Elimino el registro de la base de datos si no ha subido algún archivo como evidencia

            if (cargo.getIdDocCargo() == null) {
                servCargoDirectivo.eliminarCargoDirectivo(cargo);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            } else {
                //elimino el registro de la base de datos junto con el archivo físico
                eliminarArchivoRepositorio(cargo.getIdDocCargo(), cargo.getIdCargo());
                servCargoDirectivo.eliminarCargoDirectivo(cargo);
                cargoDirectivo = new CargoDirectivo();

                MensajesFaces.informacion("Eliminado", "Exitoso");
            }

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite abrir el dialogo para crear un registro de tipo cargo
     * directivo siempre que se haya seleccionado un docente
     */
    public void abrirDialogo() {
        try {
            carreraDocente.getIdDocente().getIdentificacion();
                     req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCargoDirectivoAgregar').show()");
            req = null;
        } 
        catch (NullPointerException ex) {
            MensajesFaces.informacion("Seleccione un Docente", "Primero");
        }
        catch (Exception e) {
             MensajesFaces.informacion("Error",e.getMessage());
        }
       
    }
//Método que permite cerrar

    public void cerrar() {
        cargoDirectivo = new CargoDirectivo();
        idCarreraDocente = 0;
        event = null;

    }

    /**
     * Método que permite encontrar un docente por su número de cédula
     *
     * @param idCarreraDocente
     */
    public void recuperardatosCarreraDocenteId(int idCarreraDocente) {
        carreraDocente = servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente);
        if (carreraDocente != null) {
            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();
            idCarrera=carreraDocente.getIdCarrera().getIdCarrera();
            idPeriodo=0;

            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            numeroCedula = Constantes.VACIO;
            docente = new Docente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }

    }

    /**
     * Método que devuelve todos los registros de cargo directivo de un docente
     * de un determinado período
     * @param idPeriodo
     * @return
     * servCargoDirectivo.listaCargoDirectivoporDocente(numeroCedula,idPeriodo)
     */
    public List<CargoDirectivo> listaCargoDirectivoDocente(int idPeriodo) {
        List<CargoDirectivo> lista;

        lista=servCargoDirectivo.listaCargoDirectivoporDocente(numeroCedula, idPeriodo,idCarrera);
        return lista;

    }

    /**
     * Método que encuentra un cargo directivo por su id
     *
     * @param idCargo
     */
    public void recuperarCargoID(int idCargo) {

        cargoDirectivo = servCargoDirectivo.buscarCargoDirectivoporID(idCargo);
        if (cargoDirectivo != null) {
            MensajesFaces.informacion("CargoDirectivo", "Encontrada");
        } else {
            docente = new Docente();
            MensajesFaces.informacion("CargoDirectivo", "No encontrada");
        }
    }

    /**
     * Método que duvuelve una lista de todos los docentes
     *
     * @return servDocente.listarDocente
     */
    public List<Docente> recuperartodosDocentes() {

        return servDocente.listarDocente();
    }
    //    cerrar dialogo

    public void handleClose(CloseEvent event) {
        cargoDirectivo = new CargoDirectivo();
        event = null;
        file = null;
    }

    //Archivos
    /**
     * Método que permite obtener un archivo
     *
     * @param upload evento que me permite subir el archivo en disco
     */
    public void handleFileUpload(FileUploadEvent upload) {
        event = upload.getFile();//obtengo el archivo y le asigno a la variable event
        //Guardo el nombre del archivo que se va a subir
        cargoDirectivo.setNombreDocCargo(event.getFileName());

    }

    /**
     * Método que permite subir un archivo al repositorio de alfresco utilizando
     * CMIS API
     */
    public void subirArchivoRepositorio() {

        String nombreFolder = carreraDocente.getIdCarrera().getIdFacultad().getNomFacultad();

        //String nombreFolder1 = "Capacitacion1";
        try {
            // Find the root folder of our target site
            String rootFolderId = getRootFolderId(getSite());

            // Create a new folder in the root folder
            Folder subFolder = createFolder(rootFolderId, nombreFolder);
            Folder subFolder1 = subFolder;
            for (int i = 0; i < listaSubCarpetas().size(); i++) {
                subFolder1 = createFolder(subFolder1.getId(), listaSubCarpetas().get(i));
            }

            // Create a test document in the subFolder
            createDocument(subFolder1, event, getLocalFileType(), cargarMetadatosCargo());
            cargoDirectivo.setIdDocCargo(getIdDocumento());
            servCargoDirectivo.actualizarCargoDirectivo(cargoDirectivo);
            MensajesFaces.informacion("El archivo", "Se ha subido con exito");

            event = null;
            cargoDirectivo = new CargoDirectivo();

        } catch (IOException ioe) {
            MensajesFaces.error("Error", "El archivo no ha sido subido!" + ioe.getMessage());
        }
         catch(CmisConstraintException cmcons)
        {
             MensajesFaces.error("Error", "Viloación de restrición!" + cmcons.getMessage());
        }
         catch (CmisContentAlreadyExistsException ccaee) {
       
            MensajesFaces.error("Error", "El documento: " + event.getFileName() +" ya existe, porfavor cambie de nombre al archivo");
        }
        catch (NullPointerException nPex) {
              MensajesFaces.error("Error","Antes de cargar el archivo de clic en el boton Upload");
          }
    }

    public Map<String, Object> cargarMetadatosCargo()throws NullPointerException{
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        // Add the object type ID if it wasn't already
        if (props.get(PropertyIds.OBJECT_TYPE_ID) == null) {
            props.put(PropertyIds.OBJECT_TYPE_ID, "D:uce:TipoDocCargoDirectivo");
        }
        //Lista para anadir al Documento
        List<String> asp = new ArrayList<>();
        asp.add("P:uce:carrera");
        asp.add("P:uce:docente");
        asp.add("P:uce:fechas");
        asp.add("P:uce:periodo");
        props.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, asp);

        // Add the name if it wasn't already
        if (props.get("cmis:name") == null) {
            props.put("cmis:name", event.getFileName());
        }

       if (props.get("uce:apellidos") == null) {
            if (cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoMaterno() == null || cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoMaterno().equals(" ")) {
                   props.put("uce:apellidos", cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoPaterno() + " " + cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoMaterno());
             
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", cargoDirectivo.getIdCarreraDocente().getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", cargoDirectivo.getIdCarreraDocente().getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreFacultad") == null) {
            props.put("uce:nombreFacultad", carreraDocente.getIdCarrera().getIdFacultad().getNomFacultad());
        }
        if (props.get("uce:nombreCarrera") == null) {
            props.put("uce:nombreCarrera", carreraDocente.getIdCarrera().getNomCarrera());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", cargoDirectivo.getFechaIniCargo());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", cargoDirectivo.getFechaFinCargo());
        }

        if (props.get("uce:nombreCargo") == null) {
            props.put("uce:nombreCargo", cargoDirectivo.getTipoCargo());
        }
        if (props.get("uce:numDocumentoCargo") == null) {
            props.put("uce:numDocumentoCargo", cargoDirectivo.getNumDocumento());
        }
        if (props.get("uce:nombrePeriodo") == null) {
            props.put("uce:nombrePeriodo", cargoDirectivo.getIdCarreraDocente().getIdPeriodo().getNombrePeriodo());
        }
        return props;

    }

    public void actualizarMetadatosCargo(Document documento) {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        List<Object> aspects = documento.getProperty(PropertyIds.SECONDARY_OBJECT_TYPE_IDS).getValues();

        // Add the name if it wasn't already
        if (props.get("uce:apellidos") == null) {
            if (cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoMaterno() == null || cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoPaterno());
                
            } else {
                props.put("uce:apellidos", cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoPaterno() + " " + cargoDirectivo.getIdCarreraDocente().getIdDocente().getApellidoMaterno());
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", cargoDirectivo.getIdCarreraDocente().getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", cargoDirectivo.getIdCarreraDocente().getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreFacultad") == null) {
            props.put("uce:nombreFacultad", carreraDocente.getIdCarrera().getIdFacultad().getNomFacultad());
        }
        if (props.get("uce:nombreCarrera") == null) {
            props.put("uce:nombreCarrera", carreraDocente.getIdCarrera().getNomCarrera());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", cargoDirectivo.getFechaIniCargo());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", cargoDirectivo.getFechaFinCargo());
        }

        if (props.get("uce:nombreCargo") == null) {
            props.put("uce:nombreCargo", cargoDirectivo.getTipoCargo());
        }
        if (props.get("uce:numDocumentoCargo") == null) {
            props.put("uce:numDocumentoCargo", cargoDirectivo.getNumDocumento());
        }
        if (props.get("uce:nombrePeriodo") == null) {
            props.put("uce:nombrePeriodo", cargoDirectivo.getIdCarreraDocente().getIdPeriodo().getNombrePeriodo());
        }
        Document documento_actualizado = (Document) documento.updateProperties(props);

    }

    /**
     * Método que crea una lista de subcarpetas donde se guardaran los archivo
     *
     * @return nombreSubCarpetaList
     */
    public ArrayList<String> listaSubCarpetas() {
        // carreraDocente=servCarreraDocente.buscarDocenteCarreraporID(cargoDirectivo.getIdDocente().getIdDocente(), cargoDirectivo.getIdDocente().getCarreradocenteList().)

        ArrayList<String> nombreSubCarpetaList = new ArrayList<String>();
        nombreSubCarpetaList.add("Docente");
        nombreSubCarpetaList.add("Cargo Directivo");
        nombreSubCarpetaList.add(carreraDocente.getIdCarrera().getNomCarrera());
//        nombreSubCarpetaList.add("Periodo" + "_" + cargoDirectivo.getIdPeriodo().getNombrePeriodo());

        if (docente.getApellidoMaterno() == null || docente.getApellidoMaterno().equals(" ")) {
            nombreSubCarpetaList.add(carreraDocente.getIdDocente().getIdentificacion() + "_" + carreraDocente.getIdDocente().getApellidoPaterno() + "_" + carreraDocente.getIdDocente().getNombres());
        } else {
            nombreSubCarpetaList.add(carreraDocente.getIdDocente().getIdentificacion() + "_" + carreraDocente.getIdDocente().getApellidoPaterno() + "_" + carreraDocente.getIdDocente().getApellidoMaterno() + "_" + carreraDocente.getIdDocente().getNombres());
        }
        return nombreSubCarpetaList;
    }

    /**
     * Método que me permite descargar un archivo del repositorio de
     * alfresco,por el id del documento creado y guardado en la base de datos
     *
     * @param idDocumento
     */
    public void descargarArchivoRepositorio(String idDocumento) {
        try {
            /**
             * Obtengo una session con cmis
             */
            Session cmisSession = getCmisSession();
            /**
             * Encuentro el archivo que se encuntra en el repositorio por su id
             * y una session
             */
            CmisObject object = FileUtils.getObject(idDocumento, cmisSession);
            //Verificamos que el objeto sea de tipo DocumentImpl
            if (object instanceof DocumentImpl) {
                Document doc = (DocumentImpl) object;
                ContentStream contentStream = doc.getContentStream();
                InputStream ficheroStream = doc.getContentStream().getStream();

                byte contenido[] = IOUtils.toByteArray(doc.getContentStream().getStream());

                ficheroStream.read(contenido);
                InputStream stream = new ByteArrayInputStream(contenido);

                file = new DefaultStreamedContent(stream, "Imagen/pdf", doc.getName());

                ficheroStream.close();

            }
        } catch (FileNotFoundException ex) {
            MensajesFaces.error("Error", "Detalle" + ex.getMessage());
        } catch (IOException ioex) {
            MensajesFaces.error("Error", "Detalle" + ioex.getMessage());
        } catch (CmisObjectNotFoundException cobex) {
            MensajesFaces.error("Error", "El Documento no se encuentra " + cobex.getMessage());
        }
    }

    /**
     * Método que me permite eliminar un archivo de Alfresco por el id del
     * documento y el id del cargo directivo
     *
     * @param idDocumento
     * @param idCargo
     */
    public void eliminarArchivoRepositorio(String idDocumento, int idCargo) {
        //Obtengo una session
        Session cmisSession = getCmisSession();
        //Encuentro la capacitacion por su id
        cargoDirectivo = servCargoDirectivo.buscarCargoDirectivoporID(idCargo);
        try {
            //Obtengo el objeto por su id y una session
            CmisObject object = FileUtils.getObject(idDocumento, cmisSession);
            /**
             * Verifico que el objeto se de tipo DocumentImpl
             */
            if (object instanceof DocumentImpl) {
                Document doc = (DocumentImpl) object;
                //Elimino el documento
                doc.delete(true);
                // modifico el valor de los atributo a null
                cargoDirectivo.setNombreDocCargo(null);
                cargoDirectivo.setIdDocCargo(null);
                servCargoDirectivo.actualizarCargoDirectivo(cargoDirectivo);
                cargoDirectivo = new CargoDirectivo();
                MensajesFaces.informacion("Archivo", "Eliminado");

            }
        } catch (CmisObjectNotFoundException confe) {
            //Mesaje de error 
            MensajesFaces.error("Error", "No se pudo encontrar el documento que desea eliminar" + confe.getMessage());
        }

    }

//GET AND SET
    /**
     * Retorna el archivo
     *
     * @return event
     */
    public UploadedFile getEvent() {
        return event;
    }

    /**
     * Modifica el archivo
     *
     * @param event
     */
    public void setEvent(UploadedFile event) {
        this.event = event;
    }

    /**
     * Retorna el id del periodo
     *
     * @return idPeriodo
     */
    public int getIdPeriodo() {
        return idPeriodo;
    }

    /**
     * Modifica el id del periodo
     *
     * @param idPeriodo
     */
    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    /**
     * Retorna el id de la carrera a la cual pertenece el docente
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Mofica el id de la carrera a la cual pertenece el docente
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    /**
     * Devuelve un objeto de tipo CargoDirectivo
     *
     * @return cargoDirectivo
     */
    public CargoDirectivo getCapacitacion() {
        return cargoDirectivo;
    }

    /**
     * Modifica un objeto de tipo CargoDirectivo
     *
     * @param cargoDirectivo
     */
    public void setCapacitacion(CargoDirectivo cargoDirectivo) {
        this.cargoDirectivo = cargoDirectivo;
    }

    /**
     * Devuelve los tipo de cargo
     *
     * @return tiposCargo
     */
    public Map<String, String> getTiposCargo() {
        return tiposCargo;
    }

    /**
     * Modifica los tipo de cargo
     *
     * @param tiposCargo
     */
    public void setTiposCargo(Map<String, String> tiposCargo) {
        this.tiposCargo = tiposCargo;
    }

    /**
     * Retorna el número de cedula del docente
     *
     * @return numeroCedula
     */
    public String getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * Modifica el número de cedula del docente
     *
     * @param numeroCedula
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    /**
     * Retorna un objeto de tipoDocente
     *
     * @return docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * Modifica un objeto de tipoDocente
     *
     * @param docente
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }
        /**
     * Devuelve el id del docente
     *
     * @return idCarreraDocente
     */

    public int getIdCarreraDocente() {
        return idCarreraDocente;
    }
   /**
     * Modifica el id del docente
     *
     * @param idCarreraDocente
     */
    public void setIdCarreraDocente(int idCarreraDocente) {
        this.idCarreraDocente = idCarreraDocente;
    }


    

    /**
     * Devuelve el estado del combo box
     *
     * @return estadoComboBox
     */
    public String getEstadoComboBox() {
        return estadoComboBox;
    }

    /**
     * Modifica el estado del combo box
     *
     * @param estadoComboBox
     */
    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

    /**
     * Devuelve un objeto de tipo CargoDirectivo
     *
     * @return cargoDirectivo
     */
    public CargoDirectivo getCargoDirectivo() {
        return cargoDirectivo;
    }

    /**
     * Modifica un objeto de tipo CargoDirectivo
     *
     * @param cargoDirectivo
     */
    public void setCargoDirectivo(CargoDirectivo cargoDirectivo) {
        this.cargoDirectivo = cargoDirectivo;
    }

    /**
     * Devuelve un objeto de tipo Periodo
     *
     * @return periodo
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * Modifica un objeto de tipo Periodo
     *
     * @param periodo
     */
    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    /**
     * Devuelve un objeto de tipo CarreraDocente
     *
     * @return carreraDocente
     */
    public CarreraDocente getCarreraDocente() {
        return carreraDocente;
    }

    /**
     * Modifica un objeto de tipo CarreraDocente
     *
     * @param carreraDocente
     */
    public void setCarreraDocente(CarreraDocente carreraDocente) {
        this.carreraDocente = carreraDocente;
    }

    public String getVacio() {
        return vacio;
    }

    public void setVacio(String vacio) {
        this.vacio = vacio;
    }

}
