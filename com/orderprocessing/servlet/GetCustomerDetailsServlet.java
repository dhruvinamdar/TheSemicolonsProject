package com.orderprocessing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getCustomerDetails")
public class GetCustomerDetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String credentials = request.getParameter("customerCreds");
		//EmployeeService service = new EmployeeService();
		//String customerData = service.getCustomerDetails(credentials);
		//out.println(customerData);
	}
	
}
