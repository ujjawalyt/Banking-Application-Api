package com.banking.api.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.dto.AccountDto;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.model.Accounts;
import com.banking.api.model.Branches;
import com.banking.api.model.Customers;
import com.banking.api.repository.AccountRepo;
import com.banking.api.repository.BrancheRepo;
import com.banking.api.repository.CustomerRepo;

import lombok.Data;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BrancheRepo brancheRepo;

	@Override

	public AccountDto createNewAccount(AccountDto accountDto, Long customerId, Long branchId)
			throws AccountNotFoundException, CustomerNotFoundException,BranchNotFoundException {
	
		Customers customers = customerRepo.findById(customerId)
				.orElseThrow(()-> new CustomerNotFoundException("Customer Not Found With CustomerID " + customerId));
		
		Branches branches = brancheRepo.findById(branchId)
				.orElseThrow(()-> new BranchNotFoundException ("Branch Not Found With this Branch Id" + branchId));
				
		Accounts accounts = modelMapper.map(accountDto, Accounts.class);
		accounts.setCustomers(customers);
		accounts.setBranches(branches);
		accounts.setCreatedat(LocalDateTime.now());
		
		Accounts savedAccount = accountRepo.save(accounts);
		
		return modelMapper.map(savedAccount, AccountDto.class);
		
	}

}
