/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.ejb.services;

import com.ivo.ejb.entities.Task;
import java.util.List;
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
public class TaskFacade extends AbstractFacade<Task> {
    @PersistenceContext(unitName = "task")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskFacade() {
        super(Task.class);
    }
    
    @Override
    @RolesAllowed("EMPLOYEE")
    public void create(Task entity) {
        Query query = em.createQuery("SELECT MAX(t.number) FROM Task t");
        Integer maxNumber = (Integer) query.getSingleResult();
        entity.setNumber((maxNumber == null) ? 1 : maxNumber + 1);               
        super.create(entity);
    }        
    
    public List<Task> findByUser(String userName) {
        Query query = em.createNamedQuery("Task.findByUser");
        query.setParameter("userName", userName);
        return query.getResultList();        
    }
}
