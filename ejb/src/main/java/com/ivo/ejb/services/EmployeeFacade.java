/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.ejb.services;

import com.ivo.ejb.entities.Employee;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ikolev
 */
@Stateless
@DeclareRoles({"ADMIN", "EMPLOYEE"})
@RolesAllowed({"ADMIN", "EMPLOYEE"})
public class EmployeeFacade extends AbstractFacade<Employee> {
    @PersistenceContext(unitName = "task")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }
    
    public Employee findByUserName(final String userName) {
        Query query = em.createNamedQuery("Employee.findByUsername");
        query.setParameter("username", userName);
        return (Employee) query.getSingleResult();
    }
    
    @Override
    @RolesAllowed("ADMIN")
    public void create(Employee entity) {
        super.create(entity);
    }
    
}
