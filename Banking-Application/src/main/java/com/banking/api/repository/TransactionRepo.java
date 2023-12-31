package com.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.api.model.Accounts;
import com.banking.api.model.Transactions;

public interface TransactionRepo extends JpaRepository<Transactions, Long> {

	 void deleteByAccounts(Accounts account);

}
