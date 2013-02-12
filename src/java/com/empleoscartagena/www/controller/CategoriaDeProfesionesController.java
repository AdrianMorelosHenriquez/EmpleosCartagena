package com.empleoscartagena.www.controller;

import com.empleoscartagena.www.entities.CategoriaDeProfesiones;
import com.empleoscartagena.www.session.CategoriaDeProfesionesFacade;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean(name = "controlCategoria")
@SessionScoped
public class CategoriaDeProfesionesController implements Serializable {

    private static final long serialVersionUID = -9102592194300556931L;
    private boolean isEditAction = true;
    private String[] mensajesServicio = {"SERVICIO PARA CREAR NUEVAS CATEGORÍAS DE PROFESIONES",
        "SERVICIO PARA EDITAR CATEGORÍA DE PROFESIONES"};
    private int pageSize = 16;
    private List<CategoriaDeProfesiones> categorias;//Almacena todas las entradas recuperadas de la base de datso
    private HashMap<Integer, CategoriaDeProfesiones> hashMapCategorias =
            new HashMap<Integer, CategoriaDeProfesiones>();
    private CategoriaDeProfesiones selected = new CategoriaDeProfesiones(); //La seleccionada en la tabla
    private CategoriaDeProfesiones newCategoria = new CategoriaDeProfesiones(); //Para crear una nueva categoría
    //Enseguida se definen los atributos que se mapean a los campos de la BD
    @NotNull(message = "Código de la categoría es obligatorio")
    private Integer idcategoria;
    @NotNull(message = "Nombre de la categoría es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre entre 5 y 45 caracteres")
    @Pattern(regexp = "[\\w\\s]+", message = "Debe escribir palabras bien formadas")
    private String nombreCategoria;
    @EJB
    private CategoriaDeProfesionesFacade ejbFacade;

    public CategoriaDeProfesionesController() {
    }

    @PostConstruct
    public void init() {
        categorias = ejbFacade.findAll();
        for (CategoriaDeProfesiones categ : categorias) {
            hashMapCategorias.put(categ.getIdcategoria(), categ);
        }
    }

    private CategoriaDeProfesionesFacade getFacade() {
        return ejbFacade;
    }

    public boolean isIsEditAction() {
        return isEditAction;
    }

    public void setIsEditAction(boolean isEditAction) {
        this.isEditAction = isEditAction;
    }

    public String[] getMensajesServicio() {
        return mensajesServicio;
    }

    public void setMensajesServicio(String[] mensajesServicio) {
        this.mensajesServicio = mensajesServicio;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public CategoriaDeProfesiones getSelected() {
        if (selected == null) {
            selected = new CategoriaDeProfesiones();
        }
        return selected;
    }

    public void setSelected(CategoriaDeProfesiones selected) {
        this.selected = selected;
    }

    public List<CategoriaDeProfesiones> getCategorias() {
        categorias = new ArrayList<CategoriaDeProfesiones>();
        categorias = this.ejbFacade.findAll();
        return categorias;
    }

    public void setCategorias(List<CategoriaDeProfesiones> categorias) {
        this.categorias = categorias;
    }

    public CategoriaDeProfesiones getNewCategoria() {
        return newCategoria;
    }

    public void setNewCategoria(CategoriaDeProfesiones newCategoria) {
        this.newCategoria = newCategoria;
    }

    public String handAction() {
        if (isEditAction) {
            doUpdate();
        } else {
            doCreate();
        }
        return null;
    }

    public String doUpdate() {
        try {
            //CategoriaDeProfesiones tmp = ejbFacade.find(this.idcategoria.intValue());
            CategoriaDeProfesiones tmp = hashMapCategorias.get(this.idcategoria);
            if (tmp != null) {
                tmp.setNombreCategoria(nombreCategoria);
                getFacade().edit(tmp);
                hashMapCategorias.put(this.idcategoria, tmp);
                for (CategoriaDeProfesiones categ : categorias) {
                    if(categ.getIdcategoria().intValue() == this.idcategoria.intValue()){
                        categ.setNombreCategoria(nombreCategoria);
                        break;
                    }
                }
            } else {
                System.out.println("No se halló en la tabla");
            }
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "La categoría " + nombreCategoria + " ha sido salvada", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            //result="retornar";
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La categoría " + nombreCategoria + " pudo no haberse salvado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        return null;
    }

    public String doDelete(ActionEvent e) {
        //CategoriaDeProfesiones tmp = ejbFacade.find(selected.getIdcategoria().intValue());
        CategoriaDeProfesiones tmp = hashMapCategorias.get(selected.getIdcategoria());
        try {
            if (tmp != null) {
                ejbFacade.remove(tmp);
                categorias.remove(tmp);
                hashMapCategorias.remove(selected.getIdcategoria());
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La categoría " + selected.getNombreCategoria() + " ha sido borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La categoría " + selected.getNombreCategoria() + " no se ha borrado", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La categoría " + selected.getNombreCategoria() + " podría no haberse borrado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public void doCreate() {
        try {
            newCategoria = new CategoriaDeProfesiones(idcategoria, nombreCategoria);
            ejbFacade.create(newCategoria);
            categorias.add(newCategoria);
            hashMapCategorias.put(idcategoria, newCategoria);
            idcategoria = null;
            nombreCategoria = null;
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "La categoría " + newCategoria.getNombreCategoria() + " ha sido creada", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La categoría " + newCategoria.getNombreCategoria() + " pudo no haberse creado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String comeBack(ActionEvent actionEvent) {
        return "retornar";
    }

    public String goEditPage(ActionEvent actionEvent) {
        this.idcategoria = selected.getIdcategoria();
        this.nombreCategoria = selected.getNombreCategoria();
        this.isEditAction = true;
        return "edit";
    }

    public String goCreatePage(ActionEvent actionEvent) {
        isEditAction = false;
        return "create";
    }
}
