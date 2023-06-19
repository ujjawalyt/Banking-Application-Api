package com.banking.api.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.dto.TransactionDto;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.model.Accounts;
import com.banking.api.model.Transactions;
import com.banking.api.repository.AccountRepo;
import com.banking.api.repository.TransactionRepo;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private TransactionRepo transactionRepo;
	
	@Override
	public TransactionDto createTransaction(TransactionDto transactionDto, Long accountId)
			throws AccountNotFoundException {
		
		Accounts accounts = accountRepo.findById(accountId)
				.orElseThrow(()-> new AccountNotFoundException("Account Not Found With Account id"));
		
		UUID uuid = UUID.randomUUID();
		long mostSignificantBits = uuid.getMostSignificantBits() & Long.MAX_VALUE; 
	    long limitedBits = mostSignificantBits % 1_000_000_00;
		
		Transactions transactions = modelMapper.map(transactionDto, Transactions.class);
		transactions.setTimestamp(LocalDateTime.now());
		transactions.setTransactionid(limitedBits);
		accounts.setBalance(accounts.getBalance()-transactionDto.getAmount());
		transactions.setAccounts(accounts);
		accountRepo.save(accounts);
	Transactions saved =	transactionRepo.save(transactions);
	 return modelMapper.map(saved, TransactionDto.class);
		
	}
	
	
}
