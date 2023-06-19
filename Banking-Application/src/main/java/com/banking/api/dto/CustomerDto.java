package com.banking.api.dto;

import java.util.List;

import com.banking.api.model.Accounts;
import com.banking.api.model.Customers;
import com.banking.api.model.Transactions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	
	private Long customerid;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String address;
}
