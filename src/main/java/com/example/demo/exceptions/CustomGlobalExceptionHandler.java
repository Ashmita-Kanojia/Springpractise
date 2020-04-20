package com.example.demo.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CusttomErrorDetails custtomErrorDetails = new CusttomErrorDetails(new Date(),
				"For method argument now valid GEH", ex.getLocalizedMessage());
		return new ResponseEntity<Object>(custtomErrorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		CusttomErrorDetails custtomErrorDetails = new CusttomErrorDetails(new Date(), "GEH Method not allowed",
				ex.getLocalizedMessage());
		return new ResponseEntity<Object>(custtomErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);

	}

	// User name not found exception
	@ExceptionHandler(UserNameNotFoundException.class)
	public final ResponseEntity<Object> handleUserNameNotFoundException(UserNameNotFoundException ex,
			WebRequest request) {

		CusttomErrorDetails custtomErrorDetails = new CusttomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(custtomErrorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<Object> handleConstraintVoilationException(ConstraintViolationException ex,
			WebRequest request) {

		CusttomErrorDetails custtomErrorDetails = new CusttomErrorDetails(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(custtomErrorDetails, HttpStatus.BAD_REQUEST);

	}

}
