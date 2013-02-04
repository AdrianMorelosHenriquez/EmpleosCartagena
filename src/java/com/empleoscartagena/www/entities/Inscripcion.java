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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idinscripcion"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inscripcion.findAll", query = "SELECT i FROM Inscripcion i"),
    @NamedQuery(name = "Inscripcion.findByIdinscripcion", query = "SELECT i FROM Inscripcion i WHERE i.idinscripcion = :idinscripcion"),
    @NamedQuery(name = "Inscripcion.findByFecha", query = "SELECT i FROM Inscripcion i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Inscripcion.findByEntrevista", query = "SELECT i FROM Inscripcion i WHERE i.entrevista = :entrevista")})
public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idinscripcion;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date entrevista;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "inscripcion")
    private Collection<Evaluaciones> evaluacionesCollection;
    @JoinColumn(name = "oferta", referencedColumnName = "idofertas", nullable = false)
    @ManyToOne(optional = false)
    private Ofertas oferta;
    @JoinColumn(name = "aspirante", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DatosPersonales aspirante;

    public Inscripcion() {
    }

    public Inscripcion(Integer idinscripcion) {
        this.idinscripcion = idinscripcion;
    }

    public Inscripcion(Integer idinscripcion, Date fecha, Date entrevista) {
        this.idinscripcion = idinscripcion;
        this.fecha = fecha;
        this.entrevista = entrevista;
    }

    public Integer getIdinscripcion() {
        return idinscripcion;
    }

    public void setIdinscripcion(Integer idinscripcion) {
        this.idinscripcion = idinscripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getEntrevista() {
        return entrevista;
    }

    public void setEntrevista(Date entrevista) {
        this.entrevista = entrevista;
    }

    @XmlTransient
    public Collection<Evaluaciones> getEvaluacionesCollection() {
        return evaluacionesCollection;
    }

    public void setEvaluacionesCollection(Collection<Evaluaciones> evaluacionesCollection) {
        this.evaluacionesCollection = evaluacionesCollection;
    }

    public Ofertas getOferta() {
        return oferta;
    }

    public void setOferta(Ofertas oferta) {
        this.oferta = oferta;
    }

    public DatosPersonales getAspirante() {
        return aspirante;
    }

    public void setAspirante(DatosPersonales aspirante) {
        this.aspirante = aspirante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinscripcion != null ? idinscripcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inscripcion)) {
            return false;
        }
        Inscripcion other = (Inscripcion) object;
        if ((this.idinscripcion == null && other.idinscripcion != null) || (this.idinscripcion != null && !this.idinscripcion.equals(other.idinscripcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.Inscripcion[ idinscripcion=" + idinscripcion + " ]";
    }
    
}
