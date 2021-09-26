package com.orderprocessing.dao;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.exception.CustomerNotFoundException;

// Interface for Customer Entity
public interface CustomerDao {

	public String getCustomer(String idOrName) throws CustomerNotFoundException;

	public Customer checkCredentials(String customerLogin, String customerPassword) throws CustomerNotFoundException;

}
