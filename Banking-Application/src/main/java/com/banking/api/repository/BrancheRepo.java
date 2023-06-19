package com.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.api.model.Branches;

public interface BrancheRepo extends JpaRepository<Branches, Long>{

}
