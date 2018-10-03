package com.capgemini.bankapplication.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.capgemini.bankapplication.exception.AccountNotFoundException;
import com.capgemini.bankapplication.exception.InsufficientBalanceException;
import com.capgemini.bankapplication.exception.UserNotFoundException;
import com.capgemini.bankapplication.repository.BankAccountRepository;
import com.capgemini.bankapplication.service.BankAccountService;
@Service
public class BankAccountServiceImpl implements BankAccountService {
@Autowired
	private BankAccountRepository bankAccountRepository;
	
	
	
	
	@Override
	public double getBalance(long accountId)  {
		// TODO Auto-generated method stub
	
	
		return bankAccountRepository.getBalance(accountId);
	}
		

	@Override
	public double withdraw(long accountId, double amount) {
		// TODO Auto-generated method stub
		
		
		if(bankAccountRepository.updateBalance(accountId, -1*amount))
			return bankAccountRepository.getBalance(accountId);
		return bankAccountRepository.getBalance(accountId);
	
		
	}

	@Override
	public double deposit(long accountId, double amount) throws InsufficientBalanceException {
		// TODO Auto-generated method stub
		if(bankAccountRepository.updateBalance(accountId, amount))
			return bankAccountRepository.getBalance(accountId);
		return bankAccountRepository.getBalance(accountId);
	}
	

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws InsufficientBalanceException, AccountNotFoundException {
		// TODO Auto-generated method stub
		if(bankAccountRepository.getBalance(fromAcc)<amount)
		{
			throw new InsufficientBalanceException("amount insufficient");
		}
		else if(bankAccountRepository.getBalance(toAcc)==-1) {
			throw new AccountNotFoundException("incorrect account");
		}
		
		else if(bankAccountRepository.updateBalance(fromAcc, -1*amount))
		{
			if(bankAccountRepository.updateBalance(toAcc, amount))
			{
				return true;
			}
		}
		throw new AccountNotFoundException("Fund Transfer Unsuccessful");
	}
}
	
	


