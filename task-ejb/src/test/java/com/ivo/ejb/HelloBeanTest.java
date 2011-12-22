package com.ivo.ejb;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class HelloBeanTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap.create(JavaArchive.class, "helloBean.jar")
				.addClass(HelloBean.class)
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
	}

	@EJB
	private HelloBean helloBean;

	@Test
	public void testSayHi() {
		String name = "tester";
		assertEquals(helloBean.sayHi(name), "Hello arkada6 tester");
	}

}
