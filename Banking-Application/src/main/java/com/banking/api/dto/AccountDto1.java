package com.banking.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto1 {

	private Long accountid;
	
	private String accounttype;
	
	private Double balance;
	
	private LocalDateTime createdat;
	
	private CustomerDto customerDto;
}
