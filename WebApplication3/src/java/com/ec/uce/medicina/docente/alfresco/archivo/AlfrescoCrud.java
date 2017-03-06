package com.ec.uce.medicina.docente.alfresco.archivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import com.ec.uce.medicina.docente.alfresco.modelo.ContainerEntry;
import com.ec.uce.medicina.docente.alfresco.modelo.ContainerList;
import com.ec.uce.medicina.docente.alfresco.modelo.NetworkEntry;
import com.ec.uce.medicina.docente.alfresco.modelo.NetworkList;
import com.ec.uce.medicina.docente.util.Config;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.util.FileUtils;
import org.apache.chemistry.opencmis.commons.exceptions.CmisConstraintException;
import org.primefaces.model.UploadedFile;


/**
 * Esta clase contiene constantes y métodos que son comunes en todos
  * La API de Alfresco público independientemente del lugar en el repositorio de destino es
  * Alojada.
 *
 * @author Patricio Samueza
 *
 */
abstract public class AlfrescoCrud {
    public static final String SITES_URL = "/public/alfresco/versions/1/sites/";
    public static final String NODES_URL = "/public/alfresco/versions/1/nodes/";

    private String homeNetwork;
    private String idDocumento;


    /**
     * Usa  CMIS API para crear la carpeta raíz  en destino, acontinucaión crea la carpeta y luego un documento en la nueva carpeta
     *
     * 
     * @param parentFolderId
     * @param folderName
     * @return Folder
     */
  
    public Folder createFolder(String parentFolderId, String folderName) {
        Session cmisSession = getCmisSession();
        Folder rootFolder = (Folder) cmisSession.getObject(parentFolderId);
        System.out.println("Nombrecarpeta"+rootFolder.getName());
//String folderName1="hola";
        Folder subFolder = null;
       // Folder subFolder1 = null;
        try {
            // Making an assumption here that you probably wouldn't normally do
            subFolder = (Folder) cmisSession.getObjectByPath(rootFolder.getPath() + "/" + folderName);
             System.out.println("path"+subFolder.getPath());
           // subFolder1 = (Folder) cmisSession.getObjectByPath(subFolder.getPath() + "/" + folderName1);
            System.out.println("Folder already existed!");
        } catch (CmisObjectNotFoundException onfe) {
            Map<String, Object> props = new HashMap<String, Object>();
            props.put("cmis:objectTypeId",  "cmis:folder");
            props.put("cmis:name", folderName);
          //  props.put("cmis:name", folderName1);
            subFolder = rootFolder.createFolder(props);
            // subFolder1 = rootFolder.createFolder(props);
            String subFolderId = subFolder.getId();
            System.out.println("Created new folder: " + subFolderId);
        }

        return subFolder;
    }
    public Folder createSubFolder(Folder folder,String nombre )
    {
        Folder fol=null;
         Session cmisSession = getCmisSession();
            CmisObject object = FileUtils.getObject(folder.getId(), cmisSession);
         if (object instanceof Folder) {
                 fol = (Folder) object;
                System.out.println("subcarpeta"+fol.getName());
        
    }
         return fol;
    }

    public String getHomeNetwork() throws IOException {
        if (this.homeNetwork == null) {
            GenericUrl url = new GenericUrl(getAlfrescoAPIUrl());

            HttpRequest request = getRequestFactory().buildGetRequest(url);

            NetworkList networkList = request.execute().parseAs(NetworkList.class);
            System.out.println("Found " + networkList.list.pagination.totalItems + " networks.");
            for (NetworkEntry networkEntry : networkList.list.entries) {
                if (networkEntry.entry.homeNetwork) {
                    this.homeNetwork = networkEntry.entry.id;
                }
            }

            if (this.homeNetwork == null) {
                this.homeNetwork = "-default-";
            }

            System.out.println("Your home network appears to be: " + homeNetwork);
        }
        return this.homeNetwork;
    }

    public Document createDocument(Folder parentFolder,
                                   UploadedFile file,
                                   String fileType)
            throws FileNotFoundException {
        return createDocument(parentFolder, file, fileType, null);
    }
    
