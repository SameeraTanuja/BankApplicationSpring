package com.capgemini.bankapplication.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapplication.model.BankAccount;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.repository.CustomerRepository;
@Repository
public class CustomerRepositoryImpl implements CustomerRepository{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Customer authenticate(Customer customer) throws DataAccessException {
		// TODO Auto-generated method stub
		
try {
				return jdbcTemplate.queryForObject("select * from customersdata WHERE customerId=? AND password=?",new Object[] {customer.getCustomerId(),customer.getPassword()}, new CustomerRowMapper());
		
	}
catch(DataAccessException e)
{
	e.initCause(new EmptyResultDataAccessException("empty",1));
	throw e;
}

	}

	@Override
	public Customer updateProfile(Customer customer)  {
		// TODO Auto-generated method stub
		 
		 
			 jdbcTemplate.update("UPDATE customersdata SET address=?,email=?  WHERE customerId=?", new Object[] {customer.getAddress(),customer.getEmail(),customer.getCustomerId()});
			return customer;
		 
		 
		
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
		int count=0;
		String originalPassword=jdbcTemplate.queryForObject("select password from customersdata where customerid=?",new Object[] {customer.getCustomerId()},String.class);
		if(oldPassword.equals(originalPassword)) {
        count=jdbcTemplate.update("UPDATE customersdata SET password=?  WHERE customerId=?",new Object[] {newPassword,customer.getCustomerId()});		
		}
		if(count==1)
		{
			return true;
		}
		return false;
	}
	
	catch(DataAccessException e)
	{
		e.initCause(new EmptyResultDataAccessException(1));
		throw e;
	}
}
	
private class CustomerRowMapper implements RowMapper<Customer>{
		
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException{
			Customer customer=new Customer();
			BankAccount account= new BankAccount();
			customer.setCustomerName(rs.getString(1));
			customer.setCustomerId(rs.getInt(2));
			
			customer.setEmail(rs.getString(4));
			customer.setAddress(rs.getString(5));
		    customer.setDateOfBirth(rs.getDate(6).toLocalDate());
		    account= jdbcTemplate.queryForObject("select * from bankaccounts where accountId=?", new Object[] {(rs.getInt(7))},new BankAccountRowMapper());
		    
			customer.setBankAccount(account);
		    
		    return customer;
		}
}
		private class BankAccountRowMapper implements RowMapper<BankAccount>
		{
			
			@Override
			public BankAccount mapRow (ResultSet rs , int rowNum) throws SQLException{
				BankAccount account = new BankAccount();
				account.setAccountId(rs.getLong(1));
				account.setAccountType(rs.getString(3));
				account.setBalance(rs.getDouble(4));
				return account;
				
}
}
}


		   

