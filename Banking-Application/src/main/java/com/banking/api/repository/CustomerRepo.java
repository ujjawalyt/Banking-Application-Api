package com.banking.api.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.api.model.Customers;

public interface CustomerRepo extends JpaRepository<Customers, Long> {


	Optional<Customers> findByEmail(String email);

}
