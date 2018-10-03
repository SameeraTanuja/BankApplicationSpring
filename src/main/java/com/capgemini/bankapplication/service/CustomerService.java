package com.capgemini.bankapplication.service;

import com.capgemini.bankapplication.exception.PasswordChangeFailedException;
import com.capgemini.bankapplication.exception.ProfileUpdationFailedException;
import com.capgemini.bankapplication.exception.UserNotFoundException;
import com.capgemini.bankapplication.model.Customer;

public interface CustomerService {
	public Customer authenticate(Customer customer) throws UserNotFoundException; 
	public Customer updateProfile(Customer customer) throws ProfileUpdationFailedException ;
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws PasswordChangeFailedException;
}


