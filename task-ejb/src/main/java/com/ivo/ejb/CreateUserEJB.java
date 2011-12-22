package com.ivo.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ivo.ejb.entities.User;

@Stateless
public class CreateUserEJB {

	@PersistenceContext(name="testPU")
	EntityManager em;

	public void create(final String name) {
		User user = new User();
		user.setName(name);
		em.persist(user);
	}
}
