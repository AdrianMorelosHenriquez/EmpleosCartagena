package com.empleoscartagena.www.controller;

import com.empleoscartagena.www.entities.Capacidad;
import com.empleoscartagena.www.entities.Empresas;
import com.empleoscartagena.www.entities.Ofertas;
import com.empleoscartagena.www.session.CapacidadFacade;
import com.empleoscartagena.www.session.EmpresasFacade;
import com.empleoscartagena.www.session.OfertasFacade;
import java.awt.event.ActionEvent;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "ofertasController")
@SessionScoped
public class OfertasController implements Serializable {
    
    private Ofertas selected = new Ofertas();
    private Ofertas newOfertas = new Ofertas();
    private int pageSize = 8;
    @EJB
    private OfertasFacade ejbFacade;
    @EJB
    private EmpresasFacade ejbEFacade;
    @EJB
    private CapacidadFacade ejbCOFacade;
    //@EJB
    //private CapacidadesPorOfertasFacade ejbFacadeCap;
    private List<Ofertas> ofertas;
    private List<Empresas> empresas;
    //private List<CapacidadesPorOfertas> capsDeOf;
    @NotNull(message = "Debe colocar cuantos años de experiencia requiere la oferta")
    private int expYears;
    @NotNull(message = "Debe colocar la fecha inicial de la oferta")
    private Date fechaStart;
    @NotNull(message = "Debe colocar cuando finaliza la oferta")
    private Date fechaEnd;
    @NotNull(message = "Debe colocar el cargo a ocupar")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String cargo;
    @NotNull(message = "Debe colocar el salario")    
    private long salario;
    @NotNull(message = "Debe colocar el salario")    
    @Size(min = 5, max = 270, message = "Debe entrar un nombre entre 5 y 270 caracteres")
    private String descripcion;
    @NotNull(message = "Debe colocar los estudios minimos")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String nivelEstudiosMin;
    @NotNull(message = "Código de los estudios maximos")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    private String nivelEstudiosMax;
    @NotNull(message = "Debe colocar la empresa que realiza la oferta")
    private String nit;
    private List<Capacidad> capacidadesOf;
    private List<Capacidad> selectedCap;
    
    public OfertasController() {
    }
    
    @PostConstruct
    public void init() {
        ofertas = ejbFacade.findAll();
    }
    
    public Ofertas getSelected() {
        if (selected == null) {
            selected = new Ofertas();
        }
        return selected;
    }
    
    public void setSelected(Ofertas selected) {
        this.selected = selected;
    }
    
    public Ofertas getNewOfertas() {
        if (newOfertas == null) {
            newOfertas = new Ofertas();
        }
        return newOfertas;
    }
    
    public void setNewOfertas(Ofertas newOfertas) {
        this.newOfertas = newOfertas;
    }
    
    private OfertasFacade getFacade() {
        return ejbFacade;
    }
    
    public int getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    public List<Capacidad> getSelectedCapOf() {
        return selectedCap;
    }
    
    public void setSelectedCapOf(List<Capacidad> selectedCap) {
        this.selectedCap = selectedCap;
    }


    /*public List<CapacidadesPorOfertas> getCapacidades() {
     List<CapacidadesPorOfertas> capacidadesOf = ejbFacadeCap.findAll();
     return capacidadesOf;
     }
     */
    public List<Ofertas> getOfertas() {
        ofertas = ejbFacade.findAll();
        return ofertas;
    }
    
    public List<Empresas> getEmpresas() {
        empresas = ejbEFacade.findAll();
        return empresas;
    }
    
    public List<Capacidad> getCapacidades() {
        capacidadesOf = ejbCOFacade.findAll();
        return capacidadesOf;
    }
    
