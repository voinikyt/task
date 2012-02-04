package com.ivo.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ivo.ejb.entities.Task;

@Stateless
public class TaskEJB {

	@PersistenceContext(name = "task")
	private EntityManager em;

	@Resource
	SessionContext sessionContext;

	public Task createTask(Task task) {
		if (task == null) throw new IllegalArgumentException("You cannot create a null task");
		em.persist(task);

		System.out.println("ROLE: " + sessionContext.getCallerPrincipal().getName());

		return task;
	}

	public Task updateTask(Task task) {
		if (task == null) throw new IllegalArgumentException("You cannot create a null task");
		Task managedTask = em.merge(task);
		return managedTask;
	}

	public void removeTask(Long taskId) {
		if (taskId == null) throw new IllegalArgumentException("taskId cannot e null");
		Task task = em.find(Task.class, taskId);
		em.remove(task);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Task> getAll() {
		Query query = em.createQuery("SELECT t from Task as t");
		return query.getResultList();
	}

	public Task findByID(Long taskId) {
		if (taskId == null) throw new IllegalArgumentException("taskId cannot e null");
		Query query = em.createQuery("SELECT t from Task as t where t.id = :taskId");
		query.setParameter("taskId", taskId);
		return (Task) query.getSingleResult();
	}

}
