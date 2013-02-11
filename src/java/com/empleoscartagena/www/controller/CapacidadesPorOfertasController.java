package com.empleoscartagena.www.controller;

import com.empleoscartagena.www.entities.Capacidad;
import com.empleoscartagena.www.entities.CapacidadesPorOfertas;
import com.empleoscartagena.www.entities.Ofertas;
import com.empleoscartagena.www.session.CapacidadFacade;
import com.empleoscartagena.www.session.CapacidadesPorOfertasFacade;
import com.empleoscartagena.www.session.OfertasFacade;
import java.awt.event.ActionEvent;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
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

@ManagedBean(name = "capacidadesPorOfertasController")
@SessionScoped
public class CapacidadesPorOfertasController implements Serializable {

    private CapacidadesPorOfertas selected = new CapacidadesPorOfertas();
    private CapacidadesPorOfertas newCapacidades = new CapacidadesPorOfertas();
    @EJB
    private CapacidadesPorOfertasFacade ejbFacade;
    @EJB
    private OfertasFacade ejbOFacade;
    @EJB
    private CapacidadFacade ejbCFacade;
    private int pageSize = 8;
    private Integer idOfertas;
    private Integer idCapacidades;
    private List<Capacidad> capacidades;

    public CapacidadesPorOfertasController() {
    }

    public CapacidadesPorOfertas getSelected() {
        if (selected == null) {
            selected = new CapacidadesPorOfertas();
        }
        return selected;
    }

    public void setSelected(CapacidadesPorOfertas selected) {
        this.selected = selected;
    }

    private CapacidadesPorOfertasFacade getFacade() {
        return ejbFacade;
    }

    public CapacidadesPorOfertas getNewCapacidades() {
        if (newCapacidades == null) {
            newCapacidades = new CapacidadesPorOfertas();
        }
        return newCapacidades;
    }

    public void setNewCapacidades(CapacidadesPorOfertas newCapacidades) {
        this.newCapacidades = newCapacidades;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIdOfertas() {
        return idOfertas;
    }

    public void setIdOfertas(Integer idOfertas) {
        this.idOfertas = idOfertas;
    }

    public Integer getIdCapacidades() {
        return idCapacidades;
    }

    public void setIdCapacidades(Integer idCapacidades) {
        this.idCapacidades = idCapacidades;
    }
    public void setCapacidad(List<Capacidad> capacidades)
    {
     this.capacidades = capacidades;
    }

    public List<Capacidad> getCapacidades() {
        capacidades = ejbCFacade.findAll();
        return capacidades;
    }

    public void doCreate(ActionEvent actionEvent) {
        //Ofertas tmpOf = ejbOFacade.find(this.idOfertas.intValue());
 
        try {
           Ofertas tmpO = ejbOFacade.find(this.idOfertas.intValue());
            
            Capacidad tmpC = ejbCFacade.find(this.idCapacidades.intValue());
            if (tmpO != null && tmpC != null) {
                newCapacidades = new CapacidadesPorOfertas();
                newCapacidades.setOferta(tmpO);
                newCapacidades.setCapacidad(tmpC);
                ejbFacade.create(newCapacidades);
                idOfertas = null;
                idCapacidades = null;
                newCapacidades = null;
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha asignado la capacidad a la oferta", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                //RequestContext.getCurrentInstance().reset("createProfesionForm:createProfesionPanel");  





            }
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se pudo asignar la capacidad a la oferta" + e + " ", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
