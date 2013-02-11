/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.empleoscartagena.www.controller.util;

import com.empleoscartagena.www.entities.Capacidad;
import com.empleoscartagena.www.session.CapacidadFacade;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Ignacio
 */
@FacesConverter(forClass=Capacidad.class ,value="capacidadConverter")
public class CapacidadConverter implements Converter{
CapacidadFacade ejbFacade;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value){
    Capacidad capacidad = new Capacidad();
    return capacidad;
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Capacidad)value).getIdcapacidad().toString();
    }
    
}
