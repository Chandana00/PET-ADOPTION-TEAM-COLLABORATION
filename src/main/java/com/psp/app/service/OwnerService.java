package com.psp.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.app.dao.ProductRepo;
import com.psp.app.model.Product;

@Service
public class OwnerService {
	
	@Autowired 
	private ProductRepo productRepo;
	
	public void saveProduct(Product product) {
		
		Product savedProduct = productRepo.save(product);
		
	}

	public Product getProduct() {
		// TODO Auto-generated method stub
		return productRepo.findAll().get(0);
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = productRepo.findAll();
		return products;
	}

	public void deleteProduct(Long id) {
		
		productRepo.deleteById(id);
	
	}

	public Product getProductById(Long id) {
		// TODO Auto-generated method stub
		
		Product product = productRepo.findProductById(id);

		return product;
	}

	
	public void updateProduct(Product product) {

		
		Product savedProduct = productRepo.save(product);

	}

	

}
