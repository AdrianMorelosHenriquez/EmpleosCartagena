package com.empleoscartagena.www.controller;

import com.empleoscartagena.www.controller.util.JsfUtil;
import com.empleoscartagena.www.entities.DatosPersonales;
import com.empleoscartagena.www.entities.Empresas;
import com.empleoscartagena.www.session.EmpresasFacade;
import java.awt.event.ActionEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "empresasController")
@RequestScoped
public class EmpresasController implements Serializable {

    private Empresas selected = new Empresas();
    private Empresas newEmpresas = new Empresas();
    private List<Empresas> empresas;
    //private DataModel items = null;
    @EJB
    private EmpresasFacade ejbFacade;
    private int pageSize = 8;
    @NotNull(message = "el Nit es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String nit;
    @NotNull(message = "el Nombre es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String nombre;
    @NotNull(message = "la actividad es obligatoria")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String actividad;
    @NotNull(message = "el nombre de contacto es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String nombreContacto;
    @NotNull(message = "el representante legal es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String repLegal;
    @NotNull(message = "el celular de contacto es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String celContacto;
    @NotNull(message = "el telefono de contacto es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String telContacto;
    @NotNull(message = "la direccion de la empresa es obligatoria")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String direccion;
    @NotNull(message = "el email es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String email;
    @NotNull(message = "la descripcion es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String descripcion;

    public EmpresasController() {
    }

    @PostConstruct
    public void init() {
        empresas = ejbFacade.findAll();
    }

    public Empresas getSelected() {
        if (selected == null) {
            selected = new Empresas();
            //selectedItemIndex = -1;
        }
        return selected;
    }

    public void setSelected(Empresas selected) {
        this.selected = selected;
    }

    public Empresas getNewEmpresas() {
        if (newEmpresas == null) {

            newEmpresas = new Empresas();
            //selectedItemIndex = -1;
        }
        return newEmpresas;
    }

    public void setNewEmpresas(Empresas newEmpresas) {
        this.newEmpresas = newEmpresas;
    }

    private EmpresasFacade getFacade() {
        return ejbFacade;
    }

    public String verificarNit() {
        empresas = ejbFacade.findAll();
        for (Empresas emp : empresas) {
            if (emp.getNit().equals(this.nit)) {
                return "Nit en uso";
            }
        }
        return "el Nit no esta uso";
    }

    public void doCreate(ActionEvent actionEvent) {
        String nombre_empresa = nombre;

        try {
            newEmpresas = new Empresas(nit, nombre, actividad, nombreContacto, celContacto, telContacto, direccion, email, descripcion, repLegal);
            ejbFacade.create(newEmpresas);
            empresas.add(newEmpresas);
            newEmpresas = null;
            nit = null;
            nombre = null;
            actividad = null;
            nombreContacto = null;
            celContacto = null;
            telContacto = null;
            direccion = null;
            email = null;
            descripcion = null;
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "La Empresa " + nombre_empresa + " ha sido creada", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            //RequestContext.getCurrentInstance().reset("createProfesionForm:createProfesionPanel");  
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La profesion " + nombre_empresa + " pudo no haberse creado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String doDelete(ActionEvent e) {
        Empresas tmp = ejbFacade.find(selected.getNit());
        try {
            if (tmp != null) {
                ejbFacade.remove(tmp);
                empresas.remove(tmp);
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La Empresa " + selected.getNombre() + " ha sido borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La Empresa " + selected.getNombre() + " no se ha borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La Empresa " + selected.getNombre() + " podría no haberse borrado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String doUpdate(ActionEvent actionEvent) {
        try {
            Empresas tmpP = ejbFacade.find(selected.getNit());
            if (tmpP != null) {
                selected.setNombre(nombre);
                selected.setActividad(actividad);
                selected.setNombreContacto(nombreContacto);
                selected.setCelularContacto(celContacto);
                selected.setTelefonoContacto(telContacto);
                selected.setRepresentanteLegal(repLegal);
                selected.setEmail(email);
                selected.setDescripcion(descripcion);
                selected.setDireccion(direccion);
                ejbFacade.edit(selected);
                nombre = null;
                actividad = null;
                nombreContacto = null;
                celContacto = null;
                telContacto = null;
                direccion = null;
                email = null;
                descripcion = null;
                repLegal = null;

            }
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "La Empresa " + selected.getNombre() + " ha sido salvada", null);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La Empresa " + selected.getNombre() + " pudo no haberse salvado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        return null;
    }

    public String goEditPage(ActionEvent actionEvent) {
        nit = selected.getNit();
        nombre = selected.getNombre();
        actividad = selected.getActividad();
        descripcion = selected.getDescripcion();
        direccion = selected.getDireccion();
        repLegal = selected.getRepresentanteLegal();
        nombreContacto = selected.getNombreContacto();
        telContacto = selected.getTelefonoContacto();
        celContacto = selected.getCelularContacto();
        email = selected.getEmail();

        return "empresaEdit";
    }

    public List<Empresas> getEmpresas() {
        empresas = new ArrayList<Empresas>();
        empresas = ejbFacade.findAll();
        return empresas;
    }

    public String consult() {
        return "El nit ya ha sido registrado";
    }

    public String getconsult() {
       
        try {
           String Nit = this.nit; 
           Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("nit",Nit);
            Empresas emp = new Empresas();
            emp = getFacade().findOneResult(this.selected.FIND_USER_BY_NIT, parameters);
            if (emp != null) {
                JsfUtil.addSuccessMessage("el Nit ya ha sido registrado.");
                return "El Nit ya ha sido registrado.";
            } else {
                return "El Nit "+ Nit +" se puede usar" ;
            }

        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/resources/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getRepLegal() {
        return repLegal;
    }

    public void setRepLegal(String repLegal) {
        this.repLegal = repLegal;
    }

    public String getCelContacto() {
        return celContacto;
    }

    public void setCelContacto(String celContacto) {
        this.celContacto = celContacto;
    }

    public String getTelContacto() {
        return telContacto;
    }

    public void setTelContacto(String telContacto) {
        this.telContacto = telContacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String desripcion) {
        this.descripcion = desripcion;
    }

    public String toCreate() {
        return "empresaCreate";
    }

    public String toList() {
        return "empresaList";
    }
}