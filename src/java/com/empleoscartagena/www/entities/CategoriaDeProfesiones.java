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
@Table(name = "categoria_de_profesiones", catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idcategoria"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaDeProfesiones.findAll", query = "SELECT c FROM CategoriaDeProfesiones c"),
    @NamedQuery(name = "CategoriaDeProfesiones.findByIdcategoria", query = "SELECT c FROM CategoriaDeProfesiones c WHERE c.idcategoria = :idcategoria"),
    @NamedQuery(name = "CategoriaDeProfesiones.findByNombreCategoria", query = "SELECT c FROM CategoriaDeProfesiones c WHERE c.nombreCategoria = :nombreCategoria")})
public class CategoriaDeProfesiones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idcategoria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_categoria", nullable = false, length = 45)
    private String nombreCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoria")
    private Collection<Profesion> profesionCollection;

    public CategoriaDeProfesiones() {
    }

    public CategoriaDeProfesiones(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public CategoriaDeProfesiones(Integer idcategoria, String nombreCategoria) {
        this.idcategoria = idcategoria;
        this.nombreCategoria = nombreCategoria;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @XmlTransient
    public Collection<Profesion> getProfesionCollection() {
        return profesionCollection;
    }

    public void setProfesionCollection(Collection<Profesion> profesionCollection) {
        this.profesionCollection = profesionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategoria != null ? idcategoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CategoriaDeProfesiones)) {
            return false;
        }
        CategoriaDeProfesiones other = (CategoriaDeProfesiones) object;
        if ((this.idcategoria == null && other.idcategoria != null) || (this.idcategoria != null && !this.idcategoria.equals(other.idcategoria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreCategoria;
    }
    
}
