package com.ivo.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.User;

@RunWith(Arquillian.class)
public class UserServiceTest {
	
	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackages(true, EmployeeEJB.class.getPackage())
				.addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@EJB
	EmployeeEJB employeeEJB;

	@EJB
	TaskStatusEJB taskStatusEJB;

	@EJB
	UserService userService;
	
	@Test
	public void registerNewUserTest() {
		User user = createUser();
		User persistedUser = userService.registerUser(user);		
		assertNotNull(persistedUser);		
		assertEquals(user.getEmployee().getName(), persistedUser.getEmployee().getName());
		userService.removeUser(persistedUser.getUserId());
		assertNull(userService.getUser(user.getUserId()));
	}
	
	@Test
	public void getUserTest() {
		User user = createUser();
		User persisted = userService.registerUser(user);		
		User retrievedUser = userService.getUser(persisted.getUserId());
		assertEquals(persisted.getPassword(), retrievedUser.getPassword());		
	}
	
	private User createUser() {
		Employee employee = new Employee();
		employee.setName("Ivaylo Kolev");				
		
		User user = new User();
		user.setUserId("ivo");
		user.setPassword("ivo");
		user.setEmployee(employee);
		return user;
	}
	
	
}
