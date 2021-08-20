package com.app.search.service;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Order;

public interface OrderSearchService {
	
	public int placeOrder() throws BusinessException;
	public List<Order> viewOrder() throws BusinessException;
	public int updateOrderStatus(int user_id,String status) throws BusinessException;

}
