package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Cart;
import com.app.model.Order;
import com.app.model.Product;
import com.app.model.User;
import com.app.search.service.CartSearchService;
import com.app.search.service.OrderSearchService;
import com.app.search.service.ProductSearchService;
import com.app.search.service.UserSearchService;
import com.app.search.service.impl.CartSearchServiceImpl;
import com.app.search.service.impl.OrderSearchServiceImpl;
import com.app.search.service.impl.ProductSearchServiceImpl;
import com.app.search.service.impl.UserSearchServiceImpl;

public class Main {

	private static Logger log = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		log.info("Welcome to Anchal's Console Based Shopping App");
		log.info("==========================================");
		UserSearchService userSearchService = new UserSearchServiceImpl();
		ProductSearchService productSearchService = new ProductSearchServiceImpl();
		CartSearchService cartSearchService = new CartSearchServiceImpl();
		OrderSearchService orderSearchService = new OrderSearchServiceImpl();
		int ch = 0;
		do {
			log.info("Main Menu");
			log.info("1)Login as a Customer");
			log.info("2)Login as an Employee");
			log.info("3)Not Registered Yet! Please Sign Up");
			log.info("4)EXIT");
			log.info("Please enter your choice(1-4)");

			try {
				ch = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
			}
			
			switch (ch) {
			case 1:
				log.info("Welcome to Customer Login");
				log.info("Please login using your email and password");

				try {
					log.info("Enter your email id:");
					String email = sc.nextLine();
					log.info("Enter your password:");
					String password = sc.nextLine();
					int c = userSearchService.validateUser(email, password);
					if (c == 1) {
						log.info("Login successful");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					break;
				}

				int option = 0;
				log.info("Welcome dear customer, what you wanna do today?");
				log.info("1)Search Products");
				log.info("2)View Orders");
				log.info("3)Logout");
				log.info("Please enter your choice");
				try {
					option = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
				}
				
				switch (option) {
				case 1:
					log.info("Welcome to Product Search! You can search a product from various criteria from below menu");
					log.info("1)By Product Name");
					log.info("2)By Category");
					log.info("3)By Price");
					log.info("4)By Rating");
					log.info("5)View All Products");
					log.info("6)View Cart");
					log.info("7)Logout");

					int opt = 0;

					try {
						opt = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
					}
					
					switch (opt) {
					case 1:
						log.info("Enter the product name you want to search");
						String productName = sc.nextLine();
						try {
							Product product = productSearchService.getProductByProductName(productName);
							if (product != null) {
								log.info("Product with product name " + productName
										+ " found successfully... Below is the details");
								log.info(product);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
							break;
						}
						
						log.info("Do you want to add this product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i1 = 0;
						try {
							i1 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i1==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}

						break;
						
					case 2:
						log.info("Enter the product category you want to search");
						String category = sc.nextLine();
						try {
							List<Product> productList = productSearchService.getProductByCategory(category);
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add any product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i2 = 0;
						try {
							i2 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i2==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						break;
						
					case 3:
						log.info("Enter the price range you want to search");
						double price = Double.parseDouble(sc.nextLine());
						try {
							List<Product> productList = productSearchService.getProductByPrice(price);
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available in this price range ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add any product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i3 = 0;
						try {
							i3 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i3==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						break;
						
					case 4:
						log.info("Enter the product rating you want to search");
						double rating = Double.parseDouble(sc.nextLine());
						try {
							List<Product> productList = productSearchService.getProductByRating(rating);
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available above "+rating+" rating ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add any product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i4 = 0;
						try {
							i4 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i4==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						break;
						
					case 5:
						try {
							List<Product> productList = productSearchService.getAllProducts();
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add any product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i5 = 0;
						try {
							i5 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i5==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						break;
						
					case 6:
						log.info("Showing your cart details");
						double total=0;
						try {
							List<Cart> cartList = cartSearchService.viewCart();
							if(cartList!=null && cartList.size()>0) {
								for(Cart cart:cartList) {
									log.info(cart.getProduct().getId()+"   "+cart.getProduct().getProductName()+"   "+cart.getProduct().getCategory()+"   "+cart.getProduct().getPrice());
									total+=cart.getProduct().getPrice();
								}
								log.info("Total amount for all products: "+total);
							}
						} catch(BusinessException e) {
							log.warn(e.getMessage());
						}
			
						log.info("Do you wish to place order for above products?");
						log.info("If yes press 1 if no press 0");
						
						int i6=0;
						try {
							i6 = Integer.parseInt(sc.nextLine());
						} catch(NumberFormatException e) {
						}
						
						if(i6==1) {
							try {
//								log.info("Enter the product id for the product you want to order");
//								int product_id = Integer.parseInt(sc.nextLine());
//								log.info("Enter the product quantity you want");
//								int quantity = Integer.parseInt(sc.nextLine());
								orderSearchService.placeOrder();
								log.info("Order placed successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						break;
						
					case 7:				
							log.info("Logging out...");					
						break;
						
					default:
						log.warn("Invalid choice... Choice should be only number between 1-6 only");
						break;
					}
					
					break;
				
				case 2:
					log.info("You can see your order details here");
					double total=0;
					try {
						List<Order> orderList = orderSearchService.viewOrder();
						if(orderList!=null && orderList.size()>0) {
							for(Order order:orderList) {
								log.info(order.getProduct().getId()+"   "+order.getProduct().getProductName()+"   "+order.getProduct().getCategory()+"   "+order.getProduct().getPrice()+"   "+order.getStatus());
								total+=order.getProduct().getPrice();
							}
							log.info("Total amount for all products: "+total);
						}
					} catch(BusinessException e) {
						log.warn(e.getMessage());
					}
					break;
					
				case 3:
					log.info("Logging out...");
					log.info("Thanks for using this APP we will see you soon :)");
					break;
					
				default:
					log.warn("Invalid choice... Choice should be only number between 1-2 only ");
					break;
				}
				
				break;
				
			case 2:
				log.info("Welcome to Employee Login");
				log.info("Please login using your email and password");

				try {
					log.info("Enter your email id:");
					String email = sc.nextLine();
					log.info("Enter your password");
					String password = sc.nextLine();
					int c = userSearchService.validateUser(email, password);
					if (c == 1 && email=="emp@gmail.com" && password=="Emp123@owner") 
						log.info("Login successful");
					
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					break;
				}
				
				int opt1=0;
				log.info("1)Add new product");
				log.info("2)View user details");
				log.info("3)Update order status");
				log.info("Please enter your choice");
				try {
					opt1 = Integer.parseInt(sc.nextLine());
				}catch(NumberFormatException e) {					
				}
				
				switch(opt1) {
				case 1:
					log.info("Want to add a new product!! Please give the product details below");
					try {
						log.info("Enter the product name:");
						String productName = sc.nextLine();
						log.info("Enter the product category:");
						String category = sc.nextLine();
						log.info("Enter the product price:");
						Double price = Double.parseDouble(sc.nextLine());
						log.info("Enter the product rating:");
						Double rating = Double.parseDouble(sc.nextLine());
						productSearchService.addProduct(productName,category,price,rating);
						log.info("Product item is inserted successfully");
					} catch(BusinessException e) {
						log.warn(e.getMessage());
					}
					break;
					
				case 2:
					log.info("You can view user details here");
					int opt2=0;
					log.info("1)View user by id");
					log.info("2)View user by email");
					log.info("3)View all users");
					log.info("4)Go to previous menu");
					try {
						opt2 = Integer.parseInt(sc.nextLine());
					}catch(NumberFormatException e) {					
					}
					
					switch(opt2) {
					case 1:
						log.info("Enter the customer id you want to search");
						int id = Integer.parseInt(sc.nextLine());
						try {
							User user = userSearchService.getUserById(id);
							if (user != null) {
								log.info("User with id " + id
										+ " found successfully... Below is the details");
								log.info(user);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						break;
						
					case 2:
						log.info("Enter the email id of the customer you want to search");
						String email = sc.nextLine();
						try {
							User user = userSearchService.getUserByEmail(email);
							if (user != null) {
								log.info("User with email id " + email
										+ " found successfully... Below is the details");
								log.info(user);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						break;
						
					case 3:
						try {
							List<User> userList = userSearchService.getAllUsers();
							if (userList != null && userList.size()>0) {
								log.info("Total there are " + userList.size()
									+ " users registered ... Below are the details");
								log.info(userList);
							}	
					} catch (BusinessException e) {
						log.warn(e.getMessage());
					}
						break;
						
					case 4:
						log.info("Going back to previous menu...");
						break;
						
					default:
						log.warn("Invalid choice... Choice should be only number between 1-4 only ");
						break;
						
					}
					break;
					
				case 3:
					log.info("Enter the customer id and order status you want to update");
					try {
						log.info("Enter the customer id:");
						int user_id = Integer.parseInt(sc.nextLine());
						log.info("Enter order status:");
						String status = sc.nextLine();
						orderSearchService.updateOrderStatus(user_id,status);
						log.info("Order ststus is updated successfully");
					} catch (BusinessException e) {
						log.warn(e.getMessage());
					}
					
					break;
					
				default:
					log.warn("Invalid choice... Choice should be only number between 1-2 only ");
					break;
				}
				break;
				
			case 3:
				log.info("Welcome here! Please sign up using your first name, last name, email and password");
				try {
					log.info("Enter your first name:");
					String firstName = sc.nextLine();
					log.info("Enter your last name:");
					String lastName = sc.nextLine();
					log.info("Enter your email id:");
					String email = sc.nextLine();
					log.info("Enter your password:");
					String password = sc.nextLine();
					int c = userSearchService.createUser(firstName, lastName, email, password);
					log.info("You have been registered successfully");
				} catch (BusinessException e) {
					log.warn(e.getMessage());
					break;
				}
				
				int option1 = 0;
				log.info("Welcome dear customer, what you wanna do today?");
				log.info("1)Search Products");
				log.info("2)Logout");
				log.info("Please enter your choice");
				try {
					option = Integer.parseInt(sc.nextLine());
				} catch (NumberFormatException e) {
				}
				
				switch (option1) {
				case 1:
					log.info("Welcome to Product Search! You can search a product from various criteria from below menu");
					log.info("1)By Product Name");
					log.info("2)By Category");
					log.info("3)By Price");
					log.info("4)By Rating");
					log.info("5)View All Products");
					log.info("6)View Cart");
					log.info("7)Logout");

					int opt = 0;

					try {
						opt = Integer.parseInt(sc.nextLine());
					} catch (NumberFormatException e) {
					}
					
					switch (opt) {
					case 1:
						log.info("Enter the product name you want to search");
						String productName = sc.nextLine();
						try {
							Product product = productSearchService.getProductByProductName(productName);
							if (product != null) {
								log.info("Product with product name " + productName
										+ " found successfully... Below is the details");
								log.info(product);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add this product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i1 = 0;
						try {
							i1 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i1==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}

						break;
						
					case 2:
						log.info("Enter the product category you want to search");
						String category = sc.nextLine();
						try {
							List<Product> productList = productSearchService.getProductByCategory(category);
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add this product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i2 = 0;
						try {
							i2 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i2==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						
						break;
						
					case 3:
						log.info("Enter the price range you want to search");
						double price = Double.parseDouble(sc.nextLine());
						try {
							List<Product> productList = productSearchService.getProductByPrice(price);
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available in this price range ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add this product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i3 = 0;
						try {
							i3 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i3==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						
						break;
						
					case 4:
						log.info("Enter the product rating you want to search");
						double rating = Double.parseDouble(sc.nextLine());
						try {
							List<Product> productList = productSearchService.getProductByRating(rating);
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available above "+rating+" rating ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add this product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i4 = 0;
						try {
							i4 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i4==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						
						break;
						
					case 5:
						try {
							List<Product> productList = productSearchService.getAllProducts();
							if (productList != null && productList.size()>0) {
								log.info("Total there are " + productList.size()
										+ " products available ... Below are the details");
								log.info(productList);
							}
						} catch (BusinessException e) {
							log.warn(e.getMessage());
						}
						
						log.info("Do you want to add this product to your cart?");
						log.info("If yes press 1 else press 0");
						
						int i5 = 0;
						try {
							i5 = Integer.parseInt(sc.nextLine());
						} catch (NumberFormatException e) {
						}
						
						if(i5==1) {
							try {
								log.info("Enter the product id for the product you want to add to your cart");
								int product_id = Integer.parseInt(sc.nextLine());
								cartSearchService.addToCart(product_id);
								log.info("Product added to the cart successfully");
								break;
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						
						break;
						
					case 6:
						
						log.info("Showing your cart details");
						double total=0;
						try {
							List<Cart> cartList = cartSearchService.viewCart();
							if(cartList!=null && cartList.size()>0) {
								for(Cart cart:cartList) {
									log.info(cart.getProduct().getId()+"   "+cart.getProduct().getProductName()+"   "+cart.getProduct().getCategory()+"   "+cart.getProduct().getPrice());
									total+=cart.getProduct().getPrice();
								}
								log.info("Total amount for all products: "+total);
							}
						} catch(BusinessException e) {
							log.warn(e.getMessage());
						}
			
						log.info("Do you wish to place order for above products?");
						log.info("If yes press 1 if no press 0");
						
						int i6=0;
						try {
							i6 = Integer.parseInt(sc.nextLine());
						} catch(NumberFormatException e) {
						}
						
						if(i6==1) {
							try {
//								log.info("Enter the product id for the product you want to order");
//								int product_id = Integer.parseInt(sc.nextLine());
//								log.info("Enter the product quantity you want");
//								int quantity = Integer.parseInt(sc.nextLine());
								orderSearchService.placeOrder();
								log.info("Order placed successfully");
							} catch (BusinessException e) {
								log.warn(e.getMessage());
								break;
							}
						}
						
						break;
						
					case 7:
						log.info("Logging out...");
						break;
						
					default:
						log.warn("Invalid choice... Choice should be only number between 1-7 only");
						break;
					}
					
					break;
					
				case 2:
					log.info("Logging out...");
					log.info("Thanks for using this APP we will see you soon :)");
					break;
					
				default:
					log.warn("Invalid choice... Choice should be only number between 1-2 only ");
					break;
				}
				
				break;
				
			case 4:
				log.info("Thanks for using this APP we will see you soon :)");
				break;
				
			default:
				log.info("Invalid choice... Choice should be only number between 1-4 only");
				break;
			}	
		} while (ch != 4);	
	}
}
