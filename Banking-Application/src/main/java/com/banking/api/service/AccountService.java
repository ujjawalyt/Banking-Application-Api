package com.banking.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.api.dto.AccountDto;
import com.banking.api.dto.AccountDto1;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.model.Accounts;

@Service
public interface AccountService {

	public AccountDto createNewAccount(AccountDto accountDto, Long customerId, Long branchId)
			throws AccountNotFoundException, CustomerNotFoundException, BranchNotFoundException;

	public AccountDto updateAccountAccountBalance(Double amount, Long accountId) throws AccountNotFoundException;

	public List<AccountDto1> getAllAccount() throws AccountNotFoundException;

	public AccountDto getAccountById(Long accountId) throws AccountNotFoundException;
	
	public AccountDto deleteAccountById(Long accountId) throws AccountNotFoundException;

}
