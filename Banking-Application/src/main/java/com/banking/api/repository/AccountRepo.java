package com.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.api.model.Accounts;

public interface AccountRepo extends JpaRepository<Accounts, Long> {

}
