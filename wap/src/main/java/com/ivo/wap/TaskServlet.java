/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivo.wap;

import com.ivo.ejb.services.TaskFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ikolev
 */
@WebServlet(name = "task", urlPatterns = {"/TaskServlet"})
public class TaskServlet extends HttpServlet {

    @EJB
    private TaskFacade taskFacade;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {      
        request.setAttribute("all", taskFacade.findAll());		
        request.getRequestDispatcher("/allTasks.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
