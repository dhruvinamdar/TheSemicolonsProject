package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Order;
import com.orderprocessing.utils.DBUtils;

/*
 *  Implementation of Order Dao layer.
 *  This layer connects to the database and retrieves the data as needed.
 *  Methods implemented:
 *  - To retrieve data of quotes (order whose status is "Pending")
 *  - To retrieve all the data for a particular quote
 *  - To change the status of an order from "Pending" to "Approved"
 *  - To retrieve data of orders whose status is "Approved" or "Completed"
 */
public class OrderDaoImpl implements OrderDao {
	
	LocalDate statusDate = LocalDate.now();
	private static final String quote = "select ORDER_ID, ORDER_DATE, TOTAL_ORDER_VALUE, SHIPPING_COST from orders where CUSTOMER_ID=? and STATUS='Pending'";
	private static final String quoteDetails = "SELECT orders.ORDER_ID, orders.ORDER_DATE, orders.TOTAL_ORDER_VALUE, orders.SHIPPING_COST, orders.SHIPPING_AGENCY, orders.STATUS, customer.CUSTOMER_ADDRESS_LINE1, customer.CUSTOMER_ADDRESS_CITY, customer.CUSTOMER_ADDRESS_STATE from orders INNER JOIN customer ON orders.CUSTOMER_ID = customer.CUSTOMER_ID WHERE ORDER_ID=?";
	private static final String order = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS from testorder where STATUS='Approved' or STATUS='Completed' and CUSTOMER_ID=?";
	
	Connection conn = null;
	PreparedStatement prepStatement;
	
	public OrderDaoImpl() {
		conn = DBUtils.getConnection();
		prepStatement = null;
	}
	
	/*
	 * Method to display quote id, date, shipping cost and total order value for all orders whose status is "Pending".
	 * Returns an Array List of type Order.
	 */
	public String displayQuoteDetails(String customerId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			prepStatement = conn.prepareStatement(quote);
			prepStatement.setString(1, customerId);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()) {
				Order quote = new Order();
				quote.setOrderId(rs.getString("ORDER_ID"));
				quote.setOrderDate(rs.getDate("ORDER_DATE"));
				quote.setShippingCost(rs.getFloat("TOTAL_ORDER_VALUE"));
				quote.setTotalOrderValue(rs.getFloat("SHIPPING_COST"));					
				quoteList.add(quote);
			}
			String quoteListToString = objectMapper.writeValueAsString(quoteList);
			return quoteListToString;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	/*
	 *  Method to display all details of a particular quote including customer address.
	 *  Returns an Array List of type Object containing objects of type Order and Customer.
	 */
	public String displayDetailedQuote(String orderId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			prepStatement = conn.prepareStatement(quoteDetails);
			prepStatement.setString(1, orderId);
			List<Object> quoteList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery();
			quoteList.add(new Order(rs.getString(1), rs.getDate(2), rs.getFloat(3), rs.getFloat(4), rs.getString(5), rs.getString(6)));
			quoteList.add(new Customer(rs.getString(7), rs.getString(8), rs.getString(9)));
			String detailedQuoteToString = objectMapper.writeValueAsString(quoteList);
			return detailedQuoteToString;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//closed Prepared Statement 
				prepStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 *  Method to convert quote to order by changing status from "Pending" to "Approved".
	 *  Commit the changes to the database on successful updation,
	 *  else rollback.
	 */
	public void setQuoteStatus(String orderId) {
		
		String sql = "update testorder set STATUS_DATE='" + statusDate + "', STATUS='Approved' where ORDER_ID=?";	
		try {
			prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, orderId);
			int num = prepStatement.executeUpdate();
			//System.out.println("Changed");
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
		}	finally {
			try {
				//closed Prepared Statement 
				prepStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	/*
	 * Method to display order id, date shipping cost, total order value and status of all orders whose status are "Approved" or "Completed".
	 * Returns an Array List of type Order.
	 */
	public String displayOrderDetails(String customerId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			prepStatement = conn.prepareStatement(order);
			prepStatement.setString(1, customerId);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getString(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippingCost(rs.getFloat(3));
				order.setTotalOrderValue(rs.getFloat(4));
				order.setStatus(rs.getString(5));					
				orderList.add(order);
			}
			String orderToString = objectMapper.writeValueAsString(orderList);
			return orderToString;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//closed Prepared Statement 
				prepStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null; 
	}
	
}
