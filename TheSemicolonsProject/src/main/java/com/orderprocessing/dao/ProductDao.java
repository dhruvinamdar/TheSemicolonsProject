package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.entity.Product;
import com.orderprocessing.entity.ProductsInsertionStatus;

public interface ProductDao {

	void addProduct(Product product);

	// Product getProductByID(int product_id);
	String getAllProduct();

	ProductsInsertionStatus importProducts(List<Product> productList);

}