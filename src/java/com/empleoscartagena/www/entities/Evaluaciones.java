/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(catalog = "empleabilidad", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"idtest"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluaciones.findAll", query = "SELECT e FROM Evaluaciones e"),
    @NamedQuery(name = "Evaluaciones.findByIdtest", query = "SELECT e FROM Evaluaciones e WHERE e.idtest = :idtest"),
    @NamedQuery(name = "Evaluaciones.findByPuntuacion", query = "SELECT e FROM Evaluaciones e WHERE e.puntuacion = :puntuacion"),
    @NamedQuery(name = "Evaluaciones.findByCualificacion", query = "SELECT e FROM Evaluaciones e WHERE e.cualificacion = :cualificacion"),
    @NamedQuery(name = "Evaluaciones.findByComentarios", query = "SELECT e FROM Evaluaciones e WHERE e.comentarios = :comentarios")})
public class Evaluaciones implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer idtest;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 4, scale = 1)
    private BigDecimal puntuacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(nullable = false, length = 20)
    private String cualificacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String comentarios;
    @JoinColumn(name = "prueba", referencedColumnName = "idpruebas", nullable = false)
    @ManyToOne(optional = false)
    private Pruebas prueba;
    @JoinColumn(name = "inscripcion", referencedColumnName = "idinscripcion", nullable = false)
    @ManyToOne(optional = false)
    private Inscripcion inscripcion;

    public Evaluaciones() {
    }

    public Evaluaciones(Integer idtest) {
        this.idtest = idtest;
    }

    public Evaluaciones(Integer idtest, BigDecimal puntuacion, String cualificacion, String comentarios) {
        this.idtest = idtest;
        this.puntuacion = puntuacion;
        this.cualificacion = cualificacion;
        this.comentarios = comentarios;
    }

    public Integer getIdtest() {
        return idtest;
    }

    public void setIdtest(Integer idtest) {
        this.idtest = idtest;
    }

    public BigDecimal getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(BigDecimal puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getCualificacion() {
        return cualificacion;
    }

    public void setCualificacion(String cualificacion) {
        this.cualificacion = cualificacion;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Pruebas getPrueba() {
        return prueba;
    }

    public void setPrueba(Pruebas prueba) {
        this.prueba = prueba;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtest != null ? idtest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluaciones)) {
            return false;
        }
        Evaluaciones other = (Evaluaciones) object;
        if ((this.idtest == null && other.idtest != null) || (this.idtest != null && !this.idtest.equals(other.idtest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.empleoscartagena.www.entities.Evaluaciones[ idtest=" + idtest + " ]";
    }
    
}
