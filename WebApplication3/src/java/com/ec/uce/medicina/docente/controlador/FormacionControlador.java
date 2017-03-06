/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.alfresco.archivo.AlfrescoConexion;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.Formacion;
import com.ec.uce.medicina.docente.modelo.Ies;
import com.ec.uce.medicina.docente.modelo.PaisOrigen;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.FormacionServicio;
import com.uce.uce.medicina.docente.servicio.IesServicio;
import com.uce.uce.medicina.docente.servicio.PaisOrigenServicio;
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
 * Declaración del ManagedBean FormacionControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean

public class FormacionControlador extends AlfrescoConexion {
    //Atributo id Carrera

    private int idCarrera;
    //Declaración objeto tipo CarreraDocente
    private CarreraDocente carreraDocente;
    //Atributo id docente
    private int idDocente;
    //Declaración objeto tipo Docente
    private Docente docente;
    private Ies ies;
    //Atributo id IES
    private int idIes;
    //Atributo estado del combo box
    private String estadoComboBox;
    //Declaración objeto tipo Formacion
    private Formacion formacion;
    //Servicios
    @EJB
    private IesServicio servIes;
    @EJB
    private FormacionServicio servFormacion;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private PaisOrigenServicio servPais;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;
    private List<PaisOrigen> listPais;
    /**
     * Atributo para almacenar el tipo nivel como una estructura clave valor
     */
    private Map<String, String> nivel;
    /**
     * Atributo para almacenar el tipo grado como una estructura clave valor
     */
    private Map<String, String> grado;
    /**
     * Atributo para almacenar el estado del curso como una estructura clave
     * valor
     */
    private Map<String, String> estadocurso;
    //Número de cedula del docente
    private String numeroCedula;
    private StreamedContent file;
    private UploadedFile event;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
    private String vacio;
//Inicialización de variables

