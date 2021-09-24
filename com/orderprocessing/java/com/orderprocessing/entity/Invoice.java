package com.orderprocessing.entity;

import java.sql.Date;
import java.util.Objects;

public class Invoice {
	private String invoiceId;
	private Date invoiceDate;
	private float totalGstAmount;
	private float totalInvoiceValue;
	private String status;
	private String orderId;

	public Invoice() {
		super();
	}

	public Invoice(String invoiceId, Date invoiceDate, float totalGstAmount, float totalInvoiceValue, String status,
			String orderId) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.totalGstAmount = totalGstAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.status = status;
		this.orderId = orderId;
	}

	public Invoice(String invoiceId, Date invoiceDate, float totalGstAmount, float totalInvoiceValue, String status) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		this.totalGstAmount = totalGstAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(invoiceDate, invoiceId, orderId, status, totalGstAmount, totalInvoiceValue);
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
		return Objects.equals(invoiceDate, other.invoiceDate) && Objects.equals(invoiceId, other.invoiceId)
				&& Objects.equals(orderId, other.orderId) && Objects.equals(status, other.status)
				&& Float.floatToIntBits(totalGstAmount) == Float.floatToIntBits(other.totalGstAmount)
				&& Float.floatToIntBits(totalInvoiceValue) == Float.floatToIntBits(other.totalInvoiceValue);
	}

	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", totalGstAmount=" + totalGstAmount
				+ ", totalInvoiceValue=" + totalInvoiceValue + ", status=" + status + ", orderId=" + orderId + "]";
	}

}