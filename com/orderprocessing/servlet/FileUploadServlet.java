package com.orderprocessing.servlet;

import java.io.File;
import java.io.FileReader;
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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@MultipartConfig
@WebServlet("/uploadFile")
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("Inside file upload servlet");
			Part filePart = request.getPart("jsonFile");
			if(filePart.getSubmittedFileName().endsWith(".json"))
			{
				System.out.println(".json condition satisfied");
			    InputStream fileInputStream = filePart.getInputStream();
			    String path = this.getServletContext().getRealPath("" + filePart.getSubmittedFileName());
			    File fileToSave = new File(path);
			    System.out.println("Filepath in project is => " + path);
				Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				//write function call to service

				//Code to read JSON (remove when integrating) ---
				Object obj = new JSONParser().parse(new FileReader(path));
				JSONObject jo = null;
				if(obj!=null)
					jo = (JSONObject) obj;
				if(jo!=null)
					System.out.println("Json data => " + jo.toString());
				//-----
			}
			else{
				//Send response to front end with invalid extension of file (write necessary message in double quotes)
				String errorMessage = "";
				request.setAttribute("invalidFileError",errorMessage);
				request.getRequestDispatcher("").forward(request, response);
			}
		}
		catch(Exception exp)
		{
			System.out.println("Exception => " + exp.getMessage());
		}
		
	}
}
