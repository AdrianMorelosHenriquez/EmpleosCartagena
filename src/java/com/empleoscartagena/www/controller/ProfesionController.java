package com.empleoscartagena.www.controller;



import com.empleoscartagena.www.entities.CategoriaDeProfesiones;
import com.empleoscartagena.www.entities.Profesion;
import com.empleoscartagena.www.session.CategoriaDeProfesionesFacade;
import com.empleoscartagena.www.session.ProfesionFacade;
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

@ManagedBean(name = "controlProfesion")
@SessionScoped
public class ProfesionController implements Serializable {

    private static final long serialVersionUID = -9102592194300556931L;
    private boolean isEditAction = false;
    private String[] mensajesServicio = {"SERVICIO PARA CREAR NUEVA PROFESION",
    "SERVICIO PARA EDITAR PROFESION"};
    private int pageSize = 8;
    @NotNull(message = "Un nombre de categoría es obligatorio")
    private Integer idCategoriaSelected; //Guarda el id de la categoría seleccionada en el selectOneMenu
    private List<Profesion> profesiones; //Almacena la lista de profesiones leída desde la BD
    private HashMap<Integer, Profesion> hashMapProf = new HashMap<Integer, Profesion>();
    private Profesion selected = new Profesion(); //Almacena la profesión seleccionada en la taba
    private Profesion newProfesion = new Profesion(); //Objeto que se utiliza en la creación de una nueva profesión
    private List<CategoriaDeProfesiones> categorias; //Almacena las categorías de profesiones leídas de la BD
    private HashMap<Integer, CategoriaDeProfesiones> hashMapCategorias = 
            new HashMap<Integer, CategoriaDeProfesiones>();
    
    //Los atributos de una profesión
    @NotNull(message = "Código de la profesión es obligatorio")
    private Integer id;
    @NotNull(message = "Nombre de la profesión es obligatorio")
    @Size(min=5, max=45, message="Debe entrar un nombre entre 5 y 45 caracteres")
    @Pattern(regexp="[\\w\\s]+", message = "Debe escribir palabras bien formadas")
    private String nombre;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
    @EJB
    private ProfesionFacade ejbPFacade;
    @EJB
    private CategoriaDeProfesionesFacade ejbCFacade;

    public ProfesionController() {
    }

    @PostConstruct
    public void init() {
        categorias = ejbCFacade.findAll();
        profesiones = ejbPFacade.findAll();
        for(Profesion prof : profesiones) {
            hashMapProf.put(prof.getId(), prof);
        }
        for(CategoriaDeProfesiones categ : categorias) {
            hashMapCategorias.put(categ.getIdcategoria(), categ);
        }
    }

    public Integer getIdCategoriaSelected() {
        return idCategoriaSelected;
    }

    public void setIdCategoriaSelected(Integer idCategoriaSelected) {
        this.idCategoriaSelected = idCategoriaSelected;
    }

    public List<CategoriaDeProfesiones> getCategorias() {
        categorias = new ArrayList<CategoriaDeProfesiones>();
        categorias = ejbCFacade.findAll();
        return categorias;
    }

    public void setCategorias(List<CategoriaDeProfesiones> categorias) {
        this.categorias = categorias;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Profesion getSelected() {
        if (selected == null) {
            selected = new Profesion();
        }
        return selected;
    }

    public void setSelected(Profesion selected) {
        this.selected = selected;
    }

    public List<Profesion> getProfesiones() {
        profesiones = new ArrayList<Profesion>();
        profesiones = this.ejbPFacade.findAll();
        return profesiones;
    }

    public void setProfesiones(List<Profesion> profesiones) {
        this.profesiones = profesiones;
    }

    public Profesion getNewProfesion() {
        if (newProfesion == null) {
            newProfesion = new Profesion();
        }
        return newProfesion;
    }

    public void setNewProfesion(Profesion newProfesion) {
        this.newProfesion = newProfesion;
    }
    
    public String handAction(){
        if(isEditAction){
            doUpdate();
        }else{
            doCreate();
        }
        return null;
    }

    public String doUpdate() {
        try {
            //Profesion tmpP = ejbPFacade.find(this.id.intValue());
            Profesion tmpP = hashMapProf.get(this.id);
            if (tmpP != null) {
                //CategoriaDeProfesiones tmpC = ejbCFacade.find(this.idCategoriaSelected);
                CategoriaDeProfesiones tmpC = hashMapCategorias.get(idCategoriaSelected);
                if (tmpC != null) {
                    selected.setNombre(nombre);
                    selected.setCategoria(tmpC);
                    ejbPFacade.edit(selected);
                    hashMapProf.put(id, selected);
                    nombre = null;
                    idCategoriaSelected = null;
                    for (Profesion prof : profesiones) {
                        if (prof.getId().intValue() == selected.getId().intValue()) {
                            prof.setCategoria(tmpC);
                            prof.setNombre(selected.getNombre());
                            break;
                        }
                    }
                    FacesMessage message =
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "La profesión " + selected.getNombre() + " ha sido salvada", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                    
                }
            }
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La profesión " + selected.getNombre() + " pudo no haberse salvado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        return null;
    }

    public String doDelete(ActionEvent e) {
        //Profesion tmp = ejbPFacade.find(selected.getId().intValue());
        Profesion tmp = hashMapProf.get(selected.getId());
        try {
            if (tmp != null) {
                ejbPFacade.remove(tmp);
                profesiones.remove(tmp);
                hashMapProf.remove(selected.getId());
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La profesion " + selected.getNombre() + " ha sido borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La profesion " + selected.getNombre() + " no se ha borrado", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La profesion " + selected.getNombre() + " podría no haberse borrado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String comeBack(ActionEvent actionEvent) {
        return "retornar";
    }

    public void doCreate() {
        String nombre_profesion = nombre;
                
        try {
            //CategoriaDeProfesiones tmp = ejbCFacade.find(this.idCategoriaSelected);
            CategoriaDeProfesiones tmp = hashMapCategorias.get(this.idCategoriaSelected);
            if (tmp != null) {
                newProfesion = new Profesion(id, nombre);
                newProfesion.setCategoria(tmp);
                ejbPFacade.create(newProfesion);
                profesiones.add(newProfesion);
                hashMapProf.put(id, newProfesion);
                newProfesion = null;
                id=null;
                nombre=null;
                idCategoriaSelected = null;
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "La profesion " + nombre_profesion + " ha sido creada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                //RequestContext.getCurrentInstance().reset("createProfesionForm:createProfesionPanel");  
            }

        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La profesion " + nombre_profesion + " pudo no haberse creado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String goEditPage(ActionEvent actionEvent) {
        id = selected.getId();
        nombre = selected.getNombre();
        this.isEditAction = true;
        return "edit";
    }

    public String goCreatePage(ActionEvent actionEvent) {
        isEditAction = false;
        return "edit";
    }
}
