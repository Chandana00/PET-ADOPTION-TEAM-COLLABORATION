package com.psp.app.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.psp.app.dao.AdoptRepo;
import com.psp.app.dao.AssistanceRepo;
import com.psp.app.dao.ContactRepo;
import com.psp.app.dao.CustomerRepo;
import com.psp.app.dao.DonationFoodRepo;
import com.psp.app.dao.DonationRepo;
import com.psp.app.dao.PetRepo;
import com.psp.app.dao.ReportRepo;
import com.psp.app.dao.ScheduleRepo;
import com.psp.app.dao.ServiceRepo;
import com.psp.app.dao.VolunteerRepo;
import com.psp.app.model.Adopt;
import com.psp.app.model.Customer;
import com.psp.app.model.DonationFood;
import com.psp.app.model.Pet;
import com.psp.app.model.Schedule;
import com.psp.app.model.Volunteer;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Mock
    private PetRepo petRepo;

    @Mock
    private ScheduleRepo scheduleRepo;
	
    @Mock
	private DonationRepo donationRepo;
	
    @Mock
	private DonationFoodRepo donationFoodRepo;
	
    @Mock
	private AdoptRepo adoptRepo;
    
    @Mock
	private VolunteerRepo volunteerRepo;

    
    @Test
    public void getAllPetTest()
    {
    	Pet pet = new Pet();
    	
    	pet.setName("Sunny");
    	pet.setIsAvailable("1");
    	pet.setAge("3");
    	pet.setBreed("Lab");
    	pet.setType("Dog");
    	
        List<Pet> pets = new ArrayList<>();
        pets.add(pet);
       when(petRepo.findAll()).thenReturn(pets);
       assertEquals(pets, ownerService.getAllPets());
    }
    
    @Test
    public void getAllPetNegativeTest()
    {
    	Pet pet = new Pet();
    	
    	pet.setName("Sunny");
    	pet.setIsAvailable("1");
    	pet.setAge("3");
    	pet.setBreed("Lab");
    	pet.setType("Dog");
    	
        List<Pet> pets = new ArrayList<>();
        
       when(petRepo.findAll()).thenReturn(pets);
       assertEquals(0, ownerService.getAllPets().size());
    }
    
    @Test
    public void getAllAdoptionsTest()
    {
    	Adopt adopt = new Adopt();
    	adopt.setAddress("address");
    	adopt.setCustomerEmail("cus@gmail.com");
    	adopt.setCustomerMobile("299238");
    	adopt.setCustomerName("jjhjh");
    	
        List<Adopt> adopts = new ArrayList<>();
        adopts.add(adopt);
       when(adoptRepo.findAll()).thenReturn(adopts);
       assertEquals(adopts, ownerService.getAllAdoptions());
    }
    
    @Test
    public void getAllAdoptionsNegativeTest()
    {
    	Adopt adopt = new Adopt();
    	adopt.setAddress("address");
    	adopt.setCustomerEmail("cus@gmail.com");
    	adopt.setCustomerMobile("299238");
    	adopt.setCustomerName("jjhjh");
    	
        List<Adopt> adopts = new ArrayList<>();
        
       when(adoptRepo.findAll()).thenReturn(adopts);
       assertEquals(0, ownerService.getAllAdoptions().size());
    }
    
    @Test
    public void getAllVolunteerTest()
    {
    	Volunteer vol = new Volunteer();
    	vol.setAddress("address");
    	vol.setCustomerEmail("cus@gmail.com");
    	vol.setCustomerMobile("38837372");
    	vol.setCustomerName("jjhh");
    	
    	
        List<Volunteer> vols = new ArrayList<>();
        vols.add(vol);
       when(volunteerRepo.findAll()).thenReturn(vols);
       assertEquals(vols, ownerService.getAllVolunteers());
    }
    
    @Test
    public void getAllVolunteersNegativeTest()
    {
    	Volunteer vol = new Volunteer();
    	vol.setAddress("address");
    	vol.setCustomerEmail("cus@gmail.com");
    	vol.setCustomerMobile("38837372");
    	vol.setCustomerName("jjhh");
    	
    	
        List<Volunteer> vols = new ArrayList<>();
     
       when(volunteerRepo.findAll()).thenReturn(vols);
       assertEquals(0, ownerService.getAllVolunteers().size());
    }
    
    @Test
    public void getAllFoodDonationTest()
    {
    	DonationFood df = new DonationFood();
    	df.setAddress("address");
    	df.setCustomerEmail("cus@gmail.com");
    	df.setCustomerMobile("38837372");
    	df.setCustomerName("jjhh");
    	
    	
        List<DonationFood> foodDonations = new ArrayList<>();
        foodDonations.add(df);
       when(donationFoodRepo.findAll()).thenReturn(foodDonations);
       assertEquals(foodDonations, ownerService.getAllFoodDontaions());
    }
    
    @Test
    public void getAllFoodDonationNegativeTest()
    {
    	DonationFood df = new DonationFood();
    	df.setAddress("address");
    	df.setCustomerEmail("cus@gmail.com");
    	df.setCustomerMobile("38837372");
    	df.setCustomerName("jjhh");
    	
    	
        List<DonationFood> foodDonations = new ArrayList<>();
       
       when(donationFoodRepo.findAll()).thenReturn(foodDonations);
       assertEquals(0, ownerService.getAllFoodDontaions().size());
    }
    
    @Test
    public void getAllVaccinationsTest()
    {
    	Schedule sc = new Schedule();
    	sc.setCustomerEmail("user@gmail.com");
    	sc.setCustomerMobile("88732878");
    	sc.setType("appointment");
    	sc.setCustomerName("bwcdb");
    	sc.setDatetime("datetime");
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(sc);
       when(scheduleRepo.findAll()).thenReturn(schedules);
       assertEquals(schedules, ownerService.getAllAppointments());
    }
    
    @Test
    public void getAllVaccinationsNegativeTest()
    {
    	Schedule sc = new Schedule();
    	sc.setCustomerEmail("user@gmail.com");
    	sc.setCustomerMobile("88732878");
    	sc.setCustomerName("bwcdb");
    	sc.setDatetime("12-12-23-9:00");
    	
    	
        List<Schedule> schedules = new ArrayList<>();
   
       when(scheduleRepo.findAll()).thenReturn(schedules);
       assertEquals(0, ownerService.getAllVacciness().size());
    }
    
    

    
    
   
    
    

}
