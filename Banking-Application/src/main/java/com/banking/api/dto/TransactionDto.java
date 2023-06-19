package com.banking.api.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

	
    private Long transactionid;
	private String transactiontype;
	private Double amount;
	private LocalDateTime timestamp;
	
	
}
