package com.ivo.ejb;

import static org.junit.Assert.assertTrue;

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
public class WorkflowEJBTest {

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
	public void whenCreateUserAndAssignHimToATaskHisTasksFieldShouldCountainTheTask() {
		Employee employee = createEmployee();
		TaskStatus taskStatus = createTaskStatus();

		Task task = new Task();
		task.setTitle("Should clean the kitchen!");
		task.setDescription("Start from the dishes, then clean the floor");
		task.setTaskStatus(taskStatus);
		task.setAproved(true);
		task.setExecutor(employee);
		taskEJB.createTask(task);


		Employee foundEmployee = employeeEJB.findByName(employee.getName());
		assertTrue(employeeEJB.getAllTaskList(foundEmployee.getId()).size() == 1);

		taskEJB.removeTask(task.getId());
		employeeEJB.remove(employee.getId());
		taskStatusEJB.removeTaskStatus(taskStatus);
	}

	private Employee createEmployee() {
		Employee employee = new Employee();
		employee.setName("ikolev");
		employeeEJB.create(employee);
		return employee;
	}

	private TaskStatus createTaskStatus() {
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setName("URGENT");
		taskStatus.setDescription("Used when the task is really urgent");
		taskStatusEJB.addTaskStatus(taskStatus);
		return taskStatus;
	}


}