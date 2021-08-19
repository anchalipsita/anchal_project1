package com.app.model;

public class Order {
	
	private int id;
	private int product_id;
	private int user_id;
	private int quantity;
	private String status;
	private Product product;
	
	public Order() {
		
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", product_id=" + product_id + ", user_id=" + user_id + ", quantity=" + quantity
				+ ", status=" + status + ", product=" + product + "]";
	}

	
	
	
	
	

}
