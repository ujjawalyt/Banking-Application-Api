package com.banking.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.api.dto.BranchesDto;
import com.banking.api.exception.BranchNotFoundException;
import com.banking.api.service.BrancheService;

@RestController
@RequestMapping("/branch")
public class BranchController {

	@Autowired
	private BrancheService brancheService;

	@PostMapping("/save")
	public ResponseEntity<BranchesDto> createNewBranch(@RequestBody BranchesDto branchesDto)
			throws BranchNotFoundException {

		BranchesDto branchesDto2 = brancheService.createNewBranch(branchesDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(branchesDto2);
	}

	@PutMapping("/{branchId}")
	public ResponseEntity<BranchesDto> updateBranch(@PathVariable Long branchId, @RequestBody BranchesDto branchesDto)
			throws BranchNotFoundException {
		BranchesDto updatedBranch = brancheService.updateBranch(branchesDto, branchId);
		return ResponseEntity.ok(updatedBranch);
	}

	@GetMapping("/")
	public ResponseEntity<List<BranchesDto>> getAllBranches() throws BranchNotFoundException {
		List<BranchesDto> branches = brancheService.getAllBranch();
		return ResponseEntity.ok(branches);
	}

	@GetMapping("/{branchId}")
	public ResponseEntity<BranchesDto> getBranchById(@PathVariable Long branchId) throws BranchNotFoundException {
		BranchesDto branch = brancheService.getBranchById(branchId);
		return ResponseEntity.ok(branch);
	}

	@DeleteMapping("/{branchId}")
	public ResponseEntity<String> deleteBranchById(@PathVariable Long branchId) throws BranchNotFoundException {
		String result = brancheService.deleteBranchById(branchId);
		return ResponseEntity.ok(result);
	}
}
