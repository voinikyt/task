package com.ivo.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.Task;


@Stateless
public class EmployeeEJB {

	@PersistenceContext(name = "task")
	EntityManager em;

	public Employee create(Employee employee) {
		if (employee == null) throw new IllegalArgumentException("employee cannot be null");
		em.persist(employee);
		return employee;
	}

    @SuppressWarnings("unchecked")
    public List<Employee> getAll() {
        Query query = em.createQuery("SELECT e from Employee as e");
        return query.getResultList();
    }

    public Employee findByName(final String name) {
    	Query query = em.createQuery("SELECT e from Employee as e where e.name = :name");
    	query.setParameter("name", name);
    	return (Employee) query.getSingleResult();
    }

    public void remove(Long id) {
    	Employee managedEmployee = em.find(Employee.class, id);
		em.remove(managedEmployee);
    }

    @SuppressWarnings("unchecked")
    public List<Task> getAllTaskList(Long employeeId) {
    	Query query = em.createQuery("SELECT t from Task as t where t.executor.id = :employeeId");
    	query.setParameter("employeeId", employeeId);
    	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Task> getTaskList(final Long employeeId, final int firstResult, final int rowCount) {
    	Query query = em.createQuery("SELECT t from Task as t where t.executor.id = :employeeId");
    	query.setParameter("employeeId", employeeId);
    	query.setFirstResult(firstResult);
    	query.setMaxResults(rowCount);
    	return query.getResultList();
    }

}
