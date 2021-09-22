package com.orderprocessing.dao;

import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.beans.Customer;

// Customer Dao interface
public interface CustomerDao {
	
	public Customer checkCredentials(String customerLogin, String password);
	
	public String getCustomer(String customerIdOrName) throws CustomerNotFoundException;

}