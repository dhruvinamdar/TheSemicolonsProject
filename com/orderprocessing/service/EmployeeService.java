package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.entity.Employee;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.ProductsInsertionStatus;

public interface EmployeeService {

	Employee login(String id, String password);

	List<Order> getOrders();

	ProductsInsertionStatus importProducts(String productJSON);

}
