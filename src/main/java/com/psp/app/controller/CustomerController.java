package com.psp.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.psp.app.model.Customer;
import com.psp.app.model.Product;
import com.psp.app.service.CustomerService;
import com.psp.app.service.CustomerServiceImpl;
import com.psp.app.service.OwnerServiceImpl;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/customer")
	public String getCustomerWelcomePage(@ModelAttribute("customer") Customer customer, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        Customer userdata = customerService.findUser(messages.get(0));
//        String base64EncodedImage = Base64.getEncoder().encodeToString(houseOwnerService.getHouse().getHousePhoto());
//        model.addAttribute("image", base64EncodedImage);
//        System.out.println(base64EncodedImage);
        List<Product> products = customerService.getAllProducts();
        model.addAttribute("products", products);
		return "customer/welcomecustomer";
	}

}
