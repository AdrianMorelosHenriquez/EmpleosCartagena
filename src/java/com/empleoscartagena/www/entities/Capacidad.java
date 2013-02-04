/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.entities;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idcapacidad"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Capacidad.findAll", query = "SELECT c FROM Capacidad c"),
    @NamedQuery(name = "Capacidad.findByIdcapacidad", query = "SELECT c FROM Capacidad c WHERE c.idcapacidad = :idcapacidad"),
    @NamedQuery(name = "Capacidad.findByNombre", query = "SELECT c FROM Capacidad c WHERE c.nombre = :nombre")})
public class Capacidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcapacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capacidad")
    private Collection<CapacidadesPorOfertas> capacidadesPorOfertasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capacidad")
    private Collection<CapacidadesPorEstudios> capacidadesPorEstudiosCollection;
    @JoinColumn(name = "area", referencedColumnName = "idarea", nullable = false)
    @ManyToOne(optional = false)
    private Area area;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "capacidad")
    private Collection<CapacidadesPorExperiencias> capacidadesPorExperienciasCollection;

    public Capacidad() {
    }

    public Capacidad(Integer idcapacidad) {
        this.idcapacidad = idcapacidad;
    }

    public Capacidad(Integer idcapacidad, String nombre) {
        this.idcapacidad = idcapacidad;
        this.nombre = nombre;
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

    @XmlTransient
    public Collection<CapacidadesPorOfertas> getCapacidadesPorOfertasCollection() {
        return capacidadesPorOfertasCollection;
    }

    public void setCapacidadesPorOfertasCollection(Collection<CapacidadesPorOfertas> capacidadesPorOfertasCollection) {
        this.capacidadesPorOfertasCollection = capacidadesPorOfertasCollection;
    }

    @XmlTransient
    public Collection<CapacidadesPorEstudios> getCapacidadesPorEstudiosCollection() {
        return capacidadesPorEstudiosCollection;
    }

    public void setCapacidadesPorEstudiosCollection(Collection<CapacidadesPorEstudios> capacidadesPorEstudiosCollection) {
        this.capacidadesPorEstudiosCollection = capacidadesPorEstudiosCollection;
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
        hash += (idcapacidad != null ? idcapacidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Capacidad)) {
            return false;
        }
        Capacidad other = (Capacidad) object;
        if ((this.idcapacidad == null && other.idcapacidad != null) || (this.idcapacidad != null && !this.idcapacidad.equals(other.idcapacidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.Capacidad[ idcapacidad=" + idcapacidad + " ]";
    }
    
}
