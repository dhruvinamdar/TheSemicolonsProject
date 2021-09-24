package com.orderprocessing.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

@WebServlet("/storeQuote")
public class StoreQuoteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		
		EmployeeService service = new EmployeeServiceImpl();
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String quote = "";
		if(br != null){
			quote = br.readLine();
			System.out.println("quote = "+quote);
		}
//	String quote = request.getParameter("quote");
//		System.out.println(quote);
		try {
			String status = service.addQuote(quote);
			request.setAttribute("status",status);
			request.getRequestDispatcher("employeeOrderManagement.jsp").forward(request,response);
		}
		catch(Exception exp)
		{
			request.setAttribute("status","Error is saving quote!");
			request.getRequestDispatcher("employeeOrderManagement.jsp").forward(request,response);
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
