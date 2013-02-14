package com.empleoscartagena.www.controller;



import com.empleoscartagena.www.entities.Area;
import com.empleoscartagena.www.entities.Capacidad;
import com.empleoscartagena.www.session.AreaFacade;
import com.empleoscartagena.www.session.CapacidadFacade;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean(name = "controlCapacidad")
@SessionScoped
public class CapacidadController implements Serializable{
    private static final long serialVersionUID = -9102592194300556931L;
    private boolean isEditAction = true;
    private String[] mensajesServicio = {"SERVICIO PARA CREAR NUEVA CAPACIDAD LABORAL",
    "SERVICIO PARA ACTUALIZAR DATOS DE CAPACIDADES LABORALES"};
    private int pageSize = 8;
    @NotNull(message = "Toda competencia laboral debe estar ligada a una área de desempeño")
    private Integer idAreaSelected; //Guarda el id de la profesión a la que pertenece el área de desempeño
   
    private List<Area> areas; //Almacena la lista de profesiones leída desde la BD
    private Map<Integer, Area> hashMapAreas = new HashMap<Integer, Area>();
    private Capacidad selected = new Capacidad(); //Almacena la profesión seleccionada en la taba
    private Capacidad newCapacidad = new Capacidad(); //Objeto que se utiliza en la creación de una nueva profesión
    private List<Capacidad> capacidades; //Almacena las capacidades laborales leídas de la 
    private HashMap<Integer, Capacidad> hashMapCapacidad = new HashMap<Integer, Capacidad>();
    
    //Los siguientes atributos se definen para usarla en la edición de un área
    @NotNull(message = "El código de la competencia laboral es obligatorio")
    private Integer idcapacidad;
    @NotNull(message = "El nombre de la competencia laboral es obligatorio")
    @Size(min = 5, max = 45, message = "El nombre de la competencia debe tener entre 5 y 45 caracteres")
    private String nombre;
    @EJB
    private CapacidadFacade ejbCFacade;
    @EJB
    private AreaFacade ejbAFacade;
    
    public CapacidadController(){}
    
    @PostConstruct
    public void init() {
        capacidades = ejbCFacade.findAll();
        areas = ejbAFacade.findAll();
        for(Capacidad capa : capacidades){
            hashMapCapacidad.put(capa.getIdcapacidad(), capa);
        }
        for(Area area : areas) {
            hashMapAreas.put(area.getIdarea(), area);
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

    public Integer getIdAreaSelected() {
        return idAreaSelected;
    }

    public void setIdAreaSelected(Integer idAreaSelected) {
        this.idAreaSelected = idAreaSelected;
    }

    public List<Area> getAreas() {
         areas = ejbAFacade.findAll();
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public Capacidad getSelected() {
        return selected;
    }

    public void setSelected(Capacidad selected) {
        this.selected = selected;
    }

    public Capacidad getNewCapacidad() {
        if(newCapacidad == null) newCapacidad = new Capacidad();
        return newCapacidad;
    }

    public void setNewCapacidad(Capacidad newCapacidad) {
        this.newCapacidad = newCapacidad;
    }

    public List<Capacidad> getCapacidades() {
        return capacidades;
    }

    public void setCapacidades(List<Capacidad> capacidades) {
        this.capacidades = capacidades;
    }

    public Integer getIdcapacidad() {
        return idcapacidad;
    }

    public void setIdcapacidad(Integer idcapacidad) {
        this.idcapacidad = idcapacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
            Capacidad tmpC = hashMapCapacidad.get(this.idcapacidad); //ejbCFacade.find(idcapacidad.intValue());
            if (tmpC != null) {
                //Area tmpA = ejbAFacade.find(this.idAreaSelected);
                Area tmpA = hashMapAreas.get(this.idAreaSelected);
                if (tmpA != null) {
                    selected.setNombre(nombre);
                    selected.setArea(tmpA);
                    ejbCFacade.edit(selected);
                    hashMapCapacidad.put(this.idcapacidad, selected);
                    idcapacidad = null;
                    nombre = null;
                    idAreaSelected = null;
                    for (Capacidad capa : capacidades) {
                        if (capa.getIdcapacidad().intValue() == selected.getIdcapacidad().intValue()) {
                            capa.setNombre(selected.getNombre());
                            capa.setArea(tmpA);
                            break;
                        }
                    }
                    FacesMessage message =
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacidad " + selected.getNombre() + " ha sido salvada", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            }
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Capacidad " + selected.getNombre() + " pudo no haberse salvado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String doDelete(ActionEvent e) {
        Capacidad tmp = ejbCFacade.find(selected.getIdcapacidad().intValue());
        try {
            if (tmp != null) {
                ejbCFacade.remove(tmp);
                capacidades.remove(tmp);
                hashMapCapacidad.remove(selected.getIdcapacidad());
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacidad " + selected.getNombre() + " ha sido borrada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacidad " + selected.getNombre() + " no se ha borrado", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } catch (Exception ex) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Capacidad " + selected.getNombre() + " podría no haberse borrado", null);
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
            Area tmp = ejbAFacade.find(this.idAreaSelected);
            if (tmp != null) {
                newCapacidad = new Capacidad(this.idcapacidad, this.nombre);
                newCapacidad.setArea(tmp);
                ejbCFacade.create(newCapacidad);
                capacidades.add(newCapacidad);
                hashMapCapacidad.put(newCapacidad.getIdcapacidad(), newCapacidad);
                nombre = null;
                idcapacidad = null;
                idAreaSelected = null;
                FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Capacidad " + nombre_area + " ha sido creada", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                //RequestContext.getCurrentInstance().reset("createProfesionForm:createProfesionPanel");  
            }

        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Capacidad " + nombre_area + " pudo no haberse creado", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String goEditPage(ActionEvent actionEvent) {
        this.idcapacidad = selected.getIdcapacidad();
        this.nombre = selected.getNombre();
        this.isEditAction = true;
        return "edit";
    }

    public String goCreatePage(ActionEvent actionEvent) {
        this.isEditAction = false;
        return "edit";
    }
}
