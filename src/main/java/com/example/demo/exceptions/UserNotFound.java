package com.example.demo.exceptions;

public class UserNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFound(String errorMessage) {
		super(errorMessage);
    }
}
