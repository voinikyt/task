/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.web.controllers;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.Task;
import com.ivo.ejb.entities.TaskPriority;
import com.ivo.ejb.entities.TaskStatus;
import com.ivo.ejb.services.EmployeeFacade;
import com.ivo.ejb.services.TaskFacade;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ikolev
 */
@Named
@ConversationScoped
public class TaskController implements Serializable {

    @EJB
    private TaskFacade taskFacade;
    
    @EJB
    private EmployeeFacade employeeFacade;            
    
    private Task task;
    
    private String taskStatus;
    private String taskPriority;
    
    @Inject
    private EmployeeController employeeController; 

    @Inject
    private Conversation conversation;

    @Inject
    private StaticDataController staticDataController;
    
    @Inject
    private FacesContext facesContext;            
    
    @PostConstruct
    public void initTask() {
        task = new Task();
        task.setEmployee( employeeController.getUser());
        task.setDateCreated(new Date());            
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }        
    
    public void createTask(ActionEvent e) {
        TaskPriority tp = staticDataController.getTaskPriority(taskPriority);
        TaskStatus ts = staticDataController.getTaskStatus(taskStatus);
        Employee emp = employeeFacade.findByUserName(task.getEmployee().getUserName());
        
        task.setTaskPriority(tp);
        task.setTaskStatus(ts);
        task.setEmployee(emp);
        taskFacade.create(task);
        
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Awfully created task", "Task number #" + task.getNumber()));
        
        Logger logger = Logger.getLogger(this.getClass().getName());     
        logger.log(Level.INFO, task.toString());
        
    }

    public void beginConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    public void endConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }
    
    private List<Task> allTasksForCurrentUser;
    
    public List<Task> getAllTasksForCurrentUser() {
        return taskFacade.findByUser(employeeController.getUser().getUserName());        
    }
    
    public List<Task> getAllTasksForUser(String userName) {
        return taskFacade.findByUser(userName);        
    }
}

