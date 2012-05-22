package com.ivo.ejb;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ivo.ejb.entities.TaskStatus;

/**
 * State-less EJB controlling persistence and logic operations with {@link TaskStatus} objects
 * @author ikolev
 */
@Stateless
public class TaskStatusEJB {
	@PersistenceContext(name = "task")
	public EntityManager em;

	/**
	 * Persists a new TaskStatus object
	 * @param taskStatus
	 * @return
	 * @throws IllegalArgumentException if a the parameter is null
	 */
	public TaskStatus addTaskStatus(TaskStatus taskStatus) {
		if (taskStatus == null) throw new IllegalArgumentException("taskStatus cannot be null");
		em.persist(taskStatus);
		return taskStatus;
	}

	public void updateTaskStatus(TaskStatus taskStatus) {
		if (taskStatus == null || taskStatus.getId() == null && taskStatus.getId() < 0) {
			throw new IllegalArgumentException("TaskStatus is null or TaskStatus.id is null");
		}
		em.merge(taskStatus);
	}

	/**
	 * Removes a TaskStatus object from the database
	 * @param taskStatus
	 * @throws IllegalArgumentException if the parameter is null or the parameter's id is null
	 */
	public void removeTaskStatus(TaskStatus taskStatus) {
		if (taskStatus == null || taskStatus.getId() == null) throw new IllegalArgumentException("taskStatus cannot be null");
		TaskStatus managedTaskStatus = em.find(TaskStatus.class, taskStatus.getId());
		em.remove(managedTaskStatus);
	}

	/**
	 * Returns all TaskStatus objects from the persistence context
	 * @return a {@link List} of TaskStatus objects ordered by name
	 */
	@SuppressWarnings("unchecked")
	public List<TaskStatus> getAllStatuses() {
		Query query = em.createQuery("SELECT ts FROM TaskStatus as ts ORDER BY ts.name ");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public TaskStatus findByName(String name) {
		Query query = em.createQuery("SELECT ts FROM TaskStatus as ts WHERE ts.name = :name");
		query.setParameter("name", name);
		List<TaskStatus> list = query.getResultList();
		if (list.size() == 1)
			return (TaskStatus) list.get(0);
		return null;
	}
}