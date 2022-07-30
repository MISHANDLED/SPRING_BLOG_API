package com.example.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String resourceName, String resourceField, String resourceValue) {
		super(String.format("%s not found with %s : %s", resourceName, resourceField, resourceValue));
		
	}
}
