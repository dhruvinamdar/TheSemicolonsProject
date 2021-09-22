package com.demo.serv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			response.setContentType("text/html");
			String user= request.getParameter("user");
			String pass = request.getParameter("pass");
			if(user.equals("Java") && pass.equals("Java"))
			{
				HttpSession s= request.getSession();
				String id =s.getId();
				if(id!= null) {
					RequestDispatcher rd= getServletContext().getRequestDispatcher("/Welcome.html");
					rd.forward(request, response);
				}
			}
			else {
				RequestDispatcher rd= getServletContext().getRequestDispatcher("/Login.html");
				rd.forward(request, response);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
		}

}
}
