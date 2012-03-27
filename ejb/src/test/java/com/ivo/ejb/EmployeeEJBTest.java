package com.ivo.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.Task;
import com.ivo.ejb.entities.TaskStatus;

@RunWith(Arquillian.class)
public class EmployeeEJBTest {

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
	TaskEJB taskEJB;

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

	@Test
	public void testGetAllTask() {
		Employee employee = createEmployee();
		employeeEJB.create(employee);

		TaskStatus taskStatus = createTaskStatus();
		taskStatusEJB.addTaskStatus(taskStatus);

		Task task = new Task();
		task.setDescription("Desc");
		task.setTitle("Title");
		task.setExecutor(employee);		
		task.setTaskStatus(taskStatus);
		task.setNumber("Uni");
		
		taskEJB.createTask(task);

		assertEquals(employeeEJB.getAllTaskList(employee.getId()).size(), 1);

		taskEJB.removeTask(task.getId());
		taskStatusEJB.removeTaskStatus(taskStatus);
		employeeEJB.remove(employee.getId());
	}

	@Test
	public void testGetTaskListPagination() {
		Employee employee = createEmployee();
		employeeEJB.create(employee);

		TaskStatus taskStatus = createTaskStatus();
		taskStatusEJB.addTaskStatus(taskStatus);

		String taskTitlePrefix = "Title-";
		for (int i = 0; i < 16; i++) {
			Task task = new Task();
			task.setDescription("Desc");
			task.setTitle(taskTitlePrefix + i);
			task.setExecutor(employee);
			task.setTaskStatus(taskStatus);
			task.setNumber("Uni" + i);
			taskEJB.createTask(task);
		}

		List<Task> allTasks = taskEJB.getAll();
		assertEquals(16, allTasks.size());
		assertEquals(16, employeeEJB.getAllTaskList(employee.getId()).size());

		List<Task> taskList = employeeEJB.getTaskList(employee.getId(), 0, 5);
		assertEquals(5, taskList.size());

		for (int i = 0; i < allTasks.size(); i++ )
			taskEJB.removeTask(allTasks.get(i).getId());
		taskStatusEJB.removeTaskStatus(taskStatus);
		employeeEJB.remove(employee.getId());
	}

	private TaskStatus createTaskStatus() {
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setName("Name");
		taskStatus.setDescription("Desc");
		return taskStatus;
	}

	private Employee createEmployee() {
		Employee employee = new Employee();
		employee.setName("ikolev");
		return employee;
	}

}