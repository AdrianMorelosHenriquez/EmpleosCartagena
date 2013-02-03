/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "referencias", catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Referencias.findAll", query = "SELECT r FROM Referencias r"),
    @NamedQuery(name = "Referencias.findById", query = "SELECT r FROM Referencias r WHERE r.id = :id"),
    @NamedQuery(name = "Referencias.findByNombre", query = "SELECT r FROM Referencias r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Referencias.findByTelefono", query = "SELECT r FROM Referencias r WHERE r.telefono = :telefono"),
    @NamedQuery(name = "Referencias.findByCelular", query = "SELECT r FROM Referencias r WHERE r.celular = :celular"),
    @NamedQuery(name = "Referencias.findByEmail", query = "SELECT r FROM Referencias r WHERE r.email = :email"),
    @NamedQuery(name = "Referencias.findByDireccion", query = "SELECT r FROM Referencias r WHERE r.direccion = :direccion"),
    @NamedQuery(name = "Referencias.findByBarrio", query = "SELECT r FROM Referencias r WHERE r.barrio = :barrio"),
    @NamedQuery(name = "Referencias.findByCiudad", query = "SELECT r FROM Referencias r WHERE r.ciudad = :ciudad"),
    @NamedQuery(name = "Referencias.findByProfesion", query = "SELECT r FROM Referencias r WHERE r.profesion = :profesion"),
    @NamedQuery(name = "Referencias.findByCargo", query = "SELECT r FROM Referencias r WHERE r.cargo = :cargo"),
    @NamedQuery(name = "Referencias.findByTipo", query = "SELECT r FROM Referencias r WHERE r.tipo = :tipo")})
public class Referencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;
    @Column(name = "telefono", length = 13)
    private String telefono;
    @Basic(optional = false)
    @Column(name = "celular", nullable = false, length = 13)
    private String celular;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 128)
    private String email;
    @Basic(optional = false)
    @Column(name = "direccion", nullable = false, length = 80)
    private String direccion;
    @Basic(optional = false)
    @Column(name = "barrio", nullable = false, length = 45)
    private String barrio;
    @Basic(optional = false)
    @Column(name = "ciudad", nullable = false, length = 45)
    private String ciudad;
    @Column(name = "profesion", length = 45)
    private String profesion;
    @Column(name = "cargo", length = 45)
    private String cargo;
    @Basic(optional = false)
    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;
    @JoinColumn(name = "aspirante", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DatosPersonales aspirante;

    public Referencias() {
    }

    public Referencias(Integer id) {
        this.id = id;
    }

    public Referencias(Integer id, String nombre, String celular, String email, String direccion, String barrio, String ciudad, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.email = email;
        this.direccion = direccion;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Referencias)) {
            return false;
        }
        Referencias other = (Referencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.Referencias[ id=" + id + " ]";
    }
    
}
