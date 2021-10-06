package com.example.demo.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerException {
	private long timestamp = 0L;
	
	@ExceptionHandler(value = { UserNotFound.class })
	public ResponseEntity<Object> UserNotFoundException(UserNotFound ex) {
		timestamp = Instant.now().toEpochMilli();
		CustomBadResponse cbr = new CustomBadResponse(timestamp, 404, "Not Found", "NOT_FOUND", "/users");
		return new ResponseEntity<Object>(cbr,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = { EmailAlredyExist.class })
	public ResponseEntity<Object> EmailAlredyExistexception(EmailAlredyExist ex) {
		timestamp = Instant.now().toEpochMilli();
		CustomBadResponse cbr = new CustomBadResponse(timestamp, 400, "Bad Request", "EMAIL_ALREADY_EXISTS", "/users");
		return new ResponseEntity<Object>(cbr,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = { InvalidRole.class })
	public ResponseEntity<Object> InvalidRoleExistexception(InvalidRole ex) {
		timestamp = Instant.now().toEpochMilli();
		CustomBadResponse cbr = new CustomBadResponse(timestamp, 400, "Bad Request", "INVALID_ROLE", "/users");
		return new ResponseEntity<Object>(cbr,HttpStatus.BAD_REQUEST);
	}
	
}
