package com.ivo.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class CreateUserEJBTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClass(CreateUserEJB.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@EJB
	CreateUserEJB createUserEJB;

	@Test
	public void testCreate() {
		assertNotNull(createUserEJB);
		//assertNotNull(em);
//		createUserEJB.create("name");
	}

}
