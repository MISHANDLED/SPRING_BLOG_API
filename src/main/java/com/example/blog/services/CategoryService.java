package com.example.blog.services;

import java.util.List;

import com.example.blog.payloads.CategoryData;

public interface CategoryService {
	
	//Create
	CategoryData createCategory(CategoryData category);
	
	//Read
	List<CategoryData> getAllCategories();
	CategoryData getCategoryById(int id);
	
	//Update
	CategoryData updateCategory(CategoryData category, int id);
	
	//Delete
	void deleteCategory(int id);
	

}
