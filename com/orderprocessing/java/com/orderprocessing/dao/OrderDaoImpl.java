package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.ProductTable;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.NoOrderFoundException;
import com.orderprocessing.utils.DBUtil;

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

	public List<Order> displayQuoteDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, TOTAL_ORDER_VALUE, SHIPPING_COST from orders where STATUS='Pending'";
		try {
			stmt = connection.prepareStatement(sql);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Order quote = new Order();
				quote.setOrderId(rs.getString("ORDER_ID"));
				quote.setOrderDate(rs.getDate("ORDER_DATE"));
				quote.setShippingCost(rs.getFloat("TOTAL_ORDER_VALUE"));
				quote.setTotalOrderValue(rs.getFloat("SHIPPING_COST"));
				quoteList.add(quote);
			}
			return quoteList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Method to display all details of a particular quote including customer
	 * address. Returns an Array List of type Object containing objects of type
	 * Order and Customer.
	 */
	public List<Object> displayDetailedQuote(String orderId) {
		String sql = "SELECT orders.ORDER_ID, orders.ORDER_DATE, orders.TOTAL_ORDER_VALUE, orders.SHIPPING_COST, orders.SHIPPING_AGENCY, orders.STATUS, customer.CUSTOMER_ADDRESS_LINE1, customer.CUSTOMER_ADDRESS_CITY, customer.CUSTOMER_ADDRESS_STATE from orders INNER JOIN customer ON orders.CUSTOMER_ID = customer.CUSTOMER_ID WHERE ORDER_ID=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, orderId);
			List<Object> quoteList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(sql);
			// quoteList.add(new Order(rs.getString(1), rs.getDate(2), rs.getFloat(3),
			// rs.getFloat(4), rs.getString(5),
			// rs.getString(6)));
//			quoteList.add(new Customer(rs.getString(7), rs.getString(8), rs.getString(9)));
			return quoteList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// closed Prepared Statement
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * Method to convert quote to order by changing status from "Pending" to
	 * "Approved". Commit the changes to the database on successful updation, else
	 * rollback.
	 */
	public void setQuoteStatus(String orderId) {
		LocalDate statusDate = LocalDate.now();
		String sql = "update testorder set STATUS_DATE='" + statusDate + "', STATUS='Approved' where ORDER_ID=?";
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, orderId);
			int num = stmt.executeUpdate(sql);
			// System.out.println("Changed");
			if (num > 0) {
				connection.commit();
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				// closed Prepared Statement
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Method to display order id, date shipping cost, total order value and status
	 * of all orders whose status are "Approved" or "Completed". Returns an Array
	 * List of type Order.
	 */
	public List<Order> displayOrderDetails() {
		String sql = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS from testorder where STATUS='Approved' or STATUS='Completed'";
		try {
			stmt = connection.prepareStatement(sql);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setOrderId(rs.getString(1));
				order.setOrderDate(rs.getDate(2));
				order.setShippingCost(rs.getFloat(3));
				order.setTotalOrderValue(rs.getFloat(4));
				order.setStatus(rs.getString(5));
				orderList.add(order);
			}
			return orderList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// closed Prepared Statement
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}