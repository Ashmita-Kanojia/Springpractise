package com.example.demo.exceptions;

public class OrderIdNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public OrderIdNotFoundException(String message) {
		super(message);
		
	}

}
