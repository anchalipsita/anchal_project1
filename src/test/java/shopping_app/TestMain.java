package shopping_app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.app.dao.CartDAO;
import com.app.dao.ProductSearchDAO;
import com.app.dao.UserDAO;
import com.app.dao.impl.CartDAOImpl;
import com.app.dao.impl.ProductSearchDAOImpl;
import com.app.dao.impl.UserDAOImpl;
import com.app.exception.BusinessException;
import com.app.model.User;
import com.app.search.service.CartSearchService;
import com.app.search.service.ProductSearchService;
import com.app.search.service.UserSearchService;
import com.app.search.service.impl.CartSearchServiceImpl;
import com.app.search.service.impl.ProductSearchServiceImpl;
import com.app.search.service.impl.UserSearchServiceImpl;

class TestMain {
	
	UserSearchService userSearchService = new UserSearchServiceImpl();
	UserDAO userDAO = new UserDAOImpl();
	ProductSearchService productSearchService = new ProductSearchServiceImpl();
	ProductSearchDAO productSearchDAO = new ProductSearchDAOImpl();
	CartSearchService cartSearchService = new CartSearchServiceImpl();
	CartDAO cartDAO = new CartDAOImpl();

	@Test
	// Test case 1
	void testLogin() throws BusinessException {
		assertEquals(1,userDAO.validateUser("anchal@gmail.com", "Anchal@123"));
	}
	
	@Test
	// Test case 2
	void testSignIn() throws BusinessException {
		assertEquals(1,userDAO.createUser("Sonali","Dey","sonali@gmail.com","Sonali@123"));
	}
	
	@Test
	// Test case 3
	void testAllUsers() throws BusinessException {
		assertNotNull(userSearchService.getAllUsers());
	}
	
	@Test
	// Test case 4
	void testUserById() throws BusinessException {
		assertNotNull(userSearchService.getUserById(2));
	}
	
	@Test
	// Test case 5
	void testAllProducts() throws BusinessException {
		assertNotNull(productSearchService.getAllProducts());
	}
	
	@Test
	// Test case 6
	void testProductCategory() throws BusinessException {
		assertNotNull(productSearchService.getProductByCategory("Mobile"));
	}
	
	@Test
	// Test case 7
	void testProductRating() throws BusinessException {
		assertNotNull(productSearchService.getProductByRating(4.0));
	}
	
	@Test
	// Test case 8
	void testProductPrice() throws BusinessException {
		assertNotNull(productSearchService.getProductByPrice(50000));
	}
	
	@Test
	// Test case 9
	void testAddProduct() throws BusinessException {
		assertEquals(1,productSearchDAO.addProduct("Wrogn","Sunglass",3000,3.9));
	}
	
	@Test
	// Test case 10
	void testViewCart() throws BusinessException {
		assertNotNull(cartSearchService.viewCart());
	}
	
	
	
	

}
