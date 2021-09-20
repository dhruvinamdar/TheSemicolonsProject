package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.beans.Order;

//Customer Dao interface
public interface CustomerDao {
	
	public List<Order> displayQuoteDetails();
	
	public List<Order> displayDetailedQuote();

	public void setQuoteStatus(Order orderObj);

	public List<Order> displayOrderDetails();

	public void displayOrderInvoice();
}
