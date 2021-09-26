package com.orderprocessing.entity;

public class OrderLine {
	int quantity;

	public OrderLine() {
		super();
	}

	public OrderLine(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderLine [quantity=" + quantity + "]";
	}
}