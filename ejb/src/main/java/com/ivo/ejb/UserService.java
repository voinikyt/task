package com.ivo.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ivo.ejb.entities.User;

@Stateless
public class UserService {

	@PersistenceContext(name = "task")
	private EntityManager em;
	
	@EJB
	private EmployeeEJB employeeEJB;
	
	public User registerUser(final User user) {
		if (user.getEmployee() != null && user.getEmployee().getId() == null) {
			employeeEJB.create(user.getEmployee());
		}
		em.persist(user);
		return user;		
	}

	public User getUser(String userId) {
		return em.find(User.class, userId);		
	}

	public void removeUser(String userId) {		
		em.remove(this.getUser(userId));
	}

}
