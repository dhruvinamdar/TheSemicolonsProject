package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.entity.Order;
import com.orderprocessing.exception.NoOrderFoundException;
import com.orderprocessing.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	private static final String SELECT_ORDERS = "SELECT * FROM order";
	ResultSet resultSet;
	PreparedStatement stmt;
	private Connection connection;

	public OrderDaoImpl() {
		connection = DBUtil.getMyConnection();
	}

	@Override
	public List<Order> getOrders() throws NoOrderFoundException {

		stmt = null;
		resultSet = null;
		List<Order> orderList = new ArrayList<Order>();

		try {
			stmt = connection.prepareStatement(SELECT_ORDERS);

			resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				orderList.add(new Order(resultSet.getString(1), resultSet.getDate(2), resultSet.getString(3),
						resultSet.getFloat(4), resultSet.getFloat(5), resultSet.getString(6), resultSet.getString(7)));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		return orderList;

	}

}
