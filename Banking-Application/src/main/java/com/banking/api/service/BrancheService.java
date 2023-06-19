package com.banking.api.service;

import org.springframework.stereotype.Service;

import com.banking.api.dto.BranchesDto;
import com.banking.api.exception.BranchNotFoundException;

@Service
public interface BrancheService {

	public BranchesDto createNewBranch(BranchesDto branchesDto) throws BranchNotFoundException;
}
