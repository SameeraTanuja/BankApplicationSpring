package com.capgemini.bankapplication.controller;

	import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

	import org.springframework.web.bind.annotation.ControllerAdvice;
	import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.bankapplication.exception.AccountNotFoundException;
import com.capgemini.bankapplication.exception.InsufficientBalanceException;
import com.capgemini.bankapplication.exception.PasswordChangeFailedException;
import com.capgemini.bankapplication.exception.ProfileUpdationFailedException;
import com.capgemini.bankapplication.exception.UserNotFoundException;

	@ControllerAdvice
	public class ExceptionController {

//		@ExceptionHandler(value = Exception.class)
//		public String handleError(HttpServletRequest request, Exception exception) {
//			System.out.println(exception);
//			request.setAttribute("success", false);
//			request.setAttribute("error", exception.getMessage() + request.getRequestURI());
//			return "success";
//		}

		@ExceptionHandler(value = UserNotFoundException.class)
		public String handlheError(HttpServletRequest request, UserNotFoundException exception) {
			System.out.println(exception);
			//request.setAttribute("success", false);
			request.setAttribute("error", exception);
			System.out.println(exception.getCause());
			return "success";
		}

		@ExceptionHandler(value = InsufficientBalanceException.class)
		public String handlheErrorf(HttpServletRequest request, InsufficientBalanceException exception) {
			System.out.println(exception);
			//request.setAttribute("success", false);
			request.setAttribute("error", exception);
			System.out.println(exception.getCause());
			return "success";
		}
		@ExceptionHandler(value = PasswordChangeFailedException.class)
		public String handlheErrorf(HttpServletRequest request, PasswordChangeFailedException exception) {
			System.out.println(exception);
			request.setAttribute("success", false);
			request.setAttribute("error", exception);
			System.out.println(exception.getCause());
			return "success";
		}

		@ExceptionHandler(value = ProfileUpdationFailedException.class)
		public String handlheErrorf(HttpServletRequest request, ProfileUpdationFailedException exception) {
			System.out.println(exception);
			request.setAttribute("success", false);
			request.setAttribute("error", exception);
			System.out.println(exception.getCause());
			return "success";
		}
		@ExceptionHandler(value = SQLException.class)
		public String handlheErrorf(HttpServletRequest request, SQLException exception) {
			System.out.println(exception);
			request.setAttribute("success", false);
			request.setAttribute("error", exception);
			System.out.println(exception.getCause());
			return "success";
		}

		@ExceptionHandler(value = AccountNotFoundException.class)
		public String handlheError(HttpServletRequest request, AccountNotFoundException exception) {
			System.out.println(exception);
			//request.setAttribute("success", false);
			request.setAttribute("error", exception);
			System.out.println(exception.getCause());
			return "success";
	}
	}
	



