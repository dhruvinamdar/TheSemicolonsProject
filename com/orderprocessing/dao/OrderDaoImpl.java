package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.ProductTable;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.NoOrderFoundException;
import com.orderprocessing.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	private static final String SELECT_ORDERS = "SELECT * FROM orders";
	private static final String INSERT_ORDERS = "INSERT into orders(ORDER_DATE,TOTAL_ORDER_VALUE,SHIPPING_COST,CUSTOMER_ID) VALUES (?,?,?,?)";
	private static final String INSERT_ORDER_LINE = "INSERT into order_line VALUES (?,?,?,?)";
	private static final String GET_ORDER_ID = "SELECT * FROM orders WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM orders)";

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

	@Override
	public void insertOrders(Quote newOrder) {

		stmt = null;
		try {
			stmt = connection.prepareStatement(INSERT_ORDERS);
			connection.setAutoCommit(false);
			stmt.setDate(1, Date.valueOf(newOrder.getOrderDate()));
			stmt.setFloat(2, Float.parseFloat(newOrder.getOrderValue()));
			stmt.setFloat(3, Float.parseFloat(newOrder.getShippingCost()));
			stmt.setString(4, newOrder.getCustomerId());
			int num = stmt.executeUpdate();
			if (num > 0) {
				connection.commit();
				stmt = connection.prepareStatement(GET_ORDER_ID);
				resultSet = stmt.executeQuery();
				String lastOrderId = null;
				if (resultSet.next()) {
					lastOrderId = resultSet.getString("ORDER_ID");
					insertOrderLine(newOrder, lastOrderId);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void insertOrderLine(Quote newOrder, String orderId) {

		stmt = null;
		try {

			List<ProductTable> orderList = new ArrayList<ProductTable>();
			orderList = newOrder.getProducts();
			stmt = connection.prepareStatement(INSERT_ORDER_LINE);
			// Batch Insert
			connection.setAutoCommit(false);

			for (ProductTable orderItem : orderList) {
				stmt.setString(1, orderId);
				stmt.setString(2, newOrder.getCustomerId());
				stmt.setString(3, orderItem.getProductId());
				stmt.setInt(4, Integer.parseInt(orderItem.getQuantity()));
				stmt.addBatch();
			}

			int[] inserted = stmt.executeBatch();
			System.out.println(inserted.length + "number of products current order ke liye");
			if (inserted.length > 0)
				connection.commit();

		} catch (SQLException e) {
			// Do the rollback
			doRollback(connection);
			try {
				// Make it back to default.
				connection.setAutoCommit(true);
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}

			e.printStackTrace();
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

	}

	private void doRollback(Connection c) {
		try {
			c.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}
