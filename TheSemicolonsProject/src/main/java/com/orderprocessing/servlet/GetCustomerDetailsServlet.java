package com.orderprocessing.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

@WebServlet("/getCustomerDetails")
public class GetCustomerDetailsServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic
		System.out.println("In post of customer");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String credentials = request.getParameter("customerCreds");
		System.out.println(credentials);
		EmployeeService service = new EmployeeServiceImpl();
		String customerData = "";
		String productData = "";
		try {
			System.out.println("in try of servlet");
			customerData = service.getCustomer(credentials);
			productData = service.getProductData();
			System.out.println(productData);
			System.out.println(customerData);
			out.println("["+customerData +", "+ productData+"]");
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}