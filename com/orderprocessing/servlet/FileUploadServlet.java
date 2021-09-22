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
@MultipartConfig
@WebServlet("/uploadFile")
public class FileUploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		//Use session logic to check if logged in
		
		Part filePart = request.getPart("file");
		String filePath = getFilename(filePart);
//	    InputStream filecontent = filePart.getInputStream();

//	    System.out.println(filename);
		if(filePart.getSubmittedFileName().endsWith(".json"))
		{
		    InputStream fileInputStream = filePart.getInputStream();
		    //Path of file saving could give a problem
		    File fileToSave = new File("D:\\codefury\\OrderProcessing\\src\\main\\webapp\\JSONfiles\\" + filePath.substring(filePath.lastIndexOf('/') + 1).substring(filePath.lastIndexOf('\\') + 1) );
		    Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//String fileUrl = "http://localhost:8080/uploaded-files/" + filePart.getSubmittedFileName();			
			request.setAttribute("filePath", fileToSave.toPath());
			request.getRequestDispatcher("importProductsToDB").forward(request, response);
			System.out.println("File Uploaded");
		}
		else{
			//Send response to front end with invalid extension of file
			String errorMessage = "";
			request.setAttribute("invalidFileError",errorMessage);
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
