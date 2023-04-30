package com.psp.app.service;

import java.util.List;

import com.psp.app.model.Adopt;
import com.psp.app.model.Assistance;
import com.psp.app.model.Contact;
import com.psp.app.model.Customer;
import com.psp.app.model.DonationFood;
import com.psp.app.model.DonationMoney;
import com.psp.app.model.Pet;
import com.psp.app.model.Report;
import com.psp.app.model.Schedule;
import com.psp.app.model.Service;
import com.psp.app.model.Volunteer;

public interface CustomerService {
	int saveUser(Customer user);
	
	Customer findUser(String email);
	
	int authenticateUser(Customer customer);
	
	Customer findUserByUsername(String username);
	
	int validatePassword(Customer customermodel, String securityQuestion, String securityAnswer);
	
	void saveNewPassword(Customer customermodel);
	
	void deleteUser(Long id);
	

	List<Pet> getAllPets();

	List<Pet> filterPets(String type, String breed);

	List<Pet> searchPets(String searchKey);

	void saveSlot(Schedule schedule);

	void saveTicket(Contact contact);

	void saveReport(Report report);

	void saveService(Service service);

	void saveAssistance(Assistance assistance);

	void saveDonation(DonationMoney donation);

	void saveDonationFood(DonationFood donationfood);

	void saveSdopt(Adopt adopt);

	List<Customer> getAllUsers();

	void saveVolunteer(Volunteer volunteer);

}
