package com.app.search.service.impl;

import java.util.List;

import com.app.dao.UserDAO;
import com.app.dao.impl.UserDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.User;
import com.app.search.service.UserSearchService;

public class UserSearchServiceImpl implements UserSearchService {
	private UserDAO userDAO=new UserDAOImpl();
	@Override
	public int createUser(String firstName, String lastName, String email, String password) throws BusinessException {
		int c=0;
		if((password.matches(".*[0-9]{1,}.*") && password.matches(".*[@!$#]{1,}.*") && password.matches(".*[A-Z]{1,}.*") && password.matches(".*[a-z]{1,}.*")) && (email.endsWith("@gmail.com")  || email.endsWith("@yahoo.com") || email.endsWith("@rediffmail.com") || email.endsWith("@outlook.com"))) {
			c=userDAO.createUser(firstName,lastName,email,password);
		}else {
			throw new BusinessException("Invalid email or password");
		}
		return c;

	}

	@Override
	public int validateUser(String email, String password) throws BusinessException {
		int c=0;
		if((password.matches(".*[0-9]{1,}.*") && password.matches(".*[@!$#]{1,}.*") && password.matches(".*[A-Z]{1,}.*") && password.matches(".*[a-z]{1,}.*")) && (email.endsWith("@gmail.com")  || email.endsWith("@yahoo.com") || email.endsWith("@rediffmail.com") || email.endsWith("@outlook.com"))) {
			c = userDAO.validateUser(email,password);
		}else {
			throw new BusinessException("Invalid email or password");
		}
		return c;
	}

	@Override
	public List<User> getAllUsers() throws BusinessException {
		List<User> userList = null;
		userList=userDAO.getAllUsers();
 		return userList;
	}

	@Override
	public User getUserById(int id) throws BusinessException {
		User user = null;
		if(id<1) {
			throw new BusinessException("Invalid id");
		} else {
			user=userDAO.getUserById(id);
		}
		return user;
	}

	@Override
	public User getUserByEmail(String email) throws BusinessException {
		User user = null;
		if(email.endsWith("@gmail.com")  || email.endsWith("@yahoo.com") || email.endsWith("@rediffmail.com") || email.endsWith("@outlook.com")) {
			user=userDAO.getUserByEmail(email);
		} else {
			throw new BusinessException("Invalid email id");
		}
		return user;
	}

}
