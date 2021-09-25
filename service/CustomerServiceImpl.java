package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Order;
import com.orderprocessing.utility.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderprocessing.dao.CustomerDao;
import com.orderprocessing.dao.CustomerDaoImpl;
import com.orderprocessing.dao.InvoiceDao;
import com.orderprocessing.dao.InvoiceDaoImpl;
import com.orderprocessing.dao.OrderDao;
import com.orderprocessing.dao.OrderDaoImpl;
import com.orderprocessing.exception.CustomerNotFoundException;

/* 
 * Implementation of Customer Service layer
 * This layer interacts with the servlet, creates the object of dao layer, calls the dao function and handles exceptions.
 */
public class CustomerServiceImpl implements CustomerService {
	private CustomerDao cdao;
	private OrderDao orderDao;
	private InvoiceDao invoiceDao;
	
	// default constructor
	public CustomerServiceImpl() {
		cdao = new CustomerDaoImpl();
		orderDao = new OrderDaoImpl();
		invoiceDao = new InvoiceDaoImpl();
	}
	
	/*
	 * Method to pass credentials to dao for validation and
	 * return customer object to servlet after successful validation,
	 * else handle CustomerNotFound Exception.
	 */
	public Customer validateCustomer(String customerLogin, String customerPassword) {
		try {
			Customer customer = cdao.checkCredentials(customerLogin, customerPassword);
			return customer;
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/*
	 * 
	 */
	public String getCustomer(String customerIdOrName) {
		try {
			return cdao.getCustomer(customerIdOrName);
		} catch (CustomerNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Method to use OrderDaoImpl object and call display quote method in OrderDaoImpl and 
	 * return Array List of type Order. 
	 */
	public String displayQuote(String customerId) {
		try {
			return orderDao.displayQuoteDetails(customerId);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Method to use OrderDaoImpl object and pass specific order id to display detailed quote method in OrderDaoImpl and 
	 * return Array List of type Object containing objects of type Order and Customer. 
	 */
	public String displayAllQuoteDetails(String orderId) {
		try {
			return orderDao.displayDetailedQuote(orderId);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Method to use OrderDaoImpl object and pass specific order id to change status of that order from "Pending" to "Approved".
	 */
	public void changeQuoteStatus(String orderId) {
		orderDao.setQuoteStatus(orderId);
	}
	
	/*
	 * Method to use OrderDaoImpl object and call display order method in OrderDaoImpl and 
	 * return Array List of type Order. 
	 */
	public String displayOrder(String customerId) {
		try {
			return orderDao.displayOrderDetails(customerId);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Method to use InvoiceDaoImpl object and call display order method in OrderDaoImpl and 
	 * return Array List of type Object containing objects of type Order, Product, OrderLine, Customer, Invoice.
	 */
	public String showInvoice(String orderId) {
		try {
			return invoiceDao.displayOrderInvoice(orderId);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
}
