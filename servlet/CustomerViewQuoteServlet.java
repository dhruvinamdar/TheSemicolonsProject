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

/**
 * Servlet implementation class CustomerViewQuoteServlet
 */
@WebServlet("/CustomerViewQuoteServlet")
public class CustomerViewQuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		if(session != null && session.getAttribute("customer") != null) {
			response.setContentType("text/html");
			String customerId = request.getParameter("customerId");
			CustomerService cservice = new CustomerServiceImpl();
			String quoteList = cservice.displayQuote(customerId);
			request.setAttribute("quoteList", quoteList);
			RequestDispatcher rd=request.getRequestDispatcher("displayQuote.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

}
