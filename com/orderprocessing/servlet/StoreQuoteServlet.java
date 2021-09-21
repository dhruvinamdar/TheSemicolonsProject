package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/storeQuote")
public class StoreQuoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		//EmployeeService service = new EmployeeServiceImpl();
		//Quote quote = new Quote();
		//String cust_id =;
		//String productId = ;
		//String quantity = ;
		//String shippingCost = ;
		//String totalOrderValue = ;
		//
		//
		//String formData = request.getParameter("formData");
		try {
			//String status = service.addQuote(formData);
			//request.setAttribute("status",status);
			//request.getRequestDispatcher("").forward(request,response);
		}
		catch(Exception exp)
		{
			//request.setAttribute("status","Error is saving quote!");
			//request.getRequestDispatcher("").forward(request,response);
		}
	}
}
