package com.psp.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.psp.app.model.Customer;
import com.psp.app.model.Product;
import com.psp.app.service.OwnerService;



@Controller
public class StoreOwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@GetMapping("/owner")
	public String getOwnerWelcomePage(@ModelAttribute("customer") Customer customer, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
      
		return "owner/welcomestoreowner";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model, HttpSession session) {
		
		Product product = new Product();
		model.addAttribute("product", product);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
       
		return "owner/addproduct";
	}
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product, Model model, HttpSession session)
	{
		System.out.println("product created");
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        
		
		ownerService.saveProduct(product);
		
		return "redirect:/owner";
	}
	
	@GetMapping("/viewProducts")
	public String viewProducts(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Product> products = ownerService.getAllProducts();
		model.addAttribute("products", products);
		
		return "owner/displayproducts";
	}
	
	@GetMapping("/viewSingleProduct/{id}")
	public String viewSingleProduct(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Product product = ownerService.getProductById(id);
		
		
		model.addAttribute("product", product);
	
		
		return "owner/displaysingleproduct";
	}
	
	@GetMapping("/editProduct/{id}")
	public String editProduct(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Product product = ownerService.getProductById(id);
		
		
		model.addAttribute("product", product);
		

		return "owner/updateproduct";
	}
	
	@PostMapping("/updateProduct")
	public String updateProduct(@ModelAttribute("product") Product product, Model model, HttpSession session)
	{
		System.out.println("product updated");
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		
		
		ownerService.updateProduct(product);
		
		
		return "redirect:/owner";
	}
	
	@PostMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id)
	{
		ownerService.deleteProduct(id);
		
		return "redirect:/owner";
	}

}
