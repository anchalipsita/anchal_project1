package com.app.dao;

import java.util.List;

import com.app.exception.BusinessException;
import com.app.model.Product;

public interface ProductSearchDAO {
	
	public Product getProductByProductName(String productName) throws BusinessException;
	public List<Product> getProductByCategory(String category) throws BusinessException;
	public List<Product> getProductByPrice(double price) throws BusinessException;
	public List<Product> getProductByRating(double rating) throws BusinessException;
	public List<Product> getAllProducts() throws BusinessException;
	public int addProduct(String productName,String category,double price,double rating) throws BusinessException;

}
