/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.alfresco.archivo.AlfrescoConexion;
import com.ec.uce.medicina.docente.modelo.Capacitacion;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.CapacitacionServicio;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 * Declaración del ManagedBean CapacitacionControlador que sera manejada por jsf
 * de ámbito ViewScoped que permitira realizar las operaciones en las paginas
 * xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ManagedBean
@ViewScoped
public class CapacitacionControlador extends AlfrescoConexion {

    //Atributo id Carrera
    private int idCarrera;
    //Declaración objeto tipo CarreraDocente
    private CarreraDocente carreraDocente;
    /**
     * Atributo que guardar el id del docente
     */
    private int idDocente;
    /**
     * Declaración del objeto Docente
     */
    private Docente docente;
    /**
     * Declaración del objeto Capacitacion
     */
    private Capacitacion capacitacion;
    /**
     * Servicios
     */
    @EJB
    private CapacitacionServicio servCapacitacion;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;

    /**
     * Atributo para una lista de capacitacion por docente
     */
    private List<Capacitacion> listCapacitacionAutor;
    /**
     * Atributo para almacenar los tipo de cursos como una estructura clave
     * valor
     */
    private Map<String, String> tiposCursos;
    /**
     * Atributo para almacenar localidades cursos como una estructura clave
     * valor
     */
    private Map<String, String> localidadesCursos;
    /**
     * Atributo para el número de cédula del docente
     */
    private String numeroCedula;
    /**
     * Atributo para el nuevo número de cédula del docente
     */
    private StreamedContent file = null;
    /**
     * Atributo evento UploadedFile para subir el archivo
     */
    private UploadedFile event;
    private RequestContext req;
    /**
     * 
     */
    private String vacio;
//Inicialización de variabes

    @PostConstruct
    public void inicializar() {
        docente = new Docente();//Instanciamos el objeto Docente
        carreraDocente = new CarreraDocente();
        capacitacion = new Capacitacion();//Instaciamos el objeto Capacitacion
        tiposCursos = Tablas.retornartiposCursos();
        localidadesCursos = Tablas.retornarLocalidadCursos();
        numeroCedula = Constantes.VACIO;
        file = null;
        idDocente = 0;
        event = null;
        vacio = " ";
    }

