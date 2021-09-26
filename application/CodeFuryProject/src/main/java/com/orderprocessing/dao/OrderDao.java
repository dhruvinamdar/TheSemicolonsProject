package com.orderprocessing.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderprocessing.entity.DetailedQuote;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.NoOrderFoundException;

/*
 *  OrderDao Interface
 *  This is the blueprint for OrderDao Implementation Class.
 */
public interface OrderDao {

	List<Order> getOrders() throws NoOrderFoundException;

	void insertOrders(Quote newOrder);

	void insertOrderLine(Quote newOrder, String lastOrderId);

	public String displayQuoteDetails(String customerId) throws JsonProcessingException;

	public DetailedQuote displayDetailedQuote(String orderId) throws JsonProcessingException;

	public void setQuoteStatus(String orderId);

	public String displayOrderDetails(String customerId) throws JsonProcessingException;
}