package com.orderprocessing.dao;

import com.orderprocessing.utility.Invoice;

public interface InvoiceDao {
	
	public Invoice displayOrderInvoice(String orderId);
}
