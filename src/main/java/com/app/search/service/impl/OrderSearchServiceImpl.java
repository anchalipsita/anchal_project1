package com.app.search.service.impl;

import java.util.List;

import com.app.dao.OrderDAO;
import com.app.dao.impl.OrderDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Order;
import com.app.search.service.OrderSearchService;

public class OrderSearchServiceImpl implements OrderSearchService {
	private OrderDAO orderDAO=new OrderDAOImpl();

	@Override
	public int placeOrder() throws BusinessException {
		int c=0;
		c = orderDAO.placeOrder();
		return c;
	}

	@Override
	public List<Order> viewOrder() throws BusinessException {
		List<Order> orderList = null;
		orderList = orderDAO.viewOrder();
		return orderList;
	}

	@Override
	public int updateOrderStatus(int user_id, String status) throws BusinessException {
		int c = 0;
		if(user_id>0 &&(status=="ordered" || status=="shipped" || status=="delivered")) {
			c = orderDAO.updateOrderStatus(user_id, status);
		} else {
			throw new BusinessException("Invalid user_id or status");
		}
		return c;
	}

}
