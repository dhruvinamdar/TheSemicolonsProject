package com.orderprocessing.dao;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.exception.CustomerNotFoundException;

public interface CustomerDao {

	public String getCustomer(String idOrName) throws CustomerNotFoundException; // ID or Name parameter to be fetched
																					// from user

	public Customer checkCredentials(String customerLogin, String customerPassword) throws CustomerNotFoundException;

}
