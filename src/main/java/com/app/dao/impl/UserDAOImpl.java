package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.app.dao.UserDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.User;

public class UserDAOImpl implements UserDAO {
	private static Logger log = Logger.getLogger(UserDAOImpl.class);

	@Override
	public int createUser(String firstName, String lastName, String email, String password) throws BusinessException {
		int c = 0;
		User user = null;
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "insert into user(firstName,lastName,email,password) values(?,?,?,?)";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			
			c = preparedStatement.executeUpdate();
			if(c==1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if(resultSet.next()) {
					user = new User();
					user.setId(resultSet.getInt(1));
				}
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured, please contact support");
		}
		
		return c;
	}

//	@Override
//	public User validateUser(User user) throws BusinessException {
//		User username = null;
//		try(Connection connection = MySqlDbConnection.getConnection()){
//			String sql = "select firstName,lastName from user where email=? and password=?";
//			
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, user.getEmail());
//			preparedStatement.setString(2, user.getPassword());
//			ResultSet resultSet = preparedStatement.executeQuery();
//			if(resultSet.next()) {
//				username=new User();
//				username.setFirstName(resultSet.getString("firstName"));
//				username.setLastName(resultSet.getString("lastName"));
//			}else {
//				throw new BusinessException("Entered email id isn't registered or password is wrong! Please check again");
//			}
//		} catch (ClassNotFoundException | SQLException e) {
//			log.error(e);
//			throw new BusinessException("Internal error occured contact sysadmin");
//		}
//		return username;
//	}
	
	@Override
	public int validateUser(String email, String password) throws BusinessException {
//		int c = 0;
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select id from user where email=? and password=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
//			c = preparedStatement.executeUpdate();
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return 1;
			} else {
				throw new BusinessException("Entered email and password isn't registered");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
	}

}
