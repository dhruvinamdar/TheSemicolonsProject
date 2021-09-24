package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/importProductsBase")
public class ImportProductsBaseServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("employee")!=null)
			request.getRequestDispatcher("importProduct.html").forward(request, response);
		else
			request.getRequestDispatcher("login.html").forward(request, response);
	}
}
