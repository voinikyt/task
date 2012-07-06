/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.mobile;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.Task;
import com.ivo.ejb.services.EmployeeFacade;
import com.ivo.ejb.services.TaskFacade;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ikolev
 */
@ManagedBean
@ViewScoped
public class MobileTaskController implements Serializable {
    
    @EJB
    private EmployeeFacade employeeFacade;
    
    @EJB
    private TaskFacade taskFacade;
    
    public List<Task> getAllTaskForCurrentEmployee() {
        String currentUserName = getRemoteUser();
        return taskFacade.findByUser(currentUserName);
    }
    
    public Employee getUser() {
        return employeeFacade.findByUserName(getRemoteUser());
    } 
    
    private String getRemoteUser() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }
}
