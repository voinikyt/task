package com.ivo.ejb.entities;



import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the taskstatus database table.
 * 
 */
@Entity
@Table(name = "taskstatus")
@NamedQueries({
		@NamedQuery(name = "Taskstatus.findAll", query = "SELECT t FROM TaskStatus t"),
		@NamedQuery(name = "Taskstatus.findById", query = "SELECT t FROM TaskStatus t WHERE t.id = :id"),
		@NamedQuery(name = "Taskstatus.findByName", query = "SELECT t FROM TaskStatus t WHERE t.name = :name"),
		@NamedQuery(name = "Taskstatus.findByDescription", query = "SELECT t FROM TaskStatus t WHERE t.description = :description") })
public class TaskStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
        @TableGenerator(name = "TASK_STATUS_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "TASKSTATUS")
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "TASK_STATUS_GEN")	
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 2147483647)
	@Column(name = "description")
	private String description;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name")
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "taskStatus", fetch = FetchType.EAGER)
	private List<Task> tasks;

	public TaskStatus() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}