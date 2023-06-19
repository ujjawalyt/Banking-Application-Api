package com.banking.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.api.model.Branches;

public interface BrancheRepo extends JpaRepository<Branches, Long>{

 Optional<Branches>	 findByBranchname(String branchname);

}
