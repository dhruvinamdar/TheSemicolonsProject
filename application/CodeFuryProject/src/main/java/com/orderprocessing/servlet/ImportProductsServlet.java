package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

@WebServlet("/importProductsToDB")
public class ImportProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");
		String filePath = request.getParameter("filePath");
		EmployeeService service = new EmployeeServiceImpl();

	}
}