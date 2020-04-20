package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

	@ExceptionHandler(UserNameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public CusttomErrorDetails userNameNotFound(UserNameNotFoundException ex) {
		
		return new CusttomErrorDetails(new Date(),"Form Rest Controller Advice",ex.getMessage());
	}
}