    /**
     * Método que permite crear un registro capacitación para un docente
     */
    public void crear() {
        try {
//Encuentro el id del docente
            idDocente = carreraDocente.getIdDocente().getIdDocente();
            capacitacion.setIdDocente(servDocente.encontrarDocente(idDocente));
            servCapacitacion.insertarCapcitacion(capacitacion);
            MensajesFaces.informacion("Guardado", "Exitoso");
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCapacitacionAgregar').hide();");//cierro el dialogo 
            req = null;
            capacitacion = new Capacitacion();
            idDocente = 0;

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Metodo que me permite actualizar un registro de tipo Capacitación
     *
     * @throws java.lang.Exception
     */
    public void actualizar() throws Exception {

        try {
// Encuentro al docente por su id
            idDocente = carreraDocente.getIdDocente().getIdDocente();;
            capacitacion.setIdDocente(servDocente.encontrarDocente(idDocente));
            servCapacitacion.actualizarCapacitacion(capacitacion);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide();");
            Document document = (Document) buscarDocumento(capacitacion.getIdDocCapacitacion());
            if (document != null) {
                actualizarMetadatosCapacitacion(document);
            }
            req = null;
            capacitacion = new Capacitacion();
            event = null;
            idDocente = 0;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Método que permite elimnar un registro de tipo Capacitación
     *
     * @param cap
     */
    public void eliminar(Capacitacion cap) {

        try {
            //Elimino el registro si la capacitacion no ha subido algun archivo como evidencia
            if (cap.getIdDocCapacitacion() == null) {
                servCapacitacion.eliminarCacitacion(cap);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            } else {
                eliminarArchivoRepositorio(cap.getIdDocCapacitacion(), cap.getIdCapacitacion());
                servCapacitacion.eliminarCacitacion(cap);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
            capacitacion = new Capacitacion();
        } catch (Exception e) {
            capacitacion = new Capacitacion();
            MensajesFaces.error("Error", "detalle" + e);
        }
    }

    /**
     * Método que permite abrir el dialogo para crear un registro de tipo
     * Capacitación siempre que se haya seleccionado un docente
     */
    public void abrirDialogo() {

        if (carreraDocente.getIdDocente().getIdentificacion() == null) {
            MensajesFaces.informacion("Seleccione un Docente", "Primero");
        } else {
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoCapacitacionAgregar').show()");//Abro el dialogo
            req = null;
        }
    }

    /**
     * Método que permite encontrar un docente por su número de cédula
     *
     * @param idCarreraDocente
     */
    public void recuperardatosCarrreraDocenteId(int idCarreraDocente) {
        carreraDocente = servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente);
        if (carreraDocente != null) {
            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();
            MensajesFaces.informacion("Persona", "Encontrada");
            // docente=new Docente();
        } else {
            numeroCedula = Constantes.VACIO;
            carreraDocente = new CarreraDocente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }

    }

    /**
     * Método que retorna una lista de capacitaciones por Docente
     *
     * @return listCapacitacionAutor
     */
    public List<Capacitacion> listaCapacitacionDocente() {
        listCapacitacionAutor = servCapacitacion.listaActualizacionporDocente(numeroCedula);
        return listCapacitacionAutor;

    }

    /**
     * Método que permite encontrar la capacitacion de un docente por su id
     *
     * @param id
     */
    public void recuperarCapcitacionporID(int id) {

        capacitacion = servCapacitacion.buscarCapacitacionporID(id);
        if (capacitacion != null) {

            MensajesFaces.informacion("Capacitacion", "Encontrada");
        } else {
            docente = new Docente();
            MensajesFaces.informacion("Capacitacion", "No encontrada");
        }
    }

    /**
     * Metodo que me encuentra todos los docentes
     *
     * @return servDocente.listarDocente()
     */
    public List<Docente> recuperartodosDocentes() {

        return servDocente.listarDocente();
    }
// Método que para cerrar

    public void cerrar() {
        capacitacion = new Capacitacion();
        idDocente = 0;
        event = null;
    }

    /**
     * Método que permite encontrar un docente de una deteminada carrera por su
     * id
     *
     * @param idDocen
     * @param idCarrer
     */
    public void recuperarDocenteCarreraPorId(int idDocen, int idCarrer) {
        carreraDocente = servCarreraDocente.buscarDocenteCarreraporID(idDocen, idCarrer);
        if (carreraDocente != null) {
            idDocente = carreraDocente.getIdDocente().getIdDocente();
            idCarrera = carreraDocente.getIdCarrera().getIdCarrera();
            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();

            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            numeroCedula = Constantes.VACIO;
            carreraDocente = new CarreraDocente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }

    }

    /**
     * Método que permite obtener un archivo
     *
     * @param upload evento que me permite subir el archivo en disco
     */
    public void handleFileUpload(FileUploadEvent upload) {

        event = upload.getFile();//obtengo el archivo y le asigno a la variable event
        capacitacion.setNombreDocCapacitacion(event.getFileName());

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
            createDocument(subFolder1, event, getLocalFileType(), cargarMetadatosCapacitacion());
            capacitacion.setIdDocCapacitacion(getIdDocumento());
            servCapacitacion.actualizarCapacitacion(capacitacion);
            MensajesFaces.informacion("El archivo", "Se ha subido con exito");

            event = null;
            capacitacion = new Capacitacion();

        } catch (IOException ioe) {
            MensajesFaces.error("Error", "El archivo no ha sido subido!" + ioe.getMessage());
        } catch (CmisConstraintException cmcons) {
            MensajesFaces.error("Error", "Viloación de restrición!" + cmcons.getMessage());
        } catch (CmisContentAlreadyExistsException ccaee) {

            MensajesFaces.error("Error", "El documento: " + event.getFileName() + " ya existe, porfavor cambie de nombre al archivo");
        } catch (NullPointerException nPex) {
            MensajesFaces.error("Error", "Antes de cargar el archivo de clic en el boton Upload");
        }
    }

    public Map<String, Object> cargarMetadatosCapacitacion() throws NullPointerException {
        Map<String, Object> props = null;

        if (props == null) {
            props = new HashMap<String, Object>();
        }
        // Add the object type ID if it wasn't already
        if (props.get(PropertyIds.OBJECT_TYPE_ID) == null) {
            props.put(PropertyIds.OBJECT_TYPE_ID, "D:uce:TipoDocCapacitacion");
        }
        //Lista para anadir al Documento
        List<String> asp = new ArrayList<>();

        asp.add("P:uce:docente");
        asp.add("P:uce:fechas");
        asp.add("P:uce:IES");
        props.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, asp);

        // Add the name if it wasn't already
        if (props.get("cmis:name") == null) {
            props.put("cmis:name", event.getFileName());
        }

        if (props.get("uce:apellidos") == null) {
            if (capacitacion.getIdDocente().getApellidoMaterno() == null || capacitacion.getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", capacitacion.getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", capacitacion.getIdDocente().getApellidoPaterno() + " " + capacitacion.getIdDocente().getApellidoMaterno());

            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", capacitacion.getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", capacitacion.getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreIES") == null) {
            props.put("uce:nombreIES", capacitacion.getNomInstitucion());
        }
        if (props.get("uce:nombrePais") == null) {
            props.put("uce:nombrePais", capacitacion.getPaisActualizacion());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", capacitacion.getFechaInicioEvento());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", capacitacion.getFechaFinEvento());
        }

        if (props.get("uce:nombreEvento") == null) {
            props.put("uce:nombreEvento", capacitacion.getNomEvento());
        }
        if (props.get("uce:numHoras") == null) {
            props.put("uce:numHoras", capacitacion.getNumHoras());
        }
        if (props.get("uce:tipoCurso") == null) {
            props.put("uce:tipoCurso", capacitacion.getTipoCurso());
        }
        if (props.get("uce:localidad") == null) {
            props.put("uce:localidad", capacitacion.getLocalidad());
        }

        return props;

    }

    public void actualizarMetadatosCapacitacion(Document documento) {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        List<Object> aspects = documento.getProperty(PropertyIds.SECONDARY_OBJECT_TYPE_IDS).getValues();

        // Add the name if it wasn't already
        if (props.get("uce:apellidos") == null) {
            if (capacitacion.getIdDocente().getApellidoMaterno() == null || capacitacion.getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", capacitacion.getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", capacitacion.getIdDocente().getApellidoPaterno() + " " + capacitacion.getIdDocente().getApellidoMaterno());

            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", capacitacion.getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", capacitacion.getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreIES") == null) {
            props.put("uce:nombreIES", capacitacion.getNomInstitucion());
        }
        if (props.get("uce:nombrePais") == null) {
            props.put("uce:nombrePais", capacitacion.getPaisActualizacion());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", capacitacion.getFechaInicioEvento());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", capacitacion.getFechaFinEvento());
        }

        if (props.get("uce:nombreEvento") == null) {
            props.put("uce:nombreEvento", capacitacion.getNomEvento());
        }
        if (props.get("uce:numHoras") == null) {
            props.put("uce:numHoras", capacitacion.getNumHoras());
        }
        if (props.get("uce:tipoCurso") == null) {
            props.put("uce:tipoCurso", capacitacion.getTipoCurso());
        }
        if (props.get("uce:localidad") == null) {
            props.put("uce:localidad", capacitacion.getLocalidad());
        }
        Document documento_actualizado = (Document) documento.updateProperties(props);

    }

