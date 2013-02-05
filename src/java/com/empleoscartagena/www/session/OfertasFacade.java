/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.session;


import com.empleoscartagena.www.entities.Ofertas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ignacio
 */
@Stateless
public class OfertasFacade extends AbstractFacade<Ofertas> {
    @PersistenceContext(unitName = "EmpleosCartagenaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OfertasFacade() {
        super(Ofertas.class);
    }
    
}
