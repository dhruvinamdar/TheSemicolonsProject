package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.utility.Order;
import com.orderprocessing.utils.DBUtils;

public class OrderDaoImpl implements OrderDao {
	
	Connection conn = null;
	PreparedStatement prepStatement;
	LocalDate statusDate;
	
	public OrderDaoImpl() {
		conn = DBUtils.getConnection();
		prepStatement = null;
	}
	
	// method to display quote id, date, shipping cost and total order value
	public List<Order> displayQuoteDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE from testorder where STATUS='Pending'";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				Order quote = new Order();
				quote.setOrderId(rs.getString("ORDER_ID"));
				quote.setOrderDate(rs.getDate("ORDER_DATE"));
				quote.setShippingCost(rs.getFloat("TOTAL_ORDER_VALUE"));
				quote.setTotalOrderValue(rs.getFloat("SHIPPING_COST"));					
				quoteList.add(quote);
			}
			return quoteList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	// method to display detailed quote 
	public Order displayDetailedQuote(String orderId) {
		String sql = "select * from testorder where ORDER_ID=?";
		try {
			prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, orderId);
			//List<Order> quoteList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			return new Order(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getString(7));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// method to convert quote to order by changing status to approved
	public void setQuoteStatus(String orderId) {
		statusDate = LocalDate.now();
		String sql = "update testorder set STATUS_DATE='" + statusDate + "', STATUS='Approved' where ORDER_ID=?";	
		try {
			prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, orderId);
			int num = prepStatement.executeUpdate(sql);
			System.out.println("Changed");
			if(num > 0) {
				conn.commit();
			}	
		}
		catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}			
	}

	// method to display order id, date shipping cost, total order value and status
	public List<Order> displayOrderDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS from testorder where STATUS='Approved' or STATUS='Completed'";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getString(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippingCost(rs.getFloat(3));
				order.setTotalOrderValue(rs.getFloat(4));
				order.setStatus(rs.getString(5));					
				orderList.add(order);
			}
			return orderList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

}
