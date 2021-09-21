package com.orderprocessing.models;

import java.sql.Date;

public class Invoice {
	private String invoiceId;
	private Date invoiceDate;
	private String OrderId;
	private float totalGstAmount;
	private float totalInvoiceValue;
	private String Status;
	public Invoice() {
		super();
	}
	public Invoice(String invoiceId, Date invoiceDate, String orderId, float totalGstAmount, float totalInvoiceValue,
			String status) {
		super();
		this.invoiceId = invoiceId;
		this.invoiceDate = invoiceDate;
		OrderId = orderId;
		this.totalGstAmount = totalGstAmount;
		this.totalInvoiceValue = totalInvoiceValue;
		Status = status;
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
	public String getOrderId() {
		return OrderId;
	}
	public void setOrderId(String orderId) {
		OrderId = orderId;
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
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "Invoice [invoiceId=" + invoiceId + ", invoiceDate=" + invoiceDate + ", OrderId=" + OrderId
				+ ", totalGstAmount=" + totalGstAmount + ", totalInvoiceValue=" + totalInvoiceValue + ", Status="
				+ Status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((OrderId == null) ? 0 : OrderId.hashCode());
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		result = prime * result + ((invoiceDate == null) ? 0 : invoiceDate.hashCode());
		result = prime * result + ((invoiceId == null) ? 0 : invoiceId.hashCode());
		result = prime * result + Float.floatToIntBits(totalGstAmount);
		result = prime * result + Float.floatToIntBits(totalInvoiceValue);
		return result;
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
		if (OrderId == null) {
			if (other.OrderId != null)
				return false;
		} else if (!OrderId.equals(other.OrderId))
			return false;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		if (invoiceDate == null) {
			if (other.invoiceDate != null)
				return false;
		} else if (!invoiceDate.equals(other.invoiceDate))
			return false;
		if (invoiceId == null) {
			if (other.invoiceId != null)
				return false;
		} else if (!invoiceId.equals(other.invoiceId))
			return false;
		if (Float.floatToIntBits(totalGstAmount) != Float.floatToIntBits(other.totalGstAmount))
			return false;
		if (Float.floatToIntBits(totalInvoiceValue) != Float.floatToIntBits(other.totalInvoiceValue))
			return false;
		return true;
	}
	

}
