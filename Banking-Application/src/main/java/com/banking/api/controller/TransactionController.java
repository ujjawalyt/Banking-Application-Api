package com.banking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.dto.TransactionDto;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.repository.TransactionRepo;
import com.banking.api.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	 private TransactionService transactionService;
	
	@PostMapping("/account/{accountId}")
	public ResponseEntity<TransactionDto> createTransaction(@RequestBody TransactionDto transactionDto , 
			@PathVariable("accountId") Long accountId) throws AccountNotFoundException{
		
		TransactionDto transactionDto2 = transactionService.createTransaction(transactionDto, accountId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(transactionDto2);
	}
}
