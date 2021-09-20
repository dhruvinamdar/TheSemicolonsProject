package com.orderprocessing.servlet;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/uploadFile")
public class FileUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic to check if logged in
		
		Part filePart = request.getPart("fileToUpload");
		if(filePart.getSubmittedFileName().endsWith(".json"))
		{
		    InputStream fileInputStream = filePart.getInputStream();
		    //Path of file saving could give a problem
		    File fileToSave = new File("WebContent/uploaded-files/" + filePart.getSubmittedFileName());
			Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//String fileUrl = "http://localhost:8080/uploaded-files/" + filePart.getSubmittedFileName();			
			request.setAttribute("filePath", fileToSave.toPath());
			request.getRequestDispatcher("importProductsToDB").forward(request, response);
		}
		else{
			//Send response to front end with invalid extension of file
			String errorMessage = "";
			request.setAttribute("invalidFileError",errorMessage);
			request.getRequestDispatcher("").forward(request, response);
		}
	}
}
