package com.app;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.app.exception.BusinessException;
import com.app.model.Product;
import com.app.model.User;
import com.app.search.service.ProductSearchService;
import com.app.search.service.UserSearchService;
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
				log.info("2)Logout");
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
						break;
						
					case 6:
						
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
						break;
						
					case 6:
						
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
