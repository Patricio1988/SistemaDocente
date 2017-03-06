/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;

import com.ec.uce.medicina.docente.modelo.Malla;
import com.ec.uce.medicina.docente.modelo.Materia;
import com.ec.uce.medicina.docente.modelo.Authorities;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.ec.uce.medicina.docente.util.Tablas;
import com.uce.uce.medicina.docente.servicio.DocenteContratoMateriaServicio;
import com.uce.uce.medicina.docente.servicio.MallaServicio;
import com.uce.uce.medicina.docente.servicio.MateriaServicio;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 * Declaración del ManagedBean MateriaControlador que sera manejada por jsf de
 * ámbito ViewScoped que permitira realizar las operaciones en las paginas xhtml
 *
 * @author Patricio
 * @version 2.0
 */
@ViewScoped
@ManagedBean
public class MateriaControlador {

    private Materia materia;

    private int idMalla;
    private Malla malla;
    @EJB
    private MateriaServicio serv_materia;
    @EJB
    private MallaServicio servMalla;
    @EJB
    private DocenteContratoMateriaServicio servCarga;

    /**
     * Atributo para almacenar el semestre como una estructura clave valor
     */
    private Map<String, String> tpSemestres;
    //Atributo RequestContext que encapsula informacion de una peticion HTTP
    private RequestContext req;

//Constructor por defecto
    public MateriaControlador() {

    }
//Inicialización de variables

    @PostConstruct
    public void inicializar() {
        materia = new Materia();
        malla = new Malla();
        idMalla = 0;
        tpSemestres = Tablas.retornarSemestres();

    }

    /**
     * Método que permite crear un registro en la base de datos, si todo sale
     * bien cierra el dialogo para crear el registro, caso contrario no se
     * cierra el dialogo
     */
    public void crear() {
        try {

            idMalla = malla.getIdMalla();
            materia.setIdMalla(servMalla.encontrarMallaID(idMalla));
            serv_materia.insertarMateria(materia);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoMateriaAgregar').hide();");//Cierra el dialogo
            MensajesFaces.informacion("Guardado", "Exitoso");
            materia = new Materia();
            req = null;

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

            idMalla = malla.getIdMalla();
            materia.setIdMalla(servMalla.encontrarMallaID(idMalla));
            serv_materia.actualizarMateria(materia);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoActualizarDatos').hide();");//Cierra el dialogo
            materia = new Materia();
            req = null;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    /**
     * Elimina un registro de la base de datos
     *
     * @param ma
     */
    public void eliminar(Materia ma) {
        try {
            boolean verificarMateriaEnCarga = servCarga.buscarRegistroPorMateria(ma);
            if (verificarMateriaEnCarga) {
                MensajesFaces.informacion("No se puede eliminar", "Existen Datos Relacionados");
            } else {
                serv_materia.eliminarMateria(ma);
                materia = new Materia();
                MensajesFaces.informacion("Eliminado", "Exitoso");
            }
        } catch (Exception e) {
            MensajesFaces.advertencia("Error al eliminar", "detalle" + e);
        }

    }

    public void abrirDialogo() {

        if (malla.getIdMalla() == null) {
            MensajesFaces.informacion("Seleccione una Malla", "Primero");
        } else {
            req = RequestContext.getCurrentInstance();
            req.execute("PF('dialogoMateriaAgregar').show();");
            req = null;
        }

    }

    public void cerrar() {
        materia = new Materia();
    }

    /**
     * Método que permite buscar una malla por su id
     *
     * @param id
     */
    public void encontrarMallaID(int id) {
        malla = servMalla.encontrarMallaID(id);
        if (malla != null) {
            idMalla = malla.getIdMalla();
            //nuevaCedula = numce;
            MensajesFaces.informacion("Malla", "Encontrada");
        } else {
            //numeroCedula = Constantes.VACIO;
            malla = new Malla();
            MensajesFaces.informacion("Malla", "No encontrada");
        }
    }

    /**
     * Método que permite buscar una matería por su id
     *
     * @param id
     */
    public void encntrarMateriaID(Object id) {

        materia = serv_materia.encontrarMateriaID(id);
        if (materia != null) {

            MensajesFaces.informacion("Materia", "Encontrada");
        } else {
            materia = new Materia();
            MensajesFaces.informacion("Materia", "No encontrada");
        }

    }
    //listas

    /**
     * Metodo que recupera todas las materias
     *
     * @return serv_materia.listarMateria()
     */
    public List<Materia> recuperarTodos() {
        return serv_materia.listarMateria();
    }

    /**
     * Metodo que recupera todas las mallas
     *
     * @param au
     * @return servMalla.listarMalla()
     */
    public List<Malla> listarMallasVigentes(Authorities au) {
        /**
         * if el usuario tiene rol ROLE_SUPERADMINISTRADOR retotna todas las
         * mallas, caso contrario se retorna solo las mallas vigentes
         */
        if (au.getAuthority().equalsIgnoreCase("ROLE_SUPERADMINISTRADOR")) {
            return servMalla.listarMalla();
        } else {
            return servMalla.ListaMallaVigentes();
        }
    }

    /**
     * Metodo qeu busca todas las meterias por el id de una malla
     *
     * @param id
     * @return serv_materia.listarMateriaporMalla(id)
     */
    public List<Materia> ListarMateriaporMalla(int id) {
        return serv_materia.listarMateriaporMalla(id);
    }
//GET Y SET

    /**
     * Devuelve una los semestres de una materia
     *
     * @return tpSemestres
     */
    public Map<String, String> getTpSemestres() {
        return tpSemestres;
    }

    /**
     * Modifica una los semestres de una materia
     *
     * @param tpSemestres
     */
    public void setTpSemestres(Map<String, String> tpSemestres) {
        this.tpSemestres = tpSemestres;
    }

    /**
     * Devuelve id de la malla
     *
     * @return idMalla
     */
    public int getIdMalla() {
        return idMalla;
    }

    /**
     * Modifica id de la malla
     *
     * @param idMalla
     */
    public void setIdMalla(int idMalla) {
        this.idMalla = idMalla;
    }

    /**
     * Devuelve un objeto de tipo malla
     *
     * @return malla
     */
    public Malla getMalla() {
        return malla;
    }

    /**
     * Modifica un objeto de tipo malla
     *
     * @param malla
     */
    public void setMalla(Malla malla) {
        this.malla = malla;
    }

    /**
     * Devuelve un objeto de tipo matería
     *
     * @return materia
     */
    public Materia getMateria() {
        return materia;
    }

    /**
     * Modifica un objeto de tipo matería
     *
     * @param materia
     */
    public void setMateria(Materia materia) {
        this.materia = materia;
    }

}
