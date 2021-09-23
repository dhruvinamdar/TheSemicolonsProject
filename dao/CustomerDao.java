package com.orderprocessing.dao;

import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.utility.Customer;

/*
 *  Customer Dao Interface
 *  This is the blueprint for Customer Dao Implementation Class.
 */
public interface CustomerDao {
	
	public Customer checkCredentials(String customerLogin, String password) throws CustomerNotFoundException;
	
	public String getCustomer(String customerIdOrName) throws CustomerNotFoundException;

}