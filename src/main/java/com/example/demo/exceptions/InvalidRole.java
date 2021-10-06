package com.example.demo.exceptions;

public class InvalidRole extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidRole(String errorMessage) {
		super(errorMessage);
	}
}
