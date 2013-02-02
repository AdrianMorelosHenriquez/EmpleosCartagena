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
@Table(name = "profesiones_por_oferta", catalog = "empleabilidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesionesPorOferta.findAll", query = "SELECT p FROM ProfesionesPorOferta p"),
    @NamedQuery(name = "ProfesionesPorOferta.findById", query = "SELECT p FROM ProfesionesPorOferta p WHERE p.id = :id")})
public class ProfesionesPorOferta implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "profesion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Profesion profesion;
    @JoinColumn(name = "oferta", referencedColumnName = "idofertas")
    @ManyToOne(optional = false)
    private Ofertas oferta;

    public ProfesionesPorOferta() {
    }

    public ProfesionesPorOferta(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public Ofertas getOferta() {
        return oferta;
    }

    public void setOferta(Ofertas oferta) {
        this.oferta = oferta;
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
        if (!(object instanceof ProfesionesPorOferta)) {
            return false;
        }
        ProfesionesPorOferta other = (ProfesionesPorOferta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.ProfesionesPorOferta[ id=" + id + " ]";
    }
    
}
