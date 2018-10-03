package com.capgemini.bankapplication.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapplication.repository.BankAccountRepository;

@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) throws DataAccessException{
		// TODO Auto-generated method stub
		try {
		double balance=jdbcTemplate.queryForObject("SELECT accountBalance FROM bankaccounts WHERE accountId=?", new Object[]{accountId}, Double.class);
		
		return balance;
	
	}
		catch(DataAccessException e)
		{
			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0",1));
			throw e;
		}
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) throws DataAccessException {
		// TODO Auto-generated method stub
		try {
		double balance=jdbcTemplate.queryForObject("select accountBalance from bankaccounts where accountId=?", new Object[] {accountId},Double.class);
		if(balance+newBalance>=0)
		if(jdbcTemplate.update("update bankaccounts set accountBalance = ? where accountId = ?", new Object[] {newBalance+balance,accountId})!=0)
		{
			return true;
		}
		return false;
		
	}
		catch(DataAccessException e)
		{
			e.initCause(new EmptyResultDataAccessException("Expected 1 actual 0", 1));
			throw e;
		}

	}
}
	


