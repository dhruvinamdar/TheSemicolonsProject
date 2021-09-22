package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Invoice;
import com.orderprocessing.utility.Order;

// Customer Service interface
public interface CustomerService {
	
	public Customer validateCustomer(String customerLogin, String password);
	
	public String getCustomer(String customerIdOrName);
	
	public List<Order> displayQuote();
	
	public Order displayAllQuoteDetails(String orderId);

	public void changeQuoteStatus(String orderId);
	
	public List<Order> displayOrder();
	
	public Invoice showInvoice(String orderId);

	
}