    /**
     * Método que crea una lista de subcarpetas donde se guardaran los archivo
     *
     * @return nombreSubCarpetaList
     */
    public ArrayList<String> listaSubCarpetas() {

        ArrayList<String> nombreSubCarpetaList = new ArrayList<String>();
        nombreSubCarpetaList.add("Docente");
        nombreSubCarpetaList.add("Capacitación");
        if (carreraDocente.getIdDocente().getApellidoMaterno() == null || carreraDocente.getIdDocente().getApellidoMaterno().equals(" ")) {
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
        } catch (CmisObjectNotFoundException cmex) {
            MensajesFaces.error("Error", "El Documento no se encuentra " + cmex.getMessage());
        }

    }

    /**
     * Método que me permite eliminar un archivo de Alfresco por el id del
     * documento y el id de la capacitación
     *
     * @param idDocumento
     * @param idCapacitacion
     */
    public void eliminarArchivoRepositorio(String idDocumento, int idCapacitacion) {
        //Obtengo una session
        Session cmisSession = getCmisSession();
        //Encuentro la capacitacion por su id
        capacitacion = servCapacitacion.buscarCapacitacionporID(idCapacitacion);
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
                capacitacion.setNombreDocCapacitacion(null);
                capacitacion.setIdDocCapacitacion(null);
                servCapacitacion.actualizarCapacitacion(capacitacion);
                capacitacion = new Capacitacion();
                MensajesFaces.informacion("Archivo", "Eliminado");

            }
        } catch (CmisObjectNotFoundException confe) {
            //Mesaje de error 
            MensajesFaces.error("Error", "No se pudo encontrar el documento que desea eliminar" + confe.getMessage());
        }

    }

//Get and Set
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

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
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
     * Retorna los tipos de cursos de una capacitación
     *
     * @return tiposCursos
     */
    public Map<String, String> getTiposCursos() {
        return tiposCursos;
    }

    /**
     * Modifica los tipos de cursos de una capacitación
     *
     * @param tiposCursos
     */
    public void setTiposCursos(Map<String, String> tiposCursos) {
        this.tiposCursos = tiposCursos;
    }

    /**
     * Retorna los tipo de localidades de una capacitación
     *
     * @return localidadesCursos
     */
    public Map<String, String> getLocalidadesCursos() {
        return localidadesCursos;
    }

    /**
     * Modifica los tipo de localidades de una capacitación
     *
     * @param localidadesCursos
     */
    public void setLocalidadesCursos(Map<String, String> localidadesCursos) {
        this.localidadesCursos = localidadesCursos;
    }

    /**
     * Retorna el id del docente
     *
     * @return idDocente
     */
    public int getIdDocente() {
        return idDocente;
    }

    /**
     * Modifica el id del docente
     *
     * @param idDocente
     */
    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    /**
     * Retorna un objeto de tipo Capacitacion
     *
     * @return capacitacion
     */
    public Capacitacion getCapacitacion() {
        return capacitacion;
    }

    /**
     * Modifica un objeto de tipo Capacitacion
     *
     * @param capacitacion
     */
    public void setCapacitacion(Capacitacion capacitacion) {
        this.capacitacion = capacitacion;
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
     * Devuelve el id de la Carrera
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica el id de la Carrera
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
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
