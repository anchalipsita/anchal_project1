package com.app;

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
					String email = sc.nextLine();
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
					log.info("5)View Cart");
					log.info("6)Go to previous menu");
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
						
						break;
						
					case 3:
						
						break;
						
					case 4:
						
						break;
						
					case 5:
						
						break;
						
					case 6:
						
						break;
						
					case 7:
						
						break;
						
					default:
						log.warn("Invalid choice... Choice should be only number between 1-6 only");
						break;
					}
					
					break;
					
				case 2:
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
					String email = sc.nextLine();
					String password = sc.nextLine();
					int c = userSearchService.validateUser(email, password);
					if (c == 1) {
						log.info("Login successful");
					}
				} catch (BusinessException e) {
					log.warn(e.getMessage());
				}
				break;
				
			case 3:
				log.info("Welcome here! Please sign up using your first name, last name, email and password");
				try {
					String firstName = sc.nextLine();
					String lastName = sc.nextLine();
					String email = sc.nextLine();
					String password = sc.nextLine();
					int c = userSearchService.createUser(firstName, lastName, email, password);
					log.info("You have been registered successfully");
				} catch (BusinessException e) {
					log.warn(e.getMessage());
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
