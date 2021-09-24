package com.orderprocessing.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderprocessing.entity.Product;
import com.orderprocessing.entity.ProductsInsertionStatus;
import com.orderprocessing.exception.NoProductsToImportException;
import com.orderprocessing.utils.DBUtil;

public class ProductDaoImpl implements ProductDao {

	private Connection conn;

	private PreparedStatement ps;
	private final static String INSERT_PRODUCT = "Insert into product (PRODUCT_NAME,PRODUCT_PRICE,PRODUCT_CATEGORY) values (?,?,?)";
	private final static String GET_ALL_PRODUCTS = "Select * from product";

	ResultSet rs;
	PreparedStatement stmt;
	private Connection connection;

	public ProductDaoImpl() {
		conn = DBUtil.getMyConnection();
	}

	@Override
	public void addProduct(Product product) {

	}

	@Override
	public String getAllProduct() {

		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json

		stmt = null;
		rs = null;
		String productArrayToJson = null;
		List<Product> productList = new ArrayList<Product>();
		try {
			stmt = conn.prepareStatement(GET_ALL_PRODUCTS);
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

			// To convert back to Array
			// Product [] jsonToProductArray =
			// objectMapper.readValue(arrayToJson,Person[].class);
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
	public ProductsInsertionStatus importProducts(List<Product> productList) {
		// TODO Auto-generated method stub
		stmt = null;
		try {
			ProductsInsertionStatus productsInsertionStatus = new ProductsInsertionStatus();
			stmt = conn.prepareStatement(INSERT_PRODUCT);
			// Batch Insert
			conn.setAutoCommit(false);

			for (Product product : productList) {
//				stmt.setString(1, product.getProductId());
				stmt.setString(1, product.getProductName());
				stmt.setFloat(2, product.getPrice());
				stmt.setString(3, product.getCategory());
				stmt.addBatch();
			}

			int[] inserted = stmt.executeBatch();
			System.out.println(inserted.length);
			conn.commit();
			productsInsertionStatus.setNoOfProductsImported(inserted.length);
			productsInsertionStatus.setStatus("completed");

			return productsInsertionStatus;

		} catch (SQLException e) {
			// Do the rollback
			doRollback(conn);
			try {
				// Make it back to default.
				conn.setAutoCommit(true);
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