package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.entity.DetailedQuote;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.ProductDetails;
import com.orderprocessing.entity.ProductTable;
import com.orderprocessing.entity.Quote;
import com.orderprocessing.exception.NoOrderFoundException;
import com.orderprocessing.utils.DBUtil;

public class OrderDaoImpl implements OrderDao {

	private static final String SELECT_ORDERS = "SELECT * FROM orders";
	private static final String INSERT_ORDERS = "INSERT into orders(ORDER_DATE,TOTAL_ORDER_VALUE,SHIPPING_COST,CUSTOMER_ID) VALUES (?,?,?,?)";
	private static final String INSERT_ORDER_LINE = "INSERT into order_line VALUES (?,?,?,?)";
	private static final String GET_ORDER_ID = "SELECT * FROM orders WHERE ORDER_ID = (SELECT MAX(ORDER_ID) FROM orders)";
	private static final String quote = "select ORDER_ID, ORDER_DATE, TOTAL_ORDER_VALUE, SHIPPING_COST from orders where CUSTOMER_ID=? and STATUS='Pending'";
	private static final String quoteDetails = "SELECT orders.ORDER_ID, orders.ORDER_DATE, orders.TOTAL_ORDER_VALUE, orders.SHIPPING_COST, orders.SHIPPING_AGENCY, orders.STATUS, customer.CUSTOMER_ADDRESS_LINE1, customer.CUSTOMER_ADDRESS_CITY, customer.CUSTOMER_ADDRESS_STATE, product.product_name, product.product_price, order_line.quantity from order_line INNER JOIN customer ON order_line.ORDERLINE_CUSTOMER_ID = customer.CUSTOMER_ID inner join orders on orders.order_id = order_line.orderline_order_id inner join product on product.product_id = order_line.orderline_product_id WHERE orders.ORDER_ID=?;";
	private static final String order = "select ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS from orders where STATUS='Approved' or STATUS='Completed' and CUSTOMER_ID=?";
	private static final String orderDetails = "Select * from orders where ORDER_ID = ?";

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
				orderList.add(new Order(resultSet.getString(1), resultSet.getDate(2), resultSet.getFloat(4),
						resultSet.getFloat(5), resultSet.getString(6), resultSet.getString(7)));
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
			stmt.setDate(1, java.sql.Date.valueOf(newOrder.getOrderDate()));
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

	/*
	 * Method to display all details of a particular quote including customer
	 * address. Returns an Array List of type Object containing objects of type
	 * Order and Customer.
	 */

