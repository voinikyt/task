package com.ivo.ejb;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ivo.ejb.entities.Task;

@RunWith(Arquillian.class)
public class TaskEJBTest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar").addPackages(true, TaskEJB.class.getPackage())
				.addAsManifestResource("test-persistence.xml", "persistence.xml");
	}

	@EJB
	TaskEJB taskEJB;

	@Test
	public void testCreationOfTask() throws NamingException {
		loginAs("admin", "adminPassword");
		Task task = new Task();
		task.setAproved(true);
		task.setDescription("Description");
		task.setTitle("Title");
		taskEJB.createTask(task);
		assertNotNull(task.getId());
		assertTrue(taskEJB.getAll().size() == 1);
		taskEJB.removeTask(task.getId());
		assertTrue(taskEJB.getAll().size() == 0);
		logOut();
	}

	@Test
	public void tets() throws NamingException {
        loginAs("admin", "adminPassword");
		Task task = new Task();
		task.setAproved(true);
		task.setDescription("Description");
		task.setTitle("Title");
		taskEJB.createTask(task);
		assertNotNull(task.getId());
		assertTrue(taskEJB.getAll().size() == 1);
		taskEJB.removeTask(task.getId());
		assertTrue(taskEJB.getAll().size() == 0);
	}

	private void loginAs(String userName, String password) throws NamingException {
		Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        p.put(Context.SECURITY_PRINCIPAL, userName);
        p.put(Context.SECURITY_CREDENTIALS, password);
        new InitialContext(p);
	}

	private void logOut() throws NamingException {
		Properties p = new Properties();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.LocalInitialContextFactory");
        p.put(Context.SECURITY_PRINCIPAL, "guest");
        p.put(Context.SECURITY_CREDENTIALS, "guest");
        new InitialContext(p);
	}

}