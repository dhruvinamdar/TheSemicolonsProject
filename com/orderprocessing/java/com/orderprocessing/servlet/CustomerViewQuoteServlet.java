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
@WebServlet("/CustomerViewQuoteServlet")
public class CustomerViewQuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("In servlet of customer view quote");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String customerId = request.getParameter("customerId");
		CustomerService cservice = new CustomerServiceImpl();
		String quoteList = cservice.displayQuote(customerId);
		System.out.println(quoteList);
		out.println(quoteList);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}