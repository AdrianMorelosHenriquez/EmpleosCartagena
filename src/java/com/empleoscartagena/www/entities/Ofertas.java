/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
    @UniqueConstraint(columnNames = {"idofertas"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ofertas.findAll", query = "SELECT o FROM Ofertas o"),
    @NamedQuery(name = "Ofertas.findByIdofertas", query = "SELECT o FROM Ofertas o WHERE o.idofertas = :idofertas"),
    @NamedQuery(name = "Ofertas.findByFechaStart", query = "SELECT o FROM Ofertas o WHERE o.fechaStart = :fechaStart"),
    @NamedQuery(name = "Ofertas.findByFechaEnd", query = "SELECT o FROM Ofertas o WHERE o.fechaEnd = :fechaEnd"),
    @NamedQuery(name = "Ofertas.findByCargo", query = "SELECT o FROM Ofertas o WHERE o.cargo = :cargo"),
    @NamedQuery(name = "Ofertas.findBySalario", query = "SELECT o FROM Ofertas o WHERE o.salario = :salario"),
    @NamedQuery(name = "Ofertas.findByDescripcion", query = "SELECT o FROM Ofertas o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Ofertas.findByNivelEstudiosMin", query = "SELECT o FROM Ofertas o WHERE o.nivelEstudiosMin = :nivelEstudiosMin"),
    @NamedQuery(name = "Ofertas.findByNivelEstudiosMax", query = "SELECT o FROM Ofertas o WHERE o.nivelEstudiosMax = :nivelEstudiosMax"),
    @NamedQuery(name = "Ofertas.findByAniosExperiencia", query = "SELECT o FROM Ofertas o WHERE o.aniosExperiencia = :aniosExperiencia")})
public class Ofertas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idofertas;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_start", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaStart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_end", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEnd;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String cargo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long salario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(nullable = false, length = 300)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nivel_estudios_min", nullable = false, length = 45)
    private String nivelEstudiosMin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nivel_estudios_max", nullable = false, length = 45)
    private String nivelEstudiosMax;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anios_experiencia", nullable = false)
    private int aniosExperiencia;
    @JoinColumn(name = "nit", referencedColumnName = "nit", nullable = false)
    @ManyToOne(optional = false)
    private Empresas nit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oferta")
    private Collection<ProfesionesPorOferta> profesionesPorOfertaCollection = new ArrayList<ProfesionesPorOferta>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oferta")
    private Collection<CapacidadesPorOfertas> capacidadesPorOfertasCollection = new ArrayList<CapacidadesPorOfertas>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "oferta")
    private Collection<Inscripcion> inscripcionCollection;

    public Ofertas() {
    }

    public Ofertas(Integer idofertas) {
        this.idofertas = idofertas;
    }

    public Ofertas(Integer idofertas, Date fechaStart, Date fechaEnd, String cargo, long salario, String descripcion, String nivelEstudiosMin, String nivelEstudiosMax, int aniosExperiencia) {
        this.idofertas = idofertas;
        this.fechaStart = fechaStart;
        this.fechaEnd = fechaEnd;
        this.cargo = cargo;
        this.salario = salario;
        this.descripcion = descripcion;
        this.nivelEstudiosMin = nivelEstudiosMin;
        this.nivelEstudiosMax = nivelEstudiosMax;
        this.aniosExperiencia = aniosExperiencia;
    }

    public Integer getIdofertas() {
        return idofertas;
    }

    public void setIdofertas(Integer idofertas) {
        this.idofertas = idofertas;
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

    public int getAniosExperiencia() {
        return aniosExperiencia;
    }

    public void setAniosExperiencia(int aniosExperiencia) {
        this.aniosExperiencia = aniosExperiencia;
    }

    public Empresas getNit() {
        return nit;
    }

    public void setNit(Empresas nit) {
        this.nit = nit;
    }
    public String getNombre()
    {
     return nit.getNombre() +" "+cargo;
    }

    @XmlTransient
    public Collection<ProfesionesPorOferta> getProfesionesPorOfertaCollection() {
        return profesionesPorOfertaCollection;
    }

    public void setProfesionesPorOfertaCollection(Collection<ProfesionesPorOferta> profesionesPorOfertaCollection) {
        this.profesionesPorOfertaCollection = profesionesPorOfertaCollection;
    }

    @XmlTransient
    public Collection<CapacidadesPorOfertas> getCapacidadesPorOfertasCollection() {
        return capacidadesPorOfertasCollection;
    }

    public void setCapacidadesPorOfertasCollection(Collection<CapacidadesPorOfertas> capacidadesPorOfertasCollection) {
        this.capacidadesPorOfertasCollection = capacidadesPorOfertasCollection;
    }

    @XmlTransient
    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idofertas != null ? idofertas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ofertas)) {
            return false;
        }
        Ofertas other = (Ofertas) object;
        if ((this.idofertas == null && other.idofertas != null) || (this.idofertas != null && !this.idofertas.equals(other.idofertas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}
