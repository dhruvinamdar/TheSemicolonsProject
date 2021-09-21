package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Order;
import com.orderprocessing.utility.Invoice;
import com.orderprocessing.dao.CustomerDao;
import com.orderprocessing.dao.CustomerDaoImpl;

// implementation of Customer Service layer
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao cdao;
	
	// default constructor
	public CustomerServiceImpl() {
		cdao = new CustomerDaoImpl();
	}
	
	public Customer validateCustomer(String customerName, String customerId, String customerPassword) {
		Customer customer = cdao.checkCredentials(customerName, customerId, customerPassword);
		//System.out.println(customer);
		return customer;
	}

	public List<Order> displayQuote() {
		return cdao.displayQuoteDetails();
	}
	
	public List<Order> displayAllQuoteDetails() {
		return cdao.displayDetailedQuote();
	}
	
	public void changeQuoteStatus(Order orderObj) {
		cdao.setQuoteStatus(orderObj);
	}
	
	public List<Order> displayOrder() {
		return cdao.displayOrderDetails();
	}
	
	public List<Invoice> showInvoice() {
		cdao.displayOrderInvoice();
	}

	
	
}
