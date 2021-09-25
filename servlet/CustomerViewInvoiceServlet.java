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
import com.orderprocessing.utility.Invoice;

@WebServlet("/CustomerViewInvoiceServlet")
public class CustomerViewInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		if(session != null && session.getAttribute("customer") != null) {
			String orderId = request.getParameter("orderId");
			CustomerService cservice = new CustomerServiceImpl();
			String invoice = cservice.showInvoice(orderId);
			request.setAttribute("invoice", invoice);
			RequestDispatcher rd = request.getRequestDispatcher("displayInvoice.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
