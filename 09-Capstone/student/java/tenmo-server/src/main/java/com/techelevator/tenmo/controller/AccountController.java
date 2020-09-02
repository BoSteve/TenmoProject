package com.techelevator.tenmo.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.security.SecurityUtils;

@RestController
@RequestMapping("/account")
public class AccountController {

	private AccountsDAO accountDAO;
	private UserDAO userDAO;
	
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(path = "/balance", method = RequestMethod.GET)
	public BigDecimal getUserAccountBalance() {	
		Optional<String> username = SecurityUtils.getCurrentUsername();
		Long userId = (long) userDAO.findIdByUsername(username.get());
		Long accountId = accountDAO.findAccountIdByUserId(userId);
		BigDecimal accountBalance = accountDAO.accountBalanceByAccountId(accountId);
		return accountBalance;
	}
	
}
