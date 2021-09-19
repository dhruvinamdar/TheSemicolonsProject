package com.product.services;



import java.util.List;

import com.orderProcessing.beans.Product;

public interface ProductServices {
	Product  findById(int product_id);
	List<Product> findAll();
	void createProduct(Product product);
	void modifyProduct(Product product);
	void removeProduct(int product_id);
}
