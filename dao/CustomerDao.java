package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.beans.Customer;
import com.orderprocessing.beans.Order;

//Customer Dao interface
public interface CustomerDao {
	
	public Customer checkCredentials(String customerName, String customerId, String password);
	
	public List<Order> displayQuoteDetails();
	
	public List<Order> displayDetailedQuote();

	public void setQuoteStatus(Order orderObj);

	public List<Order> displayOrderDetails();

	public List<Invoice> displayOrderInvoice();

}
