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
@Table(name = "capacidades_por_estudios", catalog = "empleabilidad", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CapacidadesPorEstudios.findAll", query = "SELECT c FROM CapacidadesPorEstudios c"),
    @NamedQuery(name = "CapacidadesPorEstudios.findByIdcapacidadPorEstudio", query = "SELECT c FROM CapacidadesPorEstudios c WHERE c.idcapacidadPorEstudio = :idcapacidadPorEstudio")})
public class CapacidadesPorEstudios implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcapacidad_por_estudio")
    private Integer idcapacidadPorEstudio;
    @JoinColumn(name = "estudios", referencedColumnName = "idestudios")
    @ManyToOne(optional = false)
    private Estudios estudios;
    @JoinColumn(name = "capacidad", referencedColumnName = "idcapacidad")
    @ManyToOne(optional = false)
    private Capacidad capacidad;

    public CapacidadesPorEstudios() {
    }

    public CapacidadesPorEstudios(Integer idcapacidadPorEstudio) {
        this.idcapacidadPorEstudio = idcapacidadPorEstudio;
    }

    public Integer getIdcapacidadPorEstudio() {
        return idcapacidadPorEstudio;
    }

    public void setIdcapacidadPorEstudio(Integer idcapacidadPorEstudio) {
        this.idcapacidadPorEstudio = idcapacidadPorEstudio;
    }

    public Estudios getEstudios() {
        return estudios;
    }

    public void setEstudios(Estudios estudios) {
        this.estudios = estudios;
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
        hash += (idcapacidadPorEstudio != null ? idcapacidadPorEstudio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CapacidadesPorEstudios)) {
            return false;
        }
        CapacidadesPorEstudios other = (CapacidadesPorEstudios) object;
        if ((this.idcapacidadPorEstudio == null && other.idcapacidadPorEstudio != null) || (this.idcapacidadPorEstudio != null && !this.idcapacidadPorEstudio.equals(other.idcapacidadPorEstudio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.CapacidadesPorEstudios[ idcapacidadPorEstudio=" + idcapacidadPorEstudio + " ]";
    }
    
}
