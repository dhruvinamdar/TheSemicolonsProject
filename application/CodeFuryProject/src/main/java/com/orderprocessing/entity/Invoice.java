package com.orderprocessing.entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Invoice {
	private String invoiceId;
	private Date invoiceDate;
	private float totalGstAmount;
	private float totalInvoiceValue;
	private String invoiceStatus;
	private Order orders;
	private Customer customer;
	private List<ProductDetails> productDetails;

	public Invoice() {

	}

	public Invoice(String invoiceId, Date invoiceDate, float totalGstAmount, float totalInvoiceValue,
			String invoiceStatus, Order orders, Customer customer, List<ProductDetails> productDetails) {
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

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, invoiceDate, invoiceId, invoiceStatus, orders, productDetails, totalGstAmount,
				totalInvoiceValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(invoiceDate, other.invoiceDate)
				&& Objects.equals(invoiceId, other.invoiceId) && Objects.equals(invoiceStatus, other.invoiceStatus)
				&& Objects.equals(orders, other.orders) && Objects.equals(productDetails, other.productDetails)
				&& Float.floatToIntBits(totalGstAmount) == Float.floatToIntBits(other.totalGstAmount)
				&& Float.floatToIntBits(totalInvoiceValue) == Float.floatToIntBits(other.totalInvoiceValue);
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", totalGstAmount=" + totalGstAmount
				+ ", totalInvoiceValue=" + totalInvoiceValue + ", invoiceStatus=" + invoiceStatus + ", orders=" + orders
				+ ", customer=" + customer + ", productDetails=" + productDetails + "]";
	}

}