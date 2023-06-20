package com.banking.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.dto.AccountDto;
import com.banking.api.dto.AccountDto1;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/customer/{customerId}/branch/{branchId}")
	public ResponseEntity<AccountDto> createNewAccountForUser(@RequestBody AccountDto accountDto,
			@PathVariable("customerId") Long customerId, @PathVariable("branchId") Long branchId)
			throws CustomerNotFoundException, AccountNotFoundException, BranchNotFoundException {

		AccountDto accountDto2 = accountService.createNewAccount(accountDto, customerId, branchId);
		return ResponseEntity.status(HttpStatus.CREATED).body(accountDto2);
	}
	
	
	 @PutMapping("/{accountId}/balance")
	    public ResponseEntity<AccountDto> updateAccountBalance(
	            @PathVariable Long accountId,
	            @RequestParam Double amount
	    ) throws AccountNotFoundException {
	        AccountDto updatedAccount =  accountService.updateAccountAccountBalance(amount, accountId);
	        return ResponseEntity.ok(updatedAccount);
	    }
			
	 
	 @GetMapping("/")
	    public ResponseEntity<List<AccountDto1>> getAllAccounts() throws AccountNotFoundException {
	        List<AccountDto1> accounts = accountService.getAllAccount();
	        return ResponseEntity.ok(accounts);
	    }

	    @GetMapping("/{accountId}")
	    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long accountId) throws AccountNotFoundException {
	        AccountDto account = accountService.getAccountById(accountId);
	        return ResponseEntity.ok(account);
	    }

	    @DeleteMapping("/{accountId}")
	    public ResponseEntity<AccountDto> deleteAccountById(@PathVariable Long accountId) throws AccountNotFoundException {
	        AccountDto deletedAccount = accountService.deleteAccountById(accountId);
	        return ResponseEntity.ok(deletedAccount);
	    }
}
