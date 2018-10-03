package com.capgemini.bankapplication;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.bankapplication.controller.CustomerController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankapplicationApplicationTests {

	@Test
	public void contextLoads() {
		CustomerController customerController = new CustomerController();
		String result = customerController.getChangePasswordPage();
		assertEquals(result, "changePassword");
	}

}



