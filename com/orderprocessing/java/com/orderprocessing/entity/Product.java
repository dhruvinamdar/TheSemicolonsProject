package com.orderprocessing.entity;

import java.util.Objects;

public class Product {
	private String productId;
	private float price;
	private String productName;
	private String category;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String productId, int price, String productName, String category) {
		super();
		this.productId = productId;
		this.price = price;
		this.productName = productName;
		this.category = category;
	}

	public Product(String productName, float price) {
		this.productName = productName;
		this.price = price;
	}

	public String getProductId() {
		return this.productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", price=" + price + ", productName=" + productName + ", category="
				+ category + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(category, price, productId, productName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(category, other.category) && price == other.price && productId == other.productId
				&& Objects.equals(productName, other.productName);
	}

}