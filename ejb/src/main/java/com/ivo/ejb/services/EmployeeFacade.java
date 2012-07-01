/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.ejb.services;

import com.ivo.ejb.entities.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ikolev
 */
@Stateless
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
    
}
