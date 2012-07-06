/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.web.controllers;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.TaskPriority;
import com.ivo.ejb.entities.TaskStatus;
import com.ivo.ejb.services.EmployeeFacade;
import com.ivo.ejb.services.TaskPriorityFacade;
import com.ivo.ejb.services.TaskStatusFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author ikolev
 */
@Named("staticData")
@ApplicationScoped
public class StaticDataController implements Serializable {
    
    @EJB
    private EmployeeFacade employeeFacade;
    
    @EJB
    private TaskPriorityFacade taskPriorityFacade;
    
    @EJB
    private TaskStatusFacade taskStatusFacade;
    
    private List<Employee> allEmployees;
    private List<TaskPriority> allPrioriries;
    private List<TaskStatus> allStatuses;
    
    @PostConstruct
    public void initStaticData() {
        allEmployees = employeeFacade.findAll();
        allPrioriries = taskPriorityFacade.findAll();
        allStatuses = taskStatusFacade.findAll();
    }
        
    public List<Employee> getAllEmployees() {
        return employeeFacade.findAll();
    }    
    
    public TaskPriority getTaskPriority(String name) {
        for (TaskPriority tp : allPrioriries) {
            if (tp.getName().equalsIgnoreCase(name)) return tp;
        }
        return null;
    }
    
    public TaskStatus getTaskStatus(String name) {
        for (TaskStatus ts : allStatuses) {
            if (ts.getName().equalsIgnoreCase(name)) return ts;
        }
        return null;
    }
}
