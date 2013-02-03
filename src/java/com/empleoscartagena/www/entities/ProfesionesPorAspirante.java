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
@Table(name = "profesiones_por_aspirante", catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idprofesones_aspirante"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesionesPorAspirante.findAll", query = "SELECT p FROM ProfesionesPorAspirante p"),
    @NamedQuery(name = "ProfesionesPorAspirante.findByIdprofesonesAspirante", query = "SELECT p FROM ProfesionesPorAspirante p WHERE p.idprofesonesAspirante = :idprofesonesAspirante")})
public class ProfesionesPorAspirante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprofesones_aspirante", nullable = false)
    private Integer idprofesonesAspirante;
    @JoinColumn(name = "profesion", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Profesion profesion;
    @JoinColumn(name = "aspirante", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private DatosPersonales aspirante;

    public ProfesionesPorAspirante() {
    }

    public ProfesionesPorAspirante(Integer idprofesonesAspirante) {
        this.idprofesonesAspirante = idprofesonesAspirante;
    }

    public Integer getIdprofesonesAspirante() {
        return idprofesonesAspirante;
    }

    public void setIdprofesonesAspirante(Integer idprofesonesAspirante) {
        this.idprofesonesAspirante = idprofesonesAspirante;
    }

    public Profesion getProfesion() {
        return profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
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
        hash += (idprofesonesAspirante != null ? idprofesonesAspirante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesionesPorAspirante)) {
            return false;
        }
        ProfesionesPorAspirante other = (ProfesionesPorAspirante) object;
        if ((this.idprofesonesAspirante == null && other.idprofesonesAspirante != null) || (this.idprofesonesAspirante != null && !this.idprofesonesAspirante.equals(other.idprofesonesAspirante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.ProfesionesPorAspirante[ idprofesonesAspirante=" + idprofesonesAspirante + " ]";
    }
    
}
