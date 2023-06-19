package com.banking.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.api.dto.CustomerDto;
import com.banking.api.exception.CustomerNotFoundException;

@Service
public interface CustomerService {

	public CustomerDto createCustomerDetails(CustomerDto customerDto) throws CustomerNotFoundException;

	public CustomerDto updateCustomerDetails(CustomerDto customerDto, Long customerId) throws CustomerNotFoundException;

	public List<CustomerDto> getAllCustomer() throws CustomerNotFoundException;
	
	public CustomerDto getSingleCustomer(Long customerId)throws CustomerNotFoundException;
	
	
}
