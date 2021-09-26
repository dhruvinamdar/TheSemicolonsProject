package com.orderprocessing.entity;

import java.util.Objects;

public class ProductTable {
	private String productId;
	private String quantity;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductTable other = (ProductTable) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(quantity, other.quantity);
	}

	@Override
	public String toString() {
		return "ProductTable [productId=" + productId + ", quantity=" + quantity + "]";
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

}