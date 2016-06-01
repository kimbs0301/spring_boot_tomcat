package com.example.boot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.account.model.Account;

/**
 * @author gimbyeongsu
 * 
 */
@RestController
@RequestMapping("/account")
public class AccountController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/member/{memberId}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Account getAccount(@PathVariable int memberId) {
		LOGGER.debug("{}", memberId);
		Account account = new Account();
		return account;
	}
}
