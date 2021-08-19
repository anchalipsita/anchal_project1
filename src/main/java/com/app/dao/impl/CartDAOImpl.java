package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.app.dao.CartDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Product;

public class CartDAOImpl implements CartDAO {
	private static Logger log = Logger.getLogger(CartDAOImpl.class);
	@Override
	public int addToCart(int product_id, int user_id) throws BusinessException {
		int c=0;
		Cart cart = null;
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "insert into cart(product_id,user_id) values(?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, product_id);
			preparedStatement.setInt(2, UserDAOImpl.ad);
			c = preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					cart = new Cart();
					cart.setId(resultSet.getInt(1));
				}
			}	
		
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured, please contact support");
		}
		return 0;
	}

	@Override
	public List<Cart> viewCart() throws BusinessException {
		List<Cart> cartList = new ArrayList<>();
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select id,productName,category,price from product p join cart c on p.id=c.product_id join user u on u.id=c.user_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Cart cart = new Cart();
				Product product = new Product();
				product.setId(resultSet.getInt("id"));
				product.setProductName(resultSet.getString("productName"));
				product.setCategory(resultSet.getString("category"));
				product.setPrice(resultSet.getDouble("price"));
				cart.setProduct(product);
				cartList.add(cart);
			}
			
			if(cartList.size()==0) {
				throw new BusinessException("Cart is empty. You haven't added any product to your cart");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return null;
	}

}
