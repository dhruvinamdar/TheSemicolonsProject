package com.orderProcessing.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orderProcessing.beans.Product;
import com.product.helper.PropertiesHelper;




public class ProductDAOImpl implements ProductDAO{
    
	 private  Connection conn;
	 private PreparedStatement ps;
	 private static String conURL;
	 private static String dbDriver;
	 private static String dbUsername;
	 private static String dbPassword;
	  
	 
	 static {
    	 PropertiesHelper helper = new PropertiesHelper();
         conURL=helper.getProperty("CONURL");
         dbDriver= helper.getProperty("DRIVERCLASSNAME");
         dbUsername=helper.getProperty("DBUSERNAME");
         dbPassword=helper.getProperty("DBPASSWORD");
         try {
        Class c= Class.forName(dbDriver);
         }catch(ClassNotFoundException e)
         {
        	 e.printStackTrace();
         }
    
	@Override
	public void createConnection() {
		try {
			conn = DriverManager.getConnection(conURL,dbUsername,dbPassword);
			System.out.println("+++++++++Connected To Db+++++++++");
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void addProduct(Product product) {
		final String SQL= "insert into product values(?,?,?,?)";
		createConnection();
		try {
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, Product.getProduct_id());
			ps.setInt(2, Product.getPrice());
			ps.setString(3,Product.getProduct_name());
			ps.setString(4, Product.getCategory());
			
			int cnt=ps.executeUpdate();
			if(cnt!=0)
			{
				System.out.println("++++++ Added Record To the +++++++");
				
			}
			}catch(SQLException e)
		{
				e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}

	@Override
	public Product getProductByID(int product_id) {
		Product product = null;
		final String SQL="select * from employee where empid = ?";
	    createConnection();
				try {
					ps=conn.prepareStatement(SQL);
					ps.setInt(1,product_id);
					ResultSet rs= ps.executeQuery();
					if(rs.next())
					{
						 product = new Product();
						product.setProduct_id(rs.getInt("product_id"));
						product.setPrice(rs.getInt("price"));
						product.setProduct_name(rs.getString("Product_name"));
						product.setCategory(rs.getString("Category"));
						
					}
				}catch(SQLException e)
				{
					e.printStackTrace();
				}finally {
					closeConnection();
				} 
			
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		ArrayList<Product> list=new ArrayList<>();
		final String SQL="select * from product";
		createConnection();
		try {
			ps=conn.prepareStatement(SQL);
			ResultSet rs= ps.executeQuery();
			while(rs.next())
			{  Product product= new Product();
			
			product.setProduct_id(rs.getInt("product_id"));
			product.setPrice(rs.getInt("price"));
			product.setProduct_name(rs.getString("Product_name"));
			product.setCategory(rs.getString("Category"));
				list.add(product);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		return list;
	}

	@Override
	public void updatePRODUCT(Product product1) {
		// TODO Auto-generated method stub
		final String SQL="update product set price=?,product_name=?,category=? where product_id=?";
		createConnection();
		try
		{
			ps=conn.prepareStatement(SQL);
			ps.setInt(1, product1.getProduct_id());
			ps.setString(2, product1.getProduct_name());
			ps.setInt(3, product1.getPrice());
			ps.setString(4,product1.getCategory());
			
			int cnt=ps.executeUpdate();
			if(cnt!=0)
			{
				System.out.println("Updated["+cnt+"] Rows");
				
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
		}finally {
			closeConnection();
		}
		
	}

	@Override
	public void deleteProduct(int product_id) {
		// TODO Auto-generated method stub
		final String SQL="delete from product where product_id=?";
		createConnection();
		try
		{
			ps=conn.prepareStatement(SQL);
			ps.setInt(1,product_id);
			int cnt=ps.executeUpdate();
			if(cnt!=0) {
				System.out.println("Deleted["+cnt+"]rows");
				
				}
			}catch(SQLException e)
		{
				e.printStackTrace();
		}finally {
			closeConnection();
		}
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		if(conn!=null)
		{
			try {
				conn.close();
				System.out.println("++++++++Connection ro DB Closed++++++++");
				
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
		
	}







