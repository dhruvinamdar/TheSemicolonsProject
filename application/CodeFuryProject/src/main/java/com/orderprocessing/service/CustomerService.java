package com.orderprocessing.service;

import com.orderprocessing.entity.Customer;
import com.orderprocessing.entity.DetailedQuote;
import com.orderprocessing.entity.Invoice;

/*
 *  Customer Service interface
 *  This is the blueprint for Customer Service Implementation Class.
 */

public interface CustomerService {

	public Customer validateCustomer(String customerLogin, String password);

	public String getCustomer(String customerIdOrName);

	public String displayQuote(String customerId);

	public DetailedQuote displayAllQuoteDetails(String orderId);

	public void changeQuoteStatus(String orderId);

	public String displayOrder(String customerId);

	public Invoice showInvoice(String orderId);

}