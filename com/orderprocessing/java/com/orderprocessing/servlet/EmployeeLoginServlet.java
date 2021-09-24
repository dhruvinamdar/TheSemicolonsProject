package com.orderprocessing.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orderprocessing.entity.Employee;
import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

@WebServlet("/loginEmployee")
public class EmployeeLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic
		
		response.setContentType("text/html");
		EmployeeService service = new EmployeeServiceImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			Employee employee = service.login(username,password);
			System.out.println("Last login" + employee.getLastLoginDateTime());
			if(employee!=null)
			{
//				HttpSession session = request.getSession(false);
//				if(session==null)
//				{
//					session = request.getSession();
//					session.setAttribute("employee", employee);
//				}
				request.setAttribute("employee",employee);
				request.getRequestDispatcher("employeeOrderManagement.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("login.html").forward(request, response);
			}
		}
		catch(Exception exception) {
			//exception.printStackTrace();
			request.setAttribute("",exception.getMessage());
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doGet(req, resp);
//	}
}
