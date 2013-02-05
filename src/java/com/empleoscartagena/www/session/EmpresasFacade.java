/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.session;

import com.empleoscartagena.www.entities.Empresas;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ignacio
 */
@Stateless
public class EmpresasFacade extends AbstractFacade<Empresas> {
    @PersistenceContext(unitName = "EmpleosCartagenaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpresasFacade() {
        super(Empresas.class);
    }
    
}
