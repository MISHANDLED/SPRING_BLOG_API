package com.example.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.CategoryData;
import com.example.blog.services.CategoryService;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//GET Request - All Categories
	@GetMapping("/all")
	public ResponseEntity<List<CategoryData>> getAllCategory(){
		return new ResponseEntity<List<CategoryData>>(categoryService.getAllCategories(),HttpStatus.OK);
	}
	
	//GET Request - Category by ID
	@GetMapping("/{id}")
	public ResponseEntity<CategoryData> getCategoryByID(@PathVariable(value = "id") int id){
		return new ResponseEntity<CategoryData>(categoryService.getCategoryById(id),HttpStatus.OK);
	}
	
	//POST Request - Add a Category
	@PostMapping("/")
	public ResponseEntity<CategoryData> addNewCategory(@Valid @RequestBody CategoryData categoryData){
		return new ResponseEntity<CategoryData>(categoryService.createCategory(categoryData),HttpStatus.CREATED);
	}
	
	//UPDATE Request - Update a Category
	@PutMapping("/{id}")
	public ResponseEntity<CategoryData> updateCategory(@Valid @RequestBody CategoryData categoryData, @PathVariable(value = "id") int id){
		return new ResponseEntity<CategoryData>(categoryService.updateCategory(categoryData, id),HttpStatus.ACCEPTED);
	}
	
	//DELETE Request - Delete a Category
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable(value="id") int id){
		categoryService.deleteCategory(id);
		return new ResponseEntity<String>(String.format("Category with Id - %d successfully deleted", id),HttpStatus.OK);
	}
}