    public void doCreate(ActionEvent actionEvent) {
        String nombre_oferta = cargo;
        
        
        try {            
            Empresas tmp = ejbEFacade.find(this.nit);
            if (tmp != null) {
                newOfertas = new Ofertas();
                newOfertas.setFechaStart(fechaStart);
                newOfertas.setFechaEnd(fechaEnd);
                newOfertas.setCargo(cargo);
                newOfertas.setDescripcion(descripcion);
                newOfertas.setSalario(salario);
                newOfertas.setNivelEstudiosMax(nivelEstudiosMax);
                newOfertas.setNivelEstudiosMin(nivelEstudiosMax);
                newOfertas.setNit(tmp);
                newOfertas.setAniosExperiencia(expYears);
                ejbFacade.create(newOfertas);
                
                
                ofertas.add(newOfertas);
                newOfertas = null;
                fechaStart = null;
                fechaEnd = null;
                cargo = null;
                descripcion = null;
                salario = 0;
                nivelEstudiosMax = null;
                nivelEstudiosMin = null;
                nit = null;
                expYears = 0;
                
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La oferta " + nombre_oferta + " ha sido creada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                //RequestContext.getCurrentInstance().reset("createProfesionForm:createProfesionPanel");  
            }
            
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oferta " + nombre_oferta + " pudo no haberse creado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public String doDelete(ActionEvent e) {
        Ofertas tmp = ejbFacade.find(selected.getIdofertas().intValue());
        try {
            if (tmp != null) {
                ejbFacade.remove(tmp);
                ofertas.remove(tmp);
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " ha sido borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " no se ha borrado", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " podría no haberse borrado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }
    
    public String doUpdate(ActionEvent actionEvent) {
        try {
            Ofertas tmpP = ejbFacade.find(selected.getIdofertas().intValue());
            if (tmpP != null) {
                Empresas tmpE = ejbEFacade.find(this.nit);
                if (tmpE != null) {
                    selected.setFechaStart(fechaStart);
                    selected.setFechaEnd(fechaEnd);
                    selected.setCargo(cargo);
                    selected.setNivelEstudiosMax(nivelEstudiosMax);
                    selected.setDescripcion(descripcion);
                    selected.setNivelEstudiosMin(nivelEstudiosMin);
                    selected.setSalario(salario);
                    selected.setAniosExperiencia(expYears);
                    selected.setNit(tmpE);
                    ejbFacade.edit(selected);
                    fechaStart = null;
                    fechaEnd = null;
                    cargo = null;
                    nivelEstudiosMax = null;
                    nivelEstudiosMin = null;
                    descripcion = null;
                    salario = 0;
                    nit = null;
                    expYears = 0;
                    /*for (Ofertas off : ofertas) {
                     if (off.getIdofertas().intValue() == selected.getId().intValue()) {
                     prof.setCategoria(tmpC);
                     prof.setNombre(selected.getNombre());
                     break;
                     }
                     }*/
                    FacesMessage message =
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " ha sido actualizada", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    
                }
            }
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " pudo no haberse actualizado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            
        }
        return null;
    }
    
    public String goEditPage(ActionEvent actionEvent) {
        fechaStart = selected.getFechaStart();
        fechaEnd = selected.getFechaEnd();
        cargo = selected.getCargo();
        salario = selected.getSalario();
        descripcion = selected.getDescripcion();
        nivelEstudiosMin = selected.getNivelEstudiosMin();
        nivelEstudiosMax = selected.getNivelEstudiosMax();
        nit = selected.getNit().getNit();
        expYears = selected.getAniosExperiencia();
        
        return "ofertasEdit";
    }
    
    public int getExpYears() {
        return expYears;
    }
    
    public void setExpYears(int expYears) {
        this.expYears = expYears;
    }
    
    public Date getFechaStart() {
        return fechaStart;
    }
    
    public void setFechaStart(Date fechaStart) {
        this.fechaStart = fechaStart;
    }
    
    public Date getFechaEnd() {
        return fechaEnd;
    }
    
    public void setFechaEnd(Date fechaEnd) {
        this.fechaEnd = fechaEnd;
    }
    
    public String getCargo() {
        return cargo;
    }
    
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    public long getSalario() {
        return salario;
    }
    
    public void setSalario(long salario) {
        this.salario = salario;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNivelEstudiosMin() {
        return nivelEstudiosMin;
    }
    
    public void setNivelEstudiosMin(String nivelEstudiosMin) {
        this.nivelEstudiosMin = nivelEstudiosMin;
    }
    
    public String getNivelEstudiosMax() {
        return nivelEstudiosMax;
    }
    
    public void setNivelEstudiosMax(String nivelEstudiosMax) {
        this.nivelEstudiosMax = nivelEstudiosMax;
    }
    
    public String getNit() {
        return nit;
    }
    
    public void setNit(String nit) {
        this.nit = nit;
    }
}
