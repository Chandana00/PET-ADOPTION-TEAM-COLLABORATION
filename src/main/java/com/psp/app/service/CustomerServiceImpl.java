package com.psp.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.app.dao.CustomerRepo;
import com.psp.app.dao.ProductRepo;
import com.psp.app.model.Customer;
import com.psp.app.model.Product;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	public int saveUser(Customer user) {
		customerRepo.save(user);
		if(customerRepo.save(user)!=null) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public Customer findUser(String email) {
		List<Customer> customer = customerRepo.findAll();
		System.out.println("----"+customer.size());
		if(customer.size() == 0) {
			return null;
		}
		List<Customer> veifiedUser = customer.stream().filter(n -> n.getEmail().equals(email)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public int authenticateUser(Customer customer) {
		
		if(customer.getEmail().equals("admin@gmail.com") && customer.getPassword().equals("admin")) {
			
			
			return 2;
		}
		
		List<Customer> users = customerRepo.findAll();
		List<Customer> veifiedUser = users.stream().filter(n -> (n.getEmail().equals(customer.getEmail()) || n.getUsername().equals(customer.getEmail())) && n.getPassword().equals(customer.getPassword())).collect(Collectors.toList());
		
		if(veifiedUser.size() ==1) {
			return 1;
		}
		else {
			return 0;
		}
			
	}
	
	public Customer findUserByUsername(String username) {
		
		List<Customer> users = customerRepo.findAll();
		List<Customer> veifiedUser = users.stream().filter(n -> n.getUsername().equals(username)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public int validatePassword(Customer customermodel, String securityQuestion, String securityAnswer) {
		List<Customer> users = customerRepo.findAll();
		List<Customer> verifiedUser = users.stream().filter(n -> n.getEmail().equals(customermodel.getEmail())).collect(Collectors.toList());
		if(verifiedUser.size() ==1) {
			List<Customer> userSecurities = customerRepo.findAll();
			
			List<Customer> securedUser = userSecurities.stream().filter(security -> security.getSecurityQuestion().equals(securityQuestion) && security.getSecurityAnswer().equals(securityAnswer)
					
					).collect(Collectors.toList());
			if(securedUser.size() == 1) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			return 0;
		}
	}
	
	public void saveNewPassword(Customer customermodel) {
			
			Customer customer = customerRepo.findbyEmail(customermodel.getEmail());
			System.out.println("user#########"+customer.toString());
			customer.setPassword(customermodel.getPassword());
			customerRepo.save(customer);
	}
	
	public void deleteUser(Long id) {
			
			customerRepo.deleteById(id);
			
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		
		return productRepo.findAll();
	}

}
