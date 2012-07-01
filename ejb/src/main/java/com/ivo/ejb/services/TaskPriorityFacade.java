/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.ejb.services;

import com.ivo.ejb.entities.TaskPriority;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ikolev
 */
@Stateless
public class TaskPriorityFacade extends AbstractFacade<TaskPriority> {
    @PersistenceContext(unitName = "task")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaskPriorityFacade() {
        super(TaskPriority.class);
    }
    
}
