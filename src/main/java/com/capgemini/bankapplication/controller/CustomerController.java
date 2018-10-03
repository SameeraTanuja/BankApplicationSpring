package com.capgemini.bankapplication.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.service.CustomerService;
import com.capgemini.bankapplication.exception.*;
@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	
	@RequestMapping(value="/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("login")
	public String getLoginCustomerPage() {
		return "login";
	}
	
	@RequestMapping(value="login.do")
	public String displayDetails(HttpSession session,HttpServletRequest request,@RequestParam int customerId, @RequestParam String password) throws UserNotFoundException {
		
		session = request.getSession();
		Customer customer=new Customer(null, customerId, password, null, null, null, null);
		Customer authenticatedCustomer=null;
		authenticatedCustomer=customerService.authenticate(customer);
		System.out.println(authenticatedCustomer);
		session.setAttribute("customer", authenticatedCustomer);
		return "displayDetails";
	}
	@RequestMapping("changePassword")
	public String getChangePasswordPage() {
		return "changePassword";
	}
	@RequestMapping("/changePassword.do")
	public String changePassword(HttpSession session,HttpServletRequest request, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmNewPassword) throws UserNotFoundException {
		session=request.getSession();
		if(newPassword.equals(confirmNewPassword))
		{
			if(customerService.updatePassword((Customer) session.getAttribute("customer"), oldPassword, confirmNewPassword))
			{
				return "passwordSuccessfullyChanged";
			}
		}
		return "err";
	}
	
	@RequestMapping("editProfile")
	public String getEditProfilePage() {
		return "editProfile";
	}

	@RequestMapping(value="/editProfile.do", method = RequestMethod.POST)
	public String editProfile(HttpSession session,HttpServletRequest request, @RequestParam String emailId, @RequestParam String address) throws ProfileUpdationFailedException {
		session=request.getSession();
		Customer customer=new Customer();
		Customer customer2=(Customer) session.getAttribute("customer");
		customer=customer2;
		customer.setAddress(address);
		customer.setEmail(emailId);
		customer = customerService.updateProfile(customer);
		session.setAttribute("customer", customer);
		return "profileUpdatedSuccessfully";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session,HttpServletRequest request) {
		session=request.getSession();
		session.invalidate();
		return "index";
	}
	@RequestMapping("displayDetails")
	public String displayUser() {
		return "displayDetails";
	}
	
}
	


