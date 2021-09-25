package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.utility.Customer;
import com.orderprocessing.utility.Invoice;
import com.orderprocessing.utility.Order;
import com.orderprocessing.utility.OrderLine;
import com.orderprocessing.utility.Product;
import com.orderprocessing.utils.DBUtils;

/*
 *  Implementation of Invoice Dao layer.
 *  This layer connects to the database and retrieves the data as needed.
 *  Methods implemented:
 *  - To retrieve invoice related data from the database
 */
public class InvoiceDaoImpl implements InvoiceDao {
	
	private static final String orderDetails = "Select * from orders where ORDER_ID = ?";
	private static final String invoiceDetails = "select invoice.invoice_id, invoice.invoice_date, invoice.total_gst_value, invoice.total_invoice_value, invoice.invoice_status, order_line.quantity, orders.order_id, orders.order_date, orders.total_order_value, orders.shipping_cost, orders.shipping_agency, customer.customer_name, customer.customer_address_line1, customer.customer_address_city, customer.customer_address_state, customer.customer_pincode, customer.customer_email, customer.customer_contact, product.product_name, product.product_price from invoice inner join order_line on order_line.orderline_order_id = invoice.order_id inner join orders on orders.order_id = order_line.orderline_order_id inner join customer on customer.customer_id = order_line.orderline_customer_id inner join product on product.product_id = order_line.orderline_product_id where current_date() > orders.STATUS_DATE and invoice.order_id=?";
	
	Connection conn = null;
	PreparedStatement prepStatement;
	ResultSet rs;
	public InvoiceDaoImpl() {
		conn = DBUtils.getConnection();
		prepStatement = null;
	}
	
	/*
	 * Method to retrieve invoice data from the database.
	 * Returns the data from multiple tables in the form of an Array List.
	 */
	public String displayOrderInvoice(String orderId) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			prepStatement = conn.prepareStatement(orderDetails);
			prepStatement.setString(1, orderId);
			rs = prepStatement.executeQuery();
			Order order = new Order();
			
			if(rs.next())
			{
				order.setShippingCost(rs.getFloat("SHIPPING_COST"));
				order.setTotalOrderValue(rs.getFloat("TOTAL_ORDER_VALUE"));
			}
			
			float gst = order.getTotalOrderValue()*0.1f;
			float invoiceValue = order.getShippingCost() + order.getTotalOrderValue() + gst;
			
			String invoiceCalculation = "insert into invoice (INVOICE_DATE, TOTAL_GST_VALUE, TOTAL_INVOICE_VALUE, INVOICE_STATUS, ORDER_ID) values (current_date(),"+gst+" ,"+invoiceValue+",'Paid','"+orderId+"')";
			prepStatement = conn.prepareStatement(invoiceCalculation);
			int n =  prepStatement.executeUpdate();
			if(n > 0) {
				prepStatement = conn.prepareStatement(invoiceDetails);
				rs = prepStatement.executeQuery();
				List<Object> invoiceList = new ArrayList<>();
				invoiceList.add(new Invoice(rs.getString(1), rs.getDate(2), rs.getFloat(3), rs.getFloat(4), rs.getString(5)));
				invoiceList.add(new OrderLine(rs.getInt(6)));
				invoiceList.add(new Order(rs.getString(7), rs.getDate(8), rs.getFloat(9), rs.getFloat(10), rs.getString(11)));
				invoiceList.add(new Customer(rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18)));
				invoiceList.add(new Product(rs.getString(19), rs.getFloat(20)));
				String invoiceToString = objectMapper.writeValueAsString(invoiceList);
				return invoiceToString;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				//closed Prepared Statement 
				prepStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
