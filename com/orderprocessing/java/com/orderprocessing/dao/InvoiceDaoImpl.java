package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.Invoice;
import com.orderprocessing.entity.Order;
import com.orderprocessing.entity.ProductDetails;
import com.orderprocessing.utils.DBUtil;

/*
 *  Implementation of Invoice Dao layer.
 *  This layer connects to the database and retrieves the data as needed.
 *  Methods implemented:
 *  - To retrieve invoice related data from the database
 */
public class InvoiceDaoImpl implements InvoiceDao {

	private static final String orderDetails = "Select * from orders where ORDER_ID = ?";
	private static final String invoiceDetails = "select invoice.invoice_id, invoice.invoice_date, invoice.total_gst_value, invoice.total_invoice_value, invoice.invoice_status, order_line.quantity, orders.order_id, orders.order_date, orders.total_order_value, orders.shipping_cost, orders.shipping_agency, customer.customer_name, customer.customer_address_line1, customer.customer_address_city, customer.customer_address_state, customer.customer_pincode, customer.customer_email, customer.customer_contact, product.product_name, product.product_price from invoice inner join order_line on order_line.orderline_order_id = invoice.order_id inner join orders on orders.order_id = order_line.orderline_order_id inner join customer on customer.customer_id = order_line.orderline_customer_id inner join product on product.product_id = order_line.orderline_product_id where current_date() > orders.STATUS_DATE and invoice.order_id=?";

	Connection connection = null;
	PreparedStatement prepStatement;
	ResultSet rs;

	public InvoiceDaoImpl() {
		connection = DBUtil.getMyConnection();
		prepStatement = null;
	}

	/*
	 * Method to retrieve invoice data from the database. Returns the data from
	 * multiple tables in the form of an Array List.
	 */
	public Invoice displayOrderInvoice(String orderId) {
		try {
			prepStatement = connection.prepareStatement(orderDetails);
			prepStatement.setString(1, orderId);
			rs = prepStatement.executeQuery();
			Order order = new Order();
			Invoice invoice = new Invoice();
			Order orders = new Order();
			Customer customer = new Customer();
			ProductDetails productDetails = new ProductDetails();

			if (rs.next()) {
				order.setShippingCost(rs.getFloat("SHIPPING_COST"));
				order.setTotalOrderValue(rs.getFloat("TOTAL_ORDER_VALUE"));
			}

			float gst = order.getTotalOrderValue() * 0.1f;
			float invoiceValue = order.getShippingCost() + order.getTotalOrderValue() + gst;

			String invoiceCalculation = "insert into invoice (INVOICE_DATE, TOTAL_GST_VALUE, TOTAL_INVOICE_VALUE, INVOICE_STATUS, ORDER_ID) values (current_date(),"
					+ gst + " ," + invoiceValue + ",'Paid','" + orderId + "')";
			prepStatement = connection.prepareStatement(invoiceCalculation);
			int n = prepStatement.executeUpdate();
			if (n > 0) {
				prepStatement = connection.prepareStatement(invoiceDetails);
				rs = prepStatement.executeQuery();

				while (rs.next()) {
					invoice.setInvoiceId(rs.getString(1));
					invoice.setInvoiceDate(rs.getDate(2));
					invoice.setTotalGstAmount(rs.getFloat(3));
					invoice.setTotalInvoiceValue(rs.getFloat(4));
					invoice.setInvoiceStatus(rs.getString(5));
					productDetails.setQuantity(rs.getInt(6));
					orders.setOrderId(rs.getString(7));
					orders.setOrderDate(rs.getDate(8));
					orders.setTotalOrderValue(rs.getFloat(9));
					orders.setShippingCost(rs.getFloat(10));
					orders.setShippingAgency(rs.getString(11));
					customer.setCustomerName(rs.getString(12));
					customer.setCustomerAddress(rs.getString(13));
					customer.setCustomerCity(rs.getString(14));
					customer.setCustomerState(rs.getString(15));
					customer.setCustomerPincode(rs.getString(16));
					customer.setCustomerEmail(rs.getString(17));
					customer.setCustomerContact(rs.getString(18));
					productDetails.setProductName(rs.getString(19));
					productDetails.setProductPrice(rs.getFloat(20));
				}
				invoice.setCustomer(customer);
				invoice.setOrders(orders);
				invoice.setProductDetails(productDetails);
				System.out.println("Invoice" + invoice);
				return invoice;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// closed Prepared Statement
				prepStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}