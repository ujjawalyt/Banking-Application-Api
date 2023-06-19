package com.banking.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Transactions ")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private Long transactionid;
	
	@Column(name="transaction_type")
	private String transactiontype;
	
	private Double amount;
	private LocalDateTime timestamp;
	
	@ManyToOne
	@JoinColumn(name="account_id")
	private Accounts accounts;
	
}
