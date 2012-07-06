package com.ivo.ejb.services;

import com.ivo.ejb.entities.Employee;
import com.ivo.ejb.entities.TaskPriority;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.arquillian.api.Deployment;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EmployeeServiceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar").
                addPackages(true, EmployeeFacade.class.getPackage()).
                addPackages(true, Employee.class.getPackage()).
                addAsManifestResource("test-persistence.xml", "persistence.xml");
    }

    @EJB
    EmployeeFacade employeeFacade;
    
    @Test
    public void testCreationOfTask() throws NamingException {
        loginAs("admin", "adminPassword");
        
        Employee e = new Employee();
        e.setFirstName("Ivaylo");
        e.setLastName("Kolev");
        e.setUserName("ikolev");
        e.setRole("ADMIN");
        e.setPassword("password");
        employeeFacade.create(e);
        Assert.assertNotNull(employeeFacade.findByUserName("ikolev"));
        logOut();
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