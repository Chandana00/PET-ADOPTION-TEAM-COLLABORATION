package com.psp.app.service;

import java.util.List;

import com.psp.app.model.Customer;
import com.psp.app.model.Product;

public interface CustomerService {
	int saveUser(Customer user);
	
	Customer findUser(String email);
	
	int authenticateUser(Customer customer);
	
	Customer findUserByUsername(String username);
	
	int validatePassword(Customer customermodel, String securityQuestion, String securityAnswer);
	
	void saveNewPassword(Customer customermodel);
	
	void deleteUser(Long id);
	
	List<Product> getAllProducts();

}
