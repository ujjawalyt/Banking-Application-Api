package com.banking.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

	
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private Long accountid;
	@Column(name="account_type")
	private String accounttype;
	
	private Double balance;
	@Column(name="created_at")
	private LocalDateTime createdat;
	
	@ManyToOne
	@JoinColumn(name ="customer_id")
	private Customers customers;
	
//	@OneToOne
//	@JoinColumn(name="branch_id")
//	private Branches branches;
	
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branches branches;
	
}
