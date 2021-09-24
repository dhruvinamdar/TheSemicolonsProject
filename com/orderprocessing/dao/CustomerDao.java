package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.entity.Order;
import com.orderprocessing.exception.CustomerNotFoundException;

public interface CustomerDao {

	public List<Order> displayQuoteDetails();

	public List<Order> displayDetailedQuote();

	public void setQuoteStatus(Order orderObj);

	public List<Order> displayOrderDetails();

	public void displayOrderInvoice();

	public String getCustomer(String idOrName) throws CustomerNotFoundException; // ID or Name parameter to be fetched
																					// // from user
}
