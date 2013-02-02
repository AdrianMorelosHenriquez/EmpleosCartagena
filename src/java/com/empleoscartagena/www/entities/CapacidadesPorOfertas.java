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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Giovanny Vasquez
 */
@Entity
@Table(name = "capacidades_por_ofertas", catalog = "empleabilidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CapacidadesPorOfertas.findAll", query = "SELECT c FROM CapacidadesPorOfertas c"),
    @NamedQuery(name = "CapacidadesPorOfertas.findByIdcapporofertas", query = "SELECT c FROM CapacidadesPorOfertas c WHERE c.idcapporofertas = :idcapporofertas")})
public class CapacidadesPorOfertas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcapporofertas")
    private Integer idcapporofertas;
    @JoinColumn(name = "oferta", referencedColumnName = "idofertas")
    @ManyToOne(optional = false)
    private Ofertas oferta;
    @JoinColumn(name = "capacidad", referencedColumnName = "idcapacidad")
    @ManyToOne(optional = false)
    private Capacidad capacidad;

    public CapacidadesPorOfertas() {
    }

    public CapacidadesPorOfertas(Integer idcapporofertas) {
        this.idcapporofertas = idcapporofertas;
    }

    public Integer getIdcapporofertas() {
        return idcapporofertas;
    }

    public void setIdcapporofertas(Integer idcapporofertas) {
        this.idcapporofertas = idcapporofertas;
    }

    public Ofertas getOferta() {
        return oferta;
    }

    public void setOferta(Ofertas oferta) {
        this.oferta = oferta;
    }

    public Capacidad getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Capacidad capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcapporofertas != null ? idcapporofertas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CapacidadesPorOfertas)) {
            return false;
        }
        CapacidadesPorOfertas other = (CapacidadesPorOfertas) object;
        if ((this.idcapporofertas == null && other.idcapporofertas != null) || (this.idcapporofertas != null && !this.idcapporofertas.equals(other.idcapporofertas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.CapacidadesPorOfertas[ idcapporofertas=" + idcapporofertas + " ]";
    }
    
}
