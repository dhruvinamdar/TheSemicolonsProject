package com.orderProcessing.dao;
import java.util.List;

import com.orderProcessing.beans.Product;
public interface ProductDAO {
	

	void createConnection();
	void addProduct(Product product);
	Product getProductByID(int product_id);
	List<Product> getAllProduct();
	void updatePRODUCT(Product product);
	void deleteProduct(int product_id);

	void closeConnection();
	}

