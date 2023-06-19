package com.banking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
}
