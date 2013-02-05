/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.session;


import com.empleoscartagena.www.entities.ProfesionesPorOferta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ignacio
 */
@Stateless
public class ProfesionesPorOfertaFacade extends AbstractFacade<ProfesionesPorOferta> {
    @PersistenceContext(unitName = "EmpleosCartagenaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProfesionesPorOfertaFacade() {
        super(ProfesionesPorOferta.class);
    }
    
}
