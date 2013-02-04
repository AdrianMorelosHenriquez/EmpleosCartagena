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
import javax.persistence.Id;
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
@Table(name = "datos_personales", catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DatosPersonales.findAll", query = "SELECT d FROM DatosPersonales d"),
    @NamedQuery(name = "DatosPersonales.findById", query = "SELECT d FROM DatosPersonales d WHERE d.id = :id"),
    @NamedQuery(name = "DatosPersonales.findByNombres", query = "SELECT d FROM DatosPersonales d WHERE d.nombres = :nombres"),
    @NamedQuery(name = "DatosPersonales.findByApellido1", query = "SELECT d FROM DatosPersonales d WHERE d.apellido1 = :apellido1"),
    @NamedQuery(name = "DatosPersonales.findBySexo", query = "SELECT d FROM DatosPersonales d WHERE d.sexo = :sexo"),
    @NamedQuery(name = "DatosPersonales.findByEstadoCivil", query = "SELECT d FROM DatosPersonales d WHERE d.estadoCivil = :estadoCivil"),
    @NamedQuery(name = "DatosPersonales.findByLugarNacimiento", query = "SELECT d FROM DatosPersonales d WHERE d.lugarNacimiento = :lugarNacimiento"),
    @NamedQuery(name = "DatosPersonales.findByFechaNacimiento", query = "SELECT d FROM DatosPersonales d WHERE d.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "DatosPersonales.findByDireccion", query = "SELECT d FROM DatosPersonales d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "DatosPersonales.findByBarrio", query = "SELECT d FROM DatosPersonales d WHERE d.barrio = :barrio"),
    @NamedQuery(name = "DatosPersonales.findByCudad", query = "SELECT d FROM DatosPersonales d WHERE d.cudad = :cudad"),
    @NamedQuery(name = "DatosPersonales.findByTelefono", query = "SELECT d FROM DatosPersonales d WHERE d.telefono = :telefono"),
    @NamedQuery(name = "DatosPersonales.findByCelular", query = "SELECT d FROM DatosPersonales d WHERE d.celular = :celular"),
    @NamedQuery(name = "DatosPersonales.findByEmail", query = "SELECT d FROM DatosPersonales d WHERE d.email = :email"),
    @NamedQuery(name = "DatosPersonales.findByDescripcion", query = "SELECT d FROM DatosPersonales d WHERE d.descripcion = :descripcion"),
    @NamedQuery(name = "DatosPersonales.findByTipoDocumento", query = "SELECT d FROM DatosPersonales d WHERE d.tipoDocumento = :tipoDocumento")})
public class DatosPersonales implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String apellido1;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(nullable = false, length = 1)
    private String sexo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "estado_civil", nullable = false, length = 1)
    private String estadoCivil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "lugar_nacimiento", nullable = false, length = 45)
    private String lugarNacimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String barrio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String cudad;
    @Size(max = 13)
    @Column(length = 13)
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(nullable = false, length = 13)
    private String celular;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_documento", nullable = false)
    private int tipoDocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante")
    private Collection<ProfesionesPorAspirante> profesionesPorAspiranteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante")
    private Collection<Referencias> referenciasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante")
    private Collection<Inscripcion> inscripcionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante")
    private Collection<ExperienciaLaboral> experienciaLaboralCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aspirante")
    private Collection<Estudios> estudiosCollection;

    public DatosPersonales() {
    }

    public DatosPersonales(Integer id) {
        this.id = id;
    }

    public DatosPersonales(Integer id, String nombres, String apellido1, String sexo, String estadoCivil, String lugarNacimiento, Date fechaNacimiento, String direccion, String barrio, String cudad, String celular, String email, String descripcion, int tipoDocumento) {
        this.id = id;
        this.nombres = nombres;
        this.apellido1 = apellido1;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.lugarNacimiento = lugarNacimiento;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.barrio = barrio;
        this.cudad = cudad;
        this.celular = celular;
        this.email = email;
        this.descripcion = descripcion;
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCudad() {
        return cudad;
    }

    public void setCudad(String cudad) {
        this.cudad = cudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @XmlTransient
    public Collection<ProfesionesPorAspirante> getProfesionesPorAspiranteCollection() {
        return profesionesPorAspiranteCollection;
    }

    public void setProfesionesPorAspiranteCollection(Collection<ProfesionesPorAspirante> profesionesPorAspiranteCollection) {
        this.profesionesPorAspiranteCollection = profesionesPorAspiranteCollection;
    }

    @XmlTransient
    public Collection<Referencias> getReferenciasCollection() {
        return referenciasCollection;
    }

    public void setReferenciasCollection(Collection<Referencias> referenciasCollection) {
        this.referenciasCollection = referenciasCollection;
    }

    @XmlTransient
    public Collection<Inscripcion> getInscripcionCollection() {
        return inscripcionCollection;
    }

    public void setInscripcionCollection(Collection<Inscripcion> inscripcionCollection) {
        this.inscripcionCollection = inscripcionCollection;
    }

    @XmlTransient
    public Collection<ExperienciaLaboral> getExperienciaLaboralCollection() {
        return experienciaLaboralCollection;
    }

    public void setExperienciaLaboralCollection(Collection<ExperienciaLaboral> experienciaLaboralCollection) {
        this.experienciaLaboralCollection = experienciaLaboralCollection;
    }

    @XmlTransient
    public Collection<Estudios> getEstudiosCollection() {
        return estudiosCollection;
    }

    public void setEstudiosCollection(Collection<Estudios> estudiosCollection) {
        this.estudiosCollection = estudiosCollection;
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
        if (!(object instanceof DatosPersonales)) {
            return false;
        }
        DatosPersonales other = (DatosPersonales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.DatosPersonales[ id=" + id + " ]";
    }
    
}
