package com.orderprocessing.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;
import com.orderprocessing.utility.Order;

/**
 * Servlet implementation class CustomerViewQuoteServlet
 */
@WebServlet("/CustomerViewOrdersServlet")
public class CustomerViewOrdersServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		if(session != null && session.getAttribute("customer") != null) {
			response.setContentType("text/html");
			CustomerService cservice = new CustomerServiceImpl();
			List<Order> quoteList = cservice.displayQuote();
			List<Order> orderList = cservice.displayOrder();
			request.setAttribute("quoteList", quoteList);
			request.setAttribute("orderList", orderList);
			RequestDispatcher rd=request.getRequestDispatcher("displayOrders.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
