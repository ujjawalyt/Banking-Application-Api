package com.banking.api.exception;

public class BranchNotFoundException extends Exception {

	public BranchNotFoundException() {

	}

	public BranchNotFoundException(String message) {
		super(message);
	}
}
