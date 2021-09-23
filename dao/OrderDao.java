package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.utility.Order;

/*
 *  Order Dao Interface
 *  This is the blueprint for Order Dao Implementation Class.
 */
public interface OrderDao {
	
	public List<Order> displayQuoteDetails();
	
	public List<Object> displayDetailedQuote(String orderId);
	
	public void setQuoteStatus(String orderId);
	
	public List<Order> displayOrderDetails();
	
}
