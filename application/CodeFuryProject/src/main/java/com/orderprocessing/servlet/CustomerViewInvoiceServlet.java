package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.entity.Invoice;
import com.orderprocessing.service.CustomerService;
import com.orderprocessing.service.CustomerServiceImpl;

@WebServlet("/CustomerViewInvoiceServlet")
public class CustomerViewInvoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String orderId = request.getParameter("orderId");
		CustomerService cservice = new CustomerServiceImpl();
		Invoice invoice = cservice.showInvoice(orderId);
		request.setAttribute("invoice", invoice);
		RequestDispatcher rd = request.getRequestDispatcher("invoice.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}