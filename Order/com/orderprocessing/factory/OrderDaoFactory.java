package com.orderprocessing.factory;

import com.orderprocessing.daos.OrderDaosImpl;

public class OrderDaoFactory {
	public static OrderDaosImpl getOrderDAO() {
		return new OrderDaosImpl();
	}
}
