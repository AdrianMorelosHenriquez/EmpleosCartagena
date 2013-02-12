package com.empleoscartagena.www.controller;


import com.empleoscartagena.www.entities.Area;
import com.empleoscartagena.www.entities.Profesion;
import com.empleoscartagena.www.session.AreaFacade;
import com.empleoscartagena.www.session.ProfesionFacade;
import java.awt.event.ActionEvent;
import java.io.Serializable;
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

@ManagedBean(name = "controlArea")
@SessionScoped
public class AreaController implements Serializable{
    private static final long serialVersionUID = -9102592194300556931L;
    private boolean isEditAction = true;
    private String[] mensajesServicio = {"SERVICIO PARA CREAR NUEVA ÁREA DE DESEMPEÑO PROFESIONAL",
    "SERVICIO PARA EDITAR AREA DE DESEMPEÑO PROFESIONAL"};
    private int pageSize = 8;
    @NotNull(message = "Toda área de desempeño debe estar ligada a una profesión")
    private Integer idProfesionSelected; //Guarda el id de la profesión a la que pertenece el área de desempeño
    private List<Area> areas; //Almacena las áreas de desempeño laboral leída desde la BD
    private HashMap<Integer, Area> hashMapArea = new HashMap<Integer, Area>();
    private Area selected = new Area(); //Almacena la profesión seleccionada en la taba
    private Area newArea = new Area(); //Objeto que se utiliza en la creación de una nueva profesión
    private List<Profesion> profesiones; //Almacena las profesiones leídas de la BD
    private HashMap<Integer, Profesion> hashMapProf = new HashMap<Integer, Profesion>();
    
    //Los siguientes atributos se definen para usarla en la edición de un área
    @NotNull(message = "El código del área de desempeño es obligatorio")
    private Integer idarea;
    @NotNull(message = "El nombre del área de desempeño es obligatorio")
    @Size(min = 5, max = 45, message = "Debe entrar un nombre de 5 a 45 caracteres")
    @Pattern(regexp="[\\w\\s]+", message = "Nombre dado al área de desempeño debe ser con palabras bien formadas")
    private String nombre;
    @NotNull(message = "El nombre de la categoría del área de desmpeño es obligatorio")
    @Size(min = 5, max = 45, message = "Para la categoría debe entrar un nombre de 5 a 45 caracteres")
    @Pattern(regexp="[\\w\\s]+", message = "Nombre dado a la categoría del área de desempeño debe ser con palabras bien formadas")
    private String categoria;
    
    
    @EJB
    private ProfesionFacade ejbPFacade;
    @EJB
    private AreaFacade ejbAFacade;
    
    public AreaController(){}
    
    @PostConstruct
    public void init() {
        profesiones = ejbPFacade.findAll();
        areas = ejbAFacade.findAll();
        for(Profesion prof : profesiones){
            hashMapProf.put(prof.getId(), prof);
        }
        for(Area area : areas) {
            hashMapArea.put(area.getIdarea(), area);
        }
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

    public Integer getIdProfesionSelected() {
        return idProfesionSelected;
    }

    public void setIdProfesionSelected(Integer idProfesionSelected) {
        this.idProfesionSelected = idProfesionSelected;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Area getSelected() {
        return selected;
    }

    public void setSelected(Area selected) {
        this.selected = selected;
    }

    public Area getNewArea() {
        if(newArea==null) newArea = new Area();
        return newArea;
    }

    public void setNewArea(Area newArea) {
        this.newArea = newArea;
    }

    public List<Profesion> getProfesiones() {
        return profesiones;
    }

    public void setProfesiones(List<Profesion> profesiones) {
        this.profesiones = profesiones;
    }

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
            //Area tmpA = ejbAFacade.find(idarea.intValue());
            Area tmpA = hashMapArea.get(idarea);
            if (tmpA != null) {
                //Profesion tmpP = ejbPFacade.find(this.idProfesionSelected);
                Profesion tmpP = hashMapProf.get(this.idProfesionSelected);
                if (tmpP != null) {
                    selected.setNombre(nombre);
                    selected.setCategoria(categoria);
                    selected.setProfesion(tmpP);
                    ejbAFacade.edit(selected);
                    hashMapArea.put(idarea, selected);
                    nombre = null;
                    categoria = null;
                    idProfesionSelected = null;
                    for (Area area : areas) {
                        if (area.getIdarea().intValue() == selected.getIdarea().intValue()) {
                            area.setProfesion(tmpP);
                            area.setCategoria(selected.getCategoria());
                            area.setNombre(selected.getNombre());
                            break;
                        }
                    }
                    FacesMessage message =
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Área de desempeño " + selected.getNombre() + " ha sido salvada", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Área de desempeño " + selected.getNombre() + " pudo no haberse salvado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String doDelete(ActionEvent e) {
        //Area tmp = ejbAFacade.find(selected.getIdarea().intValue());
        Area tmp = hashMapArea.get(selected.getIdarea());
        try {
            if (tmp != null) {
                ejbAFacade.remove(tmp);
                areas.remove(tmp);
                hashMapArea.remove(selected.getIdarea());
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Área de desempeño " + selected.getNombre() + " ha sido borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Área de desempeño " + selected.getNombre() + " no se ha borrado", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Área de desempeño " + selected.getNombre() + " podría no haberse borrado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String comeBack(ActionEvent actionEvent) {
        return "retornar";
    }
    
    public void doCreate() {
        String nombre_area = this.nombre;
        try {
            //Profesion tmp = ejbPFacade.find(this.idProfesionSelected);
            Profesion tmp = hashMapProf.get(this.idProfesionSelected);
            if (tmp != null) {
                newArea = new Area(this.idarea, this.nombre, this.categoria);
                newArea.setProfesion(tmp);
                ejbAFacade.create(newArea);
                areas.add(newArea);
                hashMapArea.put(idarea, newArea);
                nombre = null;
                idarea = null;
                categoria = null;
                this.idProfesionSelected = null;
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Área de desempeño " + nombre_area + " ha sido creada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                //RequestContext.getCurrentInstance().reset("createProfesionForm:createProfesionPanel");  
            }

        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Área de desempeño " + nombre_area + " pudo no haberse creado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String goEditPage(ActionEvent actionEvent) {
        this.idarea = selected.getIdarea();
        this.nombre = selected.getNombre();
        this.categoria = selected.getCategoria();
        this.isEditAction = true;
        return "editArea";
    }

    public String goCreatePage(ActionEvent actionEvent) {
        this.isEditAction = false;
        return "createArea";
    }
}
