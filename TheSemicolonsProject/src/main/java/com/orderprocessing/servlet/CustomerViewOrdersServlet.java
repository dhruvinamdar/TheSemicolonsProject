package com.orderprocessing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;

/**
 * Servlet implementation class CustomerViewQuoteServlet
 */
@WebServlet("/CustomerViewOrdersServlet")
public class CustomerViewOrdersServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("application/json");
		String customerId = request.getParameter("customerId");
		CustomerService cservice = new CustomerServiceImpl();
		String orderList = cservice.displayOrder(customerId);
		System.out.println("Order List" + orderList);
		out.println(orderList);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}