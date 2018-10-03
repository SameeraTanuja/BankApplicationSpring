package com.capgemini.bankapplication.repository;

import org.springframework.dao.DataAccessException;



public interface BankAccountRepository {
	public double getBalance(long accountId) throws DataAccessException;
	public boolean updateBalance(long accountId, double newBalance) throws DataAccessException;



}
