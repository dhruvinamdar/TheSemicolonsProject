package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.utility.Order;

public interface OrderDao {
	
	public List<Order> displayQuoteDetails();
	
	public Order displayDetailedQuote(String orderId);
	
	public void setQuoteStatus(String orderId);
	
	public List<Order> displayOrderDetails();
	
}
