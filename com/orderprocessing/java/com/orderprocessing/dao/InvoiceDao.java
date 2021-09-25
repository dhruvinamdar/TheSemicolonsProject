package com.orderprocessing.dao;

import com.orderprocessing.entity.Invoice;

/*
 *  Invoice Dao Interface
 *  This is the blueprint for Invoice Dao Implementation Class.
 */
public interface InvoiceDao {

	public Invoice displayOrderInvoice(String orderId);
}