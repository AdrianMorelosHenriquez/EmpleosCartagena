/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.session;

import com.empleoscartagena.www.entities.CategoriaDeProfesiones;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ignacio
 */
@Stateless
public class CategoriaDeProfesionesFacade extends AbstractFacade<CategoriaDeProfesiones> {
    @PersistenceContext(unitName = "EmpleosCartagenaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriaDeProfesionesFacade() {
        super(CategoriaDeProfesiones.class);
    }
    
}
