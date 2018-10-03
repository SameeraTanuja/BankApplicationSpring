package com.capgemini.bankapplication.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapplication.exception.AccountNotFoundException;
import com.capgemini.bankapplication.exception.InsufficientBalanceException;

import com.capgemini.bankapplication.exception.UserNotFoundException;
import com.capgemini.bankapplication.model.Customer;
import com.capgemini.bankapplication.service.BankAccountService;


@Controller
public class BankAccountController {
	@Autowired
	private BankAccountService bankAccountService;
	@RequestMapping("balanceEnquiry")
	public String getBalanceEnquiryPage()
	{
		return "balanceEnquiry";
	}
	
	
	@RequestMapping(value="fundTransfer")
	public String getfundTransferPage() {
	
	return "fundTransfer";
}

	@RequestMapping(value="/fundTransfer.do", method = RequestMethod.POST)
	public String fundTransfer (HttpSession session,HttpServletRequest request, @RequestParam long payeeAccountNumber,@RequestParam double amount) throws InsufficientBalanceException, AccountNotFoundException
	{
		session = request.getSession();
		Customer customer= (Customer) session.getAttribute("customer");
		
		if(bankAccountService.fundTransfer(customer.getBankAccount().getAccountId(), payeeAccountNumber, amount))
		{
			customer.getBankAccount().setBalance(bankAccountService.getBalance(customer.getBankAccount().getAccountId())); 
			session.setAttribute("customer", customer);
			return "transferSuccess";
		}
		else
		{
			System.out.println("hi");
		return "transferFail";
	}
	}
	
	
	
}
