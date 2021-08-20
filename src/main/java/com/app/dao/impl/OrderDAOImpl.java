package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.OrderDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Order;
import com.app.model.Product;

public class OrderDAOImpl implements OrderDAO{
	private static Logger log = Logger.getLogger(OrderDAOImpl.class);

//	@Override
//	public int placeOrder(int product_id,int quantity) throws BusinessException {
//		int c=0;
//		Order order = null;
//		try(Connection connection = MySqlDbConnection.getConnection()){
//			String sql = "insert into order(user_id,product_id,quantity,status) values(?,?,?,?)";
//			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
//			preparedStatement.setInt(1, UserDAOImpl.c_id);
//			preparedStatement.setInt(2, product_id);
//			preparedStatement.setInt(3, quantity);
//			preparedStatement.setString(4, "ordered");
//			c = preparedStatement.executeUpdate();
//			if(c==1) {
//				ResultSet resultSet = preparedStatement.getGeneratedKeys();
//				if(resultSet.next()) {
//					order = new Order();
//					order.setId(resultSet.getInt(1));
//				}
//			}	
//		
//		} catch (ClassNotFoundException | SQLException e) {
//			log.error(e);
//			throw new BusinessException("Internal error occured, please contact sysadmin");
//		}
//		
//		return c;
//	}
	
	@Override
	public int placeOrder() throws BusinessException {
		int c=0;
		Order order = null;
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "insert into order(user_id,product_id,status) values(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, UserDAOImpl.c_id);
			preparedStatement.setInt(2, CartDAOImpl.p_id);
			preparedStatement.setString(3, "ordered");
			c = preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					order = new Order();
					order.setId(resultSet.getInt(1));
				}
			}	
		
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		
		return c;
	}


	@Override
	public List<Order> viewOrder() throws BusinessException {
		Order order = null;
		List<Order> orderList = new ArrayList<>();
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select p.id,productName,category,price,status from product p join order o on p.id=o.product_id join user u on u.id=o.user_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				order = new Order();
				Product product = new Product();
				product.setId(resultSet.getInt("p.id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				order.setStatus(resultSet.getString("status"));
				order.setProduct(product);
				orderList.add(order);
			}
			
			if(orderList.size()==0) {
				throw new BusinessException("Order list is empty. You haven't placed any order yet");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return orderList;
	}

	@Override
	public int updateOrderStatus(int user_id,String status) throws BusinessException {
		int c=0;
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "update order set status=? where user_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status);
			preparedStatement.setInt(2, user_id);
			c = preparedStatement.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured, please contact sysadmin");
		}
		
		return c;
	}

}
