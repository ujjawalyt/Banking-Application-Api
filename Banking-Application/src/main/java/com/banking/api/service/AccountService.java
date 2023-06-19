package com.banking.api.service;

import org.springframework.stereotype.Service;

import com.banking.api.dto.AccountDto;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.model.Accounts;

@Service
public interface AccountService {

	public AccountDto createNewAccount(AccountDto accountDto) throws AccountNotFoundException;
}
