package com.orderprocessing.test;

import java.util.List;

import com.orderprocessing.models.Order;
import com.orderprocessing.service.OrderService;
import com.orderprocessing.service.OrderServiceImpl;

public abstract class TestOrder {

	public static void main(String[] args) {
		OrderService orderService = new OrderServiceImpl();
		List<Order> orderList = orderService.getAll();
		for(Order e:orderList) {
			System.out.println(e);
		}
	}
}
