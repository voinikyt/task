package com.ivo.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "security")
@RequestScoped
public class SecurityBean {
	private String userName;
	private String password;
	
	public String login() {
		 FacesContext context = FacesContext.getCurrentInstance();  
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();  
            
         try {  
        	 System.out.println("User: " + userName + " Pass: " + password);
              request.login(userName, password);  
         } catch (Throwable e) {  
              context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed!", null));  
              return null;  
         }                     
            
         HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
         if(request.isUserInRole("ADMIN")) {
        	 System.out.println("ADMIN is logged");
              return response.encodeRedirectURL("/view/admin/admin.jsf?faces-redirect=true");  
         } else {  
        	 	System.out.println("EMPLOYEE is logged");
              return response.encodeRedirectURL("/view/home.jsf?faces-redirect=true");
         }           
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
