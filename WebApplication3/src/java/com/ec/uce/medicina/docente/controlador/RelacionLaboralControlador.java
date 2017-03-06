/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.alfresco.archivo.AlfrescoConexion;
import com.ec.uce.medicina.docente.modelo.CarreraDocente;
import com.ec.uce.medicina.docente.modelo.Categoria;
import com.ec.uce.medicina.docente.modelo.Docente;
import com.ec.uce.medicina.docente.modelo.RelacionIes;
import com.ec.uce.medicina.docente.modelo.RelacionLaboral;
import com.ec.uce.medicina.docente.modelo.TiempoDedicacion;
import com.ec.uce.medicina.docente.modelo.TipoDocumento;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.CarreraDocenteServicio;
import com.uce.uce.medicina.docente.servicio.CategoriaServicio;
import com.uce.uce.medicina.docente.servicio.DocenteServicio;
import com.uce.uce.medicina.docente.servicio.RelacionIesServicio;
import com.uce.uce.medicina.docente.servicio.RelacionLaboralServicio;
import com.uce.uce.medicina.docente.servicio.TiempoDedicacionServicio;
import com.uce.uce.medicina.docente.servicio.TipoDocumentoServicio;
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
 * Declaración del ManagedBean RelacionLaboralControlador que sera manejada por
 * jsf de ámbito ViewScoped que permitira realizar las operaciones en las
 * paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class RelacionLaboralControlador extends AlfrescoConexion {

    //Atributo id Carrera
    private int idCarrera;
    //Declaración objeto tipo CarreraDocente
    private CarreraDocente carreraDocente;
    /**
     * Atributo id docente
     */
    private int idCarreraDocente;
    //Declaración objeto tipo Docente
    private Docente docente;
    /**
     * Atributo id categoría
     */
    private int idCategoria;
    //Declaración objeto tipo Categoria
    private Categoria categoria;
    /**
     * Atributo id tipo documento
     */
    private int idTipoDocumento;
    //Declaración objeto tipo TipoDocumento
    private TipoDocumento tipoDocumento;
    /**
     * Atributo id Relación IES
     */
    private int idRelacionIes;
    //Declaración objeto tipo RelacionIes
    private RelacionIes relacionIes;
    /**
     * Atributo id tiempo de dedicación
     */
    private int idTiempoDedicacion;
    //Declaración objeto tipo TiempoDedicacion
    private TiempoDedicacion tiempoDedicacion;
    /**
     * Atributo estado del combo box
     */
    private String estadoComboBox;
    //Declaración objeto tipo RelacionLaboral
    private RelacionLaboral relacionLaboral;
    //Servicios
    @EJB
    private RelacionLaboralServicio servRelacionLaboral;
    @EJB
    private DocenteServicio servDocente;
    @EJB
    private CategoriaServicio servCategoria;
    @EJB
    private TipoDocumentoServicio servTipoDocumento;
    @EJB
    private RelacionIesServicio servRelacionIes;
    @EJB
    private TiempoDedicacionServicio servTiempoDedicacion;
    @EJB
    private CarreraDocenteServicio servCarreraDocente;
    /**
     * Atributo para almacenar el tipo de personal como una estructura clave
     * valor
     */
    private Map<String, String> tiposPersonal;
    /**
     * Atributo número de cedula del docente
     */
    private String numeroCedula;
    private StreamedContent file;
    private UploadedFile event;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;
    private String vacio;

    //Inicialización de variables
    @PostConstruct
    public void inicializar() {
        //Instacia de los objetos
        carreraDocente=new CarreraDocente();
        categoria = new Categoria();
        tipoDocumento = new TipoDocumento();
        relacionIes = new RelacionIes();
        tiempoDedicacion = new TiempoDedicacion();
        relacionLaboral = new RelacionLaboral();
        tiposPersonal = Tablas.retornarTipoPersonal();
        numeroCedula = Constantes.VACIO;
        idCarreraDocente=0;
        file = null;
        event = null;
        vacio = " ";
    }

    /**
     * Método que permite crear un registro en la base de datos, si todo sale
     * bien cierra el dialogo para crear el registro, caso contrario no se
     * cierra el dialogo
     */
    public void crear() {
        try {

            idCarreraDocente = carreraDocente.getIdCarreraDocente();
            relacionLaboral.setIdCarreraDocente(servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente));
            relacionLaboral.setIdCategoria(servCategoria.encontrarCategoria(idCategoria));
            relacionLaboral.setIdTipoDocumento(servTipoDocumento.encontrarTipoDocumento(idTipoDocumento));
            relacionLaboral.setIdRelacionIes(servRelacionIes.encontrarRelacion(idRelacionIes));
            relacionLaboral.setIdTdedi(servTiempoDedicacion.encontarTiempoDedicacionID(idTiempoDedicacion));
            servRelacionLaboral.insertarRelacionLaboral(relacionLaboral);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoRelacionLaboralAgregar').hide();");//Cierra el dialodo para agregar
            req = null;
            idCarreraDocente = 0;
            idCategoria = 0;
            idTipoDocumento = 0;
            idRelacionIes = 0;
            idTiempoDedicacion = 0;
            relacionLaboral = new RelacionLaboral();
            MensajesFaces.informacion("Guardado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método que permite actualizar un registro en la base de datos, si todo
     * sale bien cierra el dialogo para actualizar el registro ,caso contrario
     * no se cierra el dialogo /** Método que permite actualizar un registro en
     * la base de datos, si todo sale bien cierra el dialogo para actualizar el
     * registro ,caso contrario no se cierra el dialogo
     */

    public void actualizar() {
        try {

            idCarreraDocente = carreraDocente.getIdCarreraDocente();
            relacionLaboral.setIdCarreraDocente(servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente));
            relacionLaboral.setIdCategoria(servCategoria.encontrarCategoria(idCategoria));
            relacionLaboral.setIdTipoDocumento(servTipoDocumento.encontrarTipoDocumento(idTipoDocumento));
            relacionLaboral.setIdRelacionIes(servRelacionIes.encontrarRelacion(idRelacionIes));
            relacionLaboral.setIdTdedi(servTiempoDedicacion.encontarTiempoDedicacionID(idTiempoDedicacion));
           // relacionLaboral.setIdDocente(servDocente.encontrarDocente(idDocente));
            servRelacionLaboral.actualizarRelacionLaboral(relacionLaboral);
            Document document = (Document) buscarDocumento(relacionLaboral.getIdDocRelacionLabo());
            if (document != null) {
                actualizarMetadatosRelacion(document);
            }
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoDatosActualizar').hide();");//Cierra el dialodo para actualizar
            req = null;
            idCarreraDocente = 0;
            idCategoria = 0;
            idRelacionIes = 0;
            idTipoDocumento = 0;
            idTiempoDedicacion = 0;
            relacionLaboral = new RelacionLaboral();
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);

        }

    }

    /**
     * Método para eliminar un registro de la base de datos
     *
     * @param re
     */
    public void eliminar(RelacionLaboral re) {

        try {

            if (re.getIdDocRelacionLabo() == null) {
                servRelacionLaboral.eliminarRelacionLaboral(re);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            } else {
                eliminarArchivoRepositorio(re.getIdDocRelacionLabo(), re.getIdReLaboral());
                servRelacionLaboral.eliminarRelacionLaboral(re);
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
            relacionLaboral = new RelacionLaboral();
        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Método para buscar un docente por su número de cedula
     *
     * @param idCarreraDocente
     */
    public void recuperardatosCarreraDocenteId(int idCarreraDocente) {
        carreraDocente = servCarreraDocente.buscarCarreraDocenterporId(idCarreraDocente);
        if (carreraDocente != null) {       
            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();
            idCarrera=carreraDocente.getIdCarrera().getIdCarrera();
            MensajesFaces.informacion("Persona", "Encontrada");
        } else {
            numeroCedula = Constantes.VACIO;
            idCarrera=0;
            carreraDocente = new CarreraDocente();
            MensajesFaces.informacion("Persona", "No encontrada");
        }

    }

    /**
     * Método que devuelve una lista de relacion laboral de un docente
     *
     * @param idPeriodo
     * @return servRelacionLaboral.listaRelacionporDocente(numeroCedula)
     */
    public List<RelacionLaboral> listaRelacionlaboralDocente(int idPeriodo) {
        return servRelacionLaboral.listaRelacionporDocente(numeroCedula,idCarrera,idPeriodo);
    }

    /**
     * Método que busca una relación laboral por su id
     *
     * @param id
     */
    public void recuperarRelacionporID(int id) {

        relacionLaboral = servRelacionLaboral.encontrarRelacionLaboral(id);
        if (relacionLaboral != null) {
            idCategoria = relacionLaboral.getIdCategoria().getIdCategoria();
            idTipoDocumento = relacionLaboral.getIdTipoDocumento().getIdTipoDocumento();
            idRelacionIes = relacionLaboral.getIdRelacionIes().getIdRelacionIes();
            idTiempoDedicacion = relacionLaboral.getIdTdedi().getIdTdedi();
            relacionLaboral.getNumDocumento();

            MensajesFaces.informacion("Relacionlaboral", "Encontrada");
        } else {
            docente = new Docente();
            MensajesFaces.informacion("Relacionlaboral", "No encontrada");
        }
    }

    /**
     * Metodo que devuelve una lista de todos los docentes
     *
     * @return servDocente.listarDocente();
     */
    public List<Docente> recuperartodosDocentes() {
        return servDocente.listarDocente();

    }

    /**
     * Método que permite abrir un el dialogo siempre y cuando se haya
     * selccionado un docente primero
     */
    public void abrirDialogo() {
        
        try {
            carreraDocente.getIdDocente().getIdentificacion();
             req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoRelacionLaboralAgregar').show()");
            req = null;
        } catch (NullPointerException ex) {
            MensajesFaces.informacion("Seleccione un Docente", "Primero");
        }
        catch (Exception e) {
             MensajesFaces.informacion("Error",e.getMessage());
        }
        
    }
// Método para cerrar

    public void cerrar() {
        idCarreraDocente = 0;
        idCategoria = 0;
        idTipoDocumento = 0;
        idRelacionIes = 0;
        idTiempoDedicacion = 0;
        relacionLaboral = new RelacionLaboral();
        event = null;
    }

//    /**
//     * Método que permite encontrar un docente
//     *
//     * @param idDocen
//     * @param idCarrer
//     */
//    public void recuperardatosDocenteCarreraPorCedula(int idDocen, int idCarrer) {
//        carreraDocente = servCarreraDocente.buscarDocenteCarreraporID(idDocen, idCarrer);
//        if (carreraDocente != null) {
//            idCarreraDocente = carreraDocente.getIdCarreraDocente();
//            idCarrera = carreraDocente.getIdCarrera().getIdCarrera();
//            numeroCedula = carreraDocente.getIdDocente().getIdentificacion();
//
//            MensajesFaces.informacion("Persona", "Encontrada");
//        } else {
//            numeroCedula = Constantes.VACIO;
//            carreraDocente = new CarreraDocente();
//            MensajesFaces.informacion("Persona", "No encontrada");
//        }
//
//    }
    //archivos guardado en el servidor

    /**
     * Método que permite obtener un archivo
     *
     * @param upload evento que me permite subir el archivo en disco
     */
    public void handleFileUpload(FileUploadEvent upload) {
        event = upload.getFile();//obtengo el archivo y le asigno a la variable event que es un evento de tipo FileUploadEvent
        relacionLaboral.setNombreDocRelacionLabo(event.getFileName());//Modifico el nombre del docuemnto de la relación laboral

    }

    /**
     * Método que permite subir un archivo al repositorio de alfresco utilizando
     * CMIS API
     */
    public void subirArchivoRepositorio() {
        String nombreFolder = carreraDocente.getIdCarrera().getIdFacultad().getNomFacultad();

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
            createDocument(subFolder1, event, getLocalFileType(), cargarMetadatosRelacion());
            relacionLaboral.setIdDocRelacionLabo(getIdDocumento());
            servRelacionLaboral.actualizarRelacionLaboral(relacionLaboral);
            MensajesFaces.informacion("El archivo", "Se ha subido con exito");

            event = null;
            relacionLaboral = new RelacionLaboral();

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

    public Map<String, Object> cargarMetadatosRelacion()throws NullPointerException{
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        // Add the object type ID if it wasn't already
        if (props.get(PropertyIds.OBJECT_TYPE_ID) == null) {
            props.put(PropertyIds.OBJECT_TYPE_ID, "D:uce:TipoDocRelacionLaboral");
        }
        //Lista para anadir al Documento
        List<String> asp = new ArrayList<>();
        
        asp.add("P:uce:docente");
        asp.add("P:uce:fechas");
        asp.add("P:uce:carrera");
       asp.add("P:uce:periodo");
        props.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS, asp);

        // Add the name if it wasn't already
        // Add the name if it wasn't already
        if (props.get("cmis:name") == null) {
            props.put("cmis:name", event.getFileName());
        }

        if (props.get("uce:apellidos") == null) {
            if (relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoMaterno() == null || relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoPaterno());
            } else {
                props.put("uce:apellidos", relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoPaterno() + " " + relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoMaterno());
                
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", relacionLaboral.getIdCarreraDocente().getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", relacionLaboral.getIdCarreraDocente().getIdDocente().getIdentificacion());
        }
        if (props.get("uce:nombreFacultad") == null) {
            props.put("uce:nombreFacultad", carreraDocente.getIdCarrera().getIdFacultad().getNomFacultad());
        }
        if (props.get("uce:nombreCarrera") == null) {
            props.put("uce:nombreCarrera", carreraDocente.getIdCarrera().getNomCarrera());
        }
        if (props.get("uce:nombrePeriodo") == null) {
            props.put("uce:nombrePeriodo", relacionLaboral.getIdCarreraDocente().getIdPeriodo().getNombrePeriodo());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", relacionLaboral.getFechaInicioContrato());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", relacionLaboral.getFechaFinContrato());
        }
        if (props.get("uce:numDocumentoRelacion") == null) {
            props.put("uce:numDocumentoRelacion", relacionLaboral.getNumDocumento());
        }
        if (props.get("uce:tipoPersonal") == null) {
            props.put("uce:tipoPersonal", relacionLaboral.getTipoPersonal());
        }

        if (props.get("uce:categoria") == null) {
            props.put("uce:categoria", relacionLaboral.getIdCategoria().getNomCategoria());
        }
        if (props.get("uce:tipoDocumento") == null) {
            props.put("uce:tipoDocumento", relacionLaboral.getIdTipoDocumento().getNomTipoDocumento());
        }
        if (props.get("uce:relacionIES") == null) {
            props.put("uce:relacionIES", relacionLaboral.getIdRelacionIes().getNomRelacionIes());
        }
        if (props.get("uce:numDocumentoRelacion") == null) {
            props.put("uce:numDocumentoRelacion", relacionLaboral.getNumDocumento());
        }
        if (props.get("uce:estado") == null) {
            props.put("uce:estado", relacionLaboral.isEstadoRelacion());
        }

        if (props.get("uce:tiempoDedicacion") == null) {
            props.put("uce:tiempoDedicacion", relacionLaboral.getIdTdedi().getNomTdedi());
        }
        return props;

    }

    public void actualizarMetadatosRelacion(Document documento) {
        Map<String, Object> props = null;
        if (props == null) {
            props = new HashMap<String, Object>();
        }
        List<Object> aspects = documento.getProperty(PropertyIds.SECONDARY_OBJECT_TYPE_IDS).getValues();

        // Add the name if it wasn't already
        if (props.get("uce:apellidos") == null) {
            if (relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoMaterno() == null || relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoMaterno().equals(" ")) {
                props.put("uce:apellidos", relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoPaterno());
            } else {
                 props.put("uce:apellidos", relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoPaterno() + " " + relacionLaboral.getIdCarreraDocente().getIdDocente().getApellidoMaterno());       
            }
        }
        if (props.get("uce:nombres") == null) {
            props.put("uce:nombres", relacionLaboral.getIdCarreraDocente().getIdDocente().getNombres());
        }
        if (props.get("uce:ci") == null) {
            props.put("uce:ci", relacionLaboral.getIdCarreraDocente().getIdDocente().getIdentificacion());
        }
         if (props.get("uce:nombreFacultad") == null) {
            props.put("uce:nombreFacultad", carreraDocente.getIdCarrera().getIdFacultad().getNomFacultad());
        }
        if (props.get("uce:nombreCarrera") == null) {
            props.put("uce:nombreCarrera", carreraDocente.getIdCarrera().getNomCarrera());
        }
        if (props.get("uce:nombrePeriodo") == null) {
            props.put("uce:nombrePeriodo", relacionLaboral.getIdCarreraDocente().getIdPeriodo().getNombrePeriodo());
        }
        if (props.get("uce:fechaInicio") == null) {
            props.put("uce:fechaInicio", relacionLaboral.getFechaInicioContrato());
        }
        if (props.get("uce:fechaFin") == null) {
            props.put("uce:fechaFin", relacionLaboral.getFechaFinContrato());
        }
        if (props.get("uce:numDocumentoRelacion") == null) {
            props.put("uce:numDocumentoRelacion", relacionLaboral.getNumDocumento());
        }
        if (props.get("uce:tipoPersonal") == null) {
            props.put("uce:tipoPersonal", relacionLaboral.getTipoPersonal());
        }

        if (props.get("uce:categoria") == null) {
            props.put("uce:categoria", relacionLaboral.getIdCategoria().getNomCategoria());
        }
        if (props.get("uce:tipoDocumento") == null) {
            props.put("uce:tipoDocumento", relacionLaboral.getIdTipoDocumento().getNomTipoDocumento());
        }
        if (props.get("uce:relacionIES") == null) {
            props.put("uce:relacionIES", relacionLaboral.getIdRelacionIes().getNomRelacionIes());
        }
        if (props.get("uce:numDocumentoRelacion") == null) {
            props.put("uce:numDocumentoRelacion", relacionLaboral.getNumDocumento());
        }
        if (props.get("uce:estado") == null) {
            props.put("uce:estado", relacionLaboral.isEstadoRelacion());
        }

        if (props.get("uce:tiempoDedicacion") == null) {
            props.put("uce:tiempoDedicacion", relacionLaboral.getIdTdedi().getNomTdedi());
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
        nombreSubCarpetaList.add("Relacion Laboral");
        nombreSubCarpetaList.add(carreraDocente.getIdCarrera().getNomCarrera());
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
        } catch (CmisObjectNotFoundException cobex) {
            MensajesFaces.error("Error", "El Documento no se encuentra " + cobex.getMessage());
        }
    }

    /**
     * Método que me permite eliminar un archivo de Alfresco por el id del
     * documento y el id de la formación
     *
     * @param idDocumento
     * @param idRelacion
     */
    public void eliminarArchivoRepositorio(String idDocumento, int idRelacion) {
        //Obtengo una session
        Session cmisSession = getCmisSession();
        //Encuentro la formacion por su id
        relacionLaboral = servRelacionLaboral.encontrarRelacionLaboral(idRelacion);
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
                relacionLaboral.setNombreDocRelacionLabo(null);
                relacionLaboral.setIdDocRelacionLabo(null);
                servRelacionLaboral.actualizarRelacionLaboral(relacionLaboral);
                relacionLaboral = new RelacionLaboral();
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
     * Devuelve el id de la categoría
     *
     * @return idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * Modifica el id de la categoría
     *
     * @param idCategoria
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * Devuelve un objeto de tipo categoría
     *
     * @return categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Modifica un objeto de tipo categoría
     *
     * @param categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Devuelve el id del tipo de documento
     *
     * @return idTipoDocumento
     */
    public int getIdTipoDocumento() {
        return idTipoDocumento;
    }

    /**
     * Modifica el id del tipo de documento
     *
     * @param idTipoDocumento
     */
    public void setIdTipoDocumento(int idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    /**
     * Devuelve un objeto de tipo TipoDocumento
     *
     * @return tipoDocumento
     */
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Devuelve un objeto de tipo TipoDocumento
     *
     * @param tipoDocumento
     */
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * Devuelve el id de la relación con la IES
     *
     * @return idRelacionIes
     */
    public int getIdRelacionIes() {
        return idRelacionIes;
    }

    /**
     * Modifica el id de la relación con la IES
     *
     * @param idRelacionIes
     */
    public void setIdRelacionIes(int idRelacionIes) {
        this.idRelacionIes = idRelacionIes;
    }

    /**
     * Devuelve un objeto de tipo RelacionIes
     *
     * @return relacionIes
     */
    public RelacionIes getRelacionIes() {
        return relacionIes;
    }

    /**
     * Modifica un objeto de tipo RelacionIes
     *
     * @param relacionIes
     */

    public void setRelacionIes(RelacionIes relacionIes) {
        this.relacionIes = relacionIes;
    }

    /**
     * Devuelve el id del tiempo de dedicación
     *
     * @return idTiempoDedicacion
     */
    public int getIdTiempoDedicacion() {
        return idTiempoDedicacion;
    }

    /**
     * Modifica el id del tiempo de dedicación
     *
     * @param idTiempoDedicacion
     */
    public void setIdTiempoDedicacion(int idTiempoDedicacion) {
        this.idTiempoDedicacion = idTiempoDedicacion;
    }

    /**
     * Devuelve un objeto de tipo TiempoDedicacion
     *
     * @return tiempoDedicacion
     */
    public TiempoDedicacion getTiempoDedicacion() {
        return tiempoDedicacion;
    }

    /**
     * Modifica un objeto de tipo TiempoDedicacion
     *
     * @param tiempoDedicacion
     */
    public void setTiempoDedicacion(TiempoDedicacion tiempoDedicacion) {
        this.tiempoDedicacion = tiempoDedicacion;
    }

    /**
     * Devuelve el tipo de personal del docente
     *
     * @return tiposPersonal
     */
    public Map<String, String> getTiposPersonal() {
        return tiposPersonal;
    }

    /**
     * Modifica el tipo de personal del docente
     *
     * @param tiposPersonal
     */
    public void setTiposPersonal(Map<String, String> tiposPersonal) {
        this.tiposPersonal = tiposPersonal;
    }

    /**
     * Devuelve le número de cedula
     *
     * @return numeroCedula
     */
    public String getNumeroCedula() {
        return numeroCedula;
    }

    /**
     * Modifica le número de cedula
     *
     * @param numeroCedula
     */
    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    /**
     * Devuelve un objeto de tipo Docente
     *
     * @return docente
     */
    public Docente getDocente() {
        return docente;
    }

    /**
     * Modifica un objeto de tipo Docente
     *
     * @param docente
     */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }


    /**
     * Devuelve el id del docente perteneciente a una carrera
     *
     * @return idCarreraDocente
     */
   public int getIdCarreraDocente() {
        return idCarreraDocente;
    }

    /**
     * Modifica el id del docente de una carrera
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
     * Mofifica el estado del combo box
     *
     * @param estadoComboBox
     */
    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

    /**
     * Devuelve un objeto de tipo RelacionLaboral
     *
     * @return relacionLaboral
     */
    public RelacionLaboral getRelacionLaboral() {
        return relacionLaboral;
    }

    /**
     * Modifica un objeto de tipo RelacionLaboral
     *
     * @param relacionLaboral
     */
    public void setRelacionLaboral(RelacionLaboral relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
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
