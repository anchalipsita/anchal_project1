package com.app.search.service.impl;

import java.util.List;

import com.app.dao.CartDAO;
import com.app.dao.impl.CartDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.search.service.CartSearchService;

public class CartSearchServiceImpl implements CartSearchService {
	private CartDAO cartDAO=new CartDAOImpl();

	@Override
	public int addToCart(int product_id) throws BusinessException {
		int c=0;
		if(product_id>0) {
			c = cartDAO.addToCart(product_id);
		} else {
			throw new BusinessException("Invalid product id");
		}
		return c;
	}

	@Override
	public List<Cart> viewCart() throws BusinessException {
		List<Cart> cartList = null;
		cartList = cartDAO.viewCart();
		return cartList;
	}

}
