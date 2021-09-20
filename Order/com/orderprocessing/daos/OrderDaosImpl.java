package com.orderprocessing.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.helper.PropertiesHelper;
import com.orderprocessing.models.Order;

public class OrderDaosImpl implements OrderDaos {
	
	private Connection conn;
	private PreparedStatement ps;
	private static String conURL;
	private static String dbDriver;
	private static String dbUsername;
	private static String dbPassword;
	
	public OrderDaosImpl() {
		super();
	}

	static {
		PropertiesHelper helper = new PropertiesHelper();
		conURL = helper.getProperty("conURL");
		dbDriver = helper.getProperty("dbDriver");
		dbUsername = helper.getProperty("dbUsername");
		dbPassword = helper.getProperty("dbPassword");
		try {
			Class c = Class.forName(dbDriver);
			System.out.println("Loaded the "+c.getName() + " Class");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void createConnection() {
		try {
			conn = DriverManager.getConnection(conURL, dbUsername, dbPassword);
			
			System.out.println("Connected to the database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<Order> viewOrder() {
		ArrayList<Order> list = new ArrayList<>();
		final String SQL = "select * from orderbooking";
		createConnection();
		try {
			ps = conn.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				
				order.setOrderId(rs.getString("orderId"));
				order.setOrderDate(rs.getDate("orderDate"));
				order.setTotalOrderValue(rs.getFloat("totalOrderValue"));
				order.setShippingCost(rs.getFloat("shippingCost"));
				order.setShippingAgency(rs.getString("shippingAgency"));
				order.setStatus(rs.getString("status"));
				list.add(order);
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return list;

	}

	@Override
	public void closeConnection() {
		if(conn!= null) {
			try {
				conn.close();
				System.out.println("Connection is closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
