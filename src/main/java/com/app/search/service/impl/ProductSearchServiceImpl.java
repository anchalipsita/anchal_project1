package com.app.search.service.impl;

import java.util.List;

import com.app.dao.ProductSearchDAO;
import com.app.dao.impl.ProductSearchDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.search.service.ProductSearchService;

public class ProductSearchServiceImpl implements ProductSearchService {

	private ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();
	@Override
	public Product getProductByProductName(String productName) throws BusinessException {
		Product product = null;
		if(productName.matches("[0-9]{1,}")) {
			throw new BusinessException("Invalid product "+productName);
		} else {
			product = productSearchDAO.getProductByProductName(productName);
		}
		return product;
	}

	@Override
	public List<Product> getProductByCategory(String category) throws BusinessException {
		List<Product> productList = null;
		if(category.matches("[0-9]{1,}")) {
			throw new BusinessException("Invalid category "+category);
		} else {
			productList = productSearchDAO.getProductByCategory(category);
		}
		return productList;
	}

	@Override
	public List<Product> getProductByPrice(double price) throws BusinessException {
		List<Product> productList = null;
		if(price<0) {
			throw new BusinessException("Invalid price search");
		} else {
			productList = productSearchDAO.getProductByPrice(price);
		}
		return productList;
	}

	@Override
	public List<Product> getProductByRating(double rating) throws BusinessException {
		List<Product> productList = null;
		if(rating<0 || rating>5) {
			throw new BusinessException("Invalid rating search. Rating of product is between 0-5");
		} else {
			productList = productSearchDAO.getProductByRating(rating);
		}
		return productList;
	}

	@Override
	public List<Product> getAllProducts() throws BusinessException {
		List<Product> productList = null;
		productList = productSearchDAO.getAllProducts();
		return productList;
	}

	@Override
	public int addProduct(String productName, String category, double price, double rating) throws BusinessException {
		int c=0;
		c = productSearchDAO.addProduct(productName,category,price,rating);
		return c;
	}

	

}
