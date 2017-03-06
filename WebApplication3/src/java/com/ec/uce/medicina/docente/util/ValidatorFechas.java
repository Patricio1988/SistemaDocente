/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ec.uce.medicina.docente.util;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


/**
 * Declaración de la clase ValidatorFechas que valida que la fecha de inicio debe ser menor que la fecha final
 *
 * @author Patricio
 * @version 2.0
 */
@FacesValidator("validatorFechas")
public class ValidatorFechas implements  Validator{
/**Metodo que valida que la fecha de inicio sea menor qeu la fecha de fin
     * @param context
     * @param component
     * @param value
 */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
           if (value == null) {
            return;
        }
        Object startDateValue = component.getAttributes().get("txtFechaInicio");
        System.out.println("Fecha Obtenida: "+startDateValue);
        if (startDateValue==null) {
            return;
        }
         
        Date startDate = (Date)startDateValue;
        Date endDate = (Date)value;
        if (endDate.before(startDate)) {
             FacesMessage message = new FacesMessage("La fecha Final no puede ser anterior a la fecha Inicial.");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

   
    
}
