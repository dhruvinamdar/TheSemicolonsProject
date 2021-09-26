package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerServlet
 */
@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		// String customerName = request.getParameter("customerName");
		String customerLogin = request.getParameter("customerNameOrId");
		System.out.println(customerLogin);
		String customerPassword = request.getParameter("password");
		System.out.println(customerPassword);
		CustomerService cservice = new CustomerServiceImpl();
		try {
			Customer customer = cservice.validateCustomer(customerLogin, customerPassword);
			if (customer != null) {

//				HttpSession session = request.getSession(false);
				// session.setMaxInactiveInterval(20);
//				if (session == null) {
				// System.out.print("new session");
//					session = request.getSession(true);
				// request.getSession(true);
//					session.setAttribute("customer", customer);
//				} else {
//				session.invalidate();
//				session = request.getSession(true);
//			}
				request.setAttribute("customer", customer);
				System.out.println("Customer login");
				request.getRequestDispatcher("customerOrderManagement.jsp").forward(request, response);
			} else {
				System.out.println("in else");
				request.getRequestDispatcher("login.html").forward(request, response);

			}
		} catch (Exception exception) {
			request.setAttribute("", exception.getMessage());
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

}
