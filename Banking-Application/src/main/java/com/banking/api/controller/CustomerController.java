package com.banking.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.dto.CustomerDto;
import com.banking.api.exception.CustomerNotFoundException;
import com.banking.api.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public ResponseEntity<CustomerDto> registerNewCustomer(@RequestBody CustomerDto customerDto) throws CustomerNotFoundException{
		
		CustomerDto saved = customerService.createCustomerDetails(customerDto);
		return new ResponseEntity<CustomerDto>(saved, HttpStatus.CREATED);
	}
	
	 @PutMapping("/{customerId}")
	    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDto customerDto)
	            throws CustomerNotFoundException {
	        CustomerDto updatedCustomer = customerService.updateCustomerDetails(customerDto, customerId);
	        return ResponseEntity.ok(updatedCustomer);
}
	  @GetMapping("/")
	    public ResponseEntity<List<CustomerDto>> getAllCustomers() throws CustomerNotFoundException {
	        List<CustomerDto> customers = customerService.getAllCustomer();
	        return ResponseEntity.ok(customers);
	    }
	 
	  @GetMapping("/{customerId}")
	    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long customerId) throws CustomerNotFoundException {
	        CustomerDto customer = customerService.getSingleCustomer(customerId);
	        return ResponseEntity.ok(customer);
	    }
	 
}

