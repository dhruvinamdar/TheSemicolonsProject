package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

import com.orderprocessing.beans.Customer;
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
	
	// method to validate customer login details
	public Customer checkCredentials(String customerName, String customerId, String customerPassword) {
		String sql = "select * from customer where CUSTOMER_NAME=? or CUSTOMER_ID=? and CUSTOMER_PASSWORD=?";
		try {
			prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, customerName);
			prepStatement.setString(2, customerId);
			prepStatement.setString(3, customerPassword);
			Customer customer = new Customer();
			ResultSet rs = prepStatement.executeQuery(sql);
			if(rs.next()) {
				if(((rs.getString(1).equals(customerName)) || (rs.getString(1).equals(customerId))) && (rs.getString(2).equals(customerPassword))){
					customer.setCustomerId(rs.getString(1));
					customer.setCustomerName(rs.getString(2));
					customer.setCustomerGST(rs.getString(3));
					customer.setCustomerAddress(rs.getString(4));
					customer.setCustomerCity(rs.getString(5));
					customer.setCustomerEmail(rs.getString(6));
					customer.setCustomerContact(rs.getString(7));
					customer.setCustomerPincode(rs.getString(8));
					customer.setCustomerPassword(rs.getString(9));
				}
			}
		return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
		String sql = "select * from testorder where STATUS='Pending'";
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
		statusDate = LocalDate.now();
		String sql = "update testorder set STATUS_DATE='" + statusDate + "', STATUS='Approved'";
		
		try {
			prepStatement = conn.prepareStatement(sql);
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

	// method to display invoice details
	public List<Invoice> displayOrderInvoice() {
		String sql = "select current_date(), invoice.INVOICE_ID, invoice.INVOICE_DATE, invoice.TOTAL_GST_VALUE, invoice.TOATL_INVOICE_VALUE, invoice.INVOICE_STATUS from invoice, testorder where current_date() = testorder.STATUS_DATE";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Invoice> invoiceList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				invoiceList.add(new Invoice(rs.getString(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			return invoiceList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}	
}