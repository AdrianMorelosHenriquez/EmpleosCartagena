/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.session;

import com.empleoscartagena.www.entities.DatosPersonales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USUARIO
 */
@Stateless
public class DatosPersonalesFacade extends AbstractFacade<DatosPersonales> {
    @PersistenceContext(unitName = "EmpleosCartagenaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatosPersonalesFacade() {
        super(DatosPersonales.class);
    }
    
}
