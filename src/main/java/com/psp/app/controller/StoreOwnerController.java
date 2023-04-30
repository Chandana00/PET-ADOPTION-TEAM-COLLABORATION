package com.psp.app.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.psp.app.model.Adopt;
import com.psp.app.model.Customer;
import com.psp.app.model.DonationFood;
import com.psp.app.model.DonationMoney;
import com.psp.app.model.Pet;
import com.psp.app.model.Schedule;
import com.psp.app.model.Volunteer;
import com.psp.app.service.CustomerService;
import com.psp.app.service.OwnerService;
import com.psp.app.service.OwnerServiceImpl;



@Controller
public class StoreOwnerController {
	
	@Autowired
	private OwnerService ownerService;
	
	@Autowired
	private CustomerService customerService;
	
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
	
	@GetMapping("/addPet")
	public String addPet(Model model, HttpSession session) {
		
		Pet pet = new Pet();
		model.addAttribute("pet", pet);
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
       
		return "owner/addpet";
	}
	
	@PostMapping("/savePet")
	public String savePet(@ModelAttribute("pet") Pet pet, Model model, HttpSession session,  @RequestParam("image") MultipartFile petImage)
	{
		System.out.println("product created");
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		
		  
        try {			

			pet.setPetPhoto(Base64.getEncoder().encodeToString(petImage.getBytes()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
        
		
		ownerService.savePet(pet);
		
		return "redirect:/owner";
	}
	
	@GetMapping("/viewPets")
	public String viewPets(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Pet> pets = ownerService.getAllPets();
		model.addAttribute("pets", pets);
		
		return "owner/displaypets";
	}
	
	@GetMapping("/viewSinglePet/{id}")
	public String viewSinglePet(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Pet pet = ownerService.getPetById(id);
		
		
		model.addAttribute("pet", pet);
	
		
		return "owner/displaysinglepet";
	}
	
	@GetMapping("/editPet/{id}")
	public String editProduct(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		Pet pet = ownerService.getPetById(id);
		
		
		model.addAttribute("pet", pet);
		

		return "owner/updatepet";
	}
	
	@PostMapping("/updatePet")
	public String updatePet(@ModelAttribute("pet") Pet pet, Model model, HttpSession session, @RequestParam("image") MultipartFile petImage)
	{
		System.out.println("pet updated");
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		
		   try {			

				pet.setPetPhoto(Base64.getEncoder().encodeToString(petImage.getBytes()));
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		ownerService.updatePet(pet);
		
		
		return "redirect:/owner";
	}
	
	@PostMapping("/deletePet/{id}")
	public String deletePet(@PathVariable(name="id") Long id)
	{
		ownerService.deletePet(id);
		
		return "redirect:/owner";
	}
	
	@GetMapping("/adoptions")
	public String adoptions(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Adopt> adoptions = ownerService.getAllAdoptions();
		model.addAttribute("adoptions", adoptions);
		
		return "owner/adoptions";
	}
	
	
	@GetMapping("/calendar")
	public String calendar(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Schedule> appointmnets = ownerService.getAllAppointments();
		model.addAttribute("appointmnets", appointmnets);
		
		List<Schedule> vaccines = ownerService.getAllVacciness();
		model.addAttribute("vaccines", vaccines);
		
		return "owner/calendar";
	}
	
	@GetMapping("/moneydonations")
	public String moneydonations(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<DonationMoney> donations = ownerService.getAllMoneyDontaions();
		model.addAttribute("donations", donations);
		
		
		return "owner/moneydonations";
	}


	@GetMapping("/fooddonations")
	public String fooddonations(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<DonationFood> donations = ownerService.getAllFoodDontaions();
		model.addAttribute("donations", donations);
		
		
		return "owner/fooddonations";
	}
	
	@GetMapping("/users")
	public String users(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		List<Customer> users = customerService.getAllUsers();
		model.addAttribute("users", users);
		
		
		return "owner/users";
	}
	
	@GetMapping("/volunteers")
	public String volunteers(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}	
		List<Volunteer> volunteers = ownerService.getAllVolunteers();
		model.addAttribute("volunteers", volunteers);
		
		
		return "owner/volunteers";
	}
	
	@GetMapping("/generate")
	public String generate(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}	
		List<Volunteer> volunteers = ownerService.getAllVolunteers();
		List<Customer> users = customerService.getAllUsers();
		List<DonationFood> fdonations = ownerService.getAllFoodDontaions();
		List<DonationMoney> mdonations = ownerService.getAllMoneyDontaions();
		List<Schedule> vaccines = ownerService.getAllVacciness();
		List<Schedule> appointmnets = ownerService.getAllAppointments();
		List<Adopt> adoptions = ownerService.getAllAdoptions();
		model.addAttribute("volunteers", volunteers.size());
		model.addAttribute("users", users.size());
		model.addAttribute("fdonations", fdonations.size());
		model.addAttribute("mdonations", mdonations.size());
		model.addAttribute("vaccines", vaccines.size());
		model.addAttribute("appointmnets", appointmnets.size());
		model.addAttribute("adoptions", adoptions.size());
		
		
		return "owner/generate";
	}
}
