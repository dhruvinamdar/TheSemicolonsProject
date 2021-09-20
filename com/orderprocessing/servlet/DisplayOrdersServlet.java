package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ordersDisplay")
public class DisplayOrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic to check if logged in

		response.setContentType("text/html");
		//EmployeeService service = new EmployeeService()
		//List<Order> orderList = service.getOrders();
		//if(orderList.size()!=0)
		//{
		//	request.setAttribute("noOrderError","");
		//	request.setAttribute("orderList",orderList);
		//	request.getRequestDispatcher("").forward(request, response);
		//}
		//else
		//{
		// 	request.setAttribute("noOrderError","No orders found");
		//	request.getRequestDispatcher("").forward(request, response);
		//}
	}
}
