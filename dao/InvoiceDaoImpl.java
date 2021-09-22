package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.orderprocessing.utility.Invoice;
import com.orderprocessing.utils.DBUtils;

public class InvoiceDaoImpl {
	
	Connection conn = null;
	PreparedStatement prepStatement;
	LocalDate statusDate;
	
	public InvoiceDaoImpl() {
		conn = DBUtils.getConnection();
		prepStatement = null;
	}
	
	// method to display invoice details
	public Invoice displayOrderInvoice(String orderId) {
		String sql = " select invoice.ORDER_ID, invoice.INVOICE_ID, invoice.INVOICE_DATE, invoice.TOTAL_GST_VALUE, invoice.TOATL_INVOICE_VALUE, invoice.INVOICE_STATUS from invoice, testorder where testorder.ORDER_ID = invoice.ORDER_ID and current_date() > testorder.STATUS_DATE;";
		try {
			prepStatement = conn.prepareStatement(sql);
			Invoice invoice;
			ResultSet rs = prepStatement.executeQuery(sql);
			invoice = new Invoice(rs.getString(1), rs.getDate(2), rs.getFloat(3), rs.getFloat(4), rs.getString(5), rs.getString(6));
			return invoice;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
