package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.entity.Product;
import com.orderprocessing.entity.ProductsInsertionStatus;
import com.orderprocessing.exception.NoProductsToImportException;
import com.orderprocessing.utils.DBUtil;

public class ProductDaoImpl implements ProductDao {

	private Connection connection;

	private PreparedStatement ps;
	private final static String INSERT_PRODUCT = "Insert into product (PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_CATEGORY) values (?,?,?)";
	private final static String GET_ALL_PRODUCTS = "Select * from product";

	ResultSet rs;
	PreparedStatement stmt;

	public ProductDaoImpl() {
		connection = DBUtil.getMyConnection();
	}

	@Override
	public String getAllProduct() {

		ObjectMapper objectMapper = new ObjectMapper();

		stmt = null;
		rs = null;
		String productArrayToJson = null;
		List<Product> productList = new ArrayList<Product>();
		try {
			stmt = connection.prepareStatement(GET_ALL_PRODUCTS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Product product = new Product();

				product.setProductId(rs.getString("PRODUCT_ID"));
				product.setPrice(rs.getFloat("PRODUCT_PRICE"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setCategory(rs.getString("PRODUCT_CATEGORY"));
				productList.add(product);
			}

			productArrayToJson = objectMapper.writeValueAsString(productList);

			return productArrayToJson;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public ProductsInsertionStatus importProducts(List<Product> productJsonList) {
		// TODO Auto-generated method stub
		stmt = null;
		List<Product> productfromDBList = new ArrayList<Product>();
		List<Product> finalProductList = new ArrayList<Product>();
		HashMap<String, Product> productMap = new HashMap<>();

		try {
			ProductsInsertionStatus productsInsertionStatus = new ProductsInsertionStatus();
			stmt = connection.prepareStatement(GET_ALL_PRODUCTS);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getString("PRODUCT_ID"));
				product.setPrice(rs.getFloat("PRODUCT_PRICE"));
				product.setProductName(rs.getString("PRODUCT_NAME"));
				product.setCategory(rs.getString("PRODUCT_CATEGORY"));
				productfromDBList.add(product);
			}
			for (Product product : productfromDBList)
				productMap.put(product.getProductName(), product);

			for (int i = 0; i < productJsonList.size(); i++) {
				if (productMap.containsKey(productJsonList.get(i).getProductName()))
					continue;
				else
					finalProductList.add(productJsonList.get(i));

			}

			stmt = connection.prepareStatement(INSERT_PRODUCT);
			connection.setAutoCommit(false);

			for (Product product : finalProductList) {
				stmt.setString(1, product.getProductName());
				stmt.setFloat(2, product.getPrice());
				stmt.setString(3, product.getCategory());
				stmt.addBatch();
			}

			int[] inserted = stmt.executeBatch();
			connection.commit();
			productsInsertionStatus.setNoOfProductsImported(inserted.length);
			if (inserted.length == 0)
				productsInsertionStatus.setStatus("failed");
			else
				productsInsertionStatus.setStatus("completed");

			return productsInsertionStatus;

		} catch (SQLException e) {
			doRollback(connection);
			try {
				connection.setAutoCommit(true);
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}

			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

		throw new NoProductsToImportException("Products not imported successfully");
	}

	private void doRollback(Connection c) {
		try {
			c.rollback();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

}