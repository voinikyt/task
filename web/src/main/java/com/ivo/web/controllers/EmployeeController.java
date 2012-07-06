/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.web.controllers;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.services.EmployeeFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ikolev
 */
@Named
@SessionScoped
public class EmployeeController implements Serializable {
    
    @EJB
    private EmployeeFacade employeeFacade;
 
    private Employee employee;
    
    @Inject    
    private transient FacesContext facesContext;
    
    public synchronized Employee getUser() {
        if (employee == null) {
            String userName = facesContext.getExternalContext().getRemoteUser();
            employee = employeeFacade.findByUserName(userName);
        }        
        return employee;
    }          
    
    public List<Employee> getAllUsers() {
        return employeeFacade.findAll();
    }
}
