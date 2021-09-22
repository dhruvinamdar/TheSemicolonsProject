package com.orderprocessing.servlet;

import java.io.IOException;
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


@WebServlet("/CustomerViewDetailedQuoteServlet")
public class CustomerViewDetailedQuoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(session != null && session.getAttribute("customer") != null) {
			String orderId = request.getParameter("orderId");
			CustomerService cservice = new CustomerServiceImpl();
			Order quote = cservice.displayAllQuoteDetails(orderId); 
			request.setAttribute("detailedQuote", quote);
			//request.setAttribute("order", quote);
			RequestDispatcher rd = request.getRequestDispatcher("displayDetailedQuote.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}


}
