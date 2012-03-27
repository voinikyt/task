package com.ivo.ejb.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@Table(name="task")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name = "TASK_GEN", table = "PRIMARY_KEY_SEQUENCES", pkColumnName = "TABLE_NAME", valueColumnName = "CURRENT_PK_VALUE", pkColumnValue = "TASK")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TASK_GEN")	
	private Long id;

	private Boolean approved;

	@Column(nullable=false, length=2147483647)
	private String description;

	@Column(nullable=false, length=20)
	private String number;

	@Column(nullable=false, length=200)
	private String title;

	//uni-directional many-to-one association to Employee
    @ManyToOne
	@JoinColumn(name="executor")
	private Employee executor;

	//uni-directional many-to-one association to TaskStatus
    @ManyToOne
	@JoinColumn(name="taskstatusid", nullable=false)
	private TaskStatus taskStatus;

    public Task() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getApproved() {
		return this.approved;
	}

	public void setApproved(Boolean approved) {
		this.approved = approved;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Employee getExecutor() {
		return this.executor;
	}

	public void setExecutor(Employee executor) {
		this.executor = executor;
	}
	
	public TaskStatus getTaskStatus() {
		return this.taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}
	
}