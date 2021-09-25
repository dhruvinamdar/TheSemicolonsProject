package com.orderprocessing.dao;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

/*
 *  Invoice Dao Interface
 *  This is the blueprint for Invoice Dao Implementation Class.
 */
public interface InvoiceDao {
	
	public String displayOrderInvoice(String orderId) throws JsonProcessingException;
}
