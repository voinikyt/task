package com.ivo.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ivo.ejb.entities.Employee;


@Stateless
public class CreateUserEJB {

	@PersistenceContext(name = "task")
	EntityManager em;

	public Employee create(Employee employee) {
		if (employee == null) throw new IllegalArgumentException("employee cannot be null");
		em.persist(employee);
		return employee;
	}
}
