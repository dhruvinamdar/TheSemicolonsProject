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
import com.orderprocessing.util.DBUtil;

public class ProductDaoImpl implements ProductDao {

	private Connection conn;

	private PreparedStatement ps;
	private final static String INSERT_PRODUCT = "Insert into product values (?,?,?,?)";
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
			rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();

				product.setPrice(rs.getInt(" PRODUCT_ID"));
				product.setPrice(rs.getInt(" PRODUCT_PRICE"));
				product.setProductName(rs.getString(" PRODUCT_NAME"));
				product.setCategory(rs.getString(" PRODUCT_CATEGORY"));
				productList.add(product);

			}

			productArrayToJson = objectMapper.writeValueAsString(productList);

			// To convert back to Array
			// Product [] jsonToProductArray =
			// objectMapper.readValue(arrayToJson,Person[].class);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productArrayToJson;
	}

	@Override
	public ProductsInsertionStatus importProducts(List<Product> productList) {
		// TODO Auto-generated method stub

		return null;

	}

//	@Override
//	public void addProduct(Product product) {
//		final String SQL= "insert into product values(?,?,?,?)";
//		try {
//			ps=conn.prepareStatement(SQL);
//			ps.setInt(1, Product.getProduct_id());
//			ps.setInt(2, Product.getPrice());
//			ps.setString(3,Product.getProduct_name());
//			ps.setString(4, Product.getCategory());
//			
//			int cnt=ps.executeUpdate();
//			if(cnt!=0)
//			{
//				System.out.println("++++++ Added Record To the +++++++");
//				
//			}
//			}catch(SQLException e)
//		{
//				e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
//		
//	}
//
//	@Override
//	public Product getProductByID(int product_id) {
//		Product product = null;
//		final String SQL="select * from employee where empid = ?";
//	    createConnection();
//				try {
//					ps=conn.prepareStatement(SQL);
//					ps.setInt(1,product_id);
//					ResultSet rs= ps.executeQuery();
//					if(rs.next())
//					{
//						 product = new Product();
//						product.setProduct_id(rs.getInt("product_id"));
//						product.setPrice(rs.getInt("price"));
//						product.setProduct_name(rs.getString("Product_name"));
//						product.setCategory(rs.getString("Category"));
//						
//					}
//				}catch(SQLException e)
//				{
//					e.printStackTrace();
//				}finally {
//					closeConnection();
//				} 
//			
//		return product;
//	}
//
//	@Override
//	public List<Product> getAllProduct() {
//		ArrayList<Product> list=new ArrayList<>();
//		final String SQL="select * from product";
//		createConnection();
//		try {
//			ps=conn.prepareStatement(SQL);
//			ResultSet rs= ps.executeQuery();
//			while(rs.next())
//			{  Product product= new Product();
//			
//			product.setProduct_id(rs.getInt("product_id"));
//			product.setPrice(rs.getInt("price"));
//			product.setProduct_name(rs.getString("Product_name"));
//			product.setCategory(rs.getString("Category"));
//				list.add(product);
//			}
//		}catch(SQLException e)
//		{
//			e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
//		return list;
//	}
//
//	@Override
//	public void updatePRODUCT(Product product1) {
//		// TODO Auto-generated method stub
//		final String SQL="update product set price=?,product_name=?,category=? where product_id=?";
//		createConnection();
//		try
//		{
//			ps=conn.prepareStatement(SQL);
//			ps.setInt(1, product1.getProduct_id());
//			ps.setString(2, product1.getProduct_name());
//			ps.setInt(3, product1.getPrice());
//			ps.setString(4,product1.getCategory());
//			
//			int cnt=ps.executeUpdate();
//			if(cnt!=0)
//			{
//				System.out.println("Updated["+cnt+"] Rows");
//				
//			}
//		}catch(SQLException e)
//		{
//			e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
//		
//	}
//
//	@Override
//	public void deleteProduct(int product_id) {
//		// TODO Auto-generated method stub
//		final String SQL="delete from product where product_id=?";
//		try
//		{
//			ps=conn.prepareStatement(SQL);
//			ps.setInt(1,product_id);
//			int cnt=ps.executeUpdate();
//			if(cnt!=0) {
//				System.out.println("Deleted["+cnt+"]rows");
//				
//				}
//			}catch(SQLException e)
//		{
//				e.printStackTrace();
//		}finally {
//			closeConnection();
//		}
//	}

}
