package com.ivo.ejb.facade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ivo.ejb.entities.Task;

public class TaskService extends AbstractFacade<Task> {

	@PersistenceContext(name = "task")
	EntityManager em;
	
	public TaskService(Class<Task> entityClass) {
		super(entityClass);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
