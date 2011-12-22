package com.ivo.ejb;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloBean
 */
@Stateless
public class HelloBean {

	public String sayHi(final String name) {
		return "Hello arkada6 " + name;
	}

}
