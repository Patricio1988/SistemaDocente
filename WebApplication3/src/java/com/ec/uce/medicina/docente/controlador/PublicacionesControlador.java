/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.alfresco.archivo.AlfrescoConexion;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.Publicaciones;
import com.ec.uce.medicina.docente.modelo.TipoPublicacion;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.PublicacionesServicio;
import com.uce.uce.medicina.docente.servicio.TipoPublicacionServicio;
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
import javax.faces.event.AjaxBehaviorEvent;
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
 * Declaración del ManagedBean PublicacionesControlador que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class PublicacionesControlador extends AlfrescoConexion {
    //Atributo id Carrera

    private int idCarrera;
    //Declaración objeto tipo CarreraDocente
    private CarreraDocente carreraDocente;
    /**
     * Atributo id del docente
     */
    private int idDocente;
    //Declaración objeto tipo Docente
    private Docente docente;
    //Atributo id tipo de publicación
    private int idtipopublicacion;
    //Declaración objeto tipo TipoPublicacion
    private TipoPublicacion tpPublicacion;
    //Atributo estado del combo box
    private String estadoComboBox;
    //Declaración objeto tipo Publicaciones
    private Publicaciones publicaciones;
    //Servicios
    @EJB
    private PublicacionesServicio servPublicaciones;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private TipoPublicacionServicio servTipoPublicacion;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;
    //Atributo lista de publicaciones
    private List<Publicaciones> listPublicaciones;
    //Atributo lista de doecntes
    private List<Docente> listDocente;
    //Atributo lista de publicaciones
    private List<Publicaciones> listPublicacionesAutor;
    /**
     * Atributo para almacenar los tipo departicipación como una estructura
     * clave valor
     */
    private Map<String, String> tiposParticipacion;
    /**
     * Atributo para almacenar el estado de publicacción como una estructura
     * clave valor
     */
    private Map<String, String> tiposEstadoPublicacion;
    /**
     * Atributo para almacenar los tipo de base de datos como una estructura
     * clave valor
     */
    private Map<String, String> tiposBaseDatosRevista;
    //Atributo estado que habilitalas opciones de la revista
    private String estadoOpcionesRevista;
    //Atributo número de cedula del docente
    private String numeroCedula;
    private StreamedContent file = null;
    //evento UploadedFile para subir un archivo
    private UploadedFile event;
    //Atributo RequestContext que encapsula inpublicaciones de una peticion HTTP
    private RequestContext req;
    private String vacio;

//Inicialización de variables
    @PostConstruct
    public void inicializar() {
        //Instancia de objetos
        docente = new Docente();
        carreraDocente=new CarreraDocente();
        publicaciones = new Publicaciones();
        tpPublicacion = new TipoPublicacion();
        tiposParticipacion = Tablas.retornarTiposParticipacion();
        tiposEstadoPublicacion = Tablas.retornarTiposEstadoPubli();
        tiposBaseDatosRevista = Tablas.retornarBaseDatos();
        numeroCedula = Constantes.VACIO;
        estadoOpcionesRevista = Constantes.INACTIVO;
        vacio = " ";
    }

    /**
     * Método que permite crear un registro en la base de datos, si todo sale
     * bien cierra el dialogo para crear caso contrario no
     */
    public void crear() {
        try {

            idDocente =carreraDocente.getIdDocente().getIdDocente();

            publicaciones.setIdDocente(servDocente.encontrarDocente(idDocente));

            publicaciones.setIdTipoPublicacion(servTipoPublicacion.encontrarTipoPublicacionID(idtipopublicacion));
            servPublicaciones.insertarPublicaciones(publicaciones);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoPublicacionesAgregar').hide();");//Cierra el dialogo
            req = null;
            idDocente = 0;
            idtipopublicacion = 0;
            publicaciones = new Publicaciones();
            MensajesFaces.informacion("Guardado", "Exitoso");

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

            idDocente = carreraDocente.getIdDocente().getIdDocente();
            publicaciones.setIdDocente(servDocente.encontrarDocente(idDocente));
            publicaciones.setIdTipoPublicacion(servTipoPublicacion.encontrarTipoPublicacionID(idtipopublicacion));

            servPublicaciones.actualizarPublicaciones(publicaciones);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide();");
            Document document = (Document) buscarDocumento(publicaciones.getIdDocPublicacion());
            if (document != null) {
                actualizarMetadatosPublicacion(document);
            }
            req = null;
            publicaciones = new Publicaciones();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }
    }

    /**
     * Método que permite encontrar un docente por su número de cédula
     *
     * @param idCarreraDocente
     */
    public void recuperarDatosCarreraDocenteId(int  idCarreraDocente) {
        carreraDocente = servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente);
        if (carreraDocente != null) {
            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();
            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            numeroCedula = Constantes.VACIO;
            carreraDocente = new CarreraDocente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }

    }

    /**
     * Método que devuelve todas las publicaciones de un docente
     *
     * @return listPublicacionesAutor
     */
    public List<Publicaciones> listaPublicacionesDocente() {
        listPublicacionesAutor = servPublicaciones.listaPublicacionesporDocente(numeroCedula);
        return listPublicacionesAutor;

    }

    /**
     * Método que encuentra una publicación por su id
     *
     * @param id
     */
    public void recuperarPublicacionID(Object id) {

        publicaciones = servPublicaciones.buscarPublicacionesid(id);
        if (publicaciones != null) {
            idtipopublicacion = publicaciones.getIdTipoPublicacion().getIdTipoPublicacion();

            MensajesFaces.informacion("Publicaciones", "Encontrada");
        } else {
            docente = new Docente();
            MensajesFaces.informacion("Publicaciones", "No encontrada");
        }

    }

    /**
     * Método que permite elimnar un registro de tipo Capacitación
     *
     * @param publi
     */
    public void eliminar(Publicaciones publi) {

        try {
            //Elimino el registro de la base de datos de la publicación si no ha subido algún archivo como evidencia
            if (publi.getIdDocPublicacion() == null) {
                servPublicaciones.eliminarPublicaciones(publi);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            } else {
                eliminarArchivoRepositorio(publi.getIdDocPublicacion(), publi.getIdPublicacion());
                servPublicaciones.eliminarPublicaciones(publi);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
            publicaciones = new Publicaciones();

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que duvuelve una lista de todos los docentes
     *
     * @return listDocente
     */
    public List<Docente> recuperartodosDocentes() {
        listDocente = servDocente.listarDocente();
        return listDocente;
    }

    public void habilitarRevista(AjaxBehaviorEvent ent) {
        tpPublicacion = servTipoPublicacion.encontrarTipoPublicacionID(idtipopublicacion);
        if (tpPublicacion.getNomTipoPublicacion().equalsIgnoreCase("ARTICULO")) {
            estadoOpcionesRevista = Constantes.ACTIVO;
        } else {

            estadoOpcionesRevista = Constantes.INACTIVO;
        }
    }

    /**
     * Método que permite abrir el dialogo para crear un registro de tipo
     * Publicación siempre que se haya seleccionado un docente
     */
    public void abrirDialogo() {
        if (carreraDocente.getIdDocente().getIdentificacion() == null) {
            MensajesFaces.informacion("Seleccione un Docente", "Primero");
        } else {
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoPublicacionesAgregar').show()");
            req = null;
        }

    }

    //Método que permite cerrar
    public void cerrar() {
        idDocente = 0;
        idtipopublicacion = 0;
        event = null;
        publicaciones = new Publicaciones();
    }

    //archivos guardado en el servidor
    /**
     * Método que permite obtener un archivo
     *
     * @param upload evento que me permite subir el archivo en disco
     */
    public void handleFileUpload(FileUploadEvent upload) {
        event = upload.getFile();//obtengo el archivo y le asigno a la variable event
        publicaciones.setNombreDocPublicacion(event.getFileName());

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
            createDocument(subFolder1, event, getLocalFileType(), cargarMetadatosPublicacion());
            publicaciones.setIdDocPublicacion(getIdDocumento());
            servPublicaciones.actualizarPublicaciones(publicaciones);
            MensajesFaces.informacion("El archivo", "Se ha subido con exito");

            event = null;
            publicaciones = new Publicaciones();

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

    public Map<String, Object> cargarMetadatosPublicacion()throws NullPointerException {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        // Add the object type ID if it wasn't already
        if (props.get(PropertyIds.OBJECT_TYPE_ID) == null) {
            props.put(PropertyIds.OBJECT_TYPE_ID, "D:uce:TipoDocPublicacion");
        }
        //Lista para anadir al Documento
        List<String> asp = new ArrayList<>();
        asp.add("P:uce:docente");
        props.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, asp);

        // Add the name if it wasn't already
        // Add the name if it wasn't already
        if (props.get("cmis:name") == null) {
            props.put("cmis:name", event.getFileName());
        }

        if (props.get("uce:apellidos") == null) {
            if (publicaciones.getIdDocente().getApellidoMaterno() == null || publicaciones.getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", publicaciones.getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", publicaciones.getIdDocente().getApellidoPaterno() + " " + publicaciones.getIdDocente().getApellidoMaterno());
                
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", publicaciones.getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", publicaciones.getIdDocente().getIdentificacion());
        }
        if (props.get("uce:tipoPublicacion") == null) {
            props.put("uce:tipoPublicacion", publicaciones.getIdTipoPublicacion().getNomTipoPublicacion());
        }
        if (props.get("uce:codigoPublicacion") == null) {
            props.put("uce:codigoPublicacion", publicaciones.getCodPublicacion());
        }
        if (props.get("uce:nombrePublicacion") == null) {
            props.put("uce:nombrePublicacion", publicaciones.getNomPublicacion());
        }
        if (props.get("uce:ISBN_ISSN") == null) {
            props.put("uce:ISBN_ISSN", publicaciones.getNumIsbnIssn());
        }

        if (props.get("uce:fechaPublicacion") == null) {
            props.put("uce:fechaPublicacion", publicaciones.getFechaPublicacion());
        }
        if (props.get("uce:participacion") == null) {
            props.put("uce:participacion", publicaciones.getParticipacion());
        }
        return props;

    }

    public void actualizarMetadatosPublicacion(Document documento) {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        List<Object> aspects = documento.getProperty(PropertyIds.SECONDARY_OBJECT_TYPE_IDS).getValues();

        // Add the name if it wasn't already
         if (props.get("uce:apellidos") == null) {
            if (publicaciones.getIdDocente().getApellidoMaterno() == null || publicaciones.getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", publicaciones.getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", publicaciones.getIdDocente().getApellidoPaterno() + " " + publicaciones.getIdDocente().getApellidoMaterno());
                
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", publicaciones.getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", publicaciones.getIdDocente().getIdentificacion());
        }
        if (props.get("uce:tipoPublicacion") == null) {
            props.put("uce:tipoPublicacion", publicaciones.getIdTipoPublicacion().getNomTipoPublicacion());
        }
        if (props.get("uce:codigoPublicacion") == null) {
            props.put("uce:codigoPublicacion", publicaciones.getCodPublicacion());
        }
        if (props.get("uce:nombrePublicacion") == null) {
            props.put("uce:nombrePublicacion", publicaciones.getNomPublicacion());
        }
        if (props.get("uce:ISBN_ISSN") == null) {
            props.put("uce:ISBN_ISSN", publicaciones.getNumIsbnIssn());
        }

        if (props.get("uce:fechaPublicacion") == null) {
            props.put("uce:fechaPublicacion", publicaciones.getFechaPublicacion());
        }
        if (props.get("uce:participacion") == null) {
            props.put("uce:participacion", publicaciones.getParticipacion());
        }
        Document documento_actualizado = (Document) documento.updateProperties(props);

    }

    /**
     * Metodo que devuelve una lista con las subcarpetas que se crean para
     * guardar el archiv
     *
     * @return nombreSubCarpetaList
     */
    public ArrayList<String> listaSubCarpetas() {

        ArrayList<String> nombreSubCarpetaList = new ArrayList<String>();
        nombreSubCarpetaList.add("Docente");
        nombreSubCarpetaList.add("Publicación");
        docente = servDocente.encontrarDocente(publicaciones.getIdDocente().getIdDocente());
        if (docente.getApellidoMaterno() == null || docente.getApellidoMaterno().equals(" ")) {
            nombreSubCarpetaList.add(docente.getIdentificacion() + "_" + docente.getApellidoPaterno() + "_" + docente.getNombres());
        } else {
            nombreSubCarpetaList.add(docente.getIdentificacion() + "_" + docente.getApellidoPaterno() + "_" + docente.getApellidoMaterno() + "_" + docente.getNombres());
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
     * documento y el id de la capacitación
     *
     * @param idDocumento
     * @param idPublicacion
     */
    public void eliminarArchivoRepositorio(String idDocumento, int idPublicacion) {
        //Obtengo una session
        Session cmisSession = getCmisSession();
        //Encuentro la publicaciones por su id
        publicaciones = servPublicaciones.buscarPublicacionesid(idPublicacion);
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
                publicaciones.setNombreDocPublicacion(null);
                publicaciones.setIdDocPublicacion(null);
                servPublicaciones.actualizarPublicaciones(publicaciones);
                publicaciones = new Publicaciones();
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

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    /**
     * Devuelve el estado para habilitar las opciones de revista
     *
     * @return estadoOpcionesRevista
     */
    public String getEstadoOpcionesRevista() {
        return estadoOpcionesRevista;
    }

    /**
     * Modificar el estado para habilitar las opciones de revista
     *
     * @param estadoOpcionesRevista
     */
    public void setEstadoOpcionesRevista(String estadoOpcionesRevista) {
        this.estadoOpcionesRevista = estadoOpcionesRevista;
    }

    /**
     * Devuelve el id del tipo de publicación
     *
     * @return idtipopublicacion
     */
    public int getIdtipopublicacion() {
        return idtipopublicacion;
    }

    /**
     * Modifica el id del tipo de publicación
     *
     * @param idtipopublicacion
     */
    public void setIdtipopublicacion(int idtipopublicacion) {
        this.idtipopublicacion = idtipopublicacion;
    }

    /**
     * Devuelve un objeto de tipo TipoPublicacion
     *
     * @return tpPublicacion
     */
    public TipoPublicacion getTpPublicacion() {
        return tpPublicacion;
    }

    /**
     * Modifica un objeto de tipo TipoPublicacion
     *
     * @param tpPublicacion
     */
    public void setTpPublicacion(TipoPublicacion tpPublicacion) {
        this.tpPublicacion = tpPublicacion;
    }

    /**
     * Devuelve una lista de publicaciones
     *
     * @return listPublicacionesAutor
     */
    public List<Publicaciones> getListPublicacionesAutor() {
        return listPublicacionesAutor;
    }

    /**
     * Modifica una lista de publicaciones
     *
     * @param listPublicacionesAutor
     */
    public void setListPublicacionesAutor(List<Publicaciones> listPublicacionesAutor) {
        this.listPublicacionesAutor = listPublicacionesAutor;
    }

    /**
     * Devuelve los tipos de participación de una publicación
     *
     * @return tiposParticipacion
     */
    public Map<String, String> getTiposParticipacion() {
        return tiposParticipacion;
    }

    /**
     * Modifica los tipos de participación de una publicación
     *
     * @param tiposParticipacion
     */
    public void setTiposParticipacion(Map<String, String> tiposParticipacion) {
        this.tiposParticipacion = tiposParticipacion;
    }

    /**
     * Devuelve el estado de una publicación
     *
     * @return tiposEstadoPublicacion
     */
    public Map<String, String> getTiposEstadoPublicacion() {
        return tiposEstadoPublicacion;
    }

    /**
     * Modifica el estado de una publicación
     *
     * @param tiposEstadoPublicacion
     */
    public void setTiposEstadoPublicacion(Map<String, String> tiposEstadoPublicacion) {
        this.tiposEstadoPublicacion = tiposEstadoPublicacion;
    }

    /**
     * Devuelve los tipos de base de datos en las cuales fue indexada uan
     * revista
     *
     * @return tiposBaseDatosRevista
     */
    public Map<String, String> getTiposBaseDatosRevista() {
        return tiposBaseDatosRevista;
    }

    /**
     * Modifica los tipos de base de datos en las cuales fue indexada uan
     * revista
     *
     * @param tiposBaseDatosRevista
     */
    public void setTiposBaseDatosRevista(Map<String, String> tiposBaseDatosRevista) {
        this.tiposBaseDatosRevista = tiposBaseDatosRevista;
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

    public List<Docente> getListDocente() {
        return listDocente;
    }

    public void setListDocente(List<Docente> listDocente) {
        this.listDocente = listDocente;
    }

    /**
     * Devuelve el id del docente
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
     * Devuelve un objeto de tipo Publicaciones
     *
     * @return publicaciones
     */
    public Publicaciones getPublicaciones() {
        return publicaciones;
    }

    /**
     * Modifica un objeto de tipo Publicaciones
     *
     * @param publicaciones
     */
    public void setPublicaciones(Publicaciones publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Publicaciones> getListPublicaciones() {
        return listPublicaciones;
    }

    public void setListPublicaciones(List<Publicaciones> listPublicaciones) {
        this.listPublicaciones = listPublicaciones;
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
