package com.orderProcessing.dao;
import com.orderProcessing.dao.ProductDAOImpl;
public class ProductDAOFactory {
	public static ProductDAOImpl getProductDAO()
	{
		return new ProductDAOImpl();
	}
}
