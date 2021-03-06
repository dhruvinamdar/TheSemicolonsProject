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

// implementation of CustomerDao Interface
public class CustomerDaoImpl implements CustomerDao {
	private Connection connection;
	PreparedStatement stmt;
	ResultSet resultSet;
	LocalDate statusDate;

	private static final String FIND_CUSTOMER_BY_ID_OR_NAME = "select * from customer where CUSTOMER_NAME=? or CUSTOMER_ID=? and CUSTOMER_PASSWORD=?";
	private static final String GET_CUSTOMER_BY_ID_OR_BY_NAME = "Select * from customer where CUSTOMER_ID = ? OR CUSTOMER_NAME = ?";

	public CustomerDaoImpl() {
		connection = DBUtil.getMyConnection();
	}

	// Returns customer with ID
	@Override
	public String getCustomer(String customerIdOrName) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		ObjectMapper objectMapper = new ObjectMapper();
		stmt = null;
		resultSet = null;
		Customer customer = new Customer();
		String customerToJson = null;
		try {
			stmt = connection.prepareStatement(GET_CUSTOMER_BY_ID_OR_BY_NAME);
			stmt.setString(1, customerIdOrName);
			stmt.setString(2, customerIdOrName);

			resultSet = stmt.executeQuery();
			if (resultSet.next()) {

				customer.setCustomerId(resultSet.getString("CUSTOMER_ID"));
				customer.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
				customer.setCustomerGST(resultSet.getString("CUSTOMER_GST"));
				customer.setCustomerAddress(resultSet.getString("CUSTOMER_ADDRESS_LINE1"));
				customer.setCustomerCity(resultSet.getString("CUSTOMER_ADDRESS_CITY"));
				customer.setCustomerState(resultSet.getString("CUSTOMER_ADDRESS_STATE"));
				customer.setCustomerEmail(resultSet.getString("CUSTOMER_EMAIL"));
				customer.setCustomerContact(resultSet.getString("CUSTOMER_CONTACT"));
				customer.setCustomerPincode(resultSet.getString("CUSTOMER_PINCODE"));

				customerToJson = objectMapper.writeValueAsString(customer);
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
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return customerToJson;
	}

	// Authenticating credentials
	@Override
	public Customer checkCredentials(String customerLogin, String customerPassword) throws CustomerNotFoundException {
		stmt = null;
		resultSet = null;
		Customer customer = new Customer();
		try {
			stmt = connection.prepareStatement(FIND_CUSTOMER_BY_ID_OR_NAME);
			stmt.setString(1, customerLogin);
			stmt.setString(2, customerLogin);
			stmt.setString(3, customerPassword);
			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				customer.setCustomerId(resultSet.getString("CUSTOMER_ID"));
				customer.setCustomerName(resultSet.getString("CUSTOMER_NAME"));
				customer.setCustomerGST(resultSet.getString("CUSTOMER_GST"));
				customer.setCustomerAddress(resultSet.getString("CUSTOMER_ADDRESS_LINE1"));
				customer.setCustomerCity(resultSet.getString("CUSTOMER_ADDRESS_CITY"));
				customer.setCustomerState(resultSet.getString("CUSTOMER_ADDRESS_STATE"));
				customer.setCustomerEmail(resultSet.getString("CUSTOMER_EMAIL"));
				customer.setCustomerContact(resultSet.getString("CUSTOMER_CONTACT"));
				customer.setCustomerPincode(resultSet.getString("CUSTOMER_PINCODE"));
				return customer;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		throw new CustomerNotFoundException("Customer not found");
	}

}
