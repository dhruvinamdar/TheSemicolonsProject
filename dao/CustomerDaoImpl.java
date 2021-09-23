package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.utility.Customer;
import com.orderprocessing.utils.DBUtils;

/*
 *  Implementation of Customer Dao layer.
 *  This layer connects to the database and retrieves the data as needed.
 *  Methods implemented:
 *  - Customer login credentials validation
 *  - 
 */
public class CustomerDaoImpl implements CustomerDao {
	
	Connection conn = null;
	PreparedStatement prepStatement;
	
	private static final String GET_CUSTOMER_BY_ID_OR_BY_NAME = "Select * from customer where CUSTOMER_ID = ? OR CUSTOMER_NAME = ?";

	// default constructor
	public CustomerDaoImpl() {
		conn = DBUtils.getConnection();
		prepStatement = null;
	}
	
	/* 
	 * Method to validate customer login details.
	 * Validation done on customer name/id and password.
	 * Returns the customer object if validation is successful, 
	 * else throws CustomerNotFound Exception.
	 */
	public Customer checkCredentials(String customerLogin, String customerPassword) throws CustomerNotFoundException {
		String sql = "select * from customer where CUSTOMER_NAME=? or CUSTOMER_ID=? and CUSTOMER_PASSWORD=?";
		try {
			prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, customerLogin);
			prepStatement.setString(2, customerLogin);
			prepStatement.setString(3, customerPassword);
			Customer customer = new Customer();
			ResultSet rs = prepStatement.executeQuery(sql);
			if(rs.next()) {
				if(((rs.getString(1).equals(customerLogin)) || (rs.getString(1).equals(customerLogin))) && (rs.getString(2).equals(customerPassword))){
					customer.setCustomerId(rs.getString("CUSTOMER_ID"));
					customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
					customer.setCustomerGST(rs.getString("CUSTOMER_GST"));
					customer.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS_LINE1"));
					customer.setCustomerCity(rs.getString("CUSTOMER_ADDRESS_CITY"));
					customer.setCustomerState(rs.getString("CUSTOMER_STATE"));
					customer.setCustomerEmail(rs.getString("CUSTOMER_EMAIL"));
					customer.setCustomerContact(rs.getString("CUSTOMER_CONTACT"));
					customer.setCustomerPincode(rs.getString("CUSTOMER_PINCODE"));
				}
				else {
					throw new CustomerNotFoundException("Customer not found");
				}
			}
		return customer;
			
		} catch (SQLException e) {
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
	 * 
	 */
	public String getCustomer(String customerIdOrName) throws CustomerNotFoundException {
		ObjectMapper objectMapper = new ObjectMapper();
		ResultSet rs = null;
		Customer customer = new Customer();
		String customerToJson = null;
		try {
			prepStatement = conn.prepareStatement(GET_CUSTOMER_BY_ID_OR_BY_NAME);
			prepStatement.setString(1, customerIdOrName);
			prepStatement.setString(2, customerIdOrName);
			rs = prepStatement.executeQuery();
			if (rs.next()) {
				customer.setCustomerId(rs.getString("CUSTOMER_ID"));
				customer.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customer.setCustomerGST(rs.getString("CUSTOMER_GST"));
				customer.setCustomerAddress(rs.getString("CUSTOMER_ADDRESS_LINE1"));
				customer.setCustomerCity(rs.getString("CUSTOMER_ADDRESS_CITY"));
				customer.setCustomerState(rs.getString("CUSTOMER_STATE"));
				customer.setCustomerEmail(rs.getString("CUSTOMER_EMAIL"));
				customer.setCustomerContact(rs.getString("CUSTOMER_CONTACT"));
				customer.setCustomerPincode(rs.getString("CUSTOMER_PINCODE"));

				customerToJson = objectMapper.writeValueAsString(customer);
				return customerToJson;
			} else
				return new String("");

		} catch (SQLException ex) {

			ex.printStackTrace();

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (prepStatement != null)
					prepStatement.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		return customerToJson;
	}	
}