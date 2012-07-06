/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.admin.users;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.services.EmployeeFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author ikolev
 */
@ManagedBean
@ViewScoped
public class UsersController implements Serializable {

    @EJB
    private EmployeeFacade employeeFacade;
    private Employee employee;
    private String confirmationPasswor;    
    private List<Employee> allUsers;

    @PostConstruct
    public void init() {
        employee = new Employee();
       // allUsers = employeeFacade.findAll();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getConfirmationPasswor() {
        return confirmationPasswor;
    }

    public void setConfirmationPasswor(String confirmationPasswor) {
        this.confirmationPasswor = confirmationPasswor;
    }

    public void save(ActionEvent actionEvent) {
        if (!confirmationPasswor.equalsIgnoreCase(getEmployee().getPassword())) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords should be the same", "Validation Error"));
        } else {
            try {
                employee.setId(null);
                employee.setRole("EMPLOYEE");
                employeeFacade.create(employee);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully added employee", "superb"));
                allUsers = employeeFacade.findAll();
            } catch (Exception e) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error while creating employee", e.getMessage()));
            }
        }
    }
    
    public List<Employee> getAllUsers() {
        return employeeFacade.findAll();
    }   
    
    public String delete(Employee user) {
        Employee emp = employeeFacade.findByUserName(user.getUserName());
        employeeFacade.remove(emp);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deleted user", user.getUserName()));                    
        allUsers = employeeFacade.findAll();
        return null;
    }
}