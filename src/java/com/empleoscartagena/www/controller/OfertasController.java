package com.empleoscartagena.www.controller;

import com.empleoscartagena.www.entities.Capacidad;
import com.empleoscartagena.www.entities.CapacidadesPorOfertas;
import com.empleoscartagena.www.entities.Empresas;
import com.empleoscartagena.www.entities.Ofertas;
import com.empleoscartagena.www.entities.Profesion;
import com.empleoscartagena.www.entities.ProfesionesPorOferta;
import com.empleoscartagena.www.session.CapacidadFacade;
import com.empleoscartagena.www.session.CapacidadesPorOfertasFacade;
import com.empleoscartagena.www.session.EmpresasFacade;
import com.empleoscartagena.www.session.OfertasFacade;
import com.empleoscartagena.www.session.ProfesionFacade;
import com.empleoscartagena.www.session.ProfesionesPorOfertaFacade;
import java.awt.event.ActionEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean(name = "ofertasController")
@RequestScoped
public class OfertasController implements Serializable {

    private Ofertas selected = new Ofertas();
    private Ofertas newOfertas = new Ofertas();
    private int pageSize = 8;
    @EJB
    private OfertasFacade ejbFacade;
    @EJB
    private EmpresasFacade ejbEFacade;
    @EJB
    private CapacidadFacade ejbCFacade;
    @EJB
    private CapacidadesPorOfertasFacade ejbCOFacade;
    @EJB
    private ProfesionFacade ejbPFacade;
    @EJB
    private ProfesionesPorOfertaFacade ejbPOFacade;
    private List<Ofertas> ofertas;
    private List<Empresas> empresas;
    private Map<Ofertas, String> ofertasAvailable;
    private List<String> estudiosMax;
    private List<String> estudiosMin;
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
    private List<Integer> idCapacidades;
    private Integer idProfesiones;
    

    public OfertasController() {
    }

    @PostConstruct
    public void init() {
        ofertas = ejbFacade.findAll();
        capacidadesOf = ejbCFacade.findAll();

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

    public List<Integer> getIdCapacidades() {
        return idCapacidades;
    }

    public void setIdCapacidades(List<Integer> idCapacidades) {
        this.idCapacidades = idCapacidades;
    }

    public Integer getIdProfesiones() {
        return idProfesiones;
    }

    public void setIdProfesiones(Integer idProfesiones) {
        this.idProfesiones = idProfesiones;
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

    private void setEstudiosMax() {
        estudiosMax = new ArrayList<String>();
        estudiosMax.add("Phd");
        estudiosMax.add("Master");
        estudiosMax.add("Profesional");
        estudiosMax.add("Tecnologo");
        estudiosMax.add("Tecnico");
        estudiosMax.add("Bachiller");
    }

    public List<String> getEstudiosMax() {
        setEstudiosMax();
        return estudiosMax;
    }

    private void setEstudiosMin() {
        estudiosMin = new ArrayList<String>();
        estudiosMin.add("Phd");
        estudiosMin.add("Master");
        estudiosMin.add("Profesional");
        estudiosMin.add("Tecnologo");
        estudiosMin.add("Tecnico");
        estudiosMin.add("Bachiller");
    }

    public List<String> getEstudiosMin() {
        setEstudiosMin();
        return estudiosMin;
    }

    public List<Ofertas> getOfertas() {
        ofertas = ejbFacade.findAll();
        return ofertas;
    }

    public List<Empresas> getEmpresas() {
        empresas = ejbEFacade.findAll();
        return empresas;
    }

    public List<Capacidad> getCapacidades() {
        capacidadesOf = ejbCFacade.findAll();
        return capacidadesOf;
    }

    private void setDisponibilidad() {
        ofertasAvailable = new HashMap<Ofertas, String>();
        Date actualDay = new Date();
        for (Ofertas off : ofertas) {
            if (off.getFechaEnd().compareTo(actualDay) > 0) {
                ofertasAvailable.put(off, "Disponible");
            } else {
                ofertasAvailable.put(off, "No disponible");
            }
        }
    }

    public List<Ofertas> getOffer() {
        setDisponibilidad();
        List<Ofertas> ofert = new ArrayList<Ofertas>();
        Iterator it = ofertasAvailable.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            if (e.getValue().toString().equals("Disponible")) {
                Ofertas off = (Ofertas) e.getKey();
                ofert.add(off);
            }
        }
        return ofert;
    }

    public List<Profesion> getProfesiones() {
        List<Profesion> profesiones = ejbPFacade.findAll();
        return profesiones;
    }

    public void doCreate(ActionEvent actionEvent) {
        String nombre_oferta = cargo;
        try {
            List<Capacidad> capa = new ArrayList<Capacidad>();

            Empresas tmp = ejbEFacade.find(this.nit);
            for(Integer cap : idCapacidades){
            Capacidad tmpC = ejbCFacade.find(cap);
            capa.add(tmpC);
            }
            Profesion tmpP = ejbPFacade.find(this.idProfesiones.intValue());
            if (tmp != null && !capa.isEmpty()) {
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
                for (Capacidad capaci : capa){
                    CapacidadesPorOfertas capOf = new CapacidadesPorOfertas();
                    capOf.setOferta(newOfertas);
                    capOf.setCapacidad(capaci);
                    ejbCOFacade.create(capOf);
                }

                ProfesionesPorOferta proOf = new ProfesionesPorOferta();
                proOf.setOferta(newOfertas);
                proOf.setProfesion(tmpP);
                ejbPOFacade.create(proOf);



                ofertas.add(newOfertas);
                proOf = null;
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
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oferta " + nombre_oferta + " pudo no haberse creado " + e + " ", null);
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
