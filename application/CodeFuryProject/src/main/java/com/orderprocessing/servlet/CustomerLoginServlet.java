package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String customerLogin = request.getParameter("customerNameOrId");
		String customerPassword = request.getParameter("password");
		CustomerService cservice = new CustomerServiceImpl();
		try {
			Customer customer = cservice.validateCustomer(customerLogin, customerPassword);
			if (customer != null) {
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);
				request.getRequestDispatcher("customerOrderManagement.jsp").forward(request, response);
			} else {
				request.setAttribute("ErrorMessage", "Login Credentials Failed");
				request.getRequestDispatcher("customerLogin.jsp").forward(request, response);

			}
		} catch (Exception exception) {
			request.setAttribute("ErrorMessage", "Login Credentials Failed");
			request.getRequestDispatcher("customerLogin.jsp").forward(request, response);
		}
	}

}
