package com.orderProcessing.beans;

public class Product {
	private int product_id;
	private int price;
	private String Product_name;
	private String Category;
	 
	 
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Product(int product_id, String product_name, String category) {
		super();
		this.product_id = product_id;
		Product_name = product_name;
		Category = category;
	}


	public int getProduct_id() {
		return product_id;
	}


	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		return Product_name;
	}


	public void setProduct_name(String product_name) {
		Product_name = product_name;
	}


	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Category == null) ? 0 : Category.hashCode());
		result = prime * result + ((Product_name == null) ? 0 : Product_name.hashCode());
		result = prime * result + product_id;
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
		Product other = (Product) obj;
		if (Category == null) {
			if (other.Category != null)
				return false;
		} else if (!Category.equals(other.Category))
			return false;
		if (Product_name == null) {
			if (other.Product_name != null)
				return false;
		} else if (!Product_name.equals(other.Product_name))
			return false;
		if (product_id != other.product_id)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", Product_name=" + Product_name + ", Category=" + Category + "]";
	}

}
