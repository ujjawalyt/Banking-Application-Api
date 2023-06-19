
package com.banking.api.service;

import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.api.dto.BranchesDto;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.model.Branches;
import com.banking.api.repository.BrancheRepo;

@Service
public class BrancheServiceImpl  implements BrancheService{

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
		   
		    long mostSignificantBits = uuid.getMostSignificantBits() & Long.MAX_VALUE;

		    Branches branches2 = modelMapper.map(branchesDto, Branches.class);
		    branches2.setBranchid(mostSignificantBits);
		    Branches saved = brancheRepo.save(branches2);

		    return modelMapper.map(saved, BranchesDto.class);
		
		  
	}
	
	
}
