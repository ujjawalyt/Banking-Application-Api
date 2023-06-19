package com.banking.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.dto.CustomerDto;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.model.Customers;
import com.banking.api.repository.CustomerRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CustomerDto createCustomerDetails(CustomerDto customerDto) throws CustomerNotFoundException {

		Optional<Customers> customers = customerRepo.findByEmail(customerDto.getEmail());

		if (customers.isPresent()) {
			throw new CustomerNotFoundException("Customer already present with this email id");
		}
		Customers customers2 = modelMapper.map(customerDto, Customers.class);
		Customers savedCustomer = customerRepo.save(customers2);
		return modelMapper.map(savedCustomer, CustomerDto.class);

	}

	@Override
	public CustomerDto updateCustomerDetails(CustomerDto customerDto, Long customerId)
			throws CustomerNotFoundException {
		Optional<Customers> optionalCustomer = customerRepo.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException("Customer not found with id: " + customerId);
		}

		Customers existingCustomer = optionalCustomer.get();

		existingCustomer.setFirstname(customerDto.getFirstname());
		existingCustomer.setLastname(customerDto.getLastname());
		existingCustomer.setEmail(customerDto.getEmail());
		existingCustomer.setAddress(customerDto.getAddress());
		existingCustomer.setPhone(customerDto.getPhone());

		Customers updatedCustomer = customerRepo.save(existingCustomer);
		return modelMapper.map(updatedCustomer, CustomerDto.class);
	}

	@Override
	public List<CustomerDto> getAllCustomer() throws CustomerNotFoundException {
		List<Customers> customerList = customerRepo.findAll();
		if (customerList.isEmpty()) {
			throw new CustomerNotFoundException("No customers found");
		}

		return customerList.stream().map(customer -> modelMapper.map(customer, CustomerDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDto getSingleCustomer(Long customerId) throws CustomerNotFoundException {
		Optional<Customers> optionalCustomer = customerRepo.findById(customerId);
		Customers customer = optionalCustomer
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));

		return modelMapper.map(customer, CustomerDto.class);
	}

}
