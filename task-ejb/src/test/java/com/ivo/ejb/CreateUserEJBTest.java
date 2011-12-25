package com.ivo.ejb;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ivo.ejb.entities.Employee;

@RunWith(Arquillian.class)
public class CreateUserEJBTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "foo.jar")
                .addClass(CreateUserEJB.class)
                .addManifestResource("test-persistence.xml", ArchivePaths.create("persistence.xml"));
    }

	@EJB
	CreateUserEJB createUserEJB;

	@Test
	public void testCreate() {
		assertNotNull(createUserEJB);
		Employee emp = new Employee();
		emp.setName("Ivo");
		createUserEJB.create(emp);
		assertNotNull(new Long(emp.getId()));
	}

}
