package com.example.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.models.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
}
