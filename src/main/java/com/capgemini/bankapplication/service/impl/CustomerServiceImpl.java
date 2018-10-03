package com.capgemini.bankapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankapplication.exception.PasswordChangeFailedException;
import com.capgemini.bankapplication.exception.ProfileUpdationFailedException;
import com.capgemini.bankapplication.exception.UserNotFoundException;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.repository.BankAccountRepository;
import com.capgemini.bankapplication.repository.CustomerRepository;
import com.capgemini.bankapplication.repository.impl.BankAccountRepositoryImpl;
import com.capgemini.bankapplication.repository.impl.CustomerRepositoryImpl;
import com.capgemini.bankapplication.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;


	@Override
	public Customer authenticate(Customer customer) throws UserNotFoundException {
		// TODO Auto-generated method stub
		try{
			return customerRepository.authenticate(customer);
		}
		catch(DataAccessException e)
		{
			UserNotFoundException u = new UserNotFoundException("No user Found");
			u.initCause(e);
		throw u;	
		}
		
	}

	@Override
	public Customer updateProfile(Customer customer) throws ProfileUpdationFailedException {
		// TODO Auto-generated method stub
		try
		{return customerRepository.updateProfile(customer);
	}
		catch(DataAccessException e)
		{
			ProfileUpdationFailedException updateFailed=new ProfileUpdationFailedException("Failed to update");
			updateFailed.initCause(e);
			throw updateFailed;
		}
	}
	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws PasswordChangeFailedException {
		// TODO Auto-generated method stub
		try {
		return customerRepository.updatePassword(customer, oldPassword, newPassword);
	}
		catch(DataAccessException e)
		{
			PasswordChangeFailedException passwordChangeFail=new PasswordChangeFailedException("Failed to change");
			passwordChangeFail.initCause(e);
			throw e;
		}
	

}
}
