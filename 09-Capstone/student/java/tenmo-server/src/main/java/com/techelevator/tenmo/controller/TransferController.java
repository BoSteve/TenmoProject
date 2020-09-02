package com.techelevator.tenmo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.security.SecurityUtils;

@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	private TransferDAO transferDAO;
	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping(path = "/registeredUsers", method = RequestMethod.GET)
	public List<User> getListOfRegisteredUsers() {
		Optional<String> username = SecurityUtils.getCurrentUsername();
		Long userId = (long) userDAO.findIdByUsername(username.get());
		List<User> registeredUsersList = userDAO.findAllUsersExceptCurrent(userId);
		return registeredUsersList;
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@RequestMapping(path = "", method = RequestMethod.POST)
	public void transferFunds(@RequestBody Long accountToId) {
		
	}
}
