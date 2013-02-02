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
@Table(name = "capacidades_por_experiencias", catalog = "empleabilidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CapacidadesPorExperiencias.findAll", query = "SELECT c FROM CapacidadesPorExperiencias c"),
    @NamedQuery(name = "CapacidadesPorExperiencias.findByIdcapporexperiencia", query = "SELECT c FROM CapacidadesPorExperiencias c WHERE c.idcapporexperiencia = :idcapporexperiencia")})
public class CapacidadesPorExperiencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcapporexperiencia")
    private Integer idcapporexperiencia;
    @JoinColumn(name = "experiencia", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ExperienciaLaboral experiencia;
    @JoinColumn(name = "capacidad", referencedColumnName = "idcapacidad")
    @ManyToOne(optional = false)
    private Capacidad capacidad;

    public CapacidadesPorExperiencias() {
    }

    public CapacidadesPorExperiencias(Integer idcapporexperiencia) {
        this.idcapporexperiencia = idcapporexperiencia;
    }

    public Integer getIdcapporexperiencia() {
        return idcapporexperiencia;
    }

    public void setIdcapporexperiencia(Integer idcapporexperiencia) {
        this.idcapporexperiencia = idcapporexperiencia;
    }

    public ExperienciaLaboral getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(ExperienciaLaboral experiencia) {
        this.experiencia = experiencia;
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
        hash += (idcapporexperiencia != null ? idcapporexperiencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CapacidadesPorExperiencias)) {
            return false;
        }
        CapacidadesPorExperiencias other = (CapacidadesPorExperiencias) object;
        if ((this.idcapporexperiencia == null && other.idcapporexperiencia != null) || (this.idcapporexperiencia != null && !this.idcapporexperiencia.equals(other.idcapporexperiencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.CapacidadesPorExperiencias[ idcapporexperiencia=" + idcapporexperiencia + " ]";
    }
    
}
