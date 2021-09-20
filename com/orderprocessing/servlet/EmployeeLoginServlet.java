package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginEmployee")
public class EmployeeLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic
		
		response.setContentType("text/html");
		//EmployeeService service = new EmployeeService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			//Employee employee = service.login(username,password);
			/*if(employee!=null)
			{
				request.setParameter("employee",employee)
				request.getRequestDispatcher("").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("").forward(request, response);
			}*/	
		}
		catch(Exception exception) {
			//exception.printStackTrace();
			request.setAttribute("",exception.getMessage());
			request.getRequestDispatcher("").forward(request, response);
		}
	}
}
