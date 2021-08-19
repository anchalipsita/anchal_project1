package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.User;


public interface UserDAO {
	
	public int createUser(String firstName, String lastName, String email, String password) throws BusinessException;
	public int validateUser(String email, String password) throws BusinessException;
	public List<User> getAllUsers() throws BusinessException;
	public User getUserById(int id) throws BusinessException;
	public User getUserByEmail(String email) throws BusinessException;

}
