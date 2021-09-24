package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.entity.Customer;
import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.utils.DBUtil;

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

	@Override
	public String getCustomer(String customerIdOrName) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("In get CustomerDAOIMPL" + customerIdOrName);
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

	@Override
	public Customer checkCredentials(String customerLogin, String customerPassword) throws CustomerNotFoundException {
		String sql = "select * from customer where CUSTOMER_NAME=? or CUSTOMER_ID=? and CUSTOMER_PASSWORD=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, customerLogin);
			stmt.setString(2, customerLogin);
			stmt.setString(3, customerPassword);
			Customer customer = new Customer();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (((rs.getString(1).equals(customerLogin)) || (rs.getString(1).equals(customerLogin)))
						&& (rs.getString(2).equals(customerPassword))) {
					customer.setCustomerId(rs.getString("CUSTOMER_ID"));
					customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
					customer.setCustomerGST(rs.getString("CUSTOMER_GST"));
					customer.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS_LINE1"));
					customer.setCustomerCity(rs.getString("CUSTOMER_ADDRESS_CITY"));
					customer.setCustomerState(rs.getString("CUSTOMER_STATE"));
					customer.setCustomerEmail(rs.getString("CUSTOMER_EMAIL"));
					customer.setCustomerContact(rs.getString("CUSTOMER_CONTACT"));
					customer.setCustomerPincode(rs.getString("CUSTOMER_PINCODE"));
				} else {
					throw new CustomerNotFoundException("Customer not found");
				}
			}
			return customer;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// closed Prepared Statement
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
