package com.orderprocessing.utility;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Order {
	private String orderId;
	private Date orderDate;
	private float totalOrderValue;
	private float shippingCost;
	private String shippingAgency;
	private String status;
	private LocalDate statusDate;
	
	public Order() {
		super();
	}

	public Order(String orderId, Date orderDate, float totalOrderValue,
			float shippingCost, String shippingAgency, String status) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
		this.status = status;
	}
	
	
	public Order(String orderId, Date orderDate, float totalOrderValue, float shippingCost, String shippingAgency) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.totalOrderValue = totalOrderValue;
		this.shippingCost = shippingCost;
		this.shippingAgency = shippingAgency;
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
		return "OrderImpl [orderId=" + orderId + ", orderDate=" + orderDate + ", totalOrderValue=" + totalOrderValue + ", shippingCost=" + shippingCost
				+ ", shippingAgency=" + shippingAgency + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderDate, orderId, shippingAgency, shippingCost, status, statusDate, totalOrderValue);
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
		return Objects.equals(orderDate, other.orderDate) && Objects.equals(orderId, other.orderId)
				&& Objects.equals(shippingAgency, other.shippingAgency)
				&& Float.floatToIntBits(shippingCost) == Float.floatToIntBits(other.shippingCost)
				&& Objects.equals(status, other.status) && Objects.equals(statusDate, other.statusDate)
				&& Float.floatToIntBits(totalOrderValue) == Float.floatToIntBits(other.totalOrderValue);
	}
	
	

	
}