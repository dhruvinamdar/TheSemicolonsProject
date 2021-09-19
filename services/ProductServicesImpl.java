package com.product.services;

import java.util.List;

import com.orderProcessing.beans.Product;
import com.orderProcessing.dao.ProductDAO;
import com.orderProcessing.dao.ProductDAOFactory;

public class ProductServicesImpl implements ProductServices {
	public ProductDAO dao;
	public ProductServicesImpl()
	{
		dao=ProductDAOFactory.getProductDAO();
	}
	@Override
	public Product findById(int product_id) {
		// TODO Auto-generated method stub
		return dao.getProductByID(product_id);
	}

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return dao.getAllProduct();
	}

	@Override
	public void createProduct(Product product) {
		// TODO Auto-generated method stub
		dao.addProduct(product);
		
	}

	@Override
	public void modifyProduct(Product product) {
		// TODO Auto-generated method stub
		dao.updatePRODUCT(product);
	}

	@Override
	public void removeProduct(int product_id) {
		// TODO Auto-generated method stub
		dao.deleteProduct(product_id);
	}

}
