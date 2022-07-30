package com.example.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MainExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String> ResourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	//Used for @Valid
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		
		Map<String, String> errorList = new HashMap<String, String>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError)error).getField();
			String message = ((FieldError)error).getDefaultMessage();
			errorList.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String, String>>(errorList, HttpStatus.BAD_REQUEST);
	}
	
	//Used for Unspecified Routes
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<String> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex){
		String message = ex.getMessage() + " on this route";
		return new ResponseEntity<String>(message, HttpStatus.BAD_GATEWAY);
	}

}
