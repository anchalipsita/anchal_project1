package com.app.search.service;

import com.app.exception.BusinessException;
import com.app.model.User;

public interface UserSearchService {
	
	public int createUser(String firstName, String lastName, String email, String password) throws BusinessException;
	public int validateUser(String email, String password) throws BusinessException;
	

}
