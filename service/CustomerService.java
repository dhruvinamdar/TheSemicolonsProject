package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Order;

// Customer Service interface
public interface CustomerService {
	
	public Customer validateCustomer(String customerName, String customerId, String password);
	
	public List<Order> displayQuote();
	
	public List<Order> displayAllQuoteDetails();

	public void changeQuoteStatus(Order orderObj);
	
	public List<Order> displayOrder();
	
	public List<Invoice> showInvoice();

	
}
