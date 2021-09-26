package com.orderprocessing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/importProductsBase")
public class ImportProductsBaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("importProduct.html").forward(request, response);

	}
}
