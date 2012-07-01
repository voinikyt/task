/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.ejb.services;

import com.ivo.ejb.entities.CommentedTask;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ikolev
 */
@Stateless
public class CommentedTaskFacade extends AbstractFacade<CommentedTask> {
    @PersistenceContext(unitName = "task")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentedTaskFacade() {
        super(CommentedTask.class);
    }
    
}
