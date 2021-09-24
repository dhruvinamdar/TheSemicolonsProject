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
import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.Order;
import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.util.DBUtil;

// implementation of Customer Dao layer
public class CustomerDaoImpl implements CustomerDao {

	Connection conn = null;
	PreparedStatement stmt;
	ResultSet rs = null;
	LocalDate statusDate;

	private static final String GET_CUSTOMER_BY_ID_OR_BY_NAME = "Select * from customer where CUSTOMER_ID = ? OR CUSTOMER_NAME = ?";

	public CustomerDaoImpl() {
		conn = DBUtil.getMyConnection();
		stmt = null;
	}

	// method to display quote id, date, shipping cost and total order value
	public List<Order> displayQuoteDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE from order where STATUS='Pending'";
		try {
			stmt = conn.prepareStatement(sql);
			List<Order> quoteList = new ArrayList<>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Order quote = new Order();
				quote.setOrderId(rs.getString(1));
				quote.setOrderDate(rs.getDate(2));
				quote.setShippingCost(rs.getFloat(3));
				quote.setTotalOrderValue(rs.getFloat(4));

				quoteList.add(quote);
			}
			return quoteList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// method to display detailed quote
	public List<Order> displayDetailedQuote() {
		String sql = "select * from order where STATUS='Pending'";
		try {
			stmt = conn.prepareStatement(sql);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				quoteList.add(new Order(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5),
						rs.getString(6), rs.getString(7)));
			}
			return quoteList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// method to convert quote to order by changing status to approved
	public void setQuoteStatus(Order orderObj) {
		String sql = "update Order set STATUS='Approved' where ORDER_ID=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, orderObj.getOrderId());
			statusDate = LocalDate.now();
			int num = stmt.executeUpdate(sql);
			if (num > 0) {
				conn.commit();
			}
		} catch (SQLException e) {
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
			stmt = conn.prepareStatement(sql);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getString(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippingCost(rs.getFloat(3));
				order.setTotalOrderValue(rs.getFloat(4));
				order.setStatus(rs.getString(5));

				orderList.add(order);
			}
			return orderList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// method to display invoice details
	public void displayOrderInvoice() {
		String sql = "select * from invoice";

	}

	@Override
	public String getCustomer(String customerIdOrName) throws CustomerNotFoundException {
		// TODO Auto-generated method stub

		ObjectMapper objectMapper = new ObjectMapper();
		stmt = null;
		rs = null;
		Customer customer = new Customer();
		String customerToJson = null;
		try {
			stmt = conn.prepareStatement(GET_CUSTOMER_BY_ID_OR_BY_NAME);
			stmt.setString(1, customerIdOrName);
			stmt.setString(2, customerIdOrName);

			rs = stmt.executeQuery();
			if (rs.next()) {

				customer.setCustomerId(rs.getString("CUSTOMER_ID"));
				customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customer.setCustomerGST(rs.getString("CUSTOMER_GST"));
				customer.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS_LINE1"));
				customer.setCustomerCity(rs.getString("CUSTOMER_ADDRESS_CITY"));
				customer.setCustomerState(rs.getString("CUSTOMER_ADDRESS_STATE"));
				customer.setCustomerEmail(rs.getString("CUSTOMER_EMAIL"));
				customer.setCustomerContact(rs.getString("CUSTOMER_CONTACT"));
				customer.setCustomerPincode(rs.getString("CUSTOMER_PINCODE"));

				customerToJson = objectMapper.writeValueAsString(customer);
				System.out.println(customerToJson);
				return customerToJson;
			} else
				return new String("");

		} catch (SQLException ex) {

			ex.printStackTrace();

		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return customerToJson;
	}

}
