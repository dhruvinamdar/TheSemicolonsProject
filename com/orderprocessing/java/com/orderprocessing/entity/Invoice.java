package com.orderprocessing.entity;

import java.sql.Date;

public class Invoice {
	private String invoiceId;
	private Date invoiceDate;
	private float totalGstAmount;
	private float totalInvoiceValue;
	private String invoiceStatus;
	private Order orders;
	private Customer customer;
	private ProductDetails productDetails;

	public Invoice() {

	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", totalGstAmount=" + totalGstAmount
				+ ", totalInvoiceValue=" + totalInvoiceValue + ", invoiceStatus=" + invoiceStatus + ", orders=" + orders
				+ ", customer=" + customer + ", productDetails=" + productDetails + "]";
	}

	public Invoice(String invoiceId, Date invoiceDate, float totalGstAmount, float totalInvoiceValue,
			String invoiceStatus, Order orders, Customer customer, ProductDetails productDetails) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.totalGstAmount = totalGstAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.invoiceStatus = invoiceStatus;
		this.orders = orders;
		this.customer = customer;
		this.productDetails = productDetails;
	}

	public Order getOrders() {
		return orders;
	}

	public void setOrders(Order orders) {
		this.orders = orders;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public float getTotalGstAmount() {
		return totalGstAmount;
	}

	public void setTotalGstAmount(float totalGstAmount) {
		this.totalGstAmount = totalGstAmount;
	}

	public float getTotalInvoiceValue() {
		return totalInvoiceValue;
	}

	public void setTotalInvoiceValue(float totalInvoiceValue) {
		this.totalInvoiceValue = totalInvoiceValue;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

}