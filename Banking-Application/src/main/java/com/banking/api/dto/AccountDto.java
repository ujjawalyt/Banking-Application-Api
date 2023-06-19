package com.banking.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import com.banking.api.model.Accounts;
import com.banking.api.model.Branches;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	
	private Long accountid;
	
	private String accounttype;
	
	private Double balance;
	
	private LocalDateTime createdat;
	
	private CustomerDto customerDto;
	
}
