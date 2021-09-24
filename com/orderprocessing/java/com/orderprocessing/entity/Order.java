package com.orderprocessing.entity;

import java.sql.Date;

public class Order {
	private String orderId;
	private Date orderDate;
	private String customerShippingAddress;
	private float totalOrderValue;
	private float shippingCost;
	private String shippingAgency;
	private String status;
	
	public Order() {
		super();
	}

	public Order(String orderId, Date orderDate, String customeShippingAddress, float totalOrderValue,
			float shippingCost, String shippingAgency, String status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.customerShippingAddress = customeShippingAddress;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
	}
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomerShippingAddress() {
		return customerShippingAddress;
	}
	public void setCustomerShippingAddress(String customerShippingAddress) {
		this.customerShippingAddress = customerShippingAddress;
	}
	public float getTotalOrderValue() {
		return totalOrderValue;
	}
	public void setTotalOrderValue(float totalOrderValue) {
		this.totalOrderValue = totalOrderValue;
	}
	public float getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getShippingAgency() {
		return shippingAgency;
	}
	public void setShippingAgency(String shippingAgency) {
		this.shippingAgency = shippingAgency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "OrderImpl [orderId=" + orderId + ", orderDate=" + orderDate + ", customeShippingAddress="
				+ customerShippingAddress + ", totalOrderValue=" + totalOrderValue + ", shippingCost=" + shippingCost
				+ ", shippingAgency=" + shippingAgency + ", status=" + status + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerShippingAddress == null) ? 0 : customerShippingAddress.hashCode());
		result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((shippingAgency == null) ? 0 : shippingAgency.hashCode());
		result = prime * result + Float.floatToIntBits(shippingCost);
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + Float.floatToIntBits(totalOrderValue);
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
		Order other = (Order) obj;
		if (customerShippingAddress == null) {
			if (other.customerShippingAddress != null)
				return false;
		} else if (!customerShippingAddress.equals(other.customerShippingAddress))
			return false;
		if (orderDate == null) {
			if (other.orderDate != null)
				return false;
		} else if (!orderDate.equals(other.orderDate))
			return false;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (shippingAgency == null) {
			if (other.shippingAgency != null)
				return false;
		} else if (!shippingAgency.equals(other.shippingAgency))
			return false;
		if (Float.floatToIntBits(shippingCost) != Float.floatToIntBits(other.shippingCost))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Float.floatToIntBits(totalOrderValue) != Float.floatToIntBits(other.totalOrderValue))
			return false;
		return true;
	}

}