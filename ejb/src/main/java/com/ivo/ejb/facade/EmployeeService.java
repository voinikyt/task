package com.ivo.ejb.facade;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ivo.ejb.entities.Employee;

public class EmployeeService extends AbstractFacade<Employee> {
	
	@PersistenceContext(name = "task")
	EntityManager em;
	
	public EmployeeService(Class<Employee> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
