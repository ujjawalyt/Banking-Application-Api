package com.banking.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.dto.AccountDto;
import com.banking.api.dto.AccountDto1;
import com.banking.api.dto.CustomerDto;
import com.banking.api.exception.AccountNotFoundException;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.model.Accounts;
import com.banking.api.model.Branches;
import com.banking.api.model.Customers;
import com.banking.api.repository.AccountRepo;
import com.banking.api.repository.BrancheRepo;
import com.banking.api.repository.CustomerRepo;
import com.banking.api.repository.TransactionRepo;

import lombok.Data;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepo accountRepo;
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BrancheRepo brancheRepo;
	@Autowired
	private TransactionRepo transactionRepo;

	@Override

	public AccountDto createNewAccount(AccountDto accountDto, Long customerId, Long branchId)
			throws AccountNotFoundException, CustomerNotFoundException,BranchNotFoundException {
	
		Customers customers = customerRepo.findById(customerId)
				.orElseThrow(()-> new CustomerNotFoundException("Customer Not Found With CustomerID " + customerId));
		
		Branches branches = brancheRepo.findById(branchId)
				.orElseThrow(()-> new BranchNotFoundException ("Branch Not Found With this Branch Id" + branchId));
				
		
		  UUID uuid = UUID.randomUUID();
		   
		   long accountId = uuid.getMostSignificantBits() & Long.MAX_VALUE;
		   long accountIds = accountId%1000000000;
		Accounts accounts = modelMapper.map(accountDto, Accounts.class);
		accounts.setAccountid(accountIds);
		accounts.setCustomers(customers);
		accounts.setBranches(branches);
		accounts.setCreatedat(LocalDateTime.now());
		
		Accounts savedAccount = accountRepo.save(accounts);
		
		return modelMapper.map(savedAccount, AccountDto.class);
		
	}

	@Override
	public AccountDto updateAccountAccountBalance(Double amount, Long accountId) throws AccountNotFoundException {
		Accounts existingAccount = accountRepo.findById(accountId)
	            .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));

	  
	    Double updatedBalance = existingAccount.getBalance() + amount;
	    existingAccount.setBalance(updatedBalance);

	    Accounts updatedAccount = accountRepo.save(existingAccount);
	    return modelMapper.map(updatedAccount, AccountDto.class);
		
	}

	@Override
	public List<AccountDto1> getAllAccount() throws AccountNotFoundException {
        List<Accounts> accounts = accountRepo.findAll();
        if (accounts.isEmpty()) {
            throw new AccountNotFoundException("No accounts found");
        }
//        return accounts.stream()
//                .map(account -> modelMapper.map(account, AccountDto1.class))
//                .collect(Collectors.toList());
        return accounts.stream()
                .map(account -> {
                    AccountDto1 accountDto = modelMapper.map(account, AccountDto1.class);
                    CustomerDto customerDto = modelMapper.map(account.getCustomers(), CustomerDto.class);
                    accountDto.setCustomerDto(customerDto);
                    return accountDto;
                })
                .collect(Collectors.toList());
    }
	
	
	@Override
	public AccountDto getAccountById(Long accountId) throws AccountNotFoundException {
		Accounts account = accountRepo.findById(accountId)
	            .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
	    return modelMapper.map(account, AccountDto.class);
	}

	@Override
	public AccountDto deleteAccountById(Long accountId) throws AccountNotFoundException {
		
		Accounts existingAccount = accountRepo.findById(accountId)
	            .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));

	 transactionRepo.deleteByAccounts(existingAccount);
		 
		existingAccount.setCustomers(null);
		existingAccount.setBranches(null);
		accountRepo.delete(existingAccount);
//		accountRepo.save(existingAccount);

	    return modelMapper.map(existingAccount, AccountDto.class);
	
	}

}
