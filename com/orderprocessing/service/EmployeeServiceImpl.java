package com.orderprocessing.service;


import java.sql.PreparedStatement;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
import com.orderprocessing.exception.EmployeeNotFoundException;
import com.orderprocessing.exception.NoOrderFoundException;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	private OrderDao orderDao;
	private ProductDao productDao;
	
	
	
	public EmployeeServiceImpl() {
		
	 employeeDao = new EmployeeDaoImpl();
	 orderDao = new OrderDaoImpl();
	 productDao = new ProductDaoImpl();
	 
	 
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
		
		JSONParser parser = new JSONParser();
	    JSONObject obj;
	    List<Product> products = null;
    	
	    try
	    {
	    	
	    	obj = (JSONObject)parser.parse(productJSON);
	        products = (List<Product>) obj.get("products"); 	    	
	    } catch(ParseException e) {
	         e.printStackTrace();
	      }
		
	    return productDao.importProducts(products);
	    
		
	}
	
	
	
	

}
