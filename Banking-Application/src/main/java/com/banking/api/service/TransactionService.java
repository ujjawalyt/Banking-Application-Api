package com.banking.api.service;

import org.springframework.stereotype.Service;

import com.banking.api.dto.TransactionDto;
import com.banking.api.exception.AccountNotFoundException;

@Service
public interface TransactionService {

	public TransactionDto createTransaction(TransactionDto transactionDto, Long accountId)
	throws AccountNotFoundException;
	
}
