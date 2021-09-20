package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.orderprocessing.beans.Order;
import com.orderprocessing.utils.DBUtils;

// implementation of Customer Dao layer
public class CustomerDaoImpl implements CustomerDao {
	
	Connection conn = null;
	PreparedStatement prepStatement;
	LocalDate statusDate;
	
	public CustomerDaoImpl() {
		conn = DBUtils.getConnection();
		prepStatement = null;
	}
	
	// method to display quote id, date, shipping cost and total order value
	public List<Order> displayQuoteDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE from order where STATUS='Pending'";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				Order quote = new Order();
				quote.setOrderId(rs.getString(1));
				quote.setOrderDate(rs.getDate(2));
				quote.setShippingCost(rs.getFloat(3));
				quote.setTotalOrderValue(rs.getFloat(4));
				
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
	public List<Order> displayDetailedQuote() {
		String sql = "select * from order where STATUS='Pending'";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				quoteList.add(new Order(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getString(7)));
			}
			return quoteList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}


	// method to convert quote to order by changing status to approved
	public void setQuoteStatus(Order orderObj) {
		String sql = "update Order set STATUS='Approved' where ORDER_ID=?";
		try {
			prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, orderObj.getOrderId());
			statusDate = LocalDate.now();
			int num = prepStatement.executeUpdate(sql);
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
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS from order where STATUS='Approved' or STATUS='Completed'";
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

	// method to display invoice details
	public void displayOrderInvoice() {
		String sql = "select * from invoice";
		
	}
	
}