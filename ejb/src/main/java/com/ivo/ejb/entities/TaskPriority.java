package com.ivo.ejb.entities;



import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The persistent class for the taskpriority database table.
 * 
 */
@Entity
@Table(name = "taskpriority")
@NamedQueries({
    @NamedQuery(name = "TaskPriority.findAll", query = "SELECT t FROM TaskPriority t"),
    @NamedQuery(name = "TaskPriority.findById", query = "SELECT t FROM TaskPriority t WHERE t.id = :id"),
    @NamedQuery(name = "TaskPriority.findByName", query = "SELECT t FROM TaskPriority t WHERE t.name = :name"),
    @NamedQuery(name = "TaskPriority.findByLevel", query = "SELECT t FROM TaskPriority t WHERE t.level = :level")})
public class TaskPriority implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "id")
        @TableGenerator(name = "TASK_PRIORITY_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "TASKPRIORITY")
        @GeneratedValue(strategy = GenerationType.TABLE, generator = "TASK_PRIORITY_GEN")	
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Column(name = "level")
	private Integer level;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "name")
	private String name;

	public TaskPriority() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}