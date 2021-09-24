package com.orderprocessing.service;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.dao.CustomerDao;
import com.orderprocessing.dao.CustomerDaoImpl;
import com.orderprocessing.dao.EmployeeDao;
import com.orderprocessing.dao.EmployeeDaoImpl;
import com.orderprocessing.dao.OrderDao;
import com.orderprocessing.dao.OrderDaoImpl;
import com.orderprocessing.dao.ProductDao;
import com.orderprocessing.dao.ProductDaoImpl;
import com.orderprocessing.entity.Employee;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Product;
import com.orderprocessing.entity.ProductsInsertionStatus;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.CustomerNotFoundException;
import com.orderprocessing.exception.EmployeeNotFoundException;
import com.orderprocessing.exception.NoOrderFoundException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	private OrderDao orderDao;
	private ProductDao productDao;
	private CustomerDao customerDao;
	
	
	public EmployeeServiceImpl() {
		
	 employeeDao = new EmployeeDaoImpl();
	 orderDao = new OrderDaoImpl();
	 productDao = new ProductDaoImpl();
	 customerDao = new CustomerDaoImpl();
	 
	}
	
	@Override
	public Employee login(String id,String password) {
		// TODO Auto-generated method stub
		
		try {
			return employeeDao.login(id,password);
		}catch(EmployeeNotFoundException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<Order> getOrders() {
		
		List<Order> orderList = null;
		
		try {
			orderList = orderDao.getOrders();
			if(orderList.size()!=0)
				return orderList;
				// NULL pointer exception possible to be handled later
			
				
		}catch(NoOrderFoundException e) {
			e.printStackTrace();
			// ASK PRITI
		}
		
		return null;
		
		
	}

	@Override
	public ProductsInsertionStatus importProducts(String productJSON) {
		// TODO Auto-generated method stub

		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Product> productList = Arrays
					.asList(mapper.readValue(Paths.get(productJSON).toFile(), Product[].class));

//			productList.forEach(System.out::println);

			return productDao.importProducts(productList);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			return new ProductsInsertionStatus(0, "failed");
		}
		catch(Exception e) {
			return new ProductsInsertionStatus(0, "failed");
		}
		
	}
	
	@Override
	public String getProductData() {
		// TODO Auto-generated method stub

		return productDao.getAllProduct();
	}

	@Override
	public String getCustomer(String id) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(id + "In service customer");
		return customerDao.getCustomer(id);
	}

	@Override
	public String addQuote(String quote) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void insertOrders(String orderJson) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Quote newOrder = mapper.readValue(orderJson, Quote.class);
			orderDao.insertOrders(newOrder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
