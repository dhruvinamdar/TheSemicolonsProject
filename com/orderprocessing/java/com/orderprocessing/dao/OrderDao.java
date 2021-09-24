package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.NoOrderFoundException;

public interface OrderDao {

	List<Order> getOrders() throws NoOrderFoundException;

	void insertOrders(Quote newOrder);

	void insertOrderLine(Quote newOrder, String lastOrderId);

	public List<Order> displayQuoteDetails();

	public List<Object> displayDetailedQuote(String orderId);

	public void setQuoteStatus(String orderId);

	public List<Order> displayOrderDetails();
}