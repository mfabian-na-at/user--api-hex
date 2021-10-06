package com.example.demo.exceptions;

public class EmailAlredyExist extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailAlredyExist(String errorMessage) {
		super(errorMessage);
	}

}
