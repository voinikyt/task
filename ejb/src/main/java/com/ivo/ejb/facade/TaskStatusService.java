package com.ivo.ejb.facade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ivo.ejb.entities.TaskStatus;

public class TaskStatusService extends AbstractFacade<TaskStatus> {

	@PersistenceContext(name = "task")
	EntityManager em;
	
	public TaskStatusService(Class<TaskStatus> entityClass) {
		super(entityClass); 
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
