package com.orderprocessing.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//Singleton pattern
	private static Connection conn;

	//Function attempts to establish a connection with the database and returns Connection object
	public static Connection getMyConnection() {
		try {
			if(conn==null)
			{
				String url = "jdbc:mysql://localhost:3306/order_processing?useSSL=false";
				String username = "root";
				String password = "root";
				DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
				conn = DriverManager.getConnection(url,username,password);
				System.out.println("Connection established!");
			}
		}
		catch(SQLException exp){
			System.out.println("Could not establish connection");
		}
		return conn;
	}
	
	//Function attempts to close an existing connection with the database
	public static void closeMyConnection()
	{
		try 
		{
			if(conn!=null)
			{
				conn.close();
				System.out.println("Connection closed!");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
