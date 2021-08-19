package com.app.search.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Cart;

public interface CartSearchService {
	
	public int addToCart(int product_id, int user_id) throws BusinessException;
	public List<Cart> viewCart() throws BusinessException;

}
