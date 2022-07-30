package com.example.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryData {
	
	private int categoryID;
	
	@NotEmpty(message = "Category Name cannot be empty")
	@Size(min=3, max=10, message = "Category Name should be between 3-10 characters")
	private String categoryName;
	
	@NotEmpty(message = "Category Description cannot be empty")
	@Size(min=10, max=100, message = "Category Description should be between 10-100 characters")
	private String categoryDesc;

}
