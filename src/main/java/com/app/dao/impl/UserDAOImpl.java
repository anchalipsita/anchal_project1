package com.app.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.app.dao.UserDAO;
import com.app.dao.dbutil.MySqlDbConnection;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.model.User;

public class UserDAOImpl implements UserDAO {
	private static Logger log = Logger.getLogger(UserDAOImpl.class);
	public static int ad = 0;

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
					ad = user.getId();
				}
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured, please contact support");
		}
		
		return c;
	}
	
	@Override
	public int validateUser(String email, String password) throws BusinessException {

		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select id from user where email=? and password=?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
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

	@Override
	public List<User> getAllUsers() throws BusinessException {
		List<User> userList = new ArrayList<>();
		try(Connection connection = MySqlDbConnection.getConnection()){
			String sql = "select id,firstName,lastName,email from user";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
				userList.add(user);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return userList;
	}

	@Override
	public User getUserById(int id) throws BusinessException {
		User user = null;
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,firstName,lastName,email from user where id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
			} else {
				throw new BusinessException("User with "+id+" id is not available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws BusinessException {
		User user = null;
		try(Connection connection=MySqlDbConnection.getConnection()){
			String sql="select id,firstName,lastName,email from user where email=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("firstName"));
				user.setLastName(resultSet.getString("lastName"));
				user.setEmail(resultSet.getString("email"));
			} else {
				throw new BusinessException("User with "+email+" email id is not available");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BusinessException("Internal error occured contact sysadmin");
		}
		return user;
	}

}
