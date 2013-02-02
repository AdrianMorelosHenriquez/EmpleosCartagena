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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Giovanny Vasquez
 */
@Entity
@Table(name = "pruebas", catalog = "empleabilidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pruebas.findAll", query = "SELECT p FROM Pruebas p"),
    @NamedQuery(name = "Pruebas.findByIdpruebas", query = "SELECT p FROM Pruebas p WHERE p.idpruebas = :idpruebas"),
    @NamedQuery(name = "Pruebas.findByNombrePrueba", query = "SELECT p FROM Pruebas p WHERE p.nombrePrueba = :nombrePrueba")})
public class Pruebas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpruebas")
    private Integer idpruebas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombre_prueba")
    private String nombrePrueba;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prueba")
    private Collection<Evaluaciones> evaluacionesCollection;

    public Pruebas() {
    }

    public Pruebas(Integer idpruebas) {
        this.idpruebas = idpruebas;
    }

    public Pruebas(Integer idpruebas, String nombrePrueba) {
        this.idpruebas = idpruebas;
        this.nombrePrueba = nombrePrueba;
    }

    public Integer getIdpruebas() {
        return idpruebas;
    }

    public void setIdpruebas(Integer idpruebas) {
        this.idpruebas = idpruebas;
    }

    public String getNombrePrueba() {
        return nombrePrueba;
    }

    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

    @XmlTransient
    public Collection<Evaluaciones> getEvaluacionesCollection() {
        return evaluacionesCollection;
    }

    public void setEvaluacionesCollection(Collection<Evaluaciones> evaluacionesCollection) {
        this.evaluacionesCollection = evaluacionesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpruebas != null ? idpruebas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pruebas)) {
            return false;
        }
        Pruebas other = (Pruebas) object;
        if ((this.idpruebas == null && other.idpruebas != null) || (this.idpruebas != null && !this.idpruebas.equals(other.idpruebas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.Pruebas[ idpruebas=" + idpruebas + " ]";
    }
    
}
