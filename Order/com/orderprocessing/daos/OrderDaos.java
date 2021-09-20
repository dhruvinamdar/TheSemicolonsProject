package com.orderprocessing.daos;

import java.util.List;

import com.orderprocessing.models.Order;

public interface OrderDaos {
	
	public void createConnection();
	public List<Order> viewOrder();
	public void closeConnection();
}
