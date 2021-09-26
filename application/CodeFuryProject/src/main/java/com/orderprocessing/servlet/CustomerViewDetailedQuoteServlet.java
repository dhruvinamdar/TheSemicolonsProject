package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.entity.DetailedQuote;
import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;

@WebServlet("/CustomerViewDetailedQuoteServlet")
public class CustomerViewDetailedQuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String orderId = request.getParameter("orderId");
		System.out.println("Order Id in Servlet" + orderId);
		CustomerService cservice = new CustomerServiceImpl();
		DetailedQuote quote = cservice.displayAllQuoteDetails(orderId);
		System.out.println("Quote" + quote);

		request.setAttribute("quote", quote);
		RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
		rd.forward(request, response);

	}

}