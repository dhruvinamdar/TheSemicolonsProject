package com.orderprocessing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;
import com.orderprocessing.utility.Customer;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		//String customerName = request.getParameter("customerName");
		String customerLogin = request.getParameter("customerId");
		String customerPassword = request.getParameter("password");
		CustomerService cservice = new CustomerServiceImpl();
		Customer customer = cservice.validateCustomer(customerLogin, customerPassword);
		
		   
		if(customer!=null){
			HttpSession session = request.getSession(false);
			//session.setMaxInactiveInterval(20);
			if(session == null) {
				//System.out.print("new session");
				session = request.getSession(true);
				session.setAttribute("customer", customer);
			}
			else {
				session.invalidate();
				session=request.getSession(true);
			}
			
			RequestDispatcher rd=request.getRequestDispatcher("CustomerViewOrdersServlet");
			rd.forward(request, response);
		}
		else {
			out.println("<h1>Please enter the correct credentials</h1>");
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
			
			
		}
		
	}

}
