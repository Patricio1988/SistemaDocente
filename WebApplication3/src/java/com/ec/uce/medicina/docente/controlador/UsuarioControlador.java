/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.controlador;


import com.ec.uce.medicina.docente.modelo.Users;
import com.ec.uce.medicina.docente.util.Constantes;
import com.ec.uce.medicina.docente.util.Encriptacion;
import com.ec.uce.medicina.docente.util.MensajesFaces;
import com.uce.uce.medicina.docente.servicio.CarreraServicio;
import com.uce.uce.medicina.docente.servicio.FacultadServicio;
import com.uce.uce.medicina.docente.servicio.AuthoritiesServicio;
import com.uce.uce.medicina.docente.servicio.UsersServicio;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Patricio
 */
@ViewScoped
@ManagedBean
public class UsuarioControlador {

    private List<SelectItem> itemsFacultad;
    private List<SelectItem> itemsCarrera;
    private List<SelectItem> itemsPerfil;
    private int idFacultad;
    private int idCarrera;
    private int idPerfil;
    private Users usuario;
    private List<Users> listUsuarios;
    private String claveActual;
    private String contrasenaActualizar;

    @EJB
    private UsersServicio serUsuario;
    @EJB
    private CarreraServicio servaCarrera;
    @EJB
    private FacultadServicio servFacultad;
    @EJB
    private AuthoritiesServicio servPefil;

    private String estadoComboBox;

    private RequestContext req;

    public UsuarioControlador() {
    }

    @PostConstruct
    public void inicializar() {

        this.estadoComboBox = Constantes.INACTIVO;
       
        usuario = new Users();
        itemsFacultad = servFacultad.oneMenuFacultad(servFacultad.listarFacultad());
        itemsCarrera = servaCarrera.oneMenuCarrera(servaCarrera.listarCarrera());
        claveActual=Constantes.VACIO;
 
    
    }

    public void getCarreras(AjaxBehaviorEvent ent) {
        this.estadoComboBox = Constantes.ACTIVO;
        this.itemsCarrera = servaCarrera.oneMenuCarrera(this.servaCarrera.buscarCarreraPorFacultad(idFacultad));

    }

    public void crear() {
        try {
            usuario.setIdCarrera(servaCarrera.encontrarCarrera(idCarrera));
            usuario.setPassword(Encriptacion.encrypt(usuario.getPassword()));

            serUsuario.insertarUsers(usuario);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('DialogoAgregarUsuario').hide();");
            req = null;
            usuario = new Users();
            idCarrera = 0;
            idFacultad = 0;
            idPerfil = 0;
            MensajesFaces.informacion("Guardado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        };
    }

    public void actualizar() throws Exception {
 BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            usuario.setIdCarrera(servaCarrera.encontrarCarrera(idCarrera));
            String uno=usuario.getPassword();            
            if(!contrasenaActualizar.equals(usuario.getPassword())){
            usuario.setPassword(Encriptacion.encrypt(usuario.getPassword()));
            }
           
            serUsuario.actualizarUsers(usuario);
            req = RequestContext.getCurrentInstance();
            req.execute("PF('usuarioItemDialog').hide();");
            req = null;
            usuario = new Users();

            idFacultad = 0;
            idCarrera = 0;
            idPerfil = 0;
            MensajesFaces.informacion("Actualizado", "Exitoso");

        } catch (Exception e) {
            MensajesFaces.error("Error", "detalle" + e);
        }

    }

    
    public void cerrar() {
        usuario = new Users();
        idCarrera = 0;
        idFacultad = 0;
        idPerfil = 0;
    }
    
    public void cambioContrasena(Users u) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        try {
            if(encoder.matches(claveActual, u.getPassword()))
            {                 
                u.setPassword(Encriptacion.encrypt(usuario.getPassword()));
               
                serUsuario.actualizarUsers(u);
                usuario=new Users();
                claveActual=Constantes.VACIO;
                MensajesFaces.informacion("Actualización de clave", " exitosa");
                
            }
            else{
                MensajesFaces.informacion("La clave atual", "No es la correcta");
            }
        } catch (Exception ex) {
            MensajesFaces.error("Error", "detalle" + ex);
            
        }
    }
    

    public List<Users> recuperarTodos() {
        listUsuarios = serUsuario.listarUsers();

        return listUsuarios;
    }

    public List<SelectItem> getItemsPerfil() {
        return itemsPerfil;
    }

    public void setItemsPerfil(List<SelectItem> itemsPerfil) {
        this.itemsPerfil = itemsPerfil;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

   
    public void encontrarUsuario(String id) {

        usuario = serUsuario.encontrarUsuarioporCI(id);
        if (usuario != null) {
            try {
                idCarrera = usuario.getIdCarrera().getIdCarrera();
                idFacultad = usuario.getIdCarrera().getIdFacultad().getIdFacultad();
                contrasenaActualizar=usuario.getPassword();
            } catch (Exception ex) {
                MensajesFaces.error("Error", ex.getMessage());

            }

            MensajesFaces.informacion("Usuario", "Encontrado");
        } else {
            usuario = new Users();
            MensajesFaces.informacion("Usuario", "No encontrado");
        }

    }

    public List<Users> getListUsuarios() {
        return listUsuarios;
    }

    public void setListUsuarios(List<Users> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public List<SelectItem> getItemsFacultad() {
        return itemsFacultad;
    }

    public void setItemsFacultad(List<SelectItem> itemsFacultad) {
        this.itemsFacultad = itemsFacultad;
    }

    public List<SelectItem> getItemsCarrera() {
        return itemsCarrera;
    }

    public void setItemsCarrera(List<SelectItem> itemsCarrera) {
        this.itemsCarrera = itemsCarrera;
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }

    public int getIdFacultad() {
        return idFacultad;
    }

    public void setIdFacultad(int idFacultad) {
        this.idFacultad = idFacultad;
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public CarreraServicio getServaCarrera() {
        return servaCarrera;
    }

    public void setServaCarrera(CarreraServicio servaCarrera) {
        this.servaCarrera = servaCarrera;
    }

    public FacultadServicio getServFacultad() {
        return servFacultad;
    }

    public void setServFacultad(FacultadServicio servFacultad) {
        this.servFacultad = servFacultad;
    }

    public String getEstadoComboBox() {
        return estadoComboBox;
    }

    public void setEstadoComboBox(String estadoComboBox) {
        this.estadoComboBox = estadoComboBox;
    }

    public String getClaveActual() {
        return claveActual;
    }

    public void setClaveActual(String claveActual) {
        this.claveActual = claveActual;
    }

    public String getContrasenaActualizar() {
        return contrasenaActualizar;
    }

    public void setContrasenaActualizar(String contrasenaActualizar) {
        this.contrasenaActualizar = contrasenaActualizar;
    }

    
    

}
