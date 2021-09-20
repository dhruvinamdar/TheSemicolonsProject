package com.orderprocessing.service;

import java.util.List;

import com.orderprocessing.daos.OrderDaos;
import com.orderprocessing.factory.OrderDaoFactory;
import com.orderprocessing.models.Order;

public class OrderServiceImpl implements OrderService {
	private OrderDaos dao;
	public OrderServiceImpl() {
		dao = OrderDaoFactory.getOrderDAO();
	}

	@Override
	public List<Order> getAll() {
		return dao.viewOrder();
	}

}
