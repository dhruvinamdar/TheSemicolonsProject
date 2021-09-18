package com.orderprocessing.service;

public class CustomerServiceImpl implements CustomerService {
	private CustomerDao cdao;
	private Order orderObj;
	
	public CustomerService() {
		cdao = new CustomerServiceImpl();
	}
	
	public List<Order> displayQuote() {
		cdao.displayQuoteDetails(orderObj);
	}
	
	public List<Order> displayAllQuoteDetails() {
		cdao.displayDetailedQuote(orderObj);
	}
}
