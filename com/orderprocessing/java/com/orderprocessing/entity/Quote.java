package com.orderprocessing.entity;

import java.util.List;
import java.util.Objects;

public class Quote {

	private String orderDate;
	private String customerId;
	private String shippingCost;
	private String orderValue;
	List<ProductTable> products;

	// private Map<String, String> products; // Map
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(String shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerId, orderDate, orderValue, products, shippingCost);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quote other = (Quote) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderValue, other.orderValue) && Objects.equals(products, other.products)
				&& Objects.equals(shippingCost, other.shippingCost);
	}

	@Override
	public String toString() {
		return "Quote [orderDate=" + orderDate + ", customerId=" + customerId + ", shippingCost=" + shippingCost
				+ ", orderValue=" + orderValue + ", products=" + products + "]";
	}

	public List<ProductTable> getProducts() {
		return products;
	}

	public void setProducts(List<ProductTable> products) {
		this.products = products;
	}

}