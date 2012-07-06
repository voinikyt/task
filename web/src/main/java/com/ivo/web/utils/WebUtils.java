/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.web.utils;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ikolev
 */
@RequestScoped
public class WebUtils {
    
    @Produces @RequestScoped
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
    
    @Produces @Named
    public String getRootPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
    
    @Produces @RequestScoped
    public HttpServletRequest getServletRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }
    
    @Produces @RequestScoped
    public HttpServletResponse getServletResponce() {
        return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
    }
}
