package com.orderprocessing.entity;

import java.util.List;

public class DetailedQuote {
	private String orderId;
	private String orderDate;
	private String shippingAgency;
	private float shippingCost;
	private String shippingAddress;
	private float orderValue;
	List<ProductDetails> products;

	public List<ProductDetails> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDetails> products) {
		this.products = products;
	}

	public DetailedQuote() {
	}

	public DetailedQuote(String orderId, String orderDate, String shippingAgency, float shippingCost,
			String shippingAddress, float orderValue, List<ProductDetails> products, String productName,
			String productPrice, String productQuantity) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shippingAgency = shippingAgency;
		this.shippingCost = shippingCost;
		this.shippingAddress = shippingAddress;
		this.orderValue = orderValue;
		this.products = products;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
	}

	private String productName;
	private String productPrice;
	private String productQuantity;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAgency() {
		return shippingAgency;
	}

	public void setShippingAgency(String shippingAgency) {
		this.shippingAgency = shippingAgency;
	}

	public float getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(float shippingCost) {
		this.shippingCost = shippingCost;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public float getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(float orderValue) {
		this.orderValue = orderValue;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "DetailedQuote [orderId=" + orderId + ", orderDate=" + orderDate + ", shippingAgency=" + shippingAgency
				+ ", shippingCost=" + shippingCost + ", shippingAddress=" + shippingAddress + ", orderValue="
				+ orderValue + ", products=" + products + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productQuantity=" + productQuantity + "]";
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

}
