package com.orderprocessing.dao;

import java.util.List;

import com.orderprocessing.entity.Product;
import com.orderprocessing.entity.ProductsInsertionStatus;

public interface ProductDao {

	String getAllProduct();

	ProductsInsertionStatus importProducts(List<Product> productList);

}