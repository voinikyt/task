<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">

<% pageContext.setAttribute("all", request.getAttribute("all")); %>

<wml>
	<card id="all" title="All Product Types">
		<table columns="2" align="RCC" border="1">		
			<c:forEach var='task' items='${all}'>
				<tr>					
					<td><strong><c:out value='${task.number}'/></strong></td>
					<td>
                                            <c:out value='${task.title}'/>
					</td>				
                                        <td>
                                            <c:out value='${task.employee.userName}'/>
					</td>				
                                        <td>
                                            <c:out value='${task.taskStatus.name}'/>
					</td>				
				</tr>		
			</c:forEach>
		</table>
		<anchor title="New">
			<go href="#new">				
				<img align="middle" src="<%=request.getContextPath()%>/images/add.gif" alt="top"></img>
			</go>
		</anchor>		
	</card>		
</wml>