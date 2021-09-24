package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.dao.CustomerDao;
import com.orderprocessing.dao.CustomerDaoImpl;
import com.orderprocessing.dao.InvoiceDao;
import com.orderprocessing.dao.InvoiceDaoImpl;
import com.orderprocessing.dao.OrderDao;
import com.orderprocessing.dao.OrderDaoImpl;
import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.Order;
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
	 * Method to pass credentials to dao for validation and return customer object
	 * to servlet after successful validation, else handle CustomerNotFound
	 * Exception.
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
	 * Method to use OrderDaoImpl object and call display quote method in
	 * OrderDaoImpl and return Array List of type Order.
	 */
	public List<Order> displayQuote() {
		return orderDao.displayQuoteDetails();
	}

	/*
	 * Method to use OrderDaoImpl object and pass specific order id to display
	 * detailed quote method in OrderDaoImpl and return Array List of type Object
	 * containing objects of type Order and Customer.
	 */
	public List<Object> displayAllQuoteDetails(String orderId) {
		return orderDao.displayDetailedQuote(orderId);
	}

	/*
	 * Method to use OrderDaoImpl object and pass specific order id to change status
	 * of that order from "Pending" to "Approved".
	 */
	public void changeQuoteStatus(String orderId) {
		orderDao.setQuoteStatus(orderId);
	}

	/*
	 * Method to use OrderDaoImpl object and call display order method in
	 * OrderDaoImpl and return Array List of type Order.
	 */
	public List<Order> displayOrder() {
		return orderDao.displayOrderDetails();
	}

	/*
	 * Method to use InvoiceDaoImpl object and call display order method in
	 * OrderDaoImpl and return Array List of type Object containing objects of type
	 * Order, Product, OrderLine, Customer, Invoice.
	 */
	public List<Object> showInvoice(String orderId) {
		return invoiceDao.displayOrderInvoice(orderId);
	}

}