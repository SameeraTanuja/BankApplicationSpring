package com.capgemini.bankapplication;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.capgemini.bankapplication.controller.CustomerController;
import com.capgemini.bankapplication.exception.UserNotFoundException;
import com.capgemini.bankapplication.model.BankAccount;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.repository.CustomerRepository;
import com.capgemini.bankapplication.service.CustomerService;

public class BankapplicationTests {
	
		@InjectMocks
		private CustomerController customerController;

		@Mock
		CustomerService customerService;
		MockMvc mockMvc;

		@Before
		public void init() {
			MockitoAnnotations.initMocks(this);
			mockMvc=MockMvcBuilders.standaloneSetup(customerController).build();
		}

	

		@Test
		public void testAuthenticate() throws Exception {
			Customer customer = new Customer(null,1234,"tanu123",null,null,null,null);

			
			Customer customer1 = new Customer("Sameera", 1234, "tanu123", "sameera@gmail.com", "hyderabad",
					LocalDate.of(1996, 8, 26), new BankAccount());
			
			when(customerService.authenticate(customer)).thenReturn(customer1);
			mockMvc.perform(post("/login.do").param("customerId","1234").param("password",customer.getPassword())).andExpect(view().name("displayDetails"));
		}

	
	}


