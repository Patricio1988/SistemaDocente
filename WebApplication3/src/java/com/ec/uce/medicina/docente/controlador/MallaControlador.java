/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.alfresco.archivo.AlfrescoConexion;
import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.CarreraServicio;
import com.uce.uce.medicina.docente.servicio.FacultadServicio;
import com.uce.uce.medicina.docente.servicio.MallaServicio;
import com.uce.uce.medicina.docente.servicio.MateriaServicio;
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
import javax.faces.model.SelectItem;
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
 * Declaración del ManagedBean MallaControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class MallaControlador extends AlfrescoConexion {

    private List<SelectItem> itemsFacultad;
    private List<SelectItem> itemsCarrera;
    /**
     * Atributo id facultad
     */
    private int idFacultad;
    /**
     * Atributo id carrera
     */
    private int idCarrera;
    /**
     * Atributo estado del combo box
     */
    private String estadoComboBox;

    private Malla malla;
    //Servicios
    @EJB
    private MallaServicio servMalla;

    @EJB
    private CarreraServicio servaCarrera;
    @EJB
    private FacultadServicio servFacultad;
    @EJB
    private MateriaServicio servMateria;
    /**
     * Atributo para almacenar el tipo de organización como una estructura clave
     * valor
     */
    private Map<String, String> organizacion;
    private StreamedContent file;
    /**
     * Evento UploadedFile para subir el archivo
     */
    private UploadedFile event;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
    private String vacio;
//Constructor por defecto

    public MallaControlador() {

    }
//Inicialización de variables

    @PostConstruct
    public void inicializar() {

        malla = new Malla();
        itemsFacultad = servFacultad.oneMenuFacultad(servFacultad.listarFacultad());
        itemsCarrera = servaCarrera.oneMenuCarrera(servaCarrera.listarCarrera());
        organizacion = Tablas.retornarOrganizacion();
        this.estadoComboBox = Constantes.INACTIVO;
        idFacultad = 0;
        idCarrera = 0;
        file = null;
        event = null;
        vacio = " ";

    }

    public void getCarreras(AjaxBehaviorEvent ent) {
        this.estadoComboBox = Constantes.ACTIVO;
        this.itemsCarrera = servaCarrera.oneMenuCarrera(this.servaCarrera.buscarCarreraPorFacultad(idFacultad));

    }

    /**
     * Método que permite crear un registro en la base de datos, si todo sale
     * bien cierra el dialogo para crear el registro, caso contrario no se
     * cierra el dialogo, si el usuario no tiene rol ROLE_SUPERADMINISTRADOR se
     * asigna al id de la carrera el id de la carrera que tiene asignado a lo
     * que fue creado el usuario
     *
     * @param au
     */
    public void crear(Authorities au) {
        try {

            if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR") == false) {
                idCarrera = au.getUsername().getIdCarrera().getIdCarrera();

            }
            malla.setIdCarrera(servaCarrera.encontrarCarrera(idCarrera));

            servMalla.insertarMalla(malla);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoMallaAgregar').hide()");
            req = null;
            idFacultad = 0;
            idCarrera = 0;
            malla = new Malla();

            MensajesFaces.informacion("Guardado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos, si todo
     * sale bien cierra el dialogo para actualizar el registro ,caso contrario
     * no se cierra el dialogo
     *
     * @param au
     */
    public void actualizar(Authorities au) {

        try {

            if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR") == false) {
                idCarrera = au.getUsername().getIdCarrera().getIdCarrera();
            }
            malla.setIdCarrera(servaCarrera.encontrarCarrera(idCarrera));
            servMalla.actualizarMalla(malla);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoMallaActualizar').hide()");
            Document document=(Document) buscarDocumento(malla.getIdDocMalla()); 
            if(document!=null)
            {
              actualizarMetadatosMalla(document);
            }
            req = null;
            MensajesFaces.informacion("Actualizado", "Exitoso");
            idCarrera = 0;
            idFacultad = 0;
            malla = new Malla();

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

        
    }

    /**
     * Elimina un registro de la base de datos
     *
     * @param mall
     */
    public void eliminar(Malla mall) {

        try {
            boolean verificarMateriaEnMalla = servMateria.buscarRegistroPorMalla(mall);
            if (verificarMateriaEnMalla) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else if (mall.getIdDocMalla() == null) {
                servMalla.eliminarMalla(mall);

                MensajesFaces.informacion("Eliminado", "Exitoso");
            } else {
                servMalla.eliminarMalla(mall);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }

    //Método para cerrar
    public void cerrar() {
        idFacultad = 0;
        idCarrera = 0;
        malla = new Malla();
        event = null;
    }

    /**
     * Método que permite buscar una malla por su id
     *
     * @param id
     */
    public void buscarMallaID(Object id) {
        malla = servMalla.encontrarMallaID(id);

        if (malla != null) {
            idFacultad = malla.getIdCarrera().getIdFacultad().getIdFacultad();
            idCarrera = malla.getIdCarrera().getIdCarrera();

            MensajesFaces.informacion("Malla", "Encontrada");
        } else {

            MensajesFaces.informacion("Malla", "No encontrada");
            malla = new Malla();
        }

    }
    //LISTAS

    /**
     * Metodo que busca las mallas vigentes
     *
     * @return servMalla.ListaMallaVigentes()
     */
    public List<Malla> ListarMallasVigentes() {
        return servMalla.ListaMallaVigentes();
    }

    public List<Malla> listaMallasCarrera(Authorities au) {
        if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR")) {
            return servMalla.listarMalla();
        } else {
            return servMalla.ListaMallaPorCarreraVigentes(au.getUsername().getIdCarrera().getIdCarrera());
        }

    }

    /**
     * Metodo que busca todas las mallas
     *
     * @return servMalla.listarMalla()
     */
    public List<Malla> recuperarTodos() {
        return servMalla.listarMalla();
    }

    //Método que recibe un evento para subir un archivo
    public void handleFileUpload(FileUploadEvent upload) {

        event = upload.getFile();//obtengo el archivo y le asigno a la variable event
        malla.setNombreDocMalla(event.getFileName());

    }

    /**
     * Método que permite subir un archivo al repositorio de alfresco utilizando
     * CMIS API
     */
    public void subirArchivoRepositorio() {
        String nombreFolder = malla.getIdCarrera().getIdFacultad().getNomFacultad();
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
            createDocument(subFolder1, event, getLocalFileType(),cargarMetadatosMalla());
            malla.setIdDocMalla(getIdDocumento());
            servMalla.actualizarMalla(malla);
            MensajesFaces.informacion("El archivo", "Se ha subido con exito");
            event = null;
            malla = new Malla();

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

    public Map<String, Object> cargarMetadatosMalla() throws NullPointerException{
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        // Add the object type ID if it wasn't already
        if (props.get(PropertyIds.OBJECT_TYPE_ID) == null) {
            props.put(PropertyIds.OBJECT_TYPE_ID, "D:uce:TipoDocMalla");
        }
         //Lista para anadir al Documento
        List<String> asp = new ArrayList<>();
        asp.add("P:uce:carrera");
        asp.add("P:uce:fechas");
        props.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, asp);

        // Add the name if it wasn't already
        if (props.get("cmis:name") == null) {
            props.put("cmis:name", event.getFileName());
        }  
        if (props.get("uce:nombreFacultad") == null) {
            props.put("uce:nombreFacultad", malla.getIdCarrera().getIdFacultad().getNomFacultad());
        }
        if (props.get("uce:nombreCarrera") == null) {
            props.put("uce:nombreCarrera", malla.getIdCarrera().getNomCarrera());
        }
        if (props.get("uce:nombreMalla") == null) {
            props.put("uce:nombreMalla", malla.getNomMalla());
        }
        if (props.get("uce:codMalla") == null) {
            props.put("uce:codMalla", malla.getCodMalla());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", malla.getFechaIniMalla());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", malla.getFechaFinMalla());
        }
        return props;

    }
public void actualizarMetadatosMalla(Document documento) {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
         List<Object> aspects = documento.getProperty(PropertyIds.SECONDARY_OBJECT_TYPE_IDS).getValues();

        // Add the name if it wasn't already
        if (props.get("uce:nombreFacultad") == null) {
            props.put("uce:nombreFacultad", malla.getIdCarrera().getIdFacultad().getNomFacultad());
        }
        if (props.get("uce:nombreCarrera") == null) {
            props.put("uce:nombreCarrera", malla.getIdCarrera().getNomCarrera());
        }
        if (props.get("uce:nombreMalla") == null) {
            props.put("uce:nombreMalla", malla.getNomMalla());
        }
        if (props.get("uce:codMalla") == null) {
            props.put("uce:codMalla", malla.getCodMalla());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", malla.getFechaIniMalla());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", malla.getFechaFinMalla());
        }
      Document documento_actualizado =(Document) documento.updateProperties(props);
      

    }

    /**
     * Método que crea una lista de subcarpetas donde se guardaran los archivo
     *
     * @return nombreSubCarpetaList
     */
    public ArrayList<String> listaSubCarpetas() {

        ArrayList<String> nombreSubCarpetaList = new ArrayList<String>();
        nombreSubCarpetaList.add("Malla");
        nombreSubCarpetaList.add(malla.getIdCarrera().getNomCarrera());
        nombreSubCarpetaList.add(malla.getNomMalla());
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
        }
        catch (CmisObjectNotFoundException cobex) {
            MensajesFaces.error("Error", "El Documento no se encuentra " + cobex.getMessage());
        }
    }

    /**
     * Método que me permite eliminar un archivo de Alfresco por el id del
     * documento y el id de la capacitación
     *
     * @param idDocumento
     * @param idMalla
     */
    public void eliminarArchivoRepositorio(String idDocumento, int idMalla) {
        //Obtengo una session
        Session cmisSession = getCmisSession();
        //Encuentro la capacitacion por su id
        malla = servMalla.encontrarMallaID(idMalla);
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
                malla.setNombreDocMalla(null);
                malla.setIdDocMalla(null);
                servMalla.actualizarMalla(malla);
                malla = new Malla();
                MensajesFaces.informacion("Archivo", "Eliminado");

            }
        } catch (CmisObjectNotFoundException confe) {
            //Mesaje de error 
            MensajesFaces.error("Error", "No se pudo encontrar el documento que desea eliminar" + confe.getMessage());
        }

    }
