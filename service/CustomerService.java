package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Invoice;
import com.orderprocessing.utility.Order;

/*
 *  Customer Service interface
 *  This is the blueprint for Customer Service Implementation Class.
 */

public interface CustomerService {
	
	public Customer validateCustomer(String customerLogin, String password);
	
	public String getCustomer(String customerIdOrName);
	
	public List<Order> displayQuote();
	
	public List<Object> displayAllQuoteDetails(String orderId);

	public void changeQuoteStatus(String orderId);
	
	public List<Order> displayOrder();
	
	public List<Object> showInvoice(String orderId);

	
}
