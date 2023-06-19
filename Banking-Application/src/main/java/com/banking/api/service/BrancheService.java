package com.banking.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.banking.api.dto.BranchesDto;
import com.banking.api.exception.BranchNotFoundException;

@Service
public interface BrancheService {

	public BranchesDto createNewBranch(BranchesDto branchesDto) throws BranchNotFoundException;

	public BranchesDto updateBranch(BranchesDto branchesDto, Long branchId) throws BranchNotFoundException;

	public List<BranchesDto> getAllBranch() throws BranchNotFoundException;

	public BranchesDto getBranchById(Long branchId) throws BranchNotFoundException;

	public String deleteBranchById(Long branchId) throws BranchNotFoundException;
}
