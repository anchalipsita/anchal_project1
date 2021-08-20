package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.ProductSearchDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;

public class ProductSearchDAOImpl implements ProductSearchDAO {
	private static Logger log = Logger.getLogger(ProductSearchDAOImpl.class);

	@Override
	public Product getProductByProductName(String productName) throws BusinessException {
		Product product = null;
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,productName,category,price,rating from product where productName=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, productName);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				product.setRating(resultSet.getDouble("rating"));
			} else {
				throw new BusinessException("The product "+productName+"is not available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return product;
	}

	@Override
	public List<Product> getProductByCategory(String category) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,productName,category,price,rating from product where category=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, category);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				product.setRating(resultSet.getDouble("rating"));
				productList.add(product);
				log.info("\n");
			}  
			if(productList.size()==0)
			{
				throw new BusinessException("The category "+category+" is not available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return productList;
	}

	@Override
	public List<Product> getProductByPrice(double price) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,productName,category,price,rating from product where price<=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setDouble(1, price);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				product.setRating(resultSet.getDouble("rating"));
				productList.add(product);
			}  
			if(productList.size()==0)
			{
				throw new BusinessException(" No products available for "+price+" price range");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return productList;
	}

	@Override
	public List<Product> getProductByRating(double rating) throws BusinessException {
		List<Product> productList = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,productName,category,price,rating from product where rating>=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setDouble(1, rating);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				product.setRating(resultSet.getDouble("rating"));
				productList.add(product);
			}  
			if(productList.size()==0)
			{
				throw new BusinessException("No products available for "+rating+" rating");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return productList;
	}

	@Override
	public List<Product> getAllProducts() throws BusinessException {
		List<Product> productList = new ArrayList<>();
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,productName,category,price,rating from product";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				product.setRating(resultSet.getDouble("rating"));
				productList.add(product);
			}  
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return productList;
	}

	@Override
	public int addProduct(String productName,String category,double price,double rating) throws BusinessException {
		int c=0;
		Product product = null;
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="insert into product(productName,category,price,rating) values(?,?,?,?)";
			PreparedStatement preparedStatement=connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,productName);
			preparedStatement.setString(2,category);
			preparedStatement.setDouble(3,price);
			preparedStatement.setDouble(4,rating);
			
			c = preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					product = new Product();
					product.setId(resultSet.getInt(1));
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return c;
	}

}