	/*
	 * Method to convert quote to order by changing status from "Pending" to
	 * "Approved". Commit the changes to the database on successful updation, else
	 * rollback.
	 */
	public void setQuoteStatus(String orderId) {
		System.out.println("Order Id in DAOIMPL" + orderId);
		String sql = "update orders set STATUS_DATE=?, STATUS=? where ORDER_ID=?";
		Date date = new Date();
		try {
			connection.setAutoCommit(false);
			stmt = connection.prepareStatement(sql);
			stmt.setDate(1, new java.sql.Date(date.getTime()));
			stmt.setString(2, "Approved");
			stmt.setString(3, orderId);
			int num = stmt.executeUpdate();
			// System.out.println("Changed");
			if (num > 0) {
				System.out.println("Status Approved");

				stmt = connection.prepareStatement(orderDetails);
				stmt.setString(1, orderId);
				resultSet = stmt.executeQuery();
				Order order = new Order();

				if (resultSet.next()) {
					order.setShippingCost(resultSet.getFloat("SHIPPING_COST"));
					order.setTotalOrderValue(resultSet.getFloat("TOTAL_ORDER_VALUE"));
				}

				float gst = order.getTotalOrderValue() * 0.1f;
				float invoiceValue = order.getShippingCost() + order.getTotalOrderValue() + gst;

				String invoiceCalculation = "insert into invoice (INVOICE_DATE, TOTAL_GST_VALUE, TOTAL_INVOICE_VALUE, INVOICE_STATUS, ORDER_ID) values (current_date(),"
						+ gst + " ," + invoiceValue + ",'Paid','" + orderId + "')";
				stmt = connection.prepareStatement(invoiceCalculation);
				int n = stmt.executeUpdate();
				if (n > 0) {
					connection.commit();
					connection.setAutoCommit(true);
				}
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
				connection.setAutoCommit(true);
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
	public String displayOrderDetails(String customerId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			stmt = connection.prepareStatement(order);
			stmt.setString(1, customerId);
			List<Order> orderList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// ORDER_ID, ORDER_DATE, SHIPPING_COST, TOTAL_ORDER_VALUE, STATUS
				Order order = new Order();
				order.setOrderId(rs.getString("ORDER_ID"));
				order.setOrderDate(rs.getDate("ORDER_DATE"));
				order.setTotalOrderValue(rs.getFloat("TOTAL_ORDER_VALUE"));
				order.setShippingCost(rs.getFloat("SHIPPING_COST"));
				order.setStatus(rs.getString("STATUS"));
				orderList.add(order);
			}
			String orderToString = objectMapper.writeValueAsString(orderList);
			return orderToString;
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

	@Override
	public String displayQuoteDetails(String customerId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			stmt = connection.prepareStatement(quote);
			stmt.setString(1, customerId);
			List<Order> quoteList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery();
			// ORDER_ID, ORDER_DATE, TOTAL_ORDER_VALUE, SHIPPING_COST
			while (rs.next()) {
				Order quote = new Order();
				quote.setOrderId(rs.getString("ORDER_ID"));
				quote.setOrderDate(rs.getDate("ORDER_DATE"));
				quote.setShippingCost(rs.getFloat("SHIPPING_COST"));
				quote.setTotalOrderValue(rs.getFloat("TOTAL_ORDER_VALUE"));
				quoteList.add(quote);
			}
			String quoteListToString = objectMapper.writeValueAsString(quoteList);
			return quoteListToString;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DetailedQuote displayDetailedQuote(String orderId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("In Dao : display detailed quote");
		try {
			stmt = connection.prepareStatement(quoteDetails);
			stmt.setString(1, orderId);
//			List<Object> quoteList = new ArrayList<>();
			DetailedQuote quote = new DetailedQuote();
			ResultSet resultSet = stmt.executeQuery();
			String detailedQuoteToString = null;
			List<ProductDetails> products = new ArrayList<>();

			while (resultSet.next()) {
//				quoteList.add(new Order(resultSet.getString(1), resultSet.getDate(2), resultSet.getFloat(3),
//						resultSet.getFloat(4), resultSet.getString(5), resultSet.getString(6)));
//				quoteList.add(new Customer(resultSet.getString(7), resultSet.getString(8), resultSet.getString(9)));
//				quoteList.add(new Product(resultSet.getString(10), resultSet.getFloat(11)));
//				quoteList.add(new OrderLine(resultSet.getInt(12)));
				products.add(new ProductDetails(resultSet.getString("PRODUCT_NAME"),
						resultSet.getFloat("PRODUCT_PRICE"), resultSet.getInt("QUANTITY")));
				quote.setOrderId(resultSet.getString("ORDER_ID"));
				quote.setOrderDate(resultSet.getDate("ORDER_DATE").toString());
				quote.setShippingAgency(resultSet.getString("SHIPPING_AGENCY"));
				System.out.println("Order Id" + resultSet.getString("ORDER_ID"));
				quote.setShippingAddress(resultSet.getString("CUSTOMER_ADDRESS_LINE1"));
				quote.setShippingCost(resultSet.getFloat("SHIPPING_COST"));
				quote.setOrderValue(resultSet.getFloat("TOTAL_ORDER_VALUE"));
			}
			quote.setProducts(products);
			System.out.println("In Dao" + quote);
//			detailedQuoteToString = objectMapper.writeValueAsString(quote);
//			System.out.println(detailedQuoteToString);
//			return detailedQuoteToString;
			return quote;
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