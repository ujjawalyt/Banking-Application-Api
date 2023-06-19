
package com.banking.api.exception;

import java.awt.image.RescaleOp;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorDetails> myMANVException(MethodArgumentNotValidException me) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage("Validation Error...!");
		error.setDescription(me.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myANFEException(AccountNotFoundException an, WebRequest wr) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(an.getMessage());
		error.setDescription(wr.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myCNFException(CustomerNotFoundException cn, WebRequest wr) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(cn.getMessage());
		error.setDescription(wr.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(BranchNotFoundException.class)
	public ResponseEntity<MyErrorDetails> myBNFException(BranchNotFoundException cn, WebRequest wr) {

		MyErrorDetails error = new MyErrorDetails();
		error.setTimestamp(LocalDate.now());
		error.setMessage(cn.getMessage());
		error.setDescription(wr.getDescription(false));

		return new ResponseEntity<MyErrorDetails>(error, HttpStatus.BAD_REQUEST);

	}
	
	
}
