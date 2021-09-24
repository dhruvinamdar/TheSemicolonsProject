package com.orderprocessing.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderprocessing.utility.Order;

/*
 *  Order Dao Interface
 *  This is the blueprint for Order Dao Implementation Class.
 */
public interface OrderDao {
	
	public String displayQuoteDetails(String customerId) throws JsonProcessingException;
	
	public String displayDetailedQuote(String orderId) throws JsonProcessingException;
	
	public void setQuoteStatus(String orderId);
	
	public String displayOrderDetails(String customerId) throws JsonProcessingException;
	
}
