package com.banking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.dto.AccountDto;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/customer/{customerId}")
	public ResponseEntity<AccountDto> createNewAccountForUser(@RequestBody AccountDto accountDto ,
			@PathVariable("customerId") Long customerId) throws CustomerNotFoundException, AccountNotFoundException{
		
		
		AccountDto accountDto2 = accountService.createNewAccount(accountDto, customerId);
		return ResponseEntity.status(HttpStatus.CREATED).body(accountDto2);
	}
}
