package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.NoOrderFoundException;

public interface OrderDao {

	List<Order> getOrders() throws NoOrderFoundException;

	void insertOrders(Quote newOrder);

	void insertOrderLine(Quote newOrder, String lastOrderId);

}
