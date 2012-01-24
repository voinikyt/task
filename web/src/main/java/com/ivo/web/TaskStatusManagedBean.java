package com.ivo.web;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ivo.ejb.TaskStatusEJB;
import com.ivo.ejb.entities.TaskStatus;

@ManagedBean(name = "status")
public class TaskStatusManagedBean {

	@EJB
	private TaskStatusEJB taskStatusEJB;

	private TaskStatus taskStatus = new TaskStatus();

	private List<TaskStatus> allStatuses;

	public List<TaskStatus> getAllStatuses() {
		allStatuses = taskStatusEJB.getAllStatuses();
		return allStatuses;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String create() {
		taskStatusEJB.addTaskStatus(taskStatus);
		return null;
	}

}
