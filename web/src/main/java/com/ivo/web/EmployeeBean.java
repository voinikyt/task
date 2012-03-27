package com.ivo.web;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.ivo.ejb.EmployeeEJB;
import com.ivo.ejb.entities.Employee;

@ManagedBean(name = "employee")
public class EmployeeBean {

	@EJB
	private EmployeeEJB employeeService;
	
	private List<Employee> all;
	
	private String employeeName;	

	public String create() {
		Employee emp = new Employee();
		emp.setName(employeeName);
		employeeService.create(emp);
		return null;
	}	
	
	public List<Employee> getAll() {
		if (all == null) {
			updateAll();
		}
		return all;
	}

	private void updateAll() {
		all = employeeService.getAll();
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}		
}
