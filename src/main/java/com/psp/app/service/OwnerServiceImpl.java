package com.psp.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psp.app.dao.AdoptRepo;
import com.psp.app.dao.DonationFoodRepo;
import com.psp.app.dao.DonationRepo;
import com.psp.app.dao.PetRepo;
import com.psp.app.dao.ScheduleRepo;
import com.psp.app.dao.VolunteerRepo;
import com.psp.app.model.Adopt;
import com.psp.app.model.Customer;
import com.psp.app.model.DonationFood;
import com.psp.app.model.DonationMoney;
import com.psp.app.model.Pet;
import com.psp.app.model.Schedule;
import com.psp.app.model.Volunteer;

@Service
public class OwnerServiceImpl implements OwnerService{
	
	@Autowired 
	private PetRepo petRepo;
	
	@Autowired
	private AdoptRepo adoptRepo;
	
	@Autowired
	private ScheduleRepo scheduleRepo;
	
	@Autowired
	private DonationRepo donationsMoneyRepo;
	
	@Autowired
	private DonationFoodRepo donationsFoodRepo;
	
	@Autowired
	private VolunteerRepo volunteerRepo;
	
	public void savePet(Pet pet) {
		
		Pet savedPet = petRepo.save(pet);
		
	}

	public Pet getPet() {
		// TODO Auto-generated method stub
		return petRepo.findAll().get(0);
	}

	public List<Pet> getAllPets() {
		// TODO Auto-generated method stub
		List<Pet> products = petRepo.findAll();
		return products;
	}

	public void deletePet(Long id) {
		
		petRepo.deleteById(id);
	
	}

	public Pet getPetById(Long id) {
		// TODO Auto-generated method stub
		
		Pet pet = petRepo.findPetById(id);

		return pet;
	}

	
	public void updatePet(Pet pet) {

		
		Pet savedPet = petRepo.save(pet);

	}

	@Override
	public List<Adopt> getAllAdoptions() {
		// TODO Auto-generated method stub
		return adoptRepo.findAll();
	}

	@Override
	public List<Volunteer> getAllVolunteers() {
		// TODO Auto-generated method stub
		return volunteerRepo.findAll();
	}

	@Override
	public List<DonationFood> getAllFoodDontaions() {
		// TODO Auto-generated method stub
		return donationsFoodRepo.findAll();
	}

	@Override
	public List<DonationMoney> getAllMoneyDontaions() {
		// TODO Auto-generated method stub
		return donationsMoneyRepo.findAll();
	}

	@Override
	public List<Schedule> getAllVacciness() {
		// TODO Auto-generated method stub
		return scheduleRepo.findAll().stream().filter(s -> s.getType().equals("vaccination")).collect(Collectors.toList());
	}

	@Override
	public List<Schedule> getAllAppointments() {
		// TODO Auto-generated method stub
		return scheduleRepo.findAll().stream().filter(s -> s.getType().equals("appointment")).collect(Collectors.toList());
	}

	

}
