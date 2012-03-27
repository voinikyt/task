package com.ivo.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the taskstatus database table.
 * 
 */
@Entity
@Table(name = "taskstatus")
public class TaskStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "TASK_STATUS_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "TASKSTATUS")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TASK_STATUS_GEN")	
	private Long id;

	@Column(nullable = false, length = 2147483647)
	private String description;

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	public TaskStatus() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

}