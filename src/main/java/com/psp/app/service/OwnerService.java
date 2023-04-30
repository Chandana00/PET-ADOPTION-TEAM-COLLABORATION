package com.psp.app.service;

import java.util.List;

import com.psp.app.model.Adopt;
import com.psp.app.model.Customer;
import com.psp.app.model.DonationFood;
import com.psp.app.model.DonationMoney;
import com.psp.app.model.Pet;
import com.psp.app.model.Schedule;
import com.psp.app.model.Volunteer;

public interface OwnerService {

	void savePet(Pet pet);
	
	Pet getPet();
	
	List<Pet> getAllPets();
	
	void deletePet(Long id);
	
	Pet getPetById(Long id);
	
	void updatePet(Pet pet);

	List<Adopt> getAllAdoptions();

	List<Volunteer> getAllVolunteers();

	List<DonationFood> getAllFoodDontaions();

	List<DonationMoney> getAllMoneyDontaions();

	List<Schedule> getAllVacciness();

	List<Schedule> getAllAppointments();

}