    public Document buscarDocumento(String idDocumento)
    {
        System.out.println("Entrdando actualizar Metadatos");
        Document documento=null;
        Session cmisSession = getCmisSession();
        if(idDocumento==null || idDocumento.equals(" "))
        {
            MensajesFaces.informacion("Info", "Aun no ha subido un Documento al Repositorio");
        }
        else{            
         documento= (Document) cmisSession.getObject(idDocumento);
         if(documento!=null)
         {
             MensajesFaces.informacion("Info", "Documento Encontrado");
         }
         else{
                MensajesFaces.error("Error", "El Documento no se Encuentra");
         }
        }
         return documento;
    }
    public Document createDocument(Folder parentFolder,
                                   UploadedFile file,
                                   String fileType,
                                   Map<String, Object> props)
            throws FileNotFoundException,CmisConstraintException,CmisContentAlreadyExistsException {

        Session cmisSession = getCmisSession();

       String fileName = file.getFileName();
byte[] content = file.getContents();
InputStream stream = new ByteArrayInputStream(content);
        ContentStream contentStream = cmisSession.getObjectFactory().
                  createContentStream(
                    fileName,
                    file.getSize(),
                    fileType,
                    stream
                  );
        
        Document document = null;
        //try {
            document = parentFolder.createDocument(props, contentStream, null);
            System.out.println("path: " + document.getPaths());
            System.out.println("Created new document: " + document.getId());
            idDocumento=document.getId();
            ContentStream contentStream1 = null;
//        } catch (CmisContentAlreadyExistsException ccaee) {
//            document = (Document) cmisSession.getObjectByPath(parentFolder.getPath() + "/" + fileName);
//            System.out.println("Document already exists: " + fileName);
//            MensajesFaces.error("Error", "El documento: " + fileName +" ya existe porfavor cambie de nombre al archivo");
//        }
        

        return document;
    }

    /**
     * Use the REST API to find the documentLibrary folder for
     * the target site
     * @param site
     * @return String
     * @throws java.io.IOException
 
     */
    public String getRootFolderId(String site) throws IOException {

        GenericUrl containersUrl = new GenericUrl(getAlfrescoAPIUrl() +
                                             getHomeNetwork() +
                                             SITES_URL +
                                             site +
                                             "/containers");
        System.out.println(containersUrl);
        HttpRequest request = getRequestFactory().buildGetRequest(containersUrl);
        ContainerList containerList = request.execute().parseAs(ContainerList.class);
        String rootFolderId = null;
        for (ContainerEntry containerEntry : containerList.list.entries) {
                if (containerEntry.entry.folderId.equals("documentLibrary")) {
                    rootFolderId = containerEntry.entry.id;
                    break;
                }
        }
        return rootFolderId;
    }

//    /**
//     * Use the REST API to "like" an object
//     *
//     * @param requestFactory
//     * @param homeNetwork
//     * @param objectId
//     * @throws IOException
//     */
    public void like(String objectId) throws IOException {
        GenericUrl likeUrl = new GenericUrl(getAlfrescoAPIUrl() +
                             getHomeNetwork() +
                             NODES_URL +
                             objectId +
                             "/ratings");
        HttpContent body = new ByteArrayContent("application/json", "{\"id\": \"likes\", \"myRating\": true}".getBytes());
        HttpRequest request = getRequestFactory().buildPostRequest(likeUrl, body);
        request.execute();
        System.out.println("You liked: " + objectId);
    }

    /**
     * Use the REST API to comment on an object
     *
     * @param objectId
     * @param comment
     * @throws IOException
     */
    public void comment(String objectId, String comment) throws IOException {
        GenericUrl commentUrl = new GenericUrl(getAlfrescoAPIUrl() +
                             getHomeNetwork() +
                             NODES_URL +
                             objectId +
                             "/comments");
        HttpContent body = new ByteArrayContent("application/json",
                                                ("{\"content\": \"" + comment + "\"}").getBytes());
        HttpRequest request = getRequestFactory().buildPostRequest(commentUrl, body);
        request.execute();
        System.out.println("You commented on: " + objectId);
    }

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getSite() {
        return Config.getConfig().getProperty("site");
    }

    public String getFolderName() {
        return Config.getConfig().getProperty("folder_name");
    }

    public File getLocalFile() {
        String filePath = Config.getConfig().getProperty("local_file_path");
        return new File(filePath);
    }

    public String getLocalFileType() {
        return Config.getConfig().getProperty("local_file_type");
    }

    abstract public String getAlfrescoAPIUrl();
    abstract public Session getCmisSession();
    abstract public HttpRequestFactory getRequestFactory();
}
