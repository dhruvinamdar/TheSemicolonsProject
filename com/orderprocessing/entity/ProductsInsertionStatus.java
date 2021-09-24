package com.orderprocessing.entity;

import java.util.Objects;

public class ProductsInsertionStatus {

	private int noOfProductsImported;

	public enum Status {
		completed, failed
	};

	private Status status;

	public ProductsInsertionStatus() {

	}

	public ProductsInsertionStatus(int noOfProductsImported, Status status) {
		super();
		this.noOfProductsImported = noOfProductsImported;
		this.status = status;
	}

	public int getNoOfProductsImported() {
		return noOfProductsImported;
	}

	public void setNoOfProductsImported(int noOfProductsImported) {
		this.noOfProductsImported = noOfProductsImported;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ProductsInsertionStatus [noOfProductsImported=" + noOfProductsImported + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(noOfProductsImported, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductsInsertionStatus other = (ProductsInsertionStatus) obj;
		return noOfProductsImported == other.noOfProductsImported && status == other.status;
	}

}
