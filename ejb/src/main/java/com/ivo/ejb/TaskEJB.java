package com.ivo.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ivo.ejb.entities.Task;

@Stateless
public class TaskEJB {

	@PersistenceContext(name = "task")
	private EntityManager em;

	public Task createTask(Task task) {
		em.persist(task);
		return task;
	}

	public Task updateTask(Task task) {
		Task managedTask = em.merge(task);
		return managedTask;
	}

	public void removeTask(Long taskId) {
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
		Query query = em.createQuery("SELECT t from Task as t where t.id = :taskId");
		query.setParameter("taskId", taskId);
		return (Task) query.getSingleResult();
	}
}
