package com.orderprocessing.entity;

import java.util.Objects;

public class ProductDetails {
	private String productName;
	private float productPrice;
	private int quantity;

	public ProductDetails() {

	}

	@Override
	public String toString() {
		return "ProductDetails [productName=" + productName + ", productPrice=" + productPrice + ", quantity="
				+ quantity + "]";
	}

	public ProductDetails(String productName, float productPrice, int quantity) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productName, productPrice, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDetails other = (ProductDetails) obj;
		return Objects.equals(productName, other.productName)
				&& Float.floatToIntBits(productPrice) == Float.floatToIntBits(other.productPrice)
				&& quantity == other.quantity;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
