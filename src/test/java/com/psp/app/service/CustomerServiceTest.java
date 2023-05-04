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
import com.psp.app.model.Customer;
import com.psp.app.model.Pet;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepo customerRepo;

    @Mock
    private PetRepo petRepo;

    @Mock
    private ScheduleRepo scheduleRepo;

    @Mock
    private ContactRepo contactRepo;
    
    @Mock
	private ReportRepo reportRepo;
	
    @Mock
	private ServiceRepo serviceRepo;
	
    @Mock
	private AssistanceRepo assistanceRepo;
	
    @Mock
	private DonationRepo donationRepo;
	
    @Mock
	private DonationFoodRepo donationFoodRepo;
	
    @Mock
	private AdoptRepo adoptRepo;

    
    @Test
    public void saveAdminTest() {

        Customer cus = new Customer();

        cus.setUsername("kim");
        cus.setEmail("kim@gmail.com");
        cus.setFirstname("kim");
        cus.setLastname("kim");
        when(customerRepo.save(cus)).thenReturn(cus);

        assertEquals(1,customerService.saveUser(cus));

    }
    
    @Test
    public void saveAdminNegativeTest() {

        Customer cus = new Customer();

        cus.setUsername("kim");
        cus.setEmail("kim@gmail.com");
        cus.setFirstname("kim");
        cus.setLastname("kim");
        when(customerRepo.save(cus)).thenReturn(null);

        assertEquals(0,customerService.saveUser(cus));

    }


    @Test
    public void authenticateUserTest() {

    	Customer cus = new Customer();

        cus.setUsername("kim");
        cus.setEmail("kim@gmail.com");
        cus.setFirstname("kim");
        cus.setLastname("kim");
         cus.setPassword("password");
         List<Customer> customers = new ArrayList<>();
         customers.add(cus);
        when(customerRepo.findAll()).thenReturn(customers);
        assertEquals(1, customerService.authenticateUser(cus));
    }
    

    @Test
    public void authenticateUserNegativeTest() {

    	Customer cus = new Customer();

        cus.setUsername("kim");
        cus.setEmail("kim@gmail.com");
        cus.setFirstname("kim");
        cus.setLastname("kim");
         cus.setPassword("password");
         List<Customer> customers = new ArrayList<>();
         customers.add(cus);
         
         Customer cus1 = new Customer();

         cus1.setUsername("kim1");
         cus1.setEmail("kim1@gmail.com");
         cus1.setFirstname("kim1");
         cus1.setLastname("kim1");
          cus1.setPassword("password1");
          
        when(customerRepo.findAll()).thenReturn(customers);
        assertEquals(0, customerService.authenticateUser(cus1));
    }

    @Test
    public void findUserTest()
    {
    	Customer cus = new Customer();

        cus.setUsername("kim");
        cus.setEmail("kim@gmail.com");
        cus.setFirstname("kim");
        cus.setLastname("kim");
         cus.setPassword("password");
        List<Customer> customers = new ArrayList<>();
        customers.add(cus);
       when(customerRepo.findAll()).thenReturn(customers);
       assertEquals(cus, customerService.findUser(cus.getEmail()));
    }
    
    @Test
    public void findUserNegativeTest()
    {
    	Customer cus = new Customer();

        cus.setUsername("kim");
        cus.setEmail("kim@gmail.com");
        cus.setFirstname("kim");
        cus.setLastname("kim");
         cus.setPassword("password");
        List<Customer> customers = new ArrayList<>();
        customers.add(cus);
       when(customerRepo.findAll()).thenReturn(customers);
       assertEquals(null, customerService.findUser("ss@gmail.com"));
    }
    
    @Test
    public void getAllPetsTest()
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
       assertEquals(pets, customerService.getAllPets());
    }
    
    @Test
    public void getAllPetsNegativeTest()
    {
    	Pet pet = new Pet();
    	
    	pet.setName("Sunny");
    	pet.setIsAvailable("1");
    	pet.setAge("3");
    	pet.setBreed("Lab");
    	pet.setType("Dog");
    	
        List<Pet> pets = new ArrayList<>();
        
       when(petRepo.findAll()).thenReturn(pets);
       assertEquals(0, customerService.getAllPets().size());
    }
    
    
   
    
    

}
