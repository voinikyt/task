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
		@NamedQuery(name = "Taskstatus.findByName", query = "SELECT t FROM TaskStatus t WHERE t.name = :name")})
public class TaskStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id", updatable = false)        
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name")
	private String name;

	public TaskStatus() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}