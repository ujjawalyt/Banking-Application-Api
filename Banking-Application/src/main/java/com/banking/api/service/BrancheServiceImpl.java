
package com.banking.api.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.dto.BranchesDto;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.model.Branches;
import com.banking.api.repository.BrancheRepo;

@Service
public class BrancheServiceImpl implements BrancheService {

	@Autowired
	private BrancheRepo brancheRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BranchesDto createNewBranch(BranchesDto branchesDto) throws BranchNotFoundException {

		Optional<Branches> branches = brancheRepo.findByBranchname(branchesDto.getBranchname());

		if (branches.isPresent()) {
			throw new BranchNotFoundException("Branch already present with this Branch Name");
		}

		UUID uuid = UUID.randomUUID();

		long branchId = uuid.getMostSignificantBits() & Long.MAX_VALUE;

		Branches branches2 = modelMapper.map(branchesDto, Branches.class);
		branches2.setBranchid(branchId);
		Branches saved = brancheRepo.save(branches2);

		return modelMapper.map(saved, BranchesDto.class);

	}

	@Override
	public BranchesDto updateBranch(BranchesDto branchesDto, Long branchId) throws BranchNotFoundException {

		Branches existingBranch = brancheRepo.findById(branchId)
				.orElseThrow(() -> new BranchNotFoundException("Branch not found with ID: " + branchId));

		existingBranch.setBranchname(branchesDto.getBranchname());
		existingBranch.setAddress(branchesDto.getAddress());
		existingBranch.setPhone(branchesDto.getPhone());

		Branches updatedBranch = brancheRepo.save(existingBranch);

		return modelMapper.map(updatedBranch, BranchesDto.class);
	}

	@Override
	public List<BranchesDto> getAllBranch() throws BranchNotFoundException {
		  List<Branches> branches = brancheRepo.findAll();

	        if (branches.isEmpty()) {
	            throw new BranchNotFoundException("No branches found");
	        }

	        return branches.stream()
	                .map(branch -> modelMapper.map(branch, BranchesDto.class))
	                .collect(Collectors.toList());
		
	}

	@Override
	public BranchesDto getBranchById(Long branchId) throws BranchNotFoundException {
	
		 Branches branch = brancheRepo.findById(branchId)
	                .orElseThrow(() -> new BranchNotFoundException("Branch not found with ID: " + branchId));

	        return modelMapper.map(branch, BranchesDto.class);
	}

	@Override
	public String deleteBranchById(Long branchId) throws BranchNotFoundException {
		
	    Branches existingBranch =  brancheRepo.findById(branchId)
	                .orElseThrow(() -> new BranchNotFoundException("Branch not found with ID: " + branchId));

	    existingBranch.setAccounts(null);
		 brancheRepo.delete(existingBranch);

	        return "Branch deleted successfully";
	}

}