    @PostConstruct
    public void inicializar() {
        //Instancia de objetos
        docente = new Docente();
        carreraDocente=new CarreraDocente();
        formacion = new Formacion();
        //carga de los items de la IES
        // itemsIes = servIes.oneMenuIes(servIes.listarIes());
        nivel = Tablas.retornarniveles();
        grado = Tablas.retornarGrado();
        estadocurso = Tablas.retornarEstado();
        numeroCedula = Constantes.VACIO;
        listPais = recuperarListaPais();
        this.estadoComboBox = Constantes.INACTIVO;
        ies = new Ies();
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

            idDocente = carreraDocente.getIdDocente().getIdDocente();
            formacion.setIdDocente(servDocente.encontrarDocente(idDocente));
            //idIes = ies.getIdIes();
            formacion.setIdIes(servIes.buscarIesID(idIes));
            servFormacion.insertarFormacion(formacion);

            MensajesFaces.informacion("Guardado", "Exitoso");
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoFormacionAgregar').hide()");//Cierra el dialogo
            req = null;
            idDocente = 0;
            formacion = new Formacion();
            idIes = 0;
            ies = new Ies();

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos, si todo
     * sale bien cierra el dialogo para actualizar el registro ,caso contrario
     * no se cierra el dialogo
     */
    public void actualizar() {

        try {

            idDocente = carreraDocente.getIdDocente().getIdDocente();
            formacion.setIdDocente(servDocente.encontrarDocente(idDocente));
            idIes = ies.getIdIes();
            formacion.setIdIes(servIes.buscarIesID(idIes));

            servFormacion.actualizarFormacion(formacion);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide()");
            Document document = (Document) buscarDocumento(formacion.getIdDocFormacion());
            if (document != null) {
                actualizarMetadatosFormacion(document);
            }
            req = null;
            formacion = new Formacion();
            idDocente = 0;
            ies = new Ies();
            idIes = 0;

            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }
    }

    /**
     * Elimina un registro de la base de datos
     *
     * @param forma
     */
    public void eliminar(Formacion forma) {

        try {
            if (forma.getIdDocFormacion() == null) {
                servFormacion.eliminarFormacion(forma);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            } else {
                eliminarArchivoRepositorio(forma.getIdDocFormacion(), forma.getIdFormacion());
                servFormacion.eliminarFormacion(forma);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
            formacion = new Formacion();

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite encontrar un docente por su número de cédula
     *
     * @param idCarreraDocente
     */
    public void recuperarDatosCarreraDocenteId(int idCarreraDocente) {
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
     * Método que devuelve todos los registros de fromación de un docente
     *
     * @return servFormacion.listaFormacionporDocente(numeroCedula)
     */
    public List<Formacion> listarecuperarFormacionDocente() {

        return servFormacion.listaFormacionporDocente(numeroCedula);

    }

    /**
     * Método que duevlve una lista con los paises
     *
     * @return servPais.listarPaisOrigen()
     */
    public List<PaisOrigen> recuperarListaPais() {

        return servPais.listarPaisOrigen();

    }

    /**
     * Método que encuentra una formación por su id
     *
     * @param idFormacion
     */
    public void recuperarFormacionporID(int idFormacion) {

        formacion = servFormacion.buscarFormacionporID(idFormacion);
        if (formacion != null) {
            idIes = formacion.getIdIes().getIdIes();
            ies = servIes.buscarIesID(idIes);
            MensajesFaces.informacion("Formacion", "Encontrada");
        } else {
            formacion = new Formacion();
            MensajesFaces.informacion("Formacion", "No encontrada");
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

    public void getCodIes(AjaxBehaviorEvent ent) {
        this.estadoComboBox = Constantes.ACTIVO;
        ies = servIes.buscarIesID(idIes);

    }

    /**
     * Método que permite abrir el dialogo para crear un registro de tipo
     * Formación siempre que se haya seleccionado un docente
     */
    public void abrirDialogo() {
        if (carreraDocente.getIdDocente().getIdentificacion() == null) {
            MensajesFaces.informacion("Seleccione un Docente", "Primero");
        } else {
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoFormacionAgregar').show()");
            req = null;
        }
    }
//Método que permite cerrar

    public void cerrar() {
        formacion = new Formacion();
        event = null;
        idDocente = 0;
        ies = new Ies();
        idIes = 0;
    }

    //archivos guardado en el servidor
    /**
     * Método que permite obtener un archivo
     *
     * @param upload evento que me permite subir el archivo en disco
     */
    public void handleFileUpload(FileUploadEvent upload) {
        event = upload.getFile();//obtengo el archivo y le asigno a la variable event
        //Guardo el nombre del archivo que se va a subir
        formacion.setNombreDocFormacion(event.getFileName());

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
            createDocument(subFolder1, event, getLocalFileType(), cargarMetadatosFormacion());
            formacion.setIdDocFormacion(getIdDocumento());
            servFormacion.actualizarFormacion(formacion);
            MensajesFaces.informacion("El archivo", "Se ha subido con exito");

            event = null;
            formacion = new Formacion();

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

    public Map<String, Object> cargarMetadatosFormacion() throws NullPointerException{
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        // Add the object type ID if it wasn't already
        if (props.get(PropertyIds.OBJECT_TYPE_ID) == null) {
            props.put(PropertyIds.OBJECT_TYPE_ID, "D:uce:TipoDocFormacion");
        }
        //Lista para anadir al Documento
        List<String> asp = new ArrayList<>();
        asp.add("P:uce:docente");
        asp.add("P:uce:IES");
        props.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, asp);

        // Add the name if it wasn't already
        // Add the name if it wasn't already
        if (props.get("cmis:name") == null) {
            props.put("cmis:name", event.getFileName());
        }

        if (props.get("uce:apellidos") == null) {
            if (formacion.getIdDocente().getApellidoMaterno() == null || formacion.getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", formacion.getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", formacion.getIdDocente().getApellidoPaterno() + " " + formacion.getIdDocente().getApellidoMaterno());
                
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", formacion.getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", formacion.getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreIES") == null) {
            props.put("uce:nombreIES", formacion.getIdIes().getNomIes());
        }
        if (props.get("uce:nombrePais") == null) {
            props.put("uce:nombrePais", formacion.getPaisEstudio());
        }
        if (props.get("uce:nombreTitulo") == null) {
            props.put("uce:nombreTitulo", formacion.getNomTitulo());
        }
        if (props.get("uce:registroSenescyt") == null) {
            props.put("uce:registroSenescyt", formacion.getNumRegSenescyt());
        }

        if (props.get("uce:fechaTitulo") == null) {
            props.put("uce:fechaTitulo", formacion.getFechaTitulo());
        }
        if (props.get("uce:tipoGrado") == null) {
            props.put("uce:tipoGrado", formacion.getGrado());
        }
        if (props.get("uce:nivel") == null) {
            props.put("uce:nivel", formacion.getNivel());
        }
        return props;

    }

    public void actualizarMetadatosFormacion(Document documento) {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        List<Object> aspects = documento.getProperty(PropertyIds.SECONDARY_OBJECT_TYPE_IDS).getValues();

        // Add the name if it wasn't already
        if (props.get("uce:apellidos") == null) {
            if (formacion.getIdDocente().getApellidoMaterno() == null || formacion.getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", formacion.getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", formacion.getIdDocente().getApellidoPaterno() + " " + formacion.getIdDocente().getApellidoMaterno());
                
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", formacion.getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", formacion.getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreIES") == null) {
            props.put("uce:nombreIES", formacion.getIdIes().getNomIes());
        }
        if (props.get("uce:nombrePais") == null) {
            props.put("uce:nombrePais", formacion.getPaisEstudio());
        }
        if (props.get("uce:nombreTitulo") == null) {
            props.put("uce:nombreTitulo", formacion.getNomTitulo());
        }
        if (props.get("uce:registroSenescyt") == null) {
            props.put("uce:registroSenescyt", formacion.getNumRegSenescyt());
        }

        if (props.get("uce:fechaTitulo") == null) {
            props.put("uce:fechaTitulo", formacion.getFechaTitulo());
        }
        if (props.get("uce:tipoGrado") == null) {
            props.put("uce:tipoGrado", formacion.getGrado());
        }
        if (props.get("uce:nivel") == null) {
            props.put("uce:nivel", formacion.getNivel());
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
        nombreSubCarpetaList.add("Formación");
        if (formacion.getNivel().equals("TERCER NIVEL")) {
            nombreSubCarpetaList.add(formacion.getNivel());
        } else {
            nombreSubCarpetaList.add("CUARTO NIVEL");

        }

        docente = servDocente.encontrarDocente(formacion.getIdDocente().getIdDocente());
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
     * documento y el id de la formación
     *
     * @param idDocumento
     * @param idFormacion
     */
    public void eliminarArchivoRepositorio(String idDocumento, int idFormacion) {
        //Obtengo una session
        Session cmisSession = getCmisSession();
        //Encuentro la formacion por su id
        formacion = servFormacion.buscarFormacionporID(idFormacion);
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
                formacion.setNombreDocFormacion(null);
                formacion.setIdDocFormacion(null);
                servFormacion.actualizarFormacion(formacion);
                formacion = new Formacion();
                MensajesFaces.informacion("Archivo", "Eliminado");

            }
        } catch (CmisObjectNotFoundException confe) {
            //Mesaje de error 
            MensajesFaces.error("Error", "No se pudo encontrar el documento que desea eliminar" + confe.getMessage());
        }

    }

    //Get an SET
    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

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
     * Devuelve una lista de paises
     *
     * @return listPais;
     *
     */
    public List<PaisOrigen> getListPais() {
        return listPais;
    }

    /**
     * Modifica una lista de paises
     *
     * @param listPais;
     *
     */
    public void setListPais(List<PaisOrigen> listPais) {
        this.listPais = listPais;
    }

    /**
     * Devuelve un objeto de tipo Ies
     *
     * @return ies;
     *
     */
    public Ies getIes() {
        return ies;
    }

    /**
     * Modifica un objeto de tipo Ies
     *
     * @param ies;
     *
     */
    public void setIes(Ies ies) {
        this.ies = ies;
    }

    /**
     * Devuelve el id de la IES
     *
     * @return idIes
     */
    public int getIdIes() {
        return idIes;
    }

    /**
     * Modifica el id de la IES
     *
     * @param idIes
     */
    public void setIdIes(int idIes) {
        this.idIes = idIes;
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
     * Devuelve el nivel de un titulo del docente
     *
     * @return nivel
     */
    public Map<String, String> getNivel() {
        return nivel;
    }

    /**
     * Modifica el nivel de un título del docente
     *
     * @param nivel
     */
    public void setNivel(Map<String, String> nivel) {
        this.nivel = nivel;
    }

    /**
     * Devuelve el grado de un título del docente
     *
     * @return grado
     */
    public Map<String, String> getGrado() {
        return grado;
    }

    /**
     * Modifica el grado de un título del docente
     *
     * @param grado
     */
    public void setGrado(Map<String, String> grado) {
        this.grado = grado;
    }

    /**
     * Devuelve el estado de un título del docente
     *
     * @return estadocurso
     */
    public Map<String, String> getEstadocurso() {
        return estadocurso;
    }

    /**
     * Modifica el estado de un título del docente
     *
     * @param estadocurso
     */
    public void setEstadocurso(Map<String, String> estadocurso) {
        this.estadocurso = estadocurso;
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
     * Devuelve un objeto de tipo Formacion
     *
     * @return formacion
     *
     */
    public Formacion getFormacion() {
        return formacion;
    }

    /**
     * Modifica un objeto de tipo Formacion
     *
     * @param formacion
     *
     */
    public void setFormacion(Formacion formacion) {
        this.formacion = formacion;
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
