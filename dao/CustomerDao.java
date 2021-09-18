package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.beans.Order;

public interface CustomerDao {
	public List<Order> displayQuoteDetails();
	public List<Order> displayDetailedQuote();
}
