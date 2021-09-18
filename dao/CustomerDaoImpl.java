package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.beans.Order;
import com.orderprocessing.utils.DBUtils;

public class CustomerDaoImpl implements CustomerDao {
	Connection conn = null;
	PreparedStatement prepStatement = null;
	
	public CustomerDaoImpl() {
		conn = DBUtils.getConnection();
	}
	
	public List<Order> displayQuoteDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE from order";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getString(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippingCost(rs.getFloat(3));
				order.setTotalOrderValue(rs.getFloat(4));
				
				orderList.add(order);
			}
			return orderList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Order> displayDetailedQuote() {
		String sql = "select * from order";
		try {
			prepStatement = conn.prepareStatement(sql);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = prepStatement.executeQuery(sql);
			while(rs.next()) {
				orderList.add(new Order(rs.getString(1), rs.getDate(2), rs.getString(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6), rs.getString(7)));
			}
			return orderList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
