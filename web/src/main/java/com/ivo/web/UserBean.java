package com.ivo.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ivo.ejb.UserService;
import com.ivo.ejb.entities.User;

@ManagedBean(name = "user")
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private User user;

	@EJB
	private UserService userService;
	
	public String getUserName() {				
		return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
	}
	
	public User getUser() {
		if (user == null) {			
			user = userService.getUser(getUserName());
		}		
		return user;
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully logout " + getUserName()));
		return "login?faces-redirect=true";
	}
	
}
