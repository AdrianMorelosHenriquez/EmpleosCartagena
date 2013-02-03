/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "experiencia_laboral", catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e"),
    @NamedQuery(name = "ExperienciaLaboral.findById", query = "SELECT e FROM ExperienciaLaboral e WHERE e.id = :id"),
    @NamedQuery(name = "ExperienciaLaboral.findByEmpresa", query = "SELECT e FROM ExperienciaLaboral e WHERE e.empresa = :empresa"),
    @NamedQuery(name = "ExperienciaLaboral.findByCargo", query = "SELECT e FROM ExperienciaLaboral e WHERE e.cargo = :cargo"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaIn", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaIn = :fechaIn"),
    @NamedQuery(name = "ExperienciaLaboral.findByFechaOut", query = "SELECT e FROM ExperienciaLaboral e WHERE e.fechaOut = :fechaOut"),
    @NamedQuery(name = "ExperienciaLaboral.findByActividadEmpresa", query = "SELECT e FROM ExperienciaLaboral e WHERE e.actividadEmpresa = :actividadEmpresa"),
    @NamedQuery(name = "ExperienciaLaboral.findByTipoCargo", query = "SELECT e FROM ExperienciaLaboral e WHERE e.tipoCargo = :tipoCargo"),
    @NamedQuery(name = "ExperienciaLaboral.findByDescripcion", query = "SELECT e FROM ExperienciaLaboral e WHERE e.descripcion = :descripcion")})
public class ExperienciaLaboral implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "empresa", nullable = false, length = 80)
    private String empresa;
    @Basic(optional = false)
    @Column(name = "cargo", nullable = false, length = 45)
    private String cargo;
    @Basic(optional = false)
    @Column(name = "fecha_in", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaIn;
    @Basic(optional = false)
    @Column(name = "fecha_out", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaOut;
    @Basic(optional = false)
    @Column(name = "actividad_empresa", nullable = false, length = 45)
    private String actividadEmpresa;
    @Basic(optional = false)
    @Column(name = "tipo_cargo", nullable = false, length = 45)
    private String tipoCargo;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;
    @JoinColumn(name = "aspirante", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DatosPersonales aspirante;
    @JoinColumn(name = "area", referencedColumnName = "idarea", nullable = false)
    @ManyToOne(optional = false)
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "experiencia")
    private Collection<CapacidadesPorExperiencias> capacidadesPorExperienciasCollection;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(Integer id) {
        this.id = id;
    }

    public ExperienciaLaboral(Integer id, String empresa, String cargo, Date fechaIn, Date fechaOut, String actividadEmpresa, String tipoCargo, String descripcion) {
        this.id = id;
        this.empresa = empresa;
        this.cargo = cargo;
        this.fechaIn = fechaIn;
        this.fechaOut = fechaOut;
        this.actividadEmpresa = actividadEmpresa;
        this.tipoCargo = tipoCargo;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    public String getActividadEmpresa() {
        return actividadEmpresa;
    }

    public void setActividadEmpresa(String actividadEmpresa) {
        this.actividadEmpresa = actividadEmpresa;
    }

    public String getTipoCargo() {
        return tipoCargo;
    }

    public void setTipoCargo(String tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public DatosPersonales getAspirante() {
        return aspirante;
    }

    public void setAspirante(DatosPersonales aspirante) {
        this.aspirante = aspirante;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @XmlTransient
    public Collection<CapacidadesPorExperiencias> getCapacidadesPorExperienciasCollection() {
        return capacidadesPorExperienciasCollection;
    }

    public void setCapacidadesPorExperienciasCollection(Collection<CapacidadesPorExperiencias> capacidadesPorExperienciasCollection) {
        this.capacidadesPorExperienciasCollection = capacidadesPorExperienciasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienciaLaboral)) {
            return false;
        }
        ExperienciaLaboral other = (ExperienciaLaboral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.ExperienciaLaboral[ id=" + id + " ]";
    }
    
}