//GET AND SET

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public UploadedFile getEvent() {
        return event;
    }

    public void setEvent(UploadedFile event) {
        this.event = event;
    }

    /**
     * Devuelve l una lista con los items de la facultad
     *
     * @return itemsFacultad
     */
    public List<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    public void setItemsFacultad(List<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

    /**
     * Devuelve l una lista con los items de la carrera
     *
     * @return itemsCarrera
     */
    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }

    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    /**
     * Devuelve el id de la facultad
     *
     * @return idFacultad
     */
    public int getIdFacultad() {
        return idFacultad;
    }

    /**
     * Modifica el id de la facultad
     *
     * @param idFacultad
     */
    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    /**
     * Devuelve el id de la carrrera
     *
     * @return idCarrera
     */
    public int getIdCarrera() {
        return idCarrera;
    }

    /**
     * Modifica el id de la carrrera
     *
     * @param idCarrera
     */
    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    /**
     * Devuelve el tipo de organización de una malla
     *
     * @return organizacion
     */
    public Map<String, String> getOrganizacion() {
        return organizacion;
    }

    /**
     * Modifica el tipo de organización de una malla
     *
     * @param organizacion
     */
    public void setOrganizacion(Map<String, String> organizacion) {
        this.organizacion = organizacion;
    }

    /**
     * Devuelve el estado del combo box para habilitar el combo box de la
     * carrera
     *
     * @return estadoComboBox
     */
    public String getEstadoComboBox() {
        return estadoComboBox;
    }

    /**
     * Modifica el estado del combo box para habilitar el combo box de la
     * carrera
     *
     * @param estadoComboBox
     */
    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

    /**
     * Devuelve un objeto de tipo Malla
     *
     * @return malla
     */
    public Malla getMalla() {
        return malla;
    }

    /**
     * Modifica un objeto de tipo Malla
     *
     * @param malla
     */
    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    public String getVacio() {
        return vacio;
    }

    public void setVacio(String vacio) {
        this.vacio = vacio;
    }

}
