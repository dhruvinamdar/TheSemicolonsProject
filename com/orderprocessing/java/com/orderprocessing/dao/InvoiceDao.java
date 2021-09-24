package com.orderprocessing.dao;

import java.util.List;

/*
 *  Invoice Dao Interface
 *  This is the blueprint for Invoice Dao Implementation Class.
 */
public interface InvoiceDao {

	public List<Object> displayOrderInvoice(String orderId);
}