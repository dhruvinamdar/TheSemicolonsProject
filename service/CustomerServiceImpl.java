package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Order;
import com.orderprocessing.utility.Invoice;
import com.orderprocessing.dao.CustomerDao;
import com.orderprocessing.dao.CustomerDaoImpl;
import com.orderprocessing.dao.InvoiceDao;
import com.orderprocessing.dao.OrderDao;
import com.orderprocessing.exception.CustomerNotFoundException;

// implementation of Customer Service layer
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao cdao;
	private OrderDao orderDao;
	private InvoiceDao invoiceDao;
	
	// default constructor
	public CustomerServiceImpl() {
		cdao = new CustomerDaoImpl();
	}
	
	public Customer validateCustomer(String customerLogin, String customerPassword) {
		Customer customer = cdao.checkCredentials(customerLogin, customerPassword);
		//System.out.println(customer);
		return customer;
	}
	
	public String getCustomer(String customerIdOrName) {
		try {
			return cdao.getCustomer(customerIdOrName);
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Order> displayQuote() {
		return orderDao.displayQuoteDetails();
	}
	
	public Order displayAllQuoteDetails(String orderId) {
		return orderDao.displayDetailedQuote(orderId);
	}
	
	public void changeQuoteStatus(String orderId) {
		orderDao.setQuoteStatus(orderId);
	}
	
	public List<Order> displayOrder() {
		return orderDao.displayOrderDetails();
	}
	
	public Invoice showInvoice(String orderId) {
		return invoiceDao.displayOrderInvoice(orderId);
	}

	
	
}
