package com.empleoscartagena.www.controller;

import com.empleoscartagena.www.entities.Area;
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
import java.io.IOException;

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
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    private List<String> estudios;
    private List<CapacidadesPorOfertas> capaOfs;
    private List<ProfesionesPorOferta> profOfs;
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
    private List<Integer> idProfesiones;
    private List<Profesion> profesiones;

    public OfertasController() {
    }

    @PostConstruct
    public void init() {
        ofertas = ejbFacade.findAll();
        capacidadesOf = ejbCFacade.findAll();
        profesiones = ejbPFacade.findAll();

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

    public List<Integer> getIdProfesiones() {
        return idProfesiones;
    }

    public void setIdProfesiones(List<Integer> idProfesiones) {
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

    private void setEstudios() {
        estudios = new ArrayList<String>();
        estudios.add("Phd");
        estudios.add("Magister");
        estudios.add("Especialista");
        estudios.add("Profesional");
        estudios.add("Tecnologo");
        estudios.add("Tecnico");
        estudios.add("Bachiller");
    }

    public List<String> getEstudios() {
        setEstudios();
        return estudios;
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

    public List<CapacidadesPorOfertas> getCapacidadesOf() {
        List<CapacidadesPorOfertas> newCapa = new ArrayList<CapacidadesPorOfertas>();
        capaOfs = ejbCOFacade.findAll();
        Integer idOferta = selected.getIdofertas();
        for (CapacidadesPorOfertas caps : capaOfs) {
            if (idOferta.equals(caps.getOferta().getIdofertas())) {
                newCapa.add(caps);
            }
        }
        return newCapa;
    }

    public List<ProfesionesPorOferta> getProfesionesOf() {
        List<ProfesionesPorOferta> newProf = new ArrayList<ProfesionesPorOferta>();
        profOfs = ejbPOFacade.findAll();
        Integer idOferta = selected.getIdofertas();
        for (ProfesionesPorOferta prof : profOfs) {
            if (idOferta.equals(prof.getOferta().getIdofertas())) {
                newProf.add(prof);
            }
        }
        return newProf;

    }

    public List<Profesion> getProfesiones() {
        profesiones = ejbPFacade.findAll();
        return profesiones;
    }

    public boolean validarOferta() {
        for (Ofertas off : ofertas) {
            if (this.cargo.equals(off.getCargo()) && this.nit.equals(off.getNit().getNit())) {
                return true;
            }
        }
        return false;
    }

    public void doCreate(ActionEvent actionEvent) {
        if (!validarOferta()) {
            String nombre_oferta = cargo;
            try {
                List<Capacidad> capa = new ArrayList<Capacidad>();
                List<Profesion> prof = new ArrayList<Profesion>();

                Empresas tmp = ejbEFacade.find(this.nit);

                for (Integer cap : idCapacidades) {
                    Capacidad tmpC = ejbCFacade.find(cap);
                    capa.add(tmpC);
                }
                for (Integer pro : idProfesiones) {
                    Profesion tmpP = ejbPFacade.find(pro);
                    prof.add(tmpP);
                }

                if (tmp != null && !capa.isEmpty() && !prof.isEmpty()) {
                    newOfertas = new Ofertas();
                    newOfertas.setFechaStart(fechaStart);
                    newOfertas.setFechaEnd(fechaEnd);
                    newOfertas.setCargo(cargo);
                    newOfertas.setDescripcion(descripcion);
                    newOfertas.setSalario(salario);
                    newOfertas.setNivelEstudiosMax(nivelEstudiosMax);
                    newOfertas.setNivelEstudiosMin(nivelEstudiosMin);
                    newOfertas.setNit(tmp);
                    newOfertas.setAniosExperiencia(expYears);
                    ejbFacade.create(newOfertas);
                    for (Capacidad capaci : capa) {
                        CapacidadesPorOfertas capOf = new CapacidadesPorOfertas();
                        capOf.setOferta(newOfertas);
                        capOf.setCapacidad(capaci);
                        ejbCOFacade.create(capOf);
                    }
                    for (Profesion profeci : prof) {
                        ProfesionesPorOferta proOf = new ProfesionesPorOferta();
                        proOf.setOferta(newOfertas);
                        proOf.setProfesion(profeci);
                        ejbPOFacade.create(proOf);
                    }
                    idCapacidades = null;
                    idProfesiones = null;
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
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oferta " + nombre_oferta + " pudo no haberse creado " + e + " ", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        } else {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ya existe una oferta con el cargo " + this.cargo + "y la empresa " + this.nit, null);
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
            List<CapacidadesPorOfertas> capOfs = (List<CapacidadesPorOfertas>) selected.getCapacidadesPorOfertasCollection();
            for (CapacidadesPorOfertas caps : capOfs) {
                ejbCOFacade.remove(caps);
            }
            List<ProfesionesPorOferta> proOfs = (List<ProfesionesPorOferta>) selected.getProfesionesPorOfertaCollection();
            for (ProfesionesPorOferta profs : proOfs) {
                ejbPOFacade.remove(profs);
            }
            if (tmpP != null) {
                List<Capacidad> capa = new ArrayList<Capacidad>();
                List<Profesion> prof = new ArrayList<Profesion>();

                Empresas tmpE = ejbEFacade.find(this.nit);

                for (Integer cap : idCapacidades) {
                    Capacidad tmpC = ejbCFacade.find(cap);
                    capa.add(tmpC);
                }
                for (Integer pro : idProfesiones) {
                    Profesion tmpPR = ejbPFacade.find(pro);
                    prof.add(tmpPR);
                }
                if (tmpE != null && !capa.isEmpty() && !prof.isEmpty()) {
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
                    for (Capacidad capaci : capa) {
                        CapacidadesPorOfertas capOf = new CapacidadesPorOfertas();
                        capOf.setOferta(tmpP);
                        capOf.setCapacidad(capaci);
                        ejbCOFacade.edit(capOf);
                    }
                    for (Profesion profeci : prof) {
                        ProfesionesPorOferta proOf = new ProfesionesPorOferta();
                        proOf.setOferta(tmpP);
                        proOf.setProfesion(profeci);
                        ejbPOFacade.edit(proOf);
                    }
                    idCapacidades = null;
                    idProfesiones = null;
                    fechaStart = null;
                    fechaEnd = null;
                    cargo = null;
                    nivelEstudiosMax = null;
                    nivelEstudiosMin = null;
                    descripcion = null;
                    salario = 0;
                    nit = null;
                    expYears = 0;

                    FacesMessage message =
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " ha sido actualizada", null);
                    FacesContext.getCurrentInstance().addMessage(null, message);

                }
            }
        } catch (Exception e) {
            FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "La oferta " + selected.getNit().getNombre() + " " + selected.getCargo() + " pudo no haberse actualizado " + e, null);
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
        List<CapacidadesPorOfertas> capOf = (List<CapacidadesPorOfertas>) selected.getCapacidadesPorOfertasCollection();
        List<ProfesionesPorOferta> proOf = (List<ProfesionesPorOferta>) selected.getProfesionesPorOfertaCollection();

        return "ofertasEdit";
    }

    public String toList() {
        return "List";
    }

    public String toCreate() {
        cargo = null;
        fechaStart = null;
        fechaEnd = null;
        descripcion = null;
        nit = null;
        salario = 0;
        expYears = 0;
        nivelEstudiosMax = null;
        nivelEstudiosMin = null;
        return "Ofertas";
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
