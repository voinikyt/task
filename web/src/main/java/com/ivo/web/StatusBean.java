package com.ivo.web;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

import com.ivo.ejb.TaskStatusEJB;
import com.ivo.ejb.entities.TaskStatus;

@ManagedBean(name = "status")
public class StatusBean {
	@EJB
	private TaskStatusEJB taskStatusEJB;

	private List<TaskStatus> allStatuses;

	private TaskStatus taskStatus = new TaskStatus();	
	
	public List<TaskStatus> getAllStatuses() {
		if (allStatuses == null) {
			refreshAll();
		}
		return allStatuses;
	}

	private void refreshAll() {
		allStatuses = taskStatusEJB.getAllStatuses();
	}
	
	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String create() {		
		if (existsWithName(taskStatus.getName())) {
			addAlreadyExistsMessage();
		} else {
			taskStatusEJB.addTaskStatus(taskStatus);
			refreshAll();
		}
		return null;
	}

	private void addAlreadyExistsMessage() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Task status with name " + taskStatus.getName() + " already exists!", ""));
	}
	
	private boolean existsWithName(String name) {
		return findByName(name) != null;
	}
	
	private TaskStatus findByName(String name) {		
		return taskStatusEJB.findByName(name);
	}

	public String delete() {
		taskStatusEJB.removeTaskStatus(taskStatus);
		refreshAll();
		return null;
	}	
	
    public void update(RowEditEvent event) {            	
    	if (event.getObject() instanceof TaskStatus) {
    		final TaskStatus status = (TaskStatus) event.getObject();
    		if (existsWithName(status.getName())) {    			
    			addAlreadyExistsMessage();    			
    		} else {    		 
    			taskStatusEJB.updateTaskStatus(status);
    			this.taskStatus = status;
    		}
    		refreshAll();
    	}    
    	
    }
    
    public void select(SelectEvent event) {     
    	if (event.getObject() instanceof TaskStatus) {
    		this.taskStatus = (TaskStatus) event.getObject();
    	}
    }    
}
