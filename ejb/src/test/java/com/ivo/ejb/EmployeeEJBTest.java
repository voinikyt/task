package com.ivo.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ivo.ejb.entities.Employee;

@RunWith(Arquillian.class)
public class EmployeeEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackages(true, EmployeeEJB.class.getPackage())
				.addManifestResource("test-persistence.xml", "persistence.xml");
	}

	@EJB
	EmployeeEJB employeeEJB;

	@Test
	public void testCreateEmployee() {
		Employee employee = createEmployee();
		employeeEJB.create(employee);
		assertTrue(employee.getId() != null);
		assertTrue(employeeEJB.getAll().size() == 1);
		employeeEJB.remove(employee.getId());
		assertTrue(employeeEJB.getAll().size() == 0);
	}

	@Test
	public void testGetByName() {
		Employee employee = createEmployee();
		employeeEJB.create(employee);
		Employee found = employeeEJB.findByName(employee.getName());
		assertEquals(employee.getName(), found.getName());
		employeeEJB.remove(employee.getId());
		assertTrue(employeeEJB.getAll().size() == 0);
	}

	private Employee createEmployee() {
		Employee employee = new Employee();
		employee.setName("ikolev");
		return employee;
	}

}