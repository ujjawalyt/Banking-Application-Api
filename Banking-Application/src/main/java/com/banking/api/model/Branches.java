package com.banking.api.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Branches")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Branches {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "branch_id"  , unique = true)
	private Long branchid;

	@Column(name = "branch_name")
	private String branchname;

	private String address;
	private String phone;

//	@OneToOne(mappedBy = "branches")
//	private Accounts accounts;
	@OneToMany(mappedBy = "branches")
	private List<Accounts> accounts;

}
