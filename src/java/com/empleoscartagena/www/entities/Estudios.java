/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Giovanny Vasquez
 */
@Entity
@Table(name = "estudios", catalog = "empleabilidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudios.findAll", query = "SELECT e FROM Estudios e"),
    @NamedQuery(name = "Estudios.findByIdestudios", query = "SELECT e FROM Estudios e WHERE e.idestudios = :idestudios"),
    @NamedQuery(name = "Estudios.findByTitulo", query = "SELECT e FROM Estudios e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Estudios.findByInstitucion", query = "SELECT e FROM Estudios e WHERE e.institucion = :institucion"),
    @NamedQuery(name = "Estudios.findByCiudad", query = "SELECT e FROM Estudios e WHERE e.ciudad = :ciudad"),
    @NamedQuery(name = "Estudios.findByFechaTitulacion", query = "SELECT e FROM Estudios e WHERE e.fechaTitulacion = :fechaTitulacion"),
    @NamedQuery(name = "Estudios.findByNivelEstudio", query = "SELECT e FROM Estudios e WHERE e.nivelEstudio = :nivelEstudio"),
    @NamedQuery(name = "Estudios.findByEstadoEstudio", query = "SELECT e FROM Estudios e WHERE e.estadoEstudio = :estadoEstudio"),
    @NamedQuery(name = "Estudios.findByAreaEstudio", query = "SELECT e FROM Estudios e WHERE e.areaEstudio = :areaEstudio"),
    @NamedQuery(name = "Estudios.findByFechaStart", query = "SELECT e FROM Estudios e WHERE e.fechaStart = :fechaStart"),
    @NamedQuery(name = "Estudios.findByTipoEstudio", query = "SELECT e FROM Estudios e WHERE e.tipoEstudio = :tipoEstudio"),
    @NamedQuery(name = "Estudios.findByPromedio", query = "SELECT e FROM Estudios e WHERE e.promedio = :promedio"),
    @NamedQuery(name = "Estudios.findByMateriasCarrera", query = "SELECT e FROM Estudios e WHERE e.materiasCarrera = :materiasCarrera"),
    @NamedQuery(name = "Estudios.findByMateriasCursadas", query = "SELECT e FROM Estudios e WHERE e.materiasCursadas = :materiasCursadas")})
public class Estudios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestudios")
    private Integer idestudios;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "institucion")
    private String institucion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_titulacion")
    @Temporal(TemporalType.DATE)
    private Date fechaTitulacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nivel_estudio")
    private String nivelEstudio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "estado_estudio")
    private String estadoEstudio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "area_estudio")
    private String areaEstudio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_start")
    @Temporal(TemporalType.DATE)
    private Date fechaStart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_estudio")
    private String tipoEstudio;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "promedio")
    private BigDecimal promedio;
    @Column(name = "materias_carrera")
    private Integer materiasCarrera;
    @Column(name = "materias_cursadas")
    private Integer materiasCursadas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estudios")
    private Collection<CapacidadesPorEstudios> capacidadesPorEstudiosCollection;
    @JoinColumn(name = "aspirante", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DatosPersonales aspirante;

    public Estudios() {
    }

    public Estudios(Integer idestudios) {
        this.idestudios = idestudios;
    }

    public Estudios(Integer idestudios, String titulo, String institucion, String ciudad, Date fechaTitulacion, String nivelEstudio, String estadoEstudio, String areaEstudio, Date fechaStart, String tipoEstudio) {
        this.idestudios = idestudios;
        this.titulo = titulo;
        this.institucion = institucion;
        this.ciudad = ciudad;
        this.fechaTitulacion = fechaTitulacion;
        this.nivelEstudio = nivelEstudio;
        this.estadoEstudio = estadoEstudio;
        this.areaEstudio = areaEstudio;
        this.fechaStart = fechaStart;
        this.tipoEstudio = tipoEstudio;
    }

    public Integer getIdestudios() {
        return idestudios;
    }

    public void setIdestudios(Integer idestudios) {
        this.idestudios = idestudios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Date getFechaTitulacion() {
        return fechaTitulacion;
    }

    public void setFechaTitulacion(Date fechaTitulacion) {
        this.fechaTitulacion = fechaTitulacion;
    }

    public String getNivelEstudio() {
        return nivelEstudio;
    }

    public void setNivelEstudio(String nivelEstudio) {
        this.nivelEstudio = nivelEstudio;
    }

    public String getEstadoEstudio() {
        return estadoEstudio;
    }

    public void setEstadoEstudio(String estadoEstudio) {
        this.estadoEstudio = estadoEstudio;
    }

    public String getAreaEstudio() {
        return areaEstudio;
    }

    public void setAreaEstudio(String areaEstudio) {
        this.areaEstudio = areaEstudio;
    }

    public Date getFechaStart() {
        return fechaStart;
    }

    public void setFechaStart(Date fechaStart) {
        this.fechaStart = fechaStart;
    }

    public String getTipoEstudio() {
        return tipoEstudio;
    }

    public void setTipoEstudio(String tipoEstudio) {
        this.tipoEstudio = tipoEstudio;
    }

    public BigDecimal getPromedio() {
        return promedio;
    }

    public void setPromedio(BigDecimal promedio) {
        this.promedio = promedio;
    }

    public Integer getMateriasCarrera() {
        return materiasCarrera;
    }

    public void setMateriasCarrera(Integer materiasCarrera) {
        this.materiasCarrera = materiasCarrera;
    }

    public Integer getMateriasCursadas() {
        return materiasCursadas;
    }

    public void setMateriasCursadas(Integer materiasCursadas) {
        this.materiasCursadas = materiasCursadas;
    }

    @XmlTransient
    public Collection<CapacidadesPorEstudios> getCapacidadesPorEstudiosCollection() {
        return capacidadesPorEstudiosCollection;
    }

    public void setCapacidadesPorEstudiosCollection(Collection<CapacidadesPorEstudios> capacidadesPorEstudiosCollection) {
        this.capacidadesPorEstudiosCollection = capacidadesPorEstudiosCollection;
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
        hash += (idestudios != null ? idestudios.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudios)) {
            return false;
        }
        Estudios other = (Estudios) object;
        if ((this.idestudios == null && other.idestudios != null) || (this.idestudios != null && !this.idestudios.equals(other.idestudios))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.Estudios[ idestudios=" + idestudios + " ]";
    }
    
}
