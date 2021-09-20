package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/importProductsToDB")
public class ImportProductsServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic to check if logged in
		
		response.setContentType("text/html");
		String filePath = request.getParameter("filePath");
		//EmployeeService service = new EmployeeService();
		//ProductsInsertionStatus productsInsertionStatus = service.insertProductsIntoDatabase(filePath);
		//if(productsInsertionStatus!=null)
		//{
		//	request.getRequestDispatcher("").forward(request, response);
		//}
		//else
		//{
		//	String dataNotStoredError = "Products could not be imported";
		//	request.setAttribute("dataNotStoredError",dataNotStoredError);
		//	request.getRequestDispatcher("").forward(request, response);
		//}
	}
}