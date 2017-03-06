package com.ec.uce.medicina.docente.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
     private static String PATH_RAIZ;
    private static String ARCHIVO_CONFIGURACION;
     private static String path;

    private static Properties config;

    public static Properties getConfig() {
        
        config = System.getProperties();
        PATH_RAIZ = config.getProperty("com.sun.aas.installRoot");
        ARCHIVO_CONFIGURACION = PATH_RAIZ + "\\modules\\Reportes\\config.properties";
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

       return props;
    }

}
