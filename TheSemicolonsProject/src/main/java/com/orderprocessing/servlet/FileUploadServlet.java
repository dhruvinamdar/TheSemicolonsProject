package com.orderprocessing.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.orderprocessing.entity.ProductsInsertionStatus;
import com.orderprocessing.service.EmployeeService;
import com.orderprocessing.service.EmployeeServiceImpl;

@MultipartConfig
@WebServlet("/uploadFile")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside file upload servlet");
		Part filePart = request.getPart("jsonFile");

		if (filePart.getSubmittedFileName().endsWith(".json")) {
			System.out.println(".json condition satisfied");
			InputStream fileInputStream = filePart.getInputStream();
			String path = this.getServletContext().getRealPath("" + filePart.getSubmittedFileName());
			File fileToSave = new File(path);
			System.out.println("Filepath in project is => " + path);
			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

			// write function call to service
			EmployeeService service = new EmployeeServiceImpl();
			ProductsInsertionStatus status = service.importProducts(path);
			System.out.println("Status - " + status);
			// Code to read JSON (remove when integrating) ---
//				Object obj = new JSONParser().parse(new FileReader(path));
//				JSONObject jo = null;
//				if(obj!=null)
//					jo = (JSONObject) obj;
//				if(jo!=null)
//					System.out.println("Json data => " + jo.toString());
			// -----
		} else {
			// Send response to front end with invalid extension of file (write necessary
			// message in double quotes)
			String errorMessage = "";
			request.setAttribute("invalidFileError", errorMessage);
			request.getRequestDispatcher("").forward(request, response);
		}

	}

	private static String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename;
			}
		}
		return null;
	}
}
