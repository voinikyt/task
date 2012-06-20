package com.ivo.web;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "home")
public class HomeBean {

	public String getHomePageForUser() {
		return "view/admin/admin.jsf?faces-redirect=true";
	}
	
}
