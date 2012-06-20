package com.ivo.ejb.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/**
 * The persistent class for the usertable database table.
 * 
 */
@Entity
@Table(name="usertable")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false, length=10)
	private String userId;

	@OneToOne
	@JoinColumn(name="EMPLOYEEID")
	private Employee employee;

	@Column(nullable=false, length=50)
	private String password;

    public User() {
    }

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}