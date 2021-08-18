package com.app.model;

public class Product {
	
	private int id;
	private String productName;
	private String category;
	private double price;
	private double rating;
	
	public Product() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Product(String productName, String category, double price, double rating) {
		super();
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", category=" + category + ", price=" + price
				+ ", rating=" + rating + "]";
	}
	
	

}
