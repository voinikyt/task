package com.ivo.ejb;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ivo.ejb.entities.TaskStatus;

@RunWith(Arquillian.class)
public class TaskStatusEJBArquillianTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return createDeploymentJAR();
	}

	private static JavaArchive createDeploymentJAR() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackages(true, TaskStatusEJB.class.getPackage())
				.addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@EJB
	private TaskStatusEJB taskStatusEJB;

	@Before
	public void assureEJBIsNotNull() {
		assertNotNull(taskStatusEJB);
		assertEquals(taskStatusEJB.getAllStatuses().size(), 0);
	}
	
	@After
	public void after() {
		assertEquals(taskStatusEJB.getAllStatuses().size(), 0);
	}

	/*
	 * This test cannot be realized with the expected attribute of the @Test annotation
	 * because all exceptions thrown from one of the EJB's public methods is wrapped in a
	 * RemoteException
	 */
	@Test
	public void testCreationOfATaskStatusWithNullParameter() {
		try {
			taskStatusEJB.addTaskStatus(null);
			fail("TaskStatusEJB.addTaskStatus should not accept null parameters");
		} catch (Exception e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testCorrectCreationOfTaskStatus() {
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setName("New");
		taskStatus.setDescription("Desc");

		taskStatusEJB.addTaskStatus(taskStatus);

		List<TaskStatus> allStatuses = taskStatusEJB.getAllStatuses();
		assertListSize(allStatuses, 1);
		taskStatusEJB.removeTaskStatus(allStatuses.get(0));
	}

	/*
	 * This test cannot be realized with the expected attribute of the @Test annotation
	 * because all exceptions thrown from one of the EJB's public methods is wrapped in a
	 * RemoteException
	 */
	@Test
	public void testRemovalOfTaskStatusWithNullParameter() {
		try {
			taskStatusEJB.removeTaskStatus(null);
			fail("TaskStatusEJB.removeTaskStatus should not accept null parameters");
		} catch (Exception e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}

	@Test
	public void testRemovalOfTaskStatus() {
		taskStatusEJB.addTaskStatus(creatStatus());

		List<TaskStatus> allStatuses = taskStatusEJB.getAllStatuses();
		assertListSize(allStatuses, 1);
		taskStatusEJB.removeTaskStatus(allStatuses.get(0));
		assertListSize(taskStatusEJB.getAllStatuses(), 0);
	}

	private void assertListSize(List<TaskStatus> allStatuses, int size) {
		assertTrue(allStatuses != null && allStatuses.size() == size);
	}

	@Test
	public void testCreationOfTaskStatusesWithSameNamesShouldFail() {

		TaskStatus ts = taskStatusEJB.addTaskStatus(creatStatus());

		try {
			taskStatusEJB.addTaskStatus(creatStatus());
			fail("TaskStatusEJB.removeTaskStatus should not accept null parameters");
		} catch (Exception e) {
		}
		taskStatusEJB.removeTaskStatus(ts);
	}

	@Test
	public void getAllStatuses() {
		System.out.println(taskStatusEJB.getAllStatuses().size());
		assertTrue(taskStatusEJB.getAllStatuses().size() == 0);

		taskStatusEJB.addTaskStatus(creatStatus());
		assertTrue(taskStatusEJB.getAllStatuses().size() == 1);
		taskStatusEJB.removeTaskStatus(taskStatusEJB.getAllStatuses().get(0));
	}
	
	@Test
	public void updateTaskShouldFailWithNullParam() {
		try {
			taskStatusEJB.updateTaskStatus(null);
			fail("TaskStatusEJB.updateTaskStatus should not accept null parameters");
		} catch (Exception e) {
			assertTrue(e.getCause() instanceof IllegalArgumentException);
		}
	}
	
	@Test
	public void updateTaskStatus() {
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setName("Init");
		taskStatus.setDescription("Init");
		taskStatus = taskStatusEJB.addTaskStatus(taskStatus);		
		
		String name = "Update";
		taskStatus.setName(name);
		taskStatusEJB.updateTaskStatus(taskStatus);
		
		TaskStatus updated = taskStatusEJB.getAllStatuses().get(0);
		assertEquals(updated.getName(), name);
		assertEquals(taskStatus.getName(), updated.getName());
		
		taskStatusEJB.removeTaskStatus(taskStatus);		
	}
	
	@Test
	public void findByName() {
		assertNull(taskStatusEJB.findByName("new"));
		
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setName("new");
		taskStatus.setDescription("new");
		taskStatus = taskStatusEJB.addTaskStatus(taskStatus);		
		
		assertNotNull(taskStatusEJB.findByName("new"));
		
		taskStatusEJB.removeTaskStatus(taskStatus);
	}

	private TaskStatus creatStatus() {
		TaskStatus taskStatus = new TaskStatus();
		taskStatus.setName("same");
		taskStatus.setDescription("Desc");
		return taskStatus;
	}
}