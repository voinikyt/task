/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.web.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ikolev
 */
@Named("security")
@RequestScoped
public class SecurityController {
    private String userName;
    private String password;      
    
    public String login() {
         FacesContext context = FacesContext.getCurrentInstance();  
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();  
         HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();  
        try {
            request.login(userName, password);
        } catch (Exception e) {                        
        }
        
        return response.encodeRedirectURL("/view/home.jsf?faces-redirect=true");
    }
    public String logout() {
         FacesContext context = FacesContext.getCurrentInstance();  
         HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest(); 
        try {
            request.logout();
        } catch (ServletException ex) {
            Logger.getLogger(SecurityController.class.getName()).log(Level.SEVERE, null, ex);
        }
         HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse(); 
         return response.encodeRedirectURL("/view/home.jsf?faces-redirect=true");
    }
    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }        
}
