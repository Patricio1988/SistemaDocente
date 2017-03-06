/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

@SuppressWarnings("deprecation")
@ManagedBean
@SessionScoped
public class GeneradorReportes implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Clase Jasper para generar el reporte
     *
     * @uml.property name="jasperPrint"
     * @uml.associationEnd
     */
    private JasperPrint jasperPrint;
    /**
     * Mapa de parï¿½metros que se van a pasar al reporte
     *
     * @uml.property name="parametrosReporte"
     */
    // No se usa un Mapa de genï¿½ricos debido a que JasperFillManager.fillReport
    // no soporta este tipo de mapas.
    @SuppressWarnings("rawtypes")
    private Map parametrosReporte = new HashMap();
    /**
     * Nombre del archivo .jasper que genera el reporte
     *
     * @uml.property name="nombreJasper"
     */
    private String nombreJasper;
    /**
     * Path en el cual se van a guardar los archivos .jasper y .jrxml del
     * reporte. Este path lo lee del archivo reportes.properties
     *
     * @uml.property name="path"
     */
    private String path;

    private Properties properties;

    /**
     * Ruta del archivo reportes.properties. Es un path relativo a la carpeta
     * bin del Jboss
     *
     * @uml.property name="aRCHIVO_CONFIGURACION"
     */
    private String PATH_RAIZ;
    private String ARCHIVO_CONFIGURACION;
    /**
     * Nombre con el cual se genera el archivo de reporte en los diferentes
     * formatos
     *
     * @uml.property name="nombreReporte"
     */
    private String nombreReporte;
    private Connection conn;

    /**
     * DataSource que se lo obtiene del servidor de aplicaciones
     *
     * @uml.property name="dataSource"
     * @uml.associationEnd readOnly="true"
     */
    /**
     * Constructor. Carga el archivo de propiedades properties y lee la
     * propiedad path
     */
    public GeneradorReportes() {
        properties = System.getProperties();
        PATH_RAIZ = properties.getProperty("com.sun.aas.installRoot");
        ARCHIVO_CONFIGURACION = PATH_RAIZ + "\\modules\\Reportes\\ReportesDocente.properties";
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(ARCHIVO_CONFIGURACION));
            path = props.getProperty("path");
        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFoundException: " + e.getMessage());
            MensajesFaces.error("NO SE PUEDE GENERAR EL REPORTE", "NO SE ENCUENTRA EL ARCHIVO PROPERTIES EN LA RUTA ESTABLECIDA.");
        } catch (IOException e) {
            System.out.println("Error IOException: " + e.getMessage());
        }
    }

    public void generarReporte() throws Exception {
        try {
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/DocenteMedicinaDS");
            conn = dataSource.getConnection();
            jasperPrint = JasperFillManager.fillReport(path + "\\"
                    + nombreJasper, parametrosReporte, conn);
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error SQLException: " + e.getMessage());
            throw new Exception("ERROR AL CONECTARSE A LA BASE DE DATOS ");
        } catch (JRException e) {
            System.out.println("Error JRException: " + e.getMessage());
            throw new Exception("NO SE PUDO GENERAR EL REPORTE");
        }
       
    }

    /**
     * Invoca a la generaciï¿½n del reporte y genera el Stream con la
     * informaciï¿½n del reporte que serï¿½ devuelto a la pï¿½gina
     *
     * @param extension extensiï¿½n del archivo a generarse (pdf, docx, xlsx.
     * odt. pptx)
     * @return el Stream con la informaciï¿½n del reporte que serï¿½ devuelto a
     * la pï¿½gina crear y descargar del pdf reporte,quieres attachment, para q
     * se abre online
     * @throws Exception si no logrï¿½ generar el reporte
     */
    public ServletOutputStream generarResponse(String extension) throws Exception {
        generarReporte();
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext
                .getCurrentInstance().getExternalContext().getResponse();

        httpServletResponse.addHeader("Content-disposition",
                "attachment; filename=" + nombreReporte + "." + extension);

        ServletOutputStream servletOutputStream = null;
        try {
            servletOutputStream = httpServletResponse.getOutputStream();
        } catch (IOException e) {
            System.out.println("Error SQLException: " + e.getMessage());
        }
        return servletOutputStream;
    }

    /**
     * Retorna el reporte en formato PDF con el Stream que obtiene de
     * generarResponse
     *
     * @throws Exception si no logra generar el reporte
     */
    public void generarXLSX() throws Exception {
        ServletOutputStream servletOutputStream = generarResponse("xlsx");
        JRXlsxExporter docxExporter = new JRXlsxExporter();
        docxExporter
                .setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        docxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
                servletOutputStream);
        docxExporter.exportReport();
        FacesContext.getCurrentInstance().responseComplete();

    }

    public void generarPDF() throws Exception {
        ServletOutputStream servletOutputStream = generarResponse("pdf");

        JasperExportManager.exportReportToPdfStream(jasperPrint,
                servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    // getters y setters
    /**
     * @return @uml.property name="nombreJasper"
     */
    public String getNombreJasper() {
        return nombreJasper;
    }

    /**
     * @param nombreJasper
     * @uml.property name="nombreJasper"
     */
    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    /**
     * @return @uml.property name="path"
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     * @uml.property name="path"
     */
    public void setPath(String path) {
        this.path = path;
    }

    @SuppressWarnings("rawtypes")
    public Map getParametrosReporte() {
        return parametrosReporte;
    }

    @SuppressWarnings("rawtypes")
    public void setParametrosReporte(Map parametrosReporte) {
        this.parametrosReporte = parametrosReporte;
    }

    /**
     * @return @uml.property name="nombreReporte"
     */
    public String getNombreReporte() {
        return nombreReporte;
    }

    /**
     * @param nombreReporte
     * @uml.property name="nombreReporte"
     */
    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }


}
