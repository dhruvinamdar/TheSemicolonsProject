package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.entity.Employee;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.ProductsInsertionStatus;
import com.orderprocessing.exception.CustomerNotFoundException;

/*
 *  Employee Service interface
 *  This is the blueprint for Employee Service Implementation Class.
 */
public interface EmployeeService {

	Employee login(String id, String password);

	List<Order> getOrders();

	ProductsInsertionStatus importProducts(String productJSON);

	String getProductData();

	String getCustomer(String id) throws CustomerNotFoundException;

	String addQuote(String quote);

	void insertOrders(String orderJson);
}
