package com.orderprocessing.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connection;

	public static Connection getConnection() {
		if (connection != null)
			return connection;

		else {
			try {
				if (connection == null) {
					String url = "jdbc:mysql://localhost:3306/ORDER_PROCESSING?useSSL=false";
					String username = "root";
					String password = "root";
					DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
					connection = DriverManager.getConnection(url, username, password);
					connection.setAutoCommit(false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}
	}

	public static void closeMyConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
